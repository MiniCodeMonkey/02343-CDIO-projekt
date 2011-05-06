/*! \file 
    \brief Holds declarations for the memory sub-system. */

#ifndef _MM_H_
#define _MM_H_

/* Macro definitions. */

#define MAX_NUMBER_OF_FRAMES    (32*1024*1024/(4*1024))
/*!< The maximum number of page frames the system can support. */

#define ALLOCATE_FLAG_KERNEL             (4)
/*!< Set if the memory block can only be de-allocated by the kernel. */

/*! This Macro extends the flags defined for the p_flags in the ELF program
   header entries. */
#define PF_KERNEL 0x8 /*!< Segment can only be accessed from the kernel. */

#define GET_PTE_ENTRY_POINTER(page_table, addr) \
 ((unsigned long *)(((page_table)+3*4*1024+(((addr)>>9)&(-8)))))
/*!< Macro returning the pointer to a PTE entry holding information on
     the page with address addr in the page table with address page_table. 
     Used in task A4. */

/* Type declarations. */

/*! Defines a page frame. */
struct page_frame
{
 int             owner; /*!< Index into the process table. The index
                             corresponds to the owning process. */
 int             start; /*!< Index into the page frame table. The index
                             corresponds to the start of the reserved
                             memory block. */
 int             free_is_allowed; /*!< Flag that is zero if the page must 
                                       not be de-allocated with free. */
};

/*! Defines a page-map level-4 table, a page-directory pointer table,
    a page-directory table, or a page table. */
struct page_table
{
 unsigned long   entries[512];
};

/* Variable declarations. */

extern struct page_frame
page_frame_table[MAX_NUMBER_OF_FRAMES];
/*!< Array holding information on all the page frames in physical memory. */

extern unsigned long
first_available_memory_byte;
/*!< The address of the first memory byte that is not used by the kernel. */

extern const unsigned long
memory_size;
/*!< Size, in bytes, of the memory. */

extern unsigned long
memory_pages;
/*!< Size, in pages, of the memory. */

extern const unsigned long
kernel_page_table_root;
/*!< The address of the page table tree that the kernel installs when 
     booting. */

/* Function declarations. */

/*! Allocates a memory block. 
    \return an address to the memory block or an error code if
            the allocation was not successful. */
extern long
kalloc(const register unsigned long length
        /*!< The number of bytes to allocate. */,
       const register unsigned int  process
        /*!< The process for which memory is to be allocated. */,
       const register unsigned long flags
        /*!< Flags indicating the type of memory block to allocate.*/);

/*! De-allocates a memory block previously allocated via kalloc.
    \return 0 if sucessful or an error code if not successful. */
extern long
kfree(const register unsigned long address
       /*!< The address of the memory block to free. */);

/*! Change the protection of a range of pages in a page table. To
    be implemented in task A4. */
extern void
update_memory_protection(const register unsigned long page_table
                          /*!< Address of the page table to update. */,
                         const register unsigned long start_address
                          /*!< Address to the first page to update. */,
                         const register unsigned long length
                          /*!< Total number of bytes to update. */,
                         const register unsigned long flags
                          /*!< A set of flags showing the protection to
                               use on the pages. */);

/*! Initialize the memory protection system. To be implemented in task A4. */
extern void
initialize_memory_protection();

/* Put any declarations you need to add to implement task A4 here. */

#endif
