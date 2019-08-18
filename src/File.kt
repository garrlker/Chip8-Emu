package Chip8
import kotlinx.cinterop.*
import platform.posix.*

class File(filePath: String) {
  val fpointer = fopen(filePath, "rb");
  var filesize : Long = 0;
  var file = UByteArray(0, { 0u });
  

  init {
    this.getFileSize();
    this.readFile();
  }

  fun close(){
    fclose(this.fpointer);
  }

  fun get(): UByteArray {
    return file;
  }

  fun readFile(){
    var readByte : Int = 0;
    var index: Int = 0;

    try {
      memScoped {
        if (fpointer != null){
          while ( readByte != EOF ){
            readByte = fgetc(fpointer);
            if ( readByte != EOF ) {
              file[index] = readByte.toUByte();
              index += 1;
            }
          }
        }

        println("File size: $filesize");
      }
    } finally { 
      println("File Loaded")
    }
  }

  fun getFileSize() {
    fseek(fpointer, 0L, SEEK_END);
    filesize = ftell(fpointer);
    val intSize: Int = filesize.toInt()
    file = UByteArray(intSize, { 0u })
    rewind(fpointer);
  }
}
