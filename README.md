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

# This project will stay incomplete
Turns out with my current solution I can hit a max peak of 300000 ops a second.

This is more than enough for chip8 emulation, but there are emulators that push much higher clocks in emulation that also use an interpretor, and I should be able to hit alot more than I am with what I'm using.

_or so I thought_

After doing some research it looks like Kotlin Native is just plain slow. It's slower than using Kotlin in JVM! And it's even slower than Javascript, PHP, and Python(PyPy).

And in [this benchmark](https://github.com/frol/completely-unscientific-benchmarks) it's 36 times slower than the fastest solution.

I'm abandoning this project for the near future to rewrite it in a faster language(like Rust, or Nim, or Go) and if KN ever starts to run faster I might revisit. 
