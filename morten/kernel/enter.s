# This file holds the long mode assembly code for handling system calls.
# WARNING: This code, like most assembly code, is far from easy to understand.
#  Take help from a teaching assistant!

.global syscall_dummy_target
.global dummy_interrupt
.global timer_interrupt
.global IDT
.global TSS
.global stack
.global interrupt_entries

syscall_dummy_target:
 # We should never reach this!
 hlt
 jmp    syscall_dummy_target

.global syscall_target
syscall_target:
 # Swap in the supervisor gs
 swapgs
 # Now we can use gs to access data. We save rax so that we have one register
 # available for calculations.
 mov    %rax,%gs:0

 # Set segment registers to supervisor mode
 mov    $32,%eax
 mov    %eax,%ds
 mov    %eax,%es
 mov    %eax,%fs

 # Get the index to the thread that should be run.
 mov    %gs:16,%eax
 # mask off everything except the lowest 8 bits
 and    $255,%rax
 # The size of a thread structure is 1024 bytes. We multiply the index with
 # 1024 to get an offset into the thread_table. Multiplying with 1024 is the
 # same as shifting left 10 bits.
 shl    $10,%rax
 # Add the address of thread_table to get the address of the thread structure.
 # We also add 0x200 to get an address to the integer registers.
 add    $thread_table+0x200,%rax

 # Save most registers
 mov    %rbx,1*8(%rax)
 mov    %rcx,17*8(%rax)    # Is the rip
 mov    %rdx,3*8(%rax)
 mov    %rdi,4*8(%rax)
 mov    %rsi,5*8(%rax)
 mov    %rbp,6*8(%rax)
 mov    %rsp,7*8(%rax)
 mov    %r8,8*8(%rax)
 mov    %r9,9*8(%rax)
 mov    %r10,10*8(%rax)
 mov    %r11,16*8(%rax)    # Is the rflags
 mov    %r12,12*8(%rax)
 mov    %r13,13*8(%rax)
 mov    %r14,14*8(%rax)
 mov    %r15,15*8(%rax)

 # Save the data we previously stored into the scratch space
 mov    %gs:0,%rbx
 mov    %rbx,0*8(%rax)

 # Set the stack pointer to the supervisor stack
 mov    $stack,%rsp

 # Save the FPU state
 fxsave -512(%rax)

 # call the c portion of the system call handler
 call   system_call_handler

.global return_to_user_mode
return_to_user_mode:
 # Return to user mode.

 # Get the index to the thread that should be run. This is a bit ugly as
 # we assume that the head is the first 4 bytes in the thread_queue
 # structure.
 mov    %gs:16,%eax

 # Check if the index is negative. In that case we should do a context switch
 # to the idle thread.
 test   %eax,%eax
 jns    no_idle

 # Set the default kernel page table root pointer.
 mov    $pml4_base,%rbx
 mov    %rbx,%cr3
	
 # The idle thread:
 swapgs
 sti    # Enable interrupts
 hlt    # Wait for something to happen
 cli    # Disable interrupts
 swapgs
	
 # Jump back and re-check the ready queue head to see if there are any ready
 # threads that can be run.
 jmp    return_to_user_mode

no_idle:
 # Set a new page table root pointer.
 mov    %gs:8,%rbx
 mov    %rbx,%cr3

 # mask off everything except the lowest 8 bits
 and    $255,%rax
 # The size of a thread structure is 1024 bytes. We multiply the index with
 # 1024 to get an offset into the thread_table. Multiplying with 1024 is the
 # same as shifting left 10 bits.
 shl    $10,%rax
 # Add the address of thread_table to get the address of the thread structure.
 # We also add 0x200 to get an address to the integer registers.
 add    $thread_table+0x200,%rax

 # Restore the FPU state
 fxrstor -512(%rax)

 # Restore most registers
 mov    1*8(%rax),%rbx
 mov    3*8(%rax),%rdx
 mov    4*8(%rax),%rdi
 mov    5*8(%rax),%rsi
 mov    6*8(%rax),%rbp

 mov    8*8(%rax),%r8
 mov    9*8(%rax),%r9
 mov    10*8(%rax),%r10
 mov    12*8(%rax),%r12
 mov    13*8(%rax),%r13
 mov    14*8(%rax),%r14
 mov    15*8(%rax),%r15

 # check if the from_interrupt flag is set
 mov    18*8(%rax),%cl
 test   %cl,%cl
 jz     return_via_sysret
 # The from_interrupt flag was set so we return via iret

 # Make room on the stack for the interrupt stack frame

 # Push the user mode SS
 pushq  $11
 # Push user mode RSP
 pushq  7*8(%rax)
 # Push user mode RFLAGS
 pushq  16*8(%rax)
 # Push the user mode CS
 pushq  $19
 # Push user mode RIP
 pushq  17*8(%rax)

 # Store rax temporarily on the stack.
 pushq  0*8(%rax)

 # We now restore the last registers.
 mov    2*8(%rax),%rcx
 mov    11*8(%rax),%r11

 # Set segment registers to user mode data access
 mov    $11,%eax
 mov    %eax,%ds
 mov    %eax,%es
 mov    %eax,%fs
 # We are now using the supervisor mode version of gs and we need
 # to switch to the user version
 swapgs
 mov    %eax,%gs
	
 # Restore rax by poping it from the stack where it was placed earlier.
 pop    %rax
 # All done. We can exit the interrupt handler and go back to user mode.
 iretq

return_via_sysret:
 # Restore all registers that were not restored earlier. Some registers are
 # temporarily pushed on the stack.
 pushq  7*8(%rax)
 pushq  0(%rax)
 mov    16*8(%rax),%r11  # r11 is the rflags
 mov    17*8(%rax),%rcx  # rcx is the rip

 # Set segment registers to user mode data access.
 mov    $11,%eax
 mov    %eax,%ds
 mov    %eax,%es
 mov    %eax,%fs

 # This is a bit technical. We swap gs and then set up gs for user space.
 # access.
 swapgs
 mov    %eax,%gs

 popq   %rax
 popq   %rsp
 # And go to user mode
 sysretq

 # Build interrupt handlers for all 256 interrupt vectors

 .align 16
interrupt_entries:
 .set interrupt_number, 0
 .rept 256
 pushq  $interrupt_number
 jmp    dummy_interrupt
 .align 16
 .set interrupt_number, interrupt_number+1
 .endr

 # Interrupt handler that catches all masked interrupt and exceptions.
 # We should not get here.

dummy_interrupt:
 # Push registers onto the stack so that we do not overwrite them
 push   %rax
 push   %rdx

 # Check for spurious interrupt
 mov    16(%rsp),%rax
 cmp    $0x27,%rax
 jnz    debugger

 # Spurious interrupt occurred. This could happen if we spend too long time
 # with interrupts disabled.

 # Just acknowledge the interrupt and return

 mov    $0x20,%rdx
 mov    %rdx,%rax
 out    %al,(%dx)

 pop    %rdx
 pop    %rax
 add    $8,%rsp
 iretq

 # Go into the debugger
debugger:
 mov    $0x8a00,%rdx
 mov    %rdx,%rax
 outw   %ax,%dx
 mov    $0x8ae0,%rax
 outw   %ax,%dx
 jmp    debugger


 # Interrupt handler for the timer interrupt
timer_interrupt:
 swapgs

 # Push a scratch register onto the stack so that we do not overwrite it
 push   %rbp

 # Set segment registers to supervisor mode
 mov    $32,%ebp
 mov    %ebp,%ds
 mov    %ebp,%es
 mov    %ebp,%fs

 # Load the index of the running thread
 mov   %gs:16,%ebp

 # Check if the index is negative. In that case we came here from the idle
 # thread and we should not save any context.
 test   %ebp,%ebp
 jns    not_in_kernel
 # The idle thread was interrupted. Just remove a stack frame and call the
 # C code.
 add    $48,%rsp
 jmp    go_to_c

not_in_kernel:
 # Interrupt occured outside the idle thread
 # Save all registers.

 # mask off everything except the lowest 8 bits
 and    $255,%rbp
 # The size of a thread structure is 1024 bytes. We multiply the index with
 # 1024 to get an offset into the thread_table. Multiplying with 1024 is the
 # same as shifting left 10 bits.
 shl    $10,%rbp
 # Add the address of thread_table to get the address of the thread structure.
 # We also add 0x200 to get an address to the integer registers.
 add    $thread_table+0x200,%rbp

 # Save the FPU state
 fxsave -512(%rbp)

 mov    %rax,0*8(%rbp)
 mov    %rbx,1*8(%rbp)
 mov    %rcx,2*8(%rbp)
 mov    %rdx,3*8(%rbp)
 mov    %rdi,4*8(%rbp)
 mov    %rsi,5*8(%rbp)
 popq   6*8(%rbp)
 mov    %r8,8*8(%rbp)
 mov    %r9,9*8(%rbp)
 mov    %r10,10*8(%rbp)
 mov    %r11,11*8(%rbp)
 mov    %r12,12*8(%rbp)
 mov    %r13,13*8(%rbp)
 mov    %r14,14*8(%rbp)
 mov    %r15,15*8(%rbp)

 popq   17*8(%rbp)         # Pop the rip
 pop    %rax
 popq   16*8(%rbp)         # Pop the rflags
 popq   7*8(%rbp)          # Pop the rsp
 pop    %rax

 # Set the interrupt flag to indicate that the context was saved in an
 # interrupt handler.
 mov    $1,%eax
 mov    %al,18*8(%rbp)

go_to_c:
 # Call the timer interrupt handler
 call   timer_interrupt_handler
 # Return back to user mode through the system call code
 jmp    return_to_user_mode

 .data
 .align 8
TSS:
 # This is the 64-bit TSS. It is used when processing interrupts
 .int  0
 .quad  stack
 .quad  stack
 .quad  stack
 .quad  0
 .quad  stack
 .quad  stack
 .quad  stack
 .quad  stack
 .quad  stack
 .quad  stack
 .quad  stack
 .quad  0
 .int   0x00680000
 .bss
 .align 8
 # We reserve two pages for stack
 .align 8
 .skip  2*4096
stack:
 .align  8
IDT:
 # This is the 64-bit interrupt descriptor table. It holds a list of
 # interrupt handler routines.
 .skip   16*256
