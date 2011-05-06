/*! \file scheduler.c
   Implement the non-preemptive scheduler here. 
 */

if (schedule == 1) {
		cpu_private_data.thread_index = thread_queue_dequeue(&ready_queue);
	}
	else{
		// sætter den kørende tråd bagerst i ready-queue'en
		thread_queue_enqueue(&ready_queue,cpu_private_data.thread_index);
		// piller første tråd ud af ready-queue og kører denne.
		cpu_private_data.thread_index = thread_queue_dequeue(&ready_queue);
	}
