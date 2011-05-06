# This file holds all 32-bit assembly code.
# WARNING: This code, like most assembly, code is far from easy to understand.
# Quick introductions to the AT&T syntax used by GNU Assembler can be found
# at http://sourceware.org/binutils/docs-2.17/as/index.html . Various 
# resources for assembly programming on x86 and AMD64 machines can be found
# at http://asm.sourceforge.net/resources.html .
#  Take help from a teaching assistant!

 .text
 # Here be dragons.
 #
 # Skip the multiboot header. This so that the header is located early in the
 # boot image. More information on the multiboot standard can be found
 # at http://www.gnu.org/software/grub/manual/multiboot/
 .global _start
 .global TSS_descriptor
 .global pml4_base

_start:
 jmp    after_multiboot_header
 .align 4
multiboot_header:
 .int    0x1BADB002             # magic word
 .int    1                      # flags
 .int    -(0x1BADB002+1)        # checksum
 .int    0
 .int    0
 .int    0
 .int    0
 .int    0
 .int    0
 .int    0
 .int    0
 .int    0

# We will end up here if something bad happens.
halt_the_machine:
 hlt    # halt the processor until the next external interrupt
 jmp    halt_the_machine

after_multiboot_header:
 # Check that the boot loader put the expected magic number in eax
 cmpl   $0x2BADB002,%eax
 jne    halt_the_machine  # Ouch, no multiboot capable loader

 # Load multiboot flags into eax
 movl   (%ebx),%eax
 # Check if the memory size field is available
 andl   $1,%eax
 jz     halt_the_machine  # No. We do not know the size of memory so stop

 # Load size of upper memory (which resides 8 bytes into the multiboot_info structure)
 movl   8(%ebx),%eax
 # Check if larger or equal to 31 megabytes of upper memory
 cmpl   $(32768-1024-64),%eax # 32 Megabytes - 1 Megabytes - 64 kilobytes that
                              # the BIOS keeps from us
 jl     halt_the_machine      # Yikes! Too little memory

 # Set the memory_size variable to the amount of available memory
 # in kilobytes. This value will be adjusted later to bytes.
 xor    %ecx,%ecx
 # This may look odd but works. The processor is little-endian!
 movl   %ecx,memory_size+4
 movl   %eax,memory_size

 # Set a stack so that we can check for CPUID instruction
 movl   $stack_32bit,%esp

 # This is a little tricky. We can test if the CPUID instruction is available
 # by testing if bit 21 in EFLAGS can be changed.
 push   %ecx
 popf                        # Should force EFLAGS to be all zeros
 pushf
 pop    %ecx
 btc    $21,%ecx             # Check if bit 21 really became 0. In the process
                             # toggle the bit.
 jb     halt_the_machine
 push   %ecx
 popf
 pushf
 pop    %ecx
 bt     $21,%ecx             # Check if bit 21 really is 1
 jae    halt_the_machine  

 # Check if the processor is the way we assume
 # See AMD64 reference books for further information.
 #
 # First check if CPUID can provide the information we need
 xor    %eax,%eax         # clear eax
 cpuid
 cmpl   $0,%eax
 jle    halt_the_machine  # CPUID does not support the input values we need!
 movl   $1,%eax
 cpuid
 andl   $0x0380a971,%edx
 cmpl   $0x0380a971,%edx
 jne    halt_the_machine  # Great all basic functions we need are there!
 # Now check if we can get into 64-bit mode
 mov    $0x80000000,%eax
 cpuid
 cmpl   $0x80000000,%eax
 jbe    halt_the_machine  # No. CPUID can not give any information about the
                          # 64-bit mode.
 movl   $0x80000001,%eax
 cpuid
 bt     $29,%edx
 jnc    halt_the_machine  # No. We can not go into 64-bit mode
 bt     $20,%edx
 jnc    halt_the_machine  # NX bit is not supported and we use it...

 # Cool! The CPU can be turned into 64-bit mode. Let us do that!

 # First enable 64-bit page table entries by setting the PAE bit
 movl   %cr4,%eax
 bts    $5,%eax   # Enable 64-bit page table entries
 bts    $9,%eax   # Enable 128-bit floating point instructions
 movl   %eax,%cr4

 # Set up a GDT with a descriptor that allows us to go into 64-bit mode
 lgdt   gdt_32

 # Set the root of the page table tree
 movl   $pml4_base,%eax
 movl   %eax,%cr3

 # Enable long mode. This is done by setting a bit in the EFER register
 # The EFER register is accessed indirectly. We also enable syscal/sysret
 # (and the use of the NX bit which we will use in task 4).
 movl   $0xc0000080,%ecx
 rdmsr  # Read EFER
 orl    $0x901,%eax
 wrmsr  # Write EFER.

 # Enable paging, write protection etc. This will also activate 64-bit mode.
 movl   $0x80010033,%eax
 movl   %eax,%cr0

 # We can now do a long jump into the 64-bit code (in boot64.s)
 ljmp   $24,$start_of_64bit_code

 .data
 .align 8
 # This is the pseduo-descriptor for the 32-bit GDT which holds the segment
 # descriptors we need.
gdt_32:
 .word  55
 .int   gdt_32_descriptors

 # It is good idea to align the GDT descriptors on an even 8 byte boundary
 .align 8
gdt_32_descriptors:
 .int   0,0               # Null descriptor
 # User mode descriptors
 .int   0xffff,0x008ff200 # Long mode descriptor for a 64-bit data segment
 .int   0xffff,0x00affb00 # Long mode descriptor for a 64-bit code segment
 # Supervisor mode descriptors
 .int   0xffff,0x00af9b00 # Long mode descriptor for a 64-bit code segment
 .int   0xffff,0x008f9200 # Long mode descriptor for a 64-bit data segment
 # Descriptor for the TSS
TSS_descriptor:
 .quad  0, 0

 # The page table tree is hardcoded in the data segment. This is not very
 # elegant and wastes space but works and reduces the amount of assembly code.
 # The tree is normally built dynamically. See AMD64 Programmers Manual Vol. 2
 # section 5.1 (pp. 115-117) for an explanation of the page translation 
 # mechanism.
 .align 4096
pml4_base:
 .int   pdpe_base+7,0  # We know the lowest 12 bits of the address are zero
 .skip  4096-8

pdpe_base:
 .int   pde_base+7,0
 .skip  4096-8

pde_base:
 .int   pte_page_0+7,0
 .int   pte_page_1+7,0
 .int   pte_page_2+7,0
 .int   pte_page_3+7,0
 .int   pte_page_4+7,0
 .int   pte_page_5+7,0
 .int   pte_page_6+7,0
 .int   pte_page_7+7,0
 .int   pte_page_8+7,0
 .int   pte_page_9+7,0
 .int   pte_page_10+7,0
 .int   pte_page_11+7,0
 .int   pte_page_12+7,0
 .int   pte_page_13+7,0
 .int   pte_page_14+7,0
 .int   pte_page_15+7,0
 .skip  4096-8*16

 .set   curr_address,0

 .macro generate_pte
 .rept  512
 .int   curr_address+7,0
 .set   curr_address,curr_address+4096
 .endr
 .endm

pte_page_0:
 generate_pte
pte_page_1:
 generate_pte
pte_page_2:
 generate_pte
pte_page_3:
 generate_pte
pte_page_4:
 generate_pte
pte_page_5:
 generate_pte
pte_page_6:
 generate_pte
pte_page_7:
 generate_pte
pte_page_8:
 generate_pte
pte_page_9:
 generate_pte
pte_page_10:
 generate_pte
pte_page_11:
 generate_pte
pte_page_12:
 generate_pte
pte_page_13:
 generate_pte
pte_page_14:
 generate_pte
pte_page_15:
 generate_pte

 .bss
 # This is a small temporary stack that we use when checking for the CPUID
 # instruction
 .align  8
 .skip   32
stack_32bit:
