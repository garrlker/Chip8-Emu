package Chip8
import kotlinx.cinterop.*
import platform.posix.*

class File(filePath: String) {
  val fpointer = fopen(filePath, "rb");
  var filesize : Int = 0;

  init {
    var lastReadByte : Int = 0;
    try {
      memScoped {
        if (fpointer != null){
          while ( lastReadByte != EOF ){
            lastReadByte = fgetc(fpointer);
            if ( lastReadByte != EOF ) {
              println( lastReadByte );
              filesize += 1;
            }
          }
        }

        println("File size: $filesize");
      }
    } finally {
      fclose(fpointer)
    }
  }
}
