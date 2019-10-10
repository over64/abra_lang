declare i8* @malloc(i64)
declare i8* @realloc(i8*, i64)
declare void @free(i8*)

define i8*  @defRcAlloc(i64 %size) {
    %realSize = add nsw i64 %size, 8
    %block = call i8* @malloc(i64 %realSize)
    %1 = bitcast i8* %block to i64*
    store i64 1, i64* %1
    %ptr = getelementptr i8, i8* %block, i64 8
    ret i8* %ptr
}

define i8*  @defRcRealloc(i8* %old, i64 %newSize) {
    %new = call i8* @realloc(i8* %old, i64 %newSize)
    ret i8* %new
}

define void @defRcInc(i8* %obj) {
    %1 = bitcast i8* %obj to i64*
    %2 = getelementptr i64, i64* %1, i64 -1
    %rc = load i64, i64* %2
    %newRc = add nsw i64 %rc, 1
    store i64 %newRc, i64* %2
    ret void
}
define void @defRcRelease(i8* %obj, void (i8*)* %releaseFn) {
    %1 = getelementptr i8, i8* %obj, i64 -8
    %2 = bitcast i8* %1 to i64*
    %3 = getelementptr i64, i64* %2, i64 0
    %rc = load i64, i64* %3
    %4 = icmp eq i64 %rc, 1

    br i1 %4, label %free, label %store
  free:
    call void %releaseFn(i8* %obj)
    call void @free(i8* %1)
    ret void
  store:
    %newRc = add nsw i64 %rc, -1
    store i64 %newRc, i64* %3
    ret void
}
@rcAlloc = thread_local(initialexec) global i8* (i64)* @defRcAlloc
@rcRealloc = thread_local(initialexec) global i8* (i8*, i64)* @defRcRealloc
@rcInc = thread_local(initialexec) global void (i8*)* @defRcInc
@rcRelease = thread_local(initialexec) global void (i8*, void (i8*)*)* @defRcRelease

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
;
;
