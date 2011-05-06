/*! \file pscheduler.c
   Implement the preemptive scheduler here. 
 */
int TIMESLICE = 20;
	cpu_private_data.ticks_left_of_time_slice--;

	if (0 >= cpu_private_data.ticks_left_of_time_slice){


			// s�tter den k�rende tr�d bagerst i ready-queue'en
			thread_queue_enqueue(&ready_queue,cpu_private_data.thread_index);
			// piller f�rste tr�d ud af ready-queue og k�rer denne.
			cpu_private_data.thread_index = thread_queue_dequeue(&ready_queue);

			cpu_private_data.ticks_left_of_time_slice = TIMESLICE;

	}
