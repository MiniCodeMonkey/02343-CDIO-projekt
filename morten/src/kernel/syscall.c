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

	new_exe_index = (int)SYSCALL_ARGUMENTS.rdi;	// parameter
	kprinthex((long)new_exe_index);

	// skal denne være en pointer??		Fandt ud af det: Nej!
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
		process_table[new_process_index].threads++;	// increments number of running thread
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

	// Allocate a new thread.	TODO  ingen ledige tråde??
	int thread_index = allocate_thread();
	kprints("new Thread allocated!\n");

	//Set the owner, rflags and rip of the allocated thread
	thread_table[thread_index].data.owner = new_process_index;
	thread_table[thread_index].data.registers.integer_registers.rflags = 0x200;
	thread_table[thread_index].data.registers.integer_registers.rip = return_val.first_instruction_address;

	// indsætter den nye tråd i ready_queue, klar til at blive kørt
	thread_queue_enqueue(&ready_queue,thread_index);


	//kode til at tildele port
	//------------------------
	if(-1 == allocate_port(0,new_process_index))
		kprints("Port allocation failed!\n");
	else
		kprints("Port allocation succeded!\n");
	//kprints("ID:")
	//kprinthex((long)new_process_index);
	//kprints("\n");
	//------------------------

	/*	//kode som er overflødig, vi sætter sceduleren til at gøre dette istedet.
	 KODE FRA B2
		Switch to the new thread and let it execute
		current_thread = thread_index;
		kprints("current_thread SET!\n\n");*/


	kprints("SYSCALL_CREATEPROCESS finish!\n");
	kprints("\n");

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
	if (0 == process_table[current_process_index].threads){
		new_running_process = process_table[current_process_index].parent;	// sætter new owner til den terminerende process' parent
		cleanup_process(current_process_index);
	}

	/*
	//kode som er overflødig, vi sætter sceduleren til at gøre dette istedet.
	int i;
	for (i = 0; i < MAX_NUMBER_OF_THREADS; ++i) {
			if (thread_table[i].data.owner == new_running_process){
				cpu_private_data.thread_index = i;
				break;	// hopper ud når en tråd med ejer: new_running_process, er fundet (finder første og bedste TODO!!)
			}
	}
	 */

	/* Force a re-schedule. */
	schedule = 1;

	SYSCALL_ARGUMENTS.rax=ALL_OK;
	kprints("SYSCALL_TERMINATE finish!\n");
	kprints("\n");

	//Kode til at fjerne porte når en process termineres
	//--------------------------------------------------
	int portIndex;
			while(-1 != find_port(0,thread_table[cpu_private_data.thread_index].data.owner)){
				int portIndex = find_port(0,thread_table[cpu_private_data.thread_index].data.owner);
				port_table[portIndex].owner = -1;
			}
	//--------------------------------------------------

	break;
}

case SYSCALL_VERSION:
{
	//kprints((char*) (KERNEL_VERSION) );
	SYSCALL_ARGUMENTS.rax=KERNEL_VERSION;
	break;
}



