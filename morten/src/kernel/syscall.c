/*! \file syscall.c 

 This files holds the implementations of all system calls.

 */

case SYSCALL_PRINTS:
{
	kprints((char*) (SYSCALL_ARGUMENTS.rdi));
	SYSCALL_ARGUMENTS.rax=ALL_OK;
	break;
}

case SYSCALL_PRINTHEX:
{
	kprinthex(SYSCALL_ARGUMENTS.rdi);
	SYSCALL_ARGUMENTS.rax=ALL_OK;
	break;
}

case SYSCALL_DEBUGGER:
{
	/* Enable the bochs iodevice and force a return to the debugger. */
	outw(0x8a00, 0x8a00);
	outw(0x8a00, 0x8ae0);

	SYSCALL_ARGUMENTS.rax=ALL_OK;
	break;
}

/* Add the implementation of more system calls here. */

case SYSCALL_CREATEPROCESS:
{

	int new_process_index;
	int new_exe_index;
	struct executable caller;
	struct prepare_process_return_value return_val;

	new_exe_index = (int)SYSCALL_ARGUMENTS.rdi; // parameter
	kprinthex((long)new_exe_index);

	caller = executable_table[new_exe_index];

	int i;
	new_process_index = -1;
	for (i = 0; i < MAX_NUMBER_OF_PROCESSES; i++) {
		if (process_table[i].threads == 0) { // her antager jeg at en 'free entry' er hvor der ingen tråde kører..
			new_process_index = i;
			break;
		}
	}
	// Set the parent of the new process to be the calling process.
	if (new_process_index != -1) {
		process_table[new_process_index].parent = thread_table[cpu_private_data.thread_index].data.owner;
		process_table[new_process_index].threads++; // increments number of running thread
	}
	else {
		kprints("NO free entry in process-table found!!. Aborting!\n");
		SYSCALL_ARGUMENTS.rax=ERROR;
		break;
	}

	//Load the program into memory.
	return_val = prepare_process(caller.elf_image, // ?? executable->elf_image ??  = nope!
			new_process_index,
			caller.memory_footprint_size
	);
	if (return_val.first_instruction_address == 0) {
		SYSCALL_ARGUMENTS.rax=ERROR;
		break;
	}
	kprints("program loaded into memory!\n");

	// Allocate a new thread.
	int thread_index = allocate_thread();
	if (thread_index == -1) {
		SYSCALL_ARGUMENTS.rax=ERROR;
		break;
	}
	//kprints("new Thread allocated!\n");

	//Set the owner, rflags and rip of the allocated thread
	thread_table[thread_index].data.owner = new_process_index;
	thread_table[thread_index].data.registers.integer_registers.rflags = 0x200;
	thread_table[thread_index].data.registers.integer_registers.rip = return_val.first_instruction_address;

	// indsætter den nye tråd i ready_queue, klar til at blive kørt
	thread_queue_enqueue(&ready_queue,thread_index);


	//kprints("SYSCALL_CREATEPROCESS finish!\n");
	//kprints("\n");

	SYSCALL_ARGUMENTS.rax=ALL_OK;

	break;
}

case SYSCALL_TERMINATE:
{
	// finder processen der ejer den kørende tråd
	int current_process_index = thread_table[cpu_private_data.thread_index].data.owner;
	thread_table[cpu_private_data.thread_index].data.owner = -1; // sætter owner til -1 = terminere tråden!

	// dekrementere antallet af kørende tråde for denne process (caller)
	process_table[current_process_index].threads--;

	int new_running_process;

	// hvis processen ikke har flere tråde
	if (0 >= process_table[current_process_index].threads) {
		new_running_process = process_table[current_process_index].parent; // sætter den kørende proces til den terminerende process' parent
		cleanup_process(current_process_index);
	}

	/* Force a re-schedule. */
	schedule = 1;

	kprints("[SYSCALL end] SYSCALL_TERMINATE\n");

	SYSCALL_ARGUMENTS.rax=ALL_OK;

	break;
}

case SYSCALL_VERSION:
{
	//kprints((char*) (KERNEL_VERSION) );
	SYSCALL_ARGUMENTS.rax=KERNEL_VERSION;
	break;
}

case SYSCALL_CREATETHREAD:
{
	// næsten samme kode som i CREATE_PROCESS:

	kprints("[SYSCALL] SYSCALL_CREATETHREAD!\n");

	int owner = thread_table[cpu_private_data.thread_index].data.owner;

	// Allocate a new thread.
	int thread_index = allocate_thread();

	// stopper hvis der ikke kan allokeres en tråd
	if (thread_index == -1) {
		SYSCALL_ARGUMENTS.rax=ERROR;
		break;
	}
	kprints("new thread allocated\n");

	// increments number of running thread
	process_table[owner].threads++;

	//Set the owner, rflags and rip of the allocated thread  ( & rsp )
	thread_table[thread_index].data.owner = owner; // caller's owner = new thread's owner
	thread_table[thread_index].data.registers.integer_registers.rflags = 0x200;
	thread_table[thread_index].data.registers.integer_registers.rip = SYSCALL_ARGUMENTS.rdi;

	thread_table[thread_index].data.registers.integer_registers.rsp = SYSCALL_ARGUMENTS.rsi;

	// indsætter den nye tråd i ready_queue, klar til at blive kørt
	thread_queue_enqueue(&ready_queue,thread_index);

	kprints("[SYSCALL end] SYSCALL_CREATETHREAD!\n");

	SYSCALL_ARGUMENTS.rax=ALL_OK;
	break;
}

case SYSCALL_CREATESEMAPHORE:
{
	/* allocate new semaphore */
	int sema_index = allocate_semaphore();

	/* returns if no free semaphore could be found */
	if (sema_index == -1) {
		SYSCALL_ARGUMENTS.rax=ERROR;
		break;
	}

	/* sets the semaphore-owner to be the calling process */
	semaphore_table[sema_index].owner = thread_table[cpu_private_data.thread_index].data.owner;

	/* sets the value of this semaphore */
	semaphore_table[sema_index].val = SYSCALL_ARGUMENTS.rdi;

	/* puts the calling thread into the blocked_queue in the semaphore (blocks thread)*/
	//	  thread_queue_enqueue(&semaphore_table[sema_index].blocked_threads,cpu_private_data.thread_index);
	//
	//	  /* Force a re-schedule. */
	//	  schedule = 1;

	SYSCALL_ARGUMENTS.rax=sema_index;
	break;
}

case SYSCALL_SEMAPHOREDOWN:
{
	int sema_index = SYSCALL_ARGUMENTS.rdi;

	schedule = semaphore_down(semaphore_table[sema_index]);

	SYSCALL_ARGUMENTS.rax=ALL_OK;
	break;
}

case SYSCALL_SEMAPHOREUP:
{
	int sema_index = SYSCALL_ARGUMENTS.rdi;

	semaphore_up(semaphore_table[sema_index]);

	SYSCALL_ARGUMENTS.rax=ALL_OK;
	break;
}

