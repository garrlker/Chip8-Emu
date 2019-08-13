import display.*

fun hello(): String = "Hello, Kotlin/Native!"

var loop : Boolean = true;
val disp = display.Display();

fun main() {
    println(hello())
    disp.init();

    while(loop){
      
    }
}
