/*! \file
 *      \brief The first user program - 
 *             the test case follows the structure of the producer consumer 
 *             example in Tanenbaum&Woodhull's book.
 *
 */

#include <scwrapper.h>

long buffer[16];
int head = 0;
int tail = 0;

long empty_semaphore_handle;
long mutex_semaphore_handle;
long full_semaphore_handle;

void thread(void) {
	/* This is the consumer. */

	while (1) {
		prints("Starting to consume..\n");
		long value;

		//prints(" .. consume  (semaphoredown(full_semaphore_handle))\n");
		if (ALL_OK != semaphoredown(full_semaphore_handle)) {
			prints("semaphoredown failed!\n");
			break;
		}

		//prints(" .. consume  (semaphoredown(mutex_semaphore_handle)))\n");
		if (ALL_OK != semaphoredown(mutex_semaphore_handle)) {
			prints("semaphoredown failed!\n");
			break;
		}
		//prints(" .. consume\n");
		value = buffer[tail];
		tail = (tail + 1) & 15;

		if (ALL_OK != semaphoreup(mutex_semaphore_handle)) {
			prints("semaphoreup failed!\n");
			break;
		}

		if (ALL_OK != semaphoreup(empty_semaphore_handle)) {
			prints("semaphoreup failed!\n");
			break;
		}

		printhex(value);
		prints("\n");
		prints("Consume ending\n");
	}
	terminate();
}

void main(int argc, char* argv[]) {
	register long counter = 0;
	register long thread_stack;

	empty_semaphore_handle = createsemaphore(16);
	if (empty_semaphore_handle < 0) {
		prints("createsemaphore failed!\n");
		return;
	}

	full_semaphore_handle = createsemaphore(0);
	if (full_semaphore_handle < 0) {
		prints("createsemaphore failed!\n");
		return;
	}

	mutex_semaphore_handle = createsemaphore(1);
	if (mutex_semaphore_handle < 0) {
		prints("createsemaphore failed!\n");
		return;
	}

	thread_stack = alloc(4096, 0);

	if (0 >= thread_stack) {
		prints("Could not allocate the thread's stack!\n");
		return;
	}

	if (ALL_OK != createthread(thread, thread_stack + 4096)) {
		prints("createthread failed!\n");
		return;
	}

	/* This is the producer. */
	while (1) {
		if (ALL_OK != semaphoredown(empty_semaphore_handle)) {
			prints("semaphoredown failed!\n");
			break;
		}

		if (ALL_OK != semaphoredown(mutex_semaphore_handle)) {
			prints("semaphoredown failed!\n");
			break;
		}

		buffer[head] = counter++;
		head = (head + 1) & 15;

		if (ALL_OK != semaphoreup(mutex_semaphore_handle)) {
			prints("semaphoreup failed!\n");
			break;
		}

		if (ALL_OK != semaphoreup(full_semaphore_handle)) {
			prints("semaphoreup failed!\n");
			break;
		}
	}
}
