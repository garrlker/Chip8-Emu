package Chip8
import kotlin.system.getTimeNanos
import kotlin.coroutines.*

class Clock() {
  var currTime : Long = 0;
  var prevTime : Long = 0;
  var functions = mutableListOf<() -> Unit>();
  var amplitudes = mutableListOf<Long>();
  var deltas = mutableListOf<Long>();

  // DEBUG
  var subAmplitude: Boolean = false;


  init {
    currTime = getTimeNanos();
  }

  fun checkTicks(){
    this.amplitudes.forEachIndexed { index, amplitude ->
      if(this.deltas[index] > amplitude){
        this.functions[index]();

        if(subAmplitude){
          this.deltas[index] -= amplitude;
        }else{
          this.deltas[index] = 0;
        }
      }
    }
  }

  fun currentDelta() : Long{
    return currTime - prevTime;
  }

  fun enableSubAmp(){
    this.subAmplitude = true;
  }

  fun registerFunction(function: () -> Unit, amplitude: Int): Int{
    val clocksPerSecond : Long = 1000000000.toLong() / amplitude;

    this.functions.add(function);
    this.amplitudes.add(clocksPerSecond);
    this.deltas.add(0.toLong());

    return this.functions.lastIndex;
  }

  fun unregisterFucntion(index: Int): Unit {
    this.functions.removeAt(index);
    this.amplitudes.removeAt(index);
    this.deltas.removeAt(index);
  }

  fun update() {
    this.updateDeltas();
    this.checkTicks();
  }

  fun updateDeltas() {
    prevTime = currTime;
    currTime = getTimeNanos();
    val newDelta = currentDelta();

    this.deltas.forEachIndexed { index, _ ->
      this.deltas[index] += newDelta;
    }    
  }
}
