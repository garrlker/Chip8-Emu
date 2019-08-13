if [ "$1" == "" ]
  then
    echo "Building Application";
    kotlinc src/Main.kt -l display -o chip8;
fi

if [ "$1" == "disp" ]
  then
    echo "Building Display";
    kotlinc src/Display.kt -l sdl -p library -o display;
    klib install display.klib;
fi