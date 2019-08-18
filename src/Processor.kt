package Chip8
import Chip8.Memory

class Processor() {
  /* --- Registers ---
    PC - Program Counter: 16 bit register that points to current position in program
    SP - Stack Pointer:   8 bit register that stores the current level in the stack
    regV - V Registers:   16 general purpose 8 bit registers accessable by the program
    regI - I Register:    16 bit register that is primarily used to store addresses
    delay:                8 bit register that is decremented at a rate of 60hz
    timer:                8 bit register that is decremented at a rate of 60hz
  */ 
  var pc: Short = 0x200;
  var sp: Byte = 0;
  var regV = ByteArray(0xF, { 0 });
  var regI: Short = 0;
  var delay: Byte = 0;
  var timer: Byte = 0;
  
  fun fetch(){

  }

  fun decode(){

  }

  fun execute(){

  }
}