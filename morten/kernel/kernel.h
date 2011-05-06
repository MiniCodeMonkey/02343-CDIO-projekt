/*!
 * \file kernel.h

 This file holds kernel wide macros and declarations.

 */

#ifndef _KERNEL_H_
#define _KERNEL_H_

#include <sysdefines.h>

/* Type definitions */

/* Macros */

#define SYSCALL_ARGUMENTS (thread_table[cpu_private_data.thread_index].\
                           data.registers.integer_registers)
/*!< Macro used in the system call switch to access the arguments to the
     system call. */

#define KERNEL_VERSION          (0x0000000100000000)
/*!< Kernel version number. */

#define MAX_NUMBER_OF_PROCESSES (16)
/*!< Size of the process_table. */
#define MAX_NUMBER_OF_THREADS   (256)
/*!< Size of the thread_table. */

/* Type declarations */

/*! Defines an execution context. */
struct context
{
 char     fpu_context[512];
 /*!< Stores the fpu/mmx/sse registers */
 struct
 {
  long    rax;
  long    rbx;
  long    rcx;
  long    rdx;
  long    rdi;
  long    rsi;
  long    rbp;
  long    rsp;
  long    r8;
  long    r9;
  long    r10;
  long    r11;
  long    r12;
  long    r13;
  long    r14;
  long    r15;
  long    rflags;   /*!< status flags */
  long    rip;      /*!< instruction pointer */
 }        integer_registers;
 /*!< Stores all user visible integer registers. The segment registers are not
      stored as the user mode processes can only use a code and a data segment
      descriptor. We assume that the cs is set to the code and all the rest
      of the segment registers are set to the data segment all the time. */
 char     from_interrupt;
 /*!< Is set to 1 if the context is saved during the processing of interrupts.
      Set to 0 otherwise. */
};

/*! Defines a thread. */
union thread
{
 struct
 {
  struct context registers;     /*!< The context of the thread. Note: the
                                     context of the thread could include more
                                     than the accessible registers. */
  int            owner;         /*!< An index, into the process table, that
                                     identifies the process that owns this
                                     thread. */
  int            next;          /*!< This is an index into the thread_table.
                                     The index corresponds to the thread
                                     following this thread in a linked list.
                                     A thread can be in a number of linked
                                     lists. */
  unsigned long  list_data;     /*!< This member variable has different
                                     meaning depending on what list the thread
                                     resides in. In the timer queue this
                                     variable is either an absolute time or a
                                     delta time.*/
 }               data;
 char            padding[1024];
};

/*! Defines a process. */
struct process
{
 int             threads;       /*!< The number of threads running in this
                                     process. */
 int             parent;        /*!< This is an index into process_table. The
                                     index corresponds to the parent process.
                                 */
 unsigned long   page_table_root; /*!< Address of the page table tree. */
};

/* ELF image structures. The names from the ELF64 specification are used and
   the structs are derived from the ELF64 specification. */

#define EI_MAG0       0  /*!< The first four bytes in an ELF image is
                              0x7f 'E' 'L' 'F'. */
#define EI_MAG1       1
#define EI_MAG2       2
#define EI_MAG3       3
#define EI_CLASS      4  /*!< The specific ELF image class. */
#define EI_DATA       5  /*!< Describes if the image is big- or little-
                              endian. */
#define EI_VERSION    6  /*!< The version of the ELF specification the
                              image adheres to. */
#define EI_OSABI      7  /*!< Type of OS the image can be run on. */
#define EI_ABIVERSION 8  /*!< Version of the ABI used. */
#define EI_PAD        9  /*!< First unused byte in the identification array.
                          */
#define EI_NIDENT     16 /*!< Number of entries in the identification array */

/*! Defines an ELF64 file header. */
struct Elf64_Ehdr
{
 unsigned char e_ident[EI_NIDENT]; /*!< Array of bytes that shows that this
                                        is an ELF image and what type of ELF
                                        image it is. */
 short         e_type;             /*!< The type of ELF executable image. */
 short         e_machine;          /*!< Identifies the type of machine that
                                        the image can execute on. */
 int           e_version;          /*!< The version of the ELF specification the
                                        image adheres to. */
 long          e_entry;            /*!< Start address of the executable. */
 long          e_phoff;            /*!< The offset into the image where the
                                        program header table is found. */
 long          e_shoff;            /*!< The offset into the image where the
                                        program header table is found. */
 int           e_flags;            /*!< Flags that are machine specific.
                                        These can be used to differentiate
                                        between similar machines. */
 short         e_ehsize;           /*!< The size, in bytes, of the header. */
 short         e_phentsize;        /*!< The size, in bytes, of each entry in
                                        the program header table. */
 short         e_phnum;            /*!< The size, in entries, of the program
                                        header table. */
 short         e_shentsize;        /*!< The size, in bytes, of each entry in
                                        the section header table. */
 short         e_shnum;            /*!< The size, in entries, of the section
                                        header table. */
 short         e_shstrndx;         /*!< The index, into the section table,
                                        of the section name string table.*/
};

/*! Defines an ELF32 file header. This is used for the kernel image itself. */
struct Elf32_Ehdr
{
 unsigned char e_ident[EI_NIDENT]; /*!< Array of bytes that shows that this
                                        is an ELF image and what type of ELF
                                        image it is. */
 short         e_type;             /*!< The type of ELF executable image. */
 short         e_machine;          /*!< Identifies the type of machine that
                                        the image can execute on. */
 int           e_version;          /*!< The version of the ELF specification the
                                        image adheres to. */
 int           e_entry;            /*!< Start address of the executable. */
 int           e_phoff;            /*!< The offset into the image where the
                                        program header table is found. */
 int           e_shoff;            /*!< The offset into the image where the
                                        program header table is found. */
 int           e_flags;            /*!< Flags that are machine specific.
                                        These can be used to differentiate
                                        between similar machines. */
 short         e_ehsize;           /*!< The size, in bytes, of the header. */
 short         e_phentsize;        /*!< The size, in bytes, of each entry in
                                        the program header table. */
 short         e_phnum;            /*!< The size, in entries, of the program
                                        header table. */
 short         e_shentsize;        /*!< The size, in bytes, of each entry in
                                        the section header table. */
 short         e_shnum;            /*!< The size, in entries, of the section
                                        header table. */
 short         e_shstrndx;         /*!< The index, into the section table,
                                        of the section name string table.*/
};

/*! Defines an entry in the ELF program header table. Each entry corresponds
    to a segment. */
struct Elf64_Phdr
{
 int  p_type;   /*!< Segments can have several types. p_type holds the type. */
 int  p_flags;  /*!< The attribute flags of the segment. */
 long p_offset; /*!< Offset into the image of the first byte of the
                     segment */
 long p_vaddr;  /*!< The (virtual) address to which the segment is to be
                     loaded. */
 long p_paddr;  /*!< Not used. */
 long p_filesz; /*!< The number of bytes the segment occupies in the
                     image. */
 long p_memsz;  /*!< The number of bytes the segment occupies in memory. */
 long p_align;  /*!< The alignment the segment should have in memory. This
                     field is currently being ignored. */
};

/*! Defines an entry in the ELF32 program header table. Each entry corresponds
    to a segment. */
struct Elf32_Phdr
{
 int  p_type;   /*!< Segments can have several types. p_type holds the type. */
 int  p_offset; /*!< Offset into the image of the first byte of the
                     segment */
 int  p_vaddr;  /*!< The (virtual) address to which the segment is to be
                     loaded. */
 int  p_paddr;  /*!< Not used. */
 int  p_filesz; /*!< The number of bytes the segment occupies in the
                     image. */
 int  p_memsz;  /*!< The number of bytes the segment occupies in memory. */
 int  p_flags;  /*!< The attribute flags of the segment. */
 int  p_align;  /*!< The alignment the segment should have in memory. This
                     field is currently being ignored. */
};

/* Values used in p_type */
#define PT_NULL 0 /*!< The entry is not used. */
#define PT_LOAD 1 /*!< The segment can be loaded into memory. */
#define PT_PHDR 6 /*!< The segment only hold a program header table. */
/* Values used in p_flags */
#define PF_X        0x1        /*!< Segment can be executed.*/
#define PF_W        0x2        /*!< Segment can be written. */
#define PF_R        0x4        /*!< Segment can be read. */
#define PF_MASKPERM 0x0000FFFF /*!< Used to mask the permission bits */

/* Data structures describing the executable images embedded in the kernel
   image. */

/*! Defines an executable program. */
struct executable
{
 const struct Elf64_Ehdr* elf_image;             /*!< The start of the ELF
                                                      file header. */
 unsigned long            memory_footprint_size; /*!< Size in bytes of the
                                                      program's memory foot
                                                      print when loaded. */
};

/*! Defines an executable image embedded into the kernel image. The executable
    images are linked together into a linked list. The linked list is built at
    link time, see the kernel link script. */
struct executable_image
{
 const struct executable_image* const next; /*!< Points to the next
                                                 executable image. The last
                                                 executable image in the list
                                                 will have a next pointer
                                                 equal to 0, i.e., null. */
 const struct Elf64_Ehdr              elf_image; /*!< The ELF image file 
                                                      header. */
};

/*! Defines the structure pointed to by the kernel GS_BASE. Every CPU has one
    of these. */
struct CPU_private
{
 unsigned long  scratch_space;   /*!< Temporary storage used during  context
                                      switches. */
 unsigned long  page_table_root; /*!< The root of the page table in use on
                                      the CPU. */
 int            thread_index;    /*!< Index into thread_table of the thread
                                      executing on the CPU. The idle thread
                                      has index -1. */
 int            ticks_left_of_time_slice;
                                 /*!< Can be used by a preemptive scheduler. */
};

/* Variable declarations */

extern union thread
thread_table[MAX_NUMBER_OF_THREADS];
/*!< Array holding all threads in the systems. */

extern struct process
process_table[MAX_NUMBER_OF_PROCESSES];
/*!< Array holding all processes in the system. */

extern const struct executable_image* const
ELF_images_start;
/*!< The first executable image in the linked list of executable images. */

extern const char* const
ELF_images_end;
/*!< The address of the first byte after the end of the last ELF image. This
     variable is used for sanity checking. */

/*! \note Linked lists are terminated with a thread with a next index of -1. */

extern struct thread_queue
ready_queue;
/*!< The ready queue. */

extern int
timer_queue_head;
/*!< The index, into thread_table, of the head of the timer queue. The timer
     queue is a list of threads blocked waiting for the system clock to reach
     a certain time.  This variable is set to -1 if there are no threads in
     the timer queue. */

extern long
system_time;
/*!< This variable holds the current system time. The system time is the
     number of clock  ticks since system start. There are 200 clock ticks
     per second. */

extern struct CPU_private
cpu_private_data;
/*!< Holds data private to the CPU. */

/* Function declarations */

/*! This fuction initializes the kernel after the assembly code portion has
    set the system and the CPU up. */
extern void
initialize(void);

/*! This function gets called from the syscall handler and responds to the
    system calls. */
extern void
system_call_handler(void);

/*! This function gets called from the interrupt handler and manages timer
    interrupts. */
extern void
timer_interrupt_handler(void);

/*! Outputs a string to the bochs console. */
extern void
kprints(const char* const string
        /*!< points to a null terminated string */
        );

/*! Prints a long formatted as a hexadecimal number to the bochs console. */
extern void
kprinthex(const register long value
          /*!< the value to be written */);

#endif
