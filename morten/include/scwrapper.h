/*! \file scwrapper.h
 *  This file contains C wrapper functions for system calls.
 *  The wrappers are used by user programs to perform system
 *  calls from C.
 */

#ifndef _SCWRAPPER_H_
#define _SCWRAPPER_H_

/* Include the constants that identifies system calls. */

#include "sysdefines.h"

/*! Wrapper for the system call that returns the version of the kernel. */
static inline unsigned long
version(void)
{
 unsigned long return_value;
 __asm volatile("syscall" : 
                 "=a" (return_value) :
                 "a" (SYSCALL_VERSION) : 
                 "cc", "%rcx", "%r11");
 return return_value;
}

/*! Wrapper for the system call that prints a string.
 * @param string Pointer to the string to be printed.
 * */
static inline long
prints(const char* const string)
{
 long return_value;
 __asm volatile("syscall" : 
                 "=a" (return_value) :
                 "a" (SYSCALL_PRINTS), "D" (string) : 
                 "cc", "%rcx", "%r11");
 return return_value;
}

/*! Wrapper for the system call that prints a hexadecimal value.
 * @param value hexadecimal value to be printed.
 */
static inline long
printhex(const unsigned long value)
{
 long return_value;
 __asm volatile("syscall" : 
                 "=a" (return_value) :
                 "a" (SYSCALL_PRINTHEX), "D" (value) : 
                 "cc", "%rcx", "%r11");
 return return_value;
}

/*! Wrapper for the system call that invokes the bochs debugger */
static inline long
debugger(void)
{
 long return_value;
 __asm volatile("syscall" : 
                 "=a" (return_value) :
                 "a" (SYSCALL_DEBUGGER) : 
                 "cc", "%rcx", "%r11");
 return return_value;
}

/*! Wrapper for the system call that terminates threads and processes. */
static inline void
terminate(void)
{
 __asm volatile("syscall" : 
                 :
                 "a" (SYSCALL_TERMINATE) : 
                 "cc", "%rcx", "%r11");
}

/*! Wrapper for the system call that creates processes.
 * @param executable integer identifying the program which should be loaded 
 * and run as a process.
 */
static inline long
createprocess(const int executable)
{
 long return_value;
 __asm volatile("syscall" : 
                 "=a" (return_value) :
                 "a" (SYSCALL_CREATEPROCESS), "D" (executable) : 
                 "cc", "%r11", "%rcx");
 return return_value;
}

/*! Wrapper for the system call that pauses the process for a 
 *  specified number of ticks. 
 *  @param ticks integer holding the number of ticks the process should 
 *         be paused.
 */
static inline long
pause(const int ticks)
{
 long return_value;
 __asm volatile("syscall" : 
                 "=a" (return_value) :
                 "a" (SYSCALL_PAUSE), "D" (ticks) : 
                 "cc", "%r11", "%rcx");
 return return_value;
}

/*! Wrapper for the system call that returns the current system time 
 *  in ticks.
 */
static inline long
time(void)
{
 long return_value;
 __asm volatile("syscall" : 
                 "=a" (return_value) :
                 "a" (SYSCALL_TIME) : 
                 "cc", "%rcx", "%r11");
 return return_value;
}

/*! Wrapper for the system call that allocates a memory block
 *  @param length integer holding the number of bytes to allocate
 *  @param flags integer holding the type of memory block to allocate
 */
static inline long
alloc(unsigned long length, int flags)
{
 long return_value;
 __asm volatile("syscall" : 
                 "=a" (return_value) :
                 "a" (SYSCALL_ALLOCATE), "D" (length), "S" (flags) : 
                 "cc", "%r11", "%rcx");
 return return_value;
}

/*! Wrapper for the system call that frees a memory block.
 *  @param address address to the memory block to free.
 */
static inline long
free(unsigned long address)
{
 long return_value;
 __asm volatile("syscall" : 
                 "=a" (return_value) :
                 "a" (SYSCALL_FREE), "D" (address) : 
                 "cc", "%r11", "%rcx");
 return return_value;
}

/*! Wrapper for the system call that allocates a port. */
static inline long
allocateport(unsigned long id)
{
 long return_value;
 __asm volatile("syscall" :
                 "=a" (return_value) :
                 "a" (SYSCALL_ALLOCATEPORT), "D" (id) :
                 "cc", "%r11", "%rcx", "memory");
 return return_value;
}

/*! Wrapper for the system call that finds a port given an owning process and
    port identity. */
static inline long
findport(unsigned long id, unsigned long process)
{
 long return_value;
 __asm volatile("syscall" :
                 "=a" (return_value) :
                 "a" (SYSCALL_FINDPORT), "D" (id), "S" (process):
                 "cc", "%r11", "%rcx", "memory");
 return return_value;
}

/*! Wrapper for the system call that sends a message to a port. */
static inline long
send(unsigned long port, const struct message* const message)
{
 long return_value;
 __asm volatile("syscall" :
                 "=a" (return_value) :
                 "a" (SYSCALL_SEND), "D" (port), "S" (SYSCALL_MSG_SHORT),
                 "b" (message):
                 "cc", "%r11", "%rcx", "memory");
 return return_value;
}

/*! Wrapper for the system call that sends a notification to a port. */
static inline long
send_notification(unsigned long port)
{
 long return_value;
 __asm volatile("syscall" :
                 "=a" (return_value) :
                 "a" (SYSCALL_SEND), "D" (port),
                 "S" (SYSCALL_MSG_NOTIFICATION):
                 "cc", "%r11", "%rcx", "memory");
 return return_value;
}

/*! Wrapper for the system call that sends a message of arbitrary length to 
    a port. */
static inline long
send_long(unsigned long               port, 
          const struct message* const message,
          const void* const           buffer, 
          const unsigned long         buffer_length)
{
 long return_value;
 __asm volatile("movq %%rcx,%%r10;\
                 syscall;\
                 movq %%r10,%%rcx" :
                 "=a" (return_value) :
                 "a" (SYSCALL_SEND), "D" (port), "S" (SYSCALL_MSG_LONG),
                 "d" (buffer), "c" (buffer_length), "b" (message):
                 "cc", "%r11", "%r10", "memory");
 return return_value;
}

/*! Wrapper for the system call that receives a message from a port. */
static inline long
receive(unsigned long         port, 
        struct message* const message,
        unsigned long* const  sender, 
        unsigned long* const  message_type)
{
 long return_value;
 __asm volatile("syscall" :
                 "=a" (return_value), "=D" (*sender), "=S" (*message_type) :
                 "a" (SYSCALL_RECEIVE), "D" (port), "S" (SYSCALL_MSG_SHORT),
                 "b" (message):
                 "cc", "%r11", "%rcx", "memory");
 return return_value;
}

/*! Wrapper for the system call that receives a message of arbitrary size 
    from a port. */
static inline long
receive_long (unsigned long         port, 
              void* const           buffer,
              unsigned long*        buffer_length, 
              struct message* const message,
              unsigned long* const  sender, 
              unsigned long* const  message_type)
{
 long return_value;
 __asm volatile("movq %%rcx,%%r10;\
                 syscall;\
                 movq %%r10,%%rcx" :
                 "=a" (return_value), "=D" (*sender),
                 "=S" (*message_type), "=c" (*buffer_length) :
                 "a" (SYSCALL_RECEIVE), "D" (port), "S" (SYSCALL_MSG_LONG),
                 "b" (message), "d" (buffer), "c" (*buffer_length):
                 "cc", "%r11", "%r10", "memory");
 return return_value;
}

/*! Wrapper for the system call that returns the process identity of the
    calling thread. */
static inline long
getpid(void)
{
 long return_value;
 __asm volatile("syscall" :
                 "=a" (return_value) :
                 "a" (SYSCALL_GETPID) :
                 "cc", "%rcx", "%r11");
 return return_value;
}

#endif
