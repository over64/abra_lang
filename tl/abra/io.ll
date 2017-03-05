declare i32 @puts(i8* nocapture readonly)
; ssize_t getline(char **lineptr, size_t *n, FILE *stream);
declare i64 @getline(i8**, i64*, i8*)

; int open(const char *pathname, int flags);
declare i8* @fopen(i8*, i8*)

;int close(int fd);
declare i32 @fclose(i8*)

; int strncmp(const char *s1, const char *s2, size_t n);
declare i32 @strncmp(i8*, i8*, i64)

; size_t strlen(const char *s);
declare i64 @strlen(i8*)

; int sscanf(const char *str, const char *format, ...);
declare i32 @sscanf(i8*, i8*, ...)

; int printf(const char *format, ...);
declare i32 @printf(i8*, ...)

; int scanf(const char *format, ...);
declare i32 @scanf(i8*, ...)


@.sscanfFloat = private constant [5 x i8] c"%f%n\00", align 1
@.sscanfInt = private constant [5 x i8] c"%d%n\00", align 1
@.scanfInt = private constant [3 x i8] c"%d\00", align 1
@.printfFloat = private constant [3 x i8] c"%f\00", align 1
@.printfInt = private constant [3 x i8] c"%d\00", align 1

