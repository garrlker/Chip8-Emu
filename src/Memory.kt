package Chip8
// const val START_ADDR: Int = 0x200;

class Memory() {
  var buffer = UByteArray(4096);

  init {
    // Character Map

    // 0
    buffer[0] = 0xF0.toUByte();  buffer[1] = 0x90.toUByte();  buffer[2] = 0x90.toUByte();  buffer[3] = 0x90.toUByte();  buffer[4] = 0xF0.toUByte(); 
    // 1
    buffer[5] = 0x02.toUByte();  buffer[6] = 0x60.toUByte();  buffer[7] = 0x02.toUByte();  buffer[8] = 0x02.toUByte();  buffer[9] = 0x70.toUByte(); 
    // 2
    buffer[10] = 0xF0.toUByte();  buffer[11] = 0x10.toUByte();  buffer[12] = 0xF0.toUByte();  buffer[13] = 0x80.toUByte();  buffer[14] = 0xF0.toUByte(); 
    // 3
    buffer[15] = 0xF0.toUByte();  buffer[16] = 0x10.toUByte();  buffer[17] = 0xF0.toUByte();  buffer[18] = 0x10.toUByte();  buffer[19] = 0xF0.toUByte(); 
    // 4
    buffer[20] = 0x90.toUByte();  buffer[21] = 0x90.toUByte();  buffer[22] = 0xF0.toUByte();  buffer[23] = 0x10.toUByte();  buffer[24] = 0x10.toUByte(); 
    // 5
    buffer[25] = 0xF0.toUByte();  buffer[26] = 0x80.toUByte();  buffer[27] = 0xF0.toUByte();  buffer[28] = 0x10.toUByte();  buffer[29] = 0xF0.toUByte(); 
    // 6
    buffer[30] = 0xF0.toUByte();  buffer[31] = 0x80.toUByte();  buffer[32] = 0xF0.toUByte();  buffer[33] = 0x90.toUByte();  buffer[34] = 0xF0.toUByte(); 
    // 7
    buffer[35] = 0xF0.toUByte();  buffer[36] = 0x10.toUByte();  buffer[37] = 0x20.toUByte();  buffer[38] = 0x40.toUByte();  buffer[39] = 0x40.toUByte(); 
    // 8
    buffer[40] = 0xF0.toUByte();  buffer[41] = 0x90.toUByte();  buffer[42] = 0xF0.toUByte();  buffer[43] = 0x90.toUByte();  buffer[44] = 0xF0.toUByte(); 
    // 9
    buffer[45] = 0xF0.toUByte();  buffer[46] = 0x90.toUByte();  buffer[47] = 0xF0.toUByte();  buffer[48] = 0x10.toUByte();  buffer[49] = 0xF0.toUByte(); 
    // A
    buffer[50] = 0xF0.toUByte();  buffer[51] = 0x90.toUByte();  buffer[52] = 0xF0.toUByte();  buffer[53] = 0x90.toUByte();  buffer[54] = 0x90.toUByte(); 
    // B
    buffer[55] = 0xE0.toUByte();  buffer[56] = 0x90.toUByte();  buffer[57] = 0xE0.toUByte();  buffer[58] = 0x90.toUByte();  buffer[59] = 0xE0.toUByte(); 
    // C
    buffer[60] = 0xF0.toUByte();  buffer[61] = 0x80.toUByte();  buffer[62] = 0x80.toUByte();  buffer[63] = 0x80.toUByte();  buffer[64] = 0xF0.toUByte(); 
    // D
    buffer[65] = 0xE0.toUByte();  buffer[66] = 0x90.toUByte();  buffer[67] = 0x90.toUByte();  buffer[68] = 0x90.toUByte();  buffer[69] = 0xE0.toUByte(); 
    // E
    buffer[70] = 0xF0.toUByte();  buffer[71] = 0x80.toUByte();  buffer[72] = 0xF0.toUByte();  buffer[73] = 0x80.toUByte();  buffer[74] = 0xF0.toUByte(); 
    // F
    buffer[75] = 0xF0.toUByte();  buffer[76] = 0x80.toUByte();  buffer[77] = 0xF0.toUByte();  buffer[78] = 0x80.toUByte();  buffer[79] = 0x80.toUByte();   
  }

  fun get(address: UInt): UInt{
    if(address >= 0u && 4096u >= address)
      return buffer[address.toInt()].toUInt();
    else
      return 0u
  }

  fun getCharSpriteAddr(address: UInt): UInt{
    return 5u * address;
  }

  fun set(address: UInt, data: UByte){
    if(address >= 0u && 4096u >= address)
      buffer[address.toInt()] = data;
  }
}

