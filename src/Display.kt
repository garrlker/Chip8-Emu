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

  // Colors
  var cWhite: Uint32? = null;
  var cBlack: Uint32? = null;

  // Framebuffer
  var buffer: Array<Boolean?> = arrayOfNulls(64 * 32);

  // Window Dimensions
  val SCREEN_WIDTH: Int = 640
  val SCREEN_HEIGHT: Int = 480

  fun init() {
    SDL_Init(SDL_INIT_VIDEO);
  
    if(this.createWindow()){
      println( "Window could not be created! SDL_Error: $window");
    }else{
      //Get window surface
      screenSurface = SDL_GetWindowSurface( window );

      // Build the 2 colors for our surface
      this.buildColors();

      renderer = SDL_CreateRenderer(window, -1, SDL_RENDERER_ACCELERATED);

      //Fill the surface white
      // SDL_FillRect( screenSurface, null, SDL_MapRGB( screenSurface!!.pointed.format, 0xFF, 0xFF, 0xFF ) );
      
      //Update the surface
      // SDL_UpdateWindowSurface( window );

      //Wait two seconds
      SDL_Delay( 10000 );
    }
  }

  fun buildColors() {
    cWhite = SDL_MapRGB(screenSurface!!.pointed.format, 255, 255, 255);
    cBlack = SDL_MapRGB(screenSurface!!.pointed.format, 0, 0, 0);
  }

  fun cleanRenderer(){
    // delete[] pixels;
    SDL_DestroyTexture(texture);
    SDL_DestroyRenderer(renderer);
  }

  fun createTexture() {
        // texture = SDL_CreateTexture(renderer, SDL_PIXELFORMAT_ARGB8888, SDL_TEXTUREACCESS_STATIC, 640, 480);
  }

  fun createWindow() : Boolean {
    //Create window
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