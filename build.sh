# Functions
function buildApp () {
  echo "Building Application";
  kotlinc src/Main.kt -l ch8display -l ch8file -l ch8clock -l ch8processor -o chip8;
}

function buildClock () {
  echo "Building Clock";
  kotlinc src/Clock.kt -p library -o ch8clock;
  klib install ch8clock.klib;
}

function buildCPU () {
  echo "Building CPU";
  kotlinc src/Processor.kt -l ch8memory -p library -o ch8processor;
  klib install ch8processor.klib;
}

function buildDisplay () {
  echo "Building Display";
  kotlinc src/Display.kt -l sdl -p library -o ch8display;
  klib install ch8display.klib;
}

function buildFile () {
  echo "Building File";
  kotlinc src/File.kt -p library -o ch8file;
  klib install ch8file.klib;
}

function buildMemory () {
  echo "Building Memory";
  kotlinc src/Memory.kt -p library -o ch8memory;
  klib install ch8memory.klib;
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
