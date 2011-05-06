/*! \file threadqueue.c
 * This file implements operations on thread queues.
 */

#include "threadqueue.h"

void
thread_queue_init(struct thread_queue* const queue_ptr)
{
 queue_ptr->head=-1;
 queue_ptr->tail=-1;
}

void
thread_queue_enqueue(struct thread_queue* const queue_ptr,
                    const int thread_index)
{
 /* Insert the thread as tail. */

 /* There is no next thread since the thread will be the new tail. */
 thread_table[thread_index].data.next=-1;

 if (thread_queue_is_empty(queue_ptr))
 {
  /* Set both head and tail if the queue is empty. */
  queue_ptr->head=thread_index;
  queue_ptr->tail=thread_index;
 }
 else
 {
  /* Replace the tail with the thread. */
  thread_table[queue_ptr->tail].data.next=thread_index;
  queue_ptr->tail=thread_index;
 }
}

int
thread_queue_dequeue(struct thread_queue* const queue_ptr)
{
 if (!thread_queue_is_empty(queue_ptr))
 {
  const register int thread_index=queue_ptr->head;

  /* The queue is not empty so we can remove one thread. */
  queue_ptr->head=thread_table[thread_index].data.next;
  if (thread_queue_is_empty(queue_ptr))
  {
   /* Make sure the tail is reset if the queue becomes empty. */
   queue_ptr->tail=-1;
  }

  return thread_index;
 }

 /* Return -1 if the queue was empty. */
 return -1;
}

int
thread_queue_is_empty(const struct thread_queue* const queue_ptr)
{
 return -1 == queue_ptr->head;
}

int
thread_queue_head(const struct thread_queue* const queue_ptr)
{
 return queue_ptr->head;
}
