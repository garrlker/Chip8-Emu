import Chip8.Display
import Chip8.Processor
import Chip8.Clock
import Chip8.File
import kotlin.system.getTimeNanos
import kotlin.coroutines.*

fun main(args: Array<String>) {
    var loop : Boolean = true;
    var clockTarget: Int = 0;
    val disp = Chip8.Display();
    val clock = Chip8.Clock();
    val cpu = Chip8.Processor();
    var maxCycles: Int = 10000;
    var numCycles: Int = 0;

    if(args.size > 0){
      var file = Chip8.File(args[0]);
      cpu.setProgram(file.get());
      // cpu.printMemory();
    }

    disp.init();
    cpu.setDisplayBuffer(disp.getBuffer());
    clock.registerFunction({
      clockTarget += 1;
      numCycles += 1;
      cpu.tick();

      if(numCycles > maxCycles) loop = false;
    }, 500)

    clock.registerFunction({
      println("Disp Tick: $clockTarget")
      clockTarget = 0;

      //Display
      disp.draw();

      //CPU Stuff
      cpu.tickDelayTimer();
      cpu.tickSoundTimer();
    }, 60)


    while(loop){
    
      clock.update();
    }
}
