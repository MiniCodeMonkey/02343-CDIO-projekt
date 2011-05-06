/*! \file
 *      \brief The first user program - 
 *             in a neverending loop, waits for messages from the other 
 *             processes and responds to them.
 *
 */

#include <scwrapper.h>

void
main(int argc, char* argv[])
{
 long recv_port;
 
 /* Launch two instances of program_1 */
 if (ALL_OK != createprocess(1))
 {
  prints("process 0: createprocess(1) failed\n");
  debugger();
 }

 if (ALL_OK != createprocess(1))
 {
  prints("process 0: Second instance of createprocess(1) failed\n");
  debugger();
 }

 /* Get the port index for our own port 0 */
 recv_port=findport(0,0);
 if (recv_port < 0)
 {
  prints("process 0: findport for process 0 failed\n");
  debugger();
 }


 /* Go into an infinite loop, waiting for messages and sending replies. */

 prints("process 0: main loop\n");
 while(1)
 {
  struct message msg;
  unsigned long  sender, type;
  long send_port;

  if ((ALL_OK != receive(recv_port, &msg, &sender, &type)) ||
      (SYSCALL_MSG_SHORT != type))
  {
   prints("process 0: receive failed\n");
   debugger();
  }
  prints("process 0: received ping\n");

  /* Test message. */
  if ((0 != msg.quad_0) ||
      (1 != msg.quad_1) ||
      (2 != msg.quad_2) ||
      (3 != msg.quad_3) ||
      (4 != msg.quad_4) ||
      (5 != msg.quad_5) ||
      (6 != msg.quad_6) ||
      (7 != msg.quad_7))
  {
   prints("process 0: message test failed\n");
   printhex(msg.quad_0);
   debugger();
  }

  send_port=findport(0, sender);
  if (send_port < 0)
  {
   prints("process 0: findport for process failed\n");
   debugger();
  }


  msg.quad_0=7;
  msg.quad_1=6;
  msg.quad_2=5;
  msg.quad_3=4;
  msg.quad_4=3;
  msg.quad_5=2;
  msg.quad_6=1;
  msg.quad_7=0;

  if (ALL_OK != send(send_port, &msg))
  {
   prints("process 0: send failed\n");
   debugger();
  }
  prints("process 0: sent pong\n");
 }
}
