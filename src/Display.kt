package display
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
  var buffer: Array<Boolean?> = arrayOfNulls(64 * 32);

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

      SDL_SetRenderDrawColor( renderer, 0xFF, 0x00, 0x00, 0xFF );        
      SDL_RenderDrawRect( renderer, pixel.ptr );

      SDL_RenderPresent( renderer );

      // SDL_RenderCopy(renderer, texture, null, null);

      //Wait two seconds
      SDL_Delay( 10000 );

    }else{
      println( "Window could not be created!");
    }
  }

  fun cleanRenderer(){
    // delete[] pixels;
    SDL_DestroyTexture(texture);
    SDL_DestroyRenderer(renderer);
  }

  fun creatRect(){
    pixel.x = 160;
    pixel.y = 160;
    pixel.w = 100;
    pixel.h = 100;
  }

  fun createTexture() {
    texture = SDL_CreateTexture(renderer, SDL_PIXELFORMAT_ARGB8888, 0, 640, 480);
  }

  fun createWindow() : Boolean {
    window = SDL_CreateWindow( "SDL Tutorial", SDL_WINDOWPOS_CENTERED.toInt(), SDL_WINDOWPOS_CENTERED.toInt(), SCREEN_WIDTH, SCREEN_HEIGHT, SDL_WINDOW_SHOWN );

    return if (window == null) false else true;
  }

  fun draw() {
    // for(i in 0..(64 * 32)) {
    //   buffer.get(i)?.let {
    //     if(buffer.get(i)){
    //       SDL_FillRect(s, NULL, );
    //     }
    //   }
    // }

    // SDL_UpdateTexture(texture, NULL, pixels, 640 * sizeof(Uint32));
  }
}