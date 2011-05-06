# This file holds long mode assembly code.
# WARNING: This code, like most assembly code, is far from easy to understand.
#  Take help from a teaching assistant!

 .text
 .global start_of_64bit_code
 .global cpu_private_data
	
start_of_64bit_code:
 # We reload segment registers so that we use 64-bit space for everything
 mov    $32,%eax
 mov    %eax,%ss
 mov    %eax,%ds
 mov    %eax,%es
 mov    %eax,%fs
 mov    %eax,%gs

 # We can now set the kernel stack
 mov    $stack,%rsp

 # Set all flags to a well defined state
 push   $0
 popf

 # Set the FS base to 0
 mov    $0xc0000100,%ecx
 xor    %eax,%eax
 xor    %edx,%edx
 wrmsr  # must use the wrmsr instruction to write a 64-bit value to FS, GS 
        # and KernelGS

 # And the GS base to point to the private data of the CPU.
 mov    $0xc0000101,%ecx
 mov    $cpu_private_data,%eax
 xor    %edx,%edx
 wrmsr
	
 # And finally the KernelGS base to 0
 mov    $0xc0000102,%ecx
 xor    %eax,%eax
 xor    %edx,%edx
 wrmsr

 # We do not set a new GDT since all the descriptors we are interested in are
 # there already and there is no problem for us to have it below the 4Gbyte
 # barrier.

 # We set up a TSS descriptor:
 mov    $TSS,%rax
 mov    %rax,%rbx
 shl    $16,%ebx
 or     $0x67,%ebx
 mov    %ebx,TSS_descriptor
 mov    %rax,%rbx
 mov    %rax,%rcx
 and    $0xff000000,%ebx
 shr    $16,%ecx
 and    $0xff,%ecx
 or     %ecx,%ebx
 or     $0x8900,%ebx
 mov    %ebx,TSS_descriptor+4
 shr    $32,%rax
 mov    %eax,TSS_descriptor+8

 # Set up the entire interrupt descriptor table to point to a dummy handler.
 # There are 256 entries in the table
 mov    $256,%rdx
 mov    $IDT,%rbp
 mov    $interrupt_entries,%rcx
interrupt_setup_loop:
 mov    %ecx,%ebx
 and    $0xffff,%ebx
 or     $24*0x10000,%ebx
 mov    %ebx,(%rbp)
 mov    %ecx,%ebx
 and    $0xffff0000,%ebx
 or     $0x8e00,%ebx
 mov    %ebx,4(%rbp)
 mov    %rcx,%rax
 shr    $32,%rax
 mov    %eax,8(%rbp)
 xor    %rax,%rax
 mov    %eax,12(%rbp)
 add    $16,%rbp
 add    $16,%rcx
 dec    %rdx
 jnz    interrupt_setup_loop

 # Force the CPU to use the new TSS
 mov    $40,%eax
 ltr    %ax

 # Load the new interrupt handler table
 sub    $16,%rsp
 mov    $16*256-1,%rax
 mov    %ax,6(%rsp)
 mov    $IDT,%rax
 mov    %rax,8(%rsp)
 mov    %rsp,%rax
 add    $6,%rax
 lidt   (%rax)
 add    $16,%rsp

 # We set up the registers necessary to use syscall
 # The registers are STAR, LSTAR, CSTAR and SFMASK.
 # LSTAR holds the target address of system calls in
 # long mode so STAR and CSTAR just points to a dummy target.
 # See section 6.1.1 in AMD64 Programmers Manual, Vol. 2

 # Write STAR
 mov    $0xc0000081,%ecx
 mov    $0x00030018,%edx
 mov    $syscall_dummy_target,%eax
 wrmsr

 # Write LSTAR
 mov    $0xc0000082,%ecx
 xor    %edx,%edx
 mov    $syscall_target,%eax
 wrmsr

 # Write CSTAR
 mov    $0xc0000083,%ecx
 xor    %edx,%edx
 mov    $syscall_dummy_target,%eax
 wrmsr

 # Write SFMASK
 mov    $0xc0000084,%ecx
 xor    %edx,%edx
 mov    $0x00000300,%eax
 wrmsr

 # Set the ELF_images_start variable to the start of the linked list of
 # executable images
 movq   $start_of_ELF_images,ELF_images_start

 # Set the ELF_images_end variable to the end of the linked list of
 # executable images
 movq   $end_of_ELF_images,ELF_images_end

 # Set the first_available_memory_byte variable to the address of the first
 # available memory byte. The address is rounded of to the nearest higher
 # address which is evenly dividable with 4096.
 mov    $end_of_bss,%rax
 add    $4096-1,%rax
 and    $-4096,%rax
 mov    %rax,first_available_memory_byte

 # Adjust the memory_size variable. We need to convert from kbytes to bytes.
 # That means multiplying with 1024 which is the same thing as shifting ten
 # bits.
 mov	memory_size,%rax
 shl    $10,%rax
 mov    %rax,memory_size

 # Set up the kernel page table root.
 movq   $pml4_base,kernel_page_table_root
	
 # We can now switch to c!
 call   initialize

 jmp    return_to_user_mode

 .data
 .align 16
cpu_private_data:
 .quad  0
 .quad  pml4_base
 .int   -1
 .int   1
	
	