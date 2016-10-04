declare i32 @SDL_Init(i32)
; SDL_Window * SDLCALL SDL_CreateWindow(const char *title, int x, int y, int w, int h, Uint32 flags)
declare i8* @SDL_CreateWindow(i8*, i32, i32, i32, i32, i32)

; int SDLCALL SDL_PollEvent(SDL_Event * event)
declare i32 @SDL_PollEvent(i8*)

; extern DECLSPEC SDL_GLContext SDLCALL SDL_GL_CreateContext(SDL_Window * window)
declare i8* @SDL_GL_CreateContext(i8*)

; extern DECLSPEC void SDLCALL SDL_GL_SwapWindow(SDL_Window * window)
declare void @SDL_GL_SwapWindow(i8*)
