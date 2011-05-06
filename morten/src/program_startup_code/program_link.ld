OUTPUT_ARCH ( "i386:x86-64" )
OUTPUT_FORMAT("elf64-x86-64")
ENTRY(_start)

PHDRS
{
 headers PT_PHDR PHDRS ;
 text PT_LOAD FILEHDR PHDRS FLAGS(5);
 rodata PT_LOAD FLAGS(4);
 data PT_LOAD FLAGS(6);
}

SECTIONS
{
  . = SIZEOF_HEADERS;

  /* You can make the image to be much more compact. This link script
     was done so that it is remotely possible to read. It is complex 
     but could have been much more complex.*/
  .text (SIZEOF_HEADERS) :
   AT (SIZEOF_HEADERS)
  {
   * (.text) /* All text sections. */
   . = ALIGN(4096);
  } : text

  .rodata (ADDR(.text) + SIZEOF (.text)) :
   AT (LOADADDR(.text) + SIZEOF (.text))
  {
   * (.ro*)  /* Any read only data sections. */
   * (.eh*)  /* Any eh_frame sections. */
   . = ALIGN(4096);
  } : rodata 

  .data (ADDR(.rodata) + SIZEOF (.rodata)) :
   AT (LOADADDR(.rodata) + SIZEOF (.rodata))
  {
   * (.data) /* Any data sections. */
   . = ALIGN(4096);
  } : data  
 
  .bss (ADDR(.data) + SIZEOF (.data)) :
   AT (LOADADDR(.data) + SIZEOF (.data))
  {
   * (.bss)  /* Any bss sections. */
   . = ALIGN(8);
  } : data
}
