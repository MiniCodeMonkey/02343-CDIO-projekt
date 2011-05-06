/*! \file
 *      \brief The second user program -
 *             in a neverending loop, sends a message to process 0 and waits
 *             for a reply. When the test case runs, there will be two
 *             instances of this program running.
 */

#include <scwrapper.h>

/* Define the debug messages to print on the console.
   Since we don't know until run-time which process id this process has,
   we defined them here and then modify the process number (the "n") later. */

char findport_process_failed[]  = "process n: findport on my own port failed\n";
char findport_process0_failed[] = "process n: findport for process 0 failed\n";
char sending_ping[] 		    = "process n: sending ping\n";
char send_failed[] 			    = "process n: send failed\n";
char recv_failed[] 			    = "process n: receive failed\n";
char recvd_pong[] 			    = "process n: received pong\n";
char test_failed[] 			    = "process n: message test failed\n";

void 
main(int argc, char* argv[])
{
 long send_port, recv_port;
 long my_pid;
 char pidchar;
 
 my_pid = getpid();

 if (my_pid<0)
 {
  prints("Can not get my PID!");
  debugger();
 }

 /* Setup the strings, replacing "n" with the actual process identity. */

 /* Convert my_pid to a character. */
 pidchar = '0' + (char) my_pid;

 /* And insert on the 9th place in the char arrays. */
 findport_process_failed[8] 	= pidchar;
 findport_process0_failed[8] 	= pidchar;
 sending_ping[8] 				= pidchar;
 send_failed[8] 				= pidchar;
 recv_failed[8] 				= pidchar;
 recvd_pong[8] 					= pidchar;
 test_failed[8] 				= pidchar;

 /* Get the port index for our own port 0. */ 
 recv_port=findport(0,my_pid);
 if (recv_port < 0)
 {
  prints(findport_process_failed);
  debugger();
 }

 /* Get the port index for process 0's port 0. */
 send_port=findport(0,0);
 if (send_port < 0)
 {
  prints(findport_process0_failed);
  debugger();
 }

 /* Go into an infinite loop, sending messages and waiting for replies. */
 while(1)
 {
  struct message msg;
  unsigned long  sender, type;

  msg.quad_0=0;
  msg.quad_1=1;
  msg.quad_2=2;
  msg.quad_3=3;
  msg.quad_4=4;
  msg.quad_5=5;
  msg.quad_6=6;
  msg.quad_7=7;

  prints(sending_ping);

  if (ALL_OK != send(send_port, &msg))
  {
   prints(send_failed);
   debugger();
  }

  if ((ALL_OK != receive(recv_port, &msg, &sender, &type)) ||
      (SYSCALL_MSG_SHORT != type) ||
      (0 != sender))
  {
   prints(recv_failed);
   debugger();
  }

  /* Test message validity. */
  if ((7 != msg.quad_0) ||
      (6 != msg.quad_1) ||
      (5 != msg.quad_2) ||
      (4 != msg.quad_3) ||
      (3 != msg.quad_4) ||
      (2 != msg.quad_5) ||
      (1 != msg.quad_6) ||
      (0 != msg.quad_7))
  {
   prints(test_failed);
   debugger();
  }

  prints(recvd_pong);

  debugger();
 }
}
