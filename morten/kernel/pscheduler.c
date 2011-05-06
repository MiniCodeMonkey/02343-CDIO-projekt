/*! \file pscheduler.c
   Implement the preemptive scheduler here. 
 */
int TIMESLICE = 20;
	cpu_private_data.ticks_left_of_time_slice--;

	if (0 >= cpu_private_data.ticks_left_of_time_slice){


			// sætter den kørende tråd bagerst i ready-queue'en
			thread_queue_enqueue(&ready_queue,cpu_private_data.thread_index);
			// piller første tråd ud af ready-queue og kører denne.
			cpu_private_data.thread_index = thread_queue_dequeue(&ready_queue);

			cpu_private_data.ticks_left_of_time_slice = TIMESLICE;

	}
