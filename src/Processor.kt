package Chip8
import Chip8.Memory
const val START_ADDR: UInt = 0x200u;

class Processor() {
  /* --- Registers ---
    PC - Program Counter: 16 bit register that points to current position in program
    SP - Stack Pointer:   8 bit register that stores the current level in the stack
    regV - V Registers:   16 general purpose 8 bit registers accessable by the program
    regI - I Register:    16 bit register that is primarily used to store addresses
    Stack:                16 16 bit address pointers
    delay:                8 bit register that is decremented at a rate of 60hz
    timer:                8 bit register that is decremented at a rate of 60hz
  */ 
  private var pc: UInt = 0x200u;
  private var stack = UIntArray(16);
  private var sp: Int = 0;
  private var regV = UIntArray(16);
  private var regI: UInt = 0u;
  private var delay: UInt = 0u;
  private var timer: UInt = 0u;
  private val mem = Chip8.Memory();
  private var opcode: UInt = 0u;

  fun tick(){
    opcode = fetch();
    incrementPC();
    execute(opcode);
  }
  
  fun fetch(): UInt{
    val byte1 = mem.get(pc);
    val byte2 = mem.get(pc + 1u);

    return byte1.shl(8).or(byte2);
  }

  fun execute(op: UInt){
    println(op.toString(16));
    when {
      /*            
      00E0 - CLS
      00EE - RET
      0nnn - SYS addr
      1nnn - JP addr
      2nnn - CALL addr
      3xkk - SE Vx, byte
      4xkk - SNE Vx, byte
      5xy0 - SE Vx, Vy
      6xkk - LD Vx, byte
      7xkk - ADD Vx, byte
      8xy0 - LD Vx, Vy
      8xy1 - OR Vx, Vy
      8xy2 - AND Vx, Vy
      8xy3 - XOR Vx, Vy
      8xy4 - ADD Vx, Vy
      8xy5 - SUB Vx, Vy
      8xy6 - SHR Vx {, Vy}
      8xy7 - SUBN Vx, Vy
      8xyE - SHL Vx {, Vy}
      9xy0 - SNE Vx, Vy
      Annn - LD I, addr
      Bnnn - JP V0, addr
      Cxkk - RND Vx, byte
      Dxyn - DRW Vx, Vy, nibble
      Ex9E - SKP Vx
      ExA1 - SKNP Vx
      Fx07 - LD Vx, DT
      Fx0A - LD Vx, K
      Fx15 - LD DT, Vx
      Fx18 - LD ST, Vx
      Fx1E - ADD I, Vx
      Fx29 - LD F, Vx
      Fx33 - LD B, Vx
      Fx55 - LD [I], Vx
      Fx65 - LD Vx, [I] */

      op == 0x00E0u -> println("Clear Screen")
      op == 0x00EEu -> { pc = stack[sp]; decrementStackPointer() }
      isOpcodeGroup(0x1u, op) -> pc = getAddressFromOp(op)
      
      isOpcodeGroup(0x2u, op) -> {
        incrementStackPointer();
        stack[sp] = pc;
        pc = getAddressFromOp(op);
      }

      isOpcodeGroup(0x3u, op) -> {
        val reg = getFirstNibble(op).toInt();

        if(regV[reg] == getByteFromOp(op)){
          incrementPC()
          incrementPC();
        }
      }

      isOpcodeGroup(0x4u, op) -> {
        val reg = getFirstNibble(op).toInt();

        if(regV[reg] != getByteFromOp(op)){
          incrementPC()
          incrementPC();
        }
      }

      isOpcodeGroup(0x5u, op) -> {
        val reg1 = getFirstNibble(op).toInt();
        val reg2 = getSecondNibble(op).toInt();

        if(regV[reg1] == regV[reg2]){
          incrementPC()
          incrementPC();
        }
      }

      isOpcodeGroup(0x6u, op) -> {
        val reg = getFirstNibble(op).toInt();
        regV[reg] = getByteFromOp(op);
      }

      isOpcodeGroup(0x7u, op) -> {
        val reg = getFirstNibble(op).toInt();
        regV[reg] += getByteFromOp(op);

        // Cap for 8 bits
        if(regV[reg] > 255) {
          regV -= 255
        }
      }

    }
  }

  fun getAddressFromOp(opcode: UInt): UInt {
    return opcode.and(0x0FFFu);
  }

  fun getByteFromOp(opcode: UInt): UInt {
    return opcode.and(0xFFu);
  }

  fun getFirstNibble(opcode: UInt): UInt {
    return opcode.and(0x0F00u).shr(8);
  }

  fun getSecondNibble(opcode: UInt): UInt {
    return opcode.and(0x00F0u).shr(4);
  }

  fun incrementPC(){
    pc += 2u; // Opcodes are 2 bytes
  }

  fun decrementStackPointer(){
    sp -= 1;
  }

  fun incrementStackPointer(){
    sp += 1;
  }

  fun isOpcodeGroup(group: UInt, opcode: UInt): Boolean {
	  return opcode.and(0xF000u) == group.shl(12);
  }

  fun printMemory(){
    var temp1: String;
    var temp2: String;
    var temp3: String;
    var temp4: String;
    for(i in START_ADDR until 4096u step 4){
      temp1 = mem.get(i.toUInt()).toString(16);
      temp2 = mem.get(i.toUInt() + 1u).toString(16);
      temp3 = mem.get(i.toUInt() + 2u).toString(16);
      temp4 = mem.get(i.toUInt() + 3u).toString(16);

      println("$temp1 $temp2 $temp3 $temp4");
    }
  }

  // Apparently kotlin's bitwise doesn't support bytes...
  fun shiftLeft(byte: UInt): UShort{
    var temp = byte.toUInt();
    return (temp shl 8).toUShort();
  }

  fun shiftRight(byte: UInt): UShort{
    var temp = byte.toUInt();
    return (temp shr 8).toUShort();
  }

  fun setProgram(program: UByteArray){
    for(i in 0 until program.size){
      mem.set(i.toUInt() + START_ADDR, program[i])
    }
  }
}