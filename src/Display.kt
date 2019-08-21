package Chip8
import sdl.*
import kotlinx.cinterop.*


class Display() {
  // Window handle
  var window: CPointer<SDL_Window>? = null

  // The surface contained by the window
  var screenSurface: CPointer<SDL_Surface>? = null;

  // Rendering Texture
  var texture: CPointer<SDL_Texture>? = null;

  // Renderer
  var renderer: CPointer<SDL_Renderer>? = null;

  // Rectangle
  var pixel = nativeHeap.alloc<SDL_Rect>();

  // Framebuffer
  var buffer = BooleanArray(64 * 32);

  // Window Dimensions
  val SCREEN_WIDTH: Int = 640
  val SCREEN_HEIGHT: Int = 480

  fun init() {
    SDL_Init(SDL_INIT_VIDEO);

    if(this.createWindow()){
      //Get window surface
      // screenSurface = SDL_GetWindowSurface( window );
      this.createTexture()
      this.creatRect()

      SDL_SetRenderTarget(renderer, texture);

      renderer = SDL_CreateRenderer(window, -1, SDL_RENDERER_ACCELERATED);

      this.clearScreen();

      SDL_RenderPresent( renderer );

      SDL_RenderCopy(renderer, texture, null, null);

      //This is debug to test our renderer
      buffer[1040] = true;

      //Wait two seconds
      // SDL_Delay( 10000 );

    }else{
      println( "Window could not be created!");
    }
  }

  fun cleanRenderer(){
    // delete[] pixels;
    SDL_DestroyTexture(texture);
    SDL_DestroyRenderer(renderer);
  }

  fun clearScreen(){
    SDL_SetRenderDrawColor( renderer, 0x00, 0x00, 0x00, 0xFF );
    SDL_RenderFillRect(renderer, null);
  }

  fun creatRect(){
    pixel.x = 0;
    pixel.y = 0;
    pixel.w = 64;
    pixel.h = 32;
  }

  fun createTexture() {
    texture = SDL_CreateTexture(renderer, SDL_PIXELFORMAT_ARGB8888, 0, 640, 480);
  }

  fun createWindow() : Boolean {
    window = SDL_CreateWindow( "SDL Tutorial", SDL_WINDOWPOS_CENTERED.toInt(), SDL_WINDOWPOS_CENTERED.toInt(), SCREEN_WIDTH, SCREEN_HEIGHT, SDL_WINDOW_SHOWN );

    return if (window == null) false else true;
  }

  fun drawBlackPixel(){
    SDL_SetRenderDrawColor( renderer, 0x00, 0x00, 0x00, 0xFF );
    SDL_RenderFillRect( renderer, pixel.ptr );
    // SDL_RenderCopy(renderer, texture, null, null);
  }

  fun drawWhitePixel(){
    println("Drawing White Pixel");
    SDL_SetRenderDrawColor( renderer, 0xFF, 0xFF, 0xFF, 0xFF );
    SDL_RenderFillRect( renderer, pixel.ptr );
    // SDL_RenderCopy(renderer, texture, null, null);
  }

  fun getBuffer(): BooleanArray {
    return buffer;
  }

  fun getPixel(x: Int, y: Int): Boolean {
    return buffer[(y * 32) + x];
  }

  fun draw() {
    for(x in 0 until 64){
      for(y in 0 until 32){
        this.setRectPosition(x, y)

        if(getPixel(x, y)){
          drawWhitePixel();
        }else{
          drawBlackPixel()
        }
      }
    }

    SDL_RenderCopy(renderer, texture, null, null);
    SDL_RenderPresent( renderer );
  }

  fun setRectPosition(x: Int, y: Int){
    pixel.x = x * 64;
    pixel.y = y * 32;
  }
}