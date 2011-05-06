/*! \file scheduler.c
   Implement the non-preemptive scheduler here. 
 */

if (schedule == 1) {
		cpu_private_data.thread_index = thread_queue_dequeue(&ready_queue);
	}
	else{
		// s�tter den k�rende tr�d bagerst i ready-queue'en
		thread_queue_enqueue(&ready_queue,cpu_private_data.thread_index);
		// piller f�rste tr�d ud af ready-queue og k�rer denne.
		cpu_private_data.thread_index = thread_queue_dequeue(&ready_queue);
	}
