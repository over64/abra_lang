declare noalias i8* @malloc(i32)

;GLAPI void GLAPIENTRY glEnable( GLenum cap );
declare void @glEnable(i32)

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

; #Shaders

; GLAPI GLuint APIENTRY glCreateShader (GLenum type)
declare i32 @glCreateShader(i32)

; GLAPI void APIENTRY glShaderSource (GLuint shader, GLsizei count, const GLchar *const*string, const GLint *length);
declare void @glShaderSource(i32, i32, i8**, i32*)

; GLAPI void APIENTRY glCompileShader (GLuint shader);
declare void @glCompileShader(i32)

; GLAPI void APIENTRY glGetShaderiv (GLuint shader, GLenum pname, GLint *params);
declare void @glGetShaderiv(i32, i32, i32*)

; GLAPI void APIENTRY glGetShaderInfoLog (GLuint shader, GLsizei bufSize, GLsizei *length, GLchar *infoLog);
declare void @glGetShaderInfoLog(i32, i32, i32*, i8*)

; GLAPI GLuint APIENTRY glCreateProgram (void);
declare i32 @glCreateProgram()

; GLAPI void APIENTRY glAttachShader (GLuint program, GLuint shader);
declare void @glAttachShader(i32, i32)

; GLAPI void APIENTRY glLinkProgram (GLuint program);
declare void @glLinkProgram(i32)

; GLAPI void APIENTRY glGetProgramiv (GLuint program, GLenum pname, GLint *params);
declare void @glGetProgramiv(i32, i32, i32*)

; GLAPI void APIENTRY glGetProgramInfoLog (GLuint program, GLsizei bufSize, GLsizei *length, GLchar *infoLog);
declare void @glGetProgramInfoLog(i32, i32, i32*, i8*)

; GLAPI void APIENTRY glUseProgram (GLuint program);
declare void @glUseProgram(i32)

; # Uniforms & Attribs

; GLint glGetAttribLocation(GLuint program, const GLchar *name)
declare i32 @glGetAttribLocation(i32, i8*)

; GLAPI GLint APIENTRY glGetUniformLocation (GLuint program, const GLchar *name);
declare i32 @glGetUniformLocation(i32, i8*)

; GLAPI void APIENTRY glUniformMatrix4fv (GLint location, GLsizei count, GLboolean transpose, const GLfloat *value);
declare void @glUniformMatrix4fv(i32, i32, i8, %struct.lib.kazmath.Mat4*)

; GLAPI void APIENTRY glUniform1i (GLint location, GLint v0);
declare void @glUniform1i(i32, i32)

; # Textures

; GLAPI void GLAPIENTRY glGenTextures( GLsizei n, GLuint *textures );
declare void @glGenTextures(i32, i32*)

; GLAPI void APIENTRY glActiveTexture (GLenum texture);
declare void @glActiveTexture(i32)

;GLAPI void GLAPIENTRY glBindTexture( GLenum target, GLuint texture );
declare void @glBindTexture(i32, i32)

; TODO
; GLAPI void GLAPIENTRY glTexImage2D( GLenum target, GLint level,
;                                     GLint internalFormat,
;                                     GLsizei width, GLsizei height,
;                                     GLint border, GLenum format, GLenum type,
;                                     const GLvoid *pixels );
; declare void @glTexImage2D(i32, i32, i32, i32, i32, i32, i32, i32, i8*)