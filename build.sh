# Functions
function buildApp () {
  echo "Building Application";
  kotlinc src/Main.kt -l display -l file -l clock -l processor -o chip8;
}

function buildClock () {
  echo "Building Clock";
  kotlinc src/Clock.kt -p library -o clock;
  klib install clock.klib;
}

function buildCPU () {
  echo "Building CPU";
  kotlinc src/Processor.kt -l memory -p library -o processor;
  klib install processor.klib;
}

function buildDisplay () {
  echo "Building Display";
  kotlinc src/Display.kt -l sdl -p library -o display;
  klib install display.klib;
}

function buildFile () {
  echo "Building File";
  kotlinc src/File.kt -p library -o file;
  klib install file.klib;
}

function buildMemory () {
  echo "Building Memory";
  kotlinc src/Memory.kt -p library -o memory;
  klib install memory.klib;
}

if [ "$1" == "" ]
  then
    buildApp
fi

if [ "$1" == "all" ]
  then
    echo "Building All"
    buildFile
    buildClock
    buildMemory
    buildCPU
    buildDisplay
    buildApp
fi

if [ "$1" == "clock" ]
  then
  buildClock
fi

if [ "$1" == "cpu" ]
  then
  buildCPU
fi

if [ "$1" == "disp" ]
  then
  buildDisplay
fi

if [ "$1" == "file" ]
  then
  buildFile
fi

if [ "$1" == "mem" ]
  then
  buildMemory
fi
