OUTPUT_ARCH ( "i386" )
OUTPUT_FORMAT("elf32-i386")
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

  /* You can make to image to be much more compact. This link script
     was done so that it is remotely possible to read. It is complex 
     but could have been much more complex.*/
  .text (0x00100000 + SIZEOF_HEADERS) :
   AT (0x00100000 + SIZEOF_HEADERS)
  {
   objects/kernel/boot32.o (.text)
   objects/kernel/boot64.o (.text) 
   objects/kernel/enter.o (.text)
   objects/kernel/kernel.o (.text)
   * (.text) /* Any remaining text sections. */
   . = ALIGN(4096);
  } : text

  .rodata (ADDR(.text) + SIZEOF (.text)) :
   AT (LOADADDR(.text) + SIZEOF (.text))
  {
   start_of_ELF_images = ABSOLUTE(.);
   QUAD(_binary_objects_program_1_executable_start - 8);
   objects/program_0/executable.o (.data)
   QUAD(_binary_objects_program_2_executable_start - 8);
   objects/program_1/executable.o (.data)
   QUAD(0);
   objects/program_2/executable.o (.data)
   end_of_ELF_images = ABSOLUTE(.);
   * (.ro*)  /* Any remaining read only data sections. */
   * (.eh*)  /* Any remaining eh_frame sections. */
   . = ALIGN(4096);
  } : rodata

  .data (ADDR(.rodata) + SIZEOF (.rodata)) :
   AT (LOADADDR(.rodata) + SIZEOF (.rodata))
  {
   objects/kernel/boot32.o (.data)
   * (.data) /* Any remaining data sections. */
  } : data
 
  .bss (ADDR(.data) + SIZEOF (.data)) :
   AT (LOADADDR(.data) + SIZEOF (.data))
  {
   objects/kernel/enter.o (.bss)
   * (.bss)  /* Any remaining bss sections. */
  } : data

  end_of_bss = ABSOLUTE(.); 
}
