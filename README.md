### test_lang:
Functional / system language with automatic memory management without GC
#### memory management:
Use hybrid memory system:
  - every variable is 'GC root' (for example: val a: A)
  - for each variable allocate memory page
  - child value store in the same memory page ( for example: a.field = B())
  - if variable is out of scope - free page with whole object graph
  
#### module system:
mInt.mod
```scala
  export deftype Int = @llvm.i32
  
  export def + (self: Int, other: Int) = @llvm {
    %1 = add nsw i32 %other, %self
    ret i32 %1
  }
```

mMain.mod
```scala
  require mInt.*
  
  def main() = {
    val a: Int = 2;
    val b: Int = 2;
    a + b;
  }
```
