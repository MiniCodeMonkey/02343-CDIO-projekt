# WARNING: This code, like most assembly, code is far from easy to understand.
# Quick introductions to the AT&T syntax used by GNU Assembler can be found
# at http://sourceware.org/binutils/docs-2.17/as/index.html . Various 
# resources for assembly programming on x86 and AMD64 machines can be found
# at http://asm.sourceforge.net/resources.html .
#  Take help from a teaching assistant!

 .text
 .global _start
_start:
 # Set up the stack pointer
 lea    stack(%rip),%rsp
 # Set up the environment for the main function
 lea    name(%rip),%rax
 mov    %rax,argv(%rip)
 # Get the address of the argv array
 lea    argv(%rip),%rsi
 # Call the main function
 mov    $1,%rdi
 call   main
 # Perform a terminate system call
 mov    $4,%rax
 syscall
 # We will never come back from the system call if it is implemented properly
 # To catch faulty implementations we use a hlt. Hlt is illegal in user mode
 # and should fail.
 hlt 
 .section .rodata
name:
 .asciz "dummy"

 .bss
 .align 8
argv:
 .skip  16
 .skip  8*1024
stack:
