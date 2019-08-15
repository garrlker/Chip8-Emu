import display.*
import kotlin.system.getTimeNanos
import kotlin.coroutines.*

var loop : Boolean = true;
val disp = display.Display();


// Timing
val cpuHz = 1000 / 500;
val displayHz = 1000 / 60;
var cpuDelta : Long = 0;
var displayDelta : Long = 0;
var clockDelta : Long = 0;
var prevClock : Long = 0;

// Debug
var shutOff = 10000;
var current = 0;
var cpuCount = 0;
var elapsedTime : Long = 0;

fun initClock(){
  clockDelta = getTimeNanos();
}

fun updateClocks() {
  prevClock = clockDelta;
  clockDelta = getTimeNanos();
   
  cpuDelta += clockDelta - prevClock;
  displayDelta += clockDelta - prevClock;
}

fun cpuTick() : Boolean{
  if(nanoToMilli(cpuDelta) > cpuHz){
    current += 1;

    if(current >= shutOff){
      loop = false;
    }

    cpuDelta = 0;
    return true;
  }
  return false;
}

fun displayTick() : Boolean {
  if(nanoToMilli(displayDelta) > displayHz){
    displayDelta = 0;
    return true;
  }
  return false;
}

fun nanoToMilli(nanosecond: Long): Double {
  return nanosecond.toDouble() / 1000000
}


fun main() {
    println("Starting up")
    initClock();
    // disp.init();

    while(loop){
      updateClocks()

      if(cpuTick()){
        println("CPU Tick")
      }
    

      if(displayTick()){
        println("Display Tick");
      }
      // println(elapsedTime);
    }
}
