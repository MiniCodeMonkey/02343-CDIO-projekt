/*! \file 
    \brief Holds the implementation of the synchronization sub-system. */

#include "kernel.h"
#include "sync.h"

/* The function interfaces are documented in sync.h */

struct port
port_table[MAX_NUMBER_OF_PORTS];

void
initialize_ports(void)
{
 register int i;

 /* Loop over all ports in the table and set the owner to -1 indicating a 
    free port. */
 for(i=0; i<MAX_NUMBER_OF_PORTS; i++)
 {
  port_table[i].owner=-1;
 }
}

int
allocate_port(const unsigned long id, const int new_owner)
{
 register int i;
 register int first_available=-1;

 /* Loop over all ports to see if the port has already been allocated. */
 for(i=0; i<MAX_NUMBER_OF_PORTS; i++)
 {
  /* We keep track of the first available port. This way we do not
     have to scan the table twice. */
  if ((-1 == port_table[i].owner) &&
      (-1 == first_available))
  {
   first_available=i;
  }

  /* Check if the port entry match the one we want to create. */
  if ((new_owner == port_table[i].owner) &&
      (id == port_table[i].id))
  {
   return -1;
  }
 }

 if (-1 != first_available)
 {
  /* Set the new owner and new identity. */
  port_table[first_available].owner=new_owner;
  port_table[first_available].id=id;

  /* Initially, the sender queue is  empty and there are no receiving thread. */
  port_table[first_available].receiver=-1;
  thread_queue_init(&port_table[first_available].sender_queue);

  return first_available;
 }

 /* Return -1 if we ran out of ports. */
 return -1;
}

int
find_port(const unsigned long id, const int owner)
{
 register int i;

 /* Loop over all ports in the table. */
 for(i=0; i<MAX_NUMBER_OF_PORTS; i++)
 {
  /* Return as soon as we find a match. */
  if ((owner == port_table[i].owner) &&
      (id == port_table[i].id))
  {
   return i;
  }
 }

 return -1;
}

void
initialize_thread_synchronization(void)
{
 /* In task 6, add code here. */
}

/* Put any code you need to add to implement tasks B5, A5, B6 or A6 here. */
