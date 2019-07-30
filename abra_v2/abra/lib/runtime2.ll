declare i8* @malloc(i64)
declare i8* @realloc(i8*, i64)
declare void @free(i8*)

define i8*  @defEvaAlloc(i64 %size) {
    %realSize = add nsw i64 %size, 8
    %block = call i8* @malloc(i64 %realSize)
    %1 = bitcast i8* %block to i64*
    store i64 1, i64* %1
    %ptr = getelementptr i8, i8* %block, i64 8
    ret i8* %ptr
}

define void @defEvaFree(i8* %obj) {
    %1 = getelementptr i8, i8* %obj, i64 -8
    call void @free(i8* %1)
    ret void
}

define void @defEvaInc(i8* %obj) {
    %1 = bitcast i8* %obj to i64*
    %2 = getelementptr i64, i64* %1, i64 -1
    %rc = load i64, i64* %2
    %newRc = add nsw i64 %rc, 1
    store i64 %newRc, i64* %2
    ret void
}

define i64 @defEvaDec(i8* %obj) {
    %1 = bitcast i8* %obj to i64*
    %rcPtr = getelementptr i64, i64* %1, i64 -1
    %rc = load i64, i64* %rcPtr
    %2 = icmp eq i64 %rc, 1
    br i1 %2, label %free, label %store
  free:
    ret i64 1
  store:
    %newRc = sub nsw i64 %rc, 1
    store i64 %newRc, i64* %rcPtr
    ret i64 %newRc
}

@evaAlloc = thread_local(initialexec) global i8* (i64)* @defEvaAlloc
@evaFree  = thread_local(initialexec) global void (i8*)* @defEvaFree
@evaInc   = thread_local(initialexec) global void (i8*)* @defEvaInc
@evaDec   = thread_local(initialexec) global i64 (i8*)* @defEvaDec

; evaAlloc - @alias to malloc
; evaFree - @alias for free
; evaInc = obj: Ptr do
;   obj.rc += 1
;
; def evaDec = obj: Ptr do
;   if obj.rc == 1 do 0
;   else
;     obj.rc -= 1
;     obj.rc
;
; scenario:
; x = evaAlloc()
; evaInc(x)
; if(evaDec(y.x) == 0)
;   $dest(y.x)
; y.x = x
