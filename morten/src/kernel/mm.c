/*! \file 
 \brief Holds the implementation of the memory sub-system. */

#include "kernel.h"
#include "mm.h"

/* Variable definitions. */

struct page_frame page_frame_table[MAX_NUMBER_OF_FRAMES];

/* Set when the system is initialized. */
unsigned long memory_pages;

/* The following three variables are set by the assembly code. */
unsigned long first_available_memory_byte;

const unsigned long memory_size;

const unsigned long kernel_page_table_root;

/* Function definitions. */

/* Change this function in task B4. */
extern long kalloc(const register unsigned long length,
		const register unsigned int process, const register unsigned long flags) {

	int i;
	int pages = length + 4095 >> 12; // beregner antal pages ud fra antal bytes (length)
	int startFrame = -1; // start index i page_frame_table, -1 hvis ingen plads fundet!
	int counter = 0;

	// Finder start-frame på sammenhængende ledig pages i page_frame_table array
	for (i = 0; i < MAX_NUMBER_OF_FRAMES; ++i) {
		if (page_frame_table[i].owner == -1 && counter == 0) { // fundet den første ledige page_frame
			startFrame = i;
			counter++;
		} else if (page_frame_table[i].owner == -1) { // næste page_frame er også ledig
			counter++;
		} else if (page_frame_table[i].owner != -1 && counter < pages) { // ikke fundet sam.hæng. pages svarende til det antal vi har brug for
			counter = 0;
			startFrame = -1;
		}
		if (counter >= pages) {
			break;
		}
	}

	if (startFrame == -1) { // returns ERROR if not enough contiguous memory could be found
		return ERROR;
	}

	int x;
	//	der allokeres pages til processen
	for (x = 0; x < pages; x++) {
		page_frame_table[startFrame + x].owner = process;
		page_frame_table[startFrame + x].start = startFrame;

		if (flags && ALLOCATE_FLAG_KERNEL){
			page_frame_table[startFrame + x].free_is_allowed = 1;
		}
		else{
			page_frame_table[startFrame + x].free_is_allowed = 1;
		}

	}

	// der retuneres start adressen på den allokerede blok
	return startFrame * 4096;
}

/* Change this function in task B4. */
long kfree(const register unsigned long address) {

	int i;
	int startIndex = address / 4096;


	if (thread_table[cpu_private_data.thread_index].data.owner != page_frame_table[startIndex].owner)
		return ERROR;

	if (page_frame_table[startIndex].free_is_allowed == 0)
		return ERROR;

	for (i = startIndex; i < MAX_NUMBER_OF_FRAMES; ++i) {
		if (page_frame_table[i].start != startIndex)
			break;

		page_frame_table[i].owner = -1;
		page_frame_table[i].free_is_allowed = 1;
		page_frame_table[i].start = -1;

	}

	return ALL_OK;
}

/* Change this function in task A4. */
extern void update_memory_protection(const register unsigned long page_table,
		const register unsigned long start_address,
		const register unsigned long length, const register unsigned long flags) {




}

/* Change this function in task A4. */
extern void initialize_memory_protection() {
}

/* Put any code you need to add to implement tasks B4 and A4 here. */

