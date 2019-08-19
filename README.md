# Chip8 Emulator
A basic chip8 emulator written in Kotlin/Native.

# SDL
Use cinterop to make kotlin bindings for SDL, then install SDL as a klib. This program should be able to use/compile with it then.

# How To Build
After setting up SDL with kotlin run the bash script with the args ("", "cpu", "mem", "disp", "clock", "file", "all") to build that file or files.

This project is setup for building on mac.

Feel free to change the script for linux or add a batch file for windows.

# Running
Run the program like `./chip8.kexe pathToRom`