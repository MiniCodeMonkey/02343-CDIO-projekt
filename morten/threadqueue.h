/*! \file threadqueue.h
 * This file defines operations on thread queues.
 */

#ifndef _THREADQUEUE_H_
#define _THREADQUEUE_H_

#include "kernel.h"

struct thread_queue
{
 int head;  /*!< The index to the head of the thread queue.
                 Is -1 if queue is empty. */
 int tail;  /*!< The index to the tail of the thread queue.
                 Is -1 if queue is empty. */
};
/*!< Describes a queue of threads. */

/*! Initialize a thread queue. */
extern void
thread_queue_init(struct thread_queue* const queue_ptr
                  /*!< Points to the thread queue to be initialized. */);

/*! Enqueue one thread into the thread queue. The thread will be placed at
    the end of the queue. */
extern void
thread_queue_enqueue(struct thread_queue* const queue_ptr
                    /*!< Points to the thread queue. */,
                    const int thread_index
                    /*!< Index, into thread_table, of the thread to be
                         inserted into the thread queue. */);

/*! Remove the  head of the queue. \returns the index, into thread_table, of
    the thread removed from the thread queue.*/
extern int
thread_queue_dequeue(struct thread_queue* const queue_ptr
                    /*!< Points to the thread queue. */);

/*! Checks if the queue is empty. \returns 1 if the queue is empty.
    Returns 0 otherwise. */
extern int
thread_queue_is_empty(const struct thread_queue* const queue_ptr
                      /*!< Points to the thread queue. */);

/*! Returns the first thread in the thread_queue. \returns the index, into
    thread_table, of the first thread in the thread_queue or -1 if the queue
    is empty. */
extern int
thread_queue_head(const struct thread_queue* const queue_ptr
                  /*!< Points to the thread queue. */);
#endif
