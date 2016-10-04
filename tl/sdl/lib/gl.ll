; GLAPI void GLAPIENTRY glClearColor( GLclampf red, GLclampf green, GLclampf blue, GLclampf alpha )
declare void @glClearColor(float, float, float)

; GLAPI void GLAPIENTRY glClear( GLbitfield mask )
declare void @glClear(i32)

; GLAPI void APIENTRY glGenBuffers (GLsizei n, GLuint *buffers)
declare void @glGenBuffers(i32, i32*)

; GLAPI void APIENTRY glGenVertexArrays (GLsizei n, GLuint *arrays)
declare void @glGenVertexArrays(i32, i32*)

; GLAPI void APIENTRY glBindBuffer (GLenum target, GLuint buffer)
declare void @glBindBuffer(i32, i32)

; GLAPI void APIENTRY glBindVertexArray (GLuint array)
declare void @glBindVertexArray(i32)

; GLAPI void APIENTRY glBufferData (GLenum target, GLsizeiptr size, const void *data, GLenum usage)
declare void @glBufferData(i32, i32, i8*, i32)

; GLAPI void APIENTRY glVertexAttribPointer (GLuint index, GLint size, GLenum type, GLboolean normalized, GLsizei stride, const void *pointer)
declare void @glVertexAttribPointer(i32, i32, i32, i8, i32, i8*)

; GLAPI void APIENTRY glEnableVertexAttribArray (GLuint index)
declare void @glEnableVertexAttribArray(i32)

; GLAPI void GLAPIENTRY glDrawArrays( GLenum mode, GLint first, GLsizei count )
declare void @glDrawArrays(i32, i32, i32)