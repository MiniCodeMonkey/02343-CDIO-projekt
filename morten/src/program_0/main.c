/*! \file
 *      \brief The first user program - 
 *
 */

#include <scwrapper.h>

void
main(int argc, char* argv[])
{

	long testNum = 0x34;

	printat(MAX_ROWS/2,MAX_COLS/2-8,"press ENTER to start test");
	printat(24,69,"test-image\n");
 

	int start = 0;

	char letter = 1;

 while(1)
 {

  register long scan_code=getscancode();
  if (0x1c==scan_code)
  {
   printat(MAX_ROWS-1, MAX_COLS-15, "Enter pressed ");

   if (start == 0)	sys_clear_screen();
   	   start = 1;

   	   testNum++;
   	   prints("press ENTER to make scroll-test ");

   	   printhex(testNum);


  } else if (0x9c==scan_code)
  {
   printat(MAX_ROWS-1, MAX_COLS-15, "Enter released");
  }
 }
}
