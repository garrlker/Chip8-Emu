import Chip8.*
import kotlin.system.getTimeNanos
import kotlin.coroutines.*

fun main(args: Array<String>) {
    var loop : Boolean = true;
    var clockTarget: Int = 0;
    val disp = Chip8.Display();
    val clock = Chip8.Clock();
    var maxCycles: Int = 10000;
    var numCycles: Int = 0;

    if(args.size > 0){
      val fPointer = Chip8.File(args[0]);
    }
    disp.init();
      clock.registerFunction({
          clockTarget += 1;
          numCycles += 1;

          if(numCycles > maxCycles) loop = false;
      }, 500)

      clock.registerFunction({
          println("Disp Tick: $clockTarget")
          clockTarget = 0;
      }, 60)


    while(loop){
    
      clock.update();
    }
}
