package Chip8
const val START_ADDR: Int = 0x200;

class Memory() {
  var buffer = ByteArray(4096, { 0 });

  init {
    // Character Map

    // 0
    buffer[0] = 0xF0.toByte();  buffer[1] = 0x90.toByte();  buffer[2] = 0x90.toByte();  buffer[3] = 0x90.toByte();  buffer[4] = 0xF0.toByte(); 
    // 1
    buffer[5] = 0x02.toByte();  buffer[6] = 0x60.toByte();  buffer[7] = 0x02.toByte();  buffer[8] = 0x02.toByte();  buffer[9] = 0x70.toByte(); 
    // 2
    buffer[10] = 0xF0.toByte();  buffer[11] = 0x10.toByte();  buffer[12] = 0xF0.toByte();  buffer[13] = 0x80.toByte();  buffer[14] = 0xF0.toByte(); 
    // 3
    buffer[15] = 0xF0.toByte();  buffer[16] = 0x10.toByte();  buffer[17] = 0xF0.toByte();  buffer[18] = 0x10.toByte();  buffer[19] = 0xF0.toByte(); 
    // 4
    buffer[20] = 0x90.toByte();  buffer[21] = 0x90.toByte();  buffer[22] = 0xF0.toByte();  buffer[23] = 0x10.toByte();  buffer[24] = 0x10.toByte(); 
    // 5
    buffer[25] = 0xF0.toByte();  buffer[26] = 0x80.toByte();  buffer[27] = 0xF0.toByte();  buffer[28] = 0x10.toByte();  buffer[29] = 0xF0.toByte(); 
    // 6
    buffer[30] = 0xF0.toByte();  buffer[31] = 0x80.toByte();  buffer[32] = 0xF0.toByte();  buffer[33] = 0x90.toByte();  buffer[34] = 0xF0.toByte(); 
    // 7
    buffer[35] = 0xF0.toByte();  buffer[36] = 0x10.toByte();  buffer[37] = 0x20.toByte();  buffer[38] = 0x40.toByte();  buffer[39] = 0x40.toByte(); 
    // 8
    buffer[40] = 0xF0.toByte();  buffer[41] = 0x90.toByte();  buffer[42] = 0xF0.toByte();  buffer[43] = 0x90.toByte();  buffer[44] = 0xF0.toByte(); 
    // 9
    buffer[45] = 0xF0.toByte();  buffer[46] = 0x90.toByte();  buffer[47] = 0xF0.toByte();  buffer[48] = 0x10.toByte();  buffer[49] = 0xF0.toByte(); 
    // A
    buffer[50] = 0xF0.toByte();  buffer[51] = 0x90.toByte();  buffer[52] = 0xF0.toByte();  buffer[53] = 0x90.toByte();  buffer[54] = 0x90.toByte(); 
    // B
    buffer[55] = 0xE0.toByte();  buffer[56] = 0x90.toByte();  buffer[57] = 0xE0.toByte();  buffer[58] = 0x90.toByte();  buffer[59] = 0xE0.toByte(); 
    // C
    buffer[60] = 0xF0.toByte();  buffer[61] = 0x80.toByte();  buffer[62] = 0x80.toByte();  buffer[63] = 0x80.toByte();  buffer[64] = 0xF0.toByte(); 
    // D
    buffer[65] = 0xE0.toByte();  buffer[66] = 0x90.toByte();  buffer[67] = 0x90.toByte();  buffer[68] = 0x90.toByte();  buffer[69] = 0xE0.toByte(); 
    // E
    buffer[70] = 0xF0.toByte();  buffer[71] = 0x80.toByte();  buffer[72] = 0xF0.toByte();  buffer[73] = 0x80.toByte();  buffer[74] = 0xF0.toByte(); 
    // F
    buffer[75] = 0xF0.toByte();  buffer[76] = 0x80.toByte();  buffer[77] = 0xF0.toByte();  buffer[78] = 0x80.toByte();  buffer[79] = 0x80.toByte();   
  }

  fun get(address: Int): Byte{
    return buffer[address];
  }

  fun setProgram(program: Array<Byte>) {
    for(i in program.indices) {
      buffer[START_ADDR + i] = program[i];
    }
  }
}

