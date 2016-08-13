### test_lang:
Research platform for design language with new memory management and modularity concepts
#### Status
  Basic C-like expression-based language with type inference based on value types (no reference types yet)
  - val / var
  - if-else expressions
  - while loops
  - functions. Rich function call syntax: infix calls, unary calls, apply calls, self calls, usual calls
  - natural operator overloading (no 'operators' in language syntax)
  - types: scalar and struct types (no arrays and ATD yet)
  - smooth integration with LLVM via scalar types and inline LLVM IR
  - local type inference
  - uniform declaration syntax

#### In progress in M2
  - command-line compiler
  - tuple initialization
  - function pointers (no closures yet)
  - simple FFI to C
  - refactor / tests

#### Hello, world
Yes, it is unicode, baby! If you have UTF8 locale...
```scala
  type Unit = llvm { void }
  type String = llvm { i8* }
  
  def print: (self: String) -> Unit = llvm  {
    %1 = call i32 @puts(i8* %self)
    ret void
  }
  
  def main = \ ->
    'こんにちは、世界!'.print
```
#### Uniform declaration sytax
  WHAT_TO_DECLARE NAME [: TYPE_HINT] = INIT_EXPRESSION
  
  There is only one rule you need to know!
  ```scala
    val a = 1
    var b: Double = 1.0
    
    def + = \self: Vec3, other: Vec3 ->
      Vec3(self.x + other.x, self.y + other.y, self.z + other.z)
      
    def >: (self: Int, other: Int) -> Boolean = llvm {
      %1 = icmp sgt i32 %self, %other
      ret i1 %1
    }
  ```
#### Types
  Predifined types by language specification:
  ```scala
    type Unit = llvm { void }
    type Boolean = llvm { i1 }
    type Int = llvm { i32 }
    type Float = llvm { float }
    type String = llvm { i8* }
  ```
  Scalar types - types direct-mapped to raw LLVM types. Any LLVM type can be used in language. Even SIMD!
  ```scala
    type Int = llvm { i32 }
    type SimdVec4f = llvm { <4 x float> }
  ```
  Struct types - composition of types (algebraic multiplication)
  ```scala
    type Float = llvm { float }
    type Vec3 = (x: Float, y: Float: z: Float)
  ```
#### Functions
  Function body can be defined in 3 styles
  1. llvm inline IR block (function type hint required)
  2. code block: function type hint optional, block type hint - required
  3. lambda-expression: function type-hint optional

  last function expression is return value
  
  ```scala
    # LLVM IR inline block
    def print: (self: String) -> Unit = llvm  {
      %1 = call i32 @puts(i8* %self)
      ret void
    }
    # Block
    def add = { x: Int, y: Int ->
     x + y
    }: Int
    
    def main = {
     0
    }: Int
    
    # Haskell-like lambdas
    def twice = \self: Int -> self + self
  ```

#### Function call
  Rich function call rules for eye-candy DSL
  ```scala
    def +: (self: Int, other: Int) -> Int = llvm {
      %1 = add nsw i32 %other, %self
      ret i32 %1
    }
    
    def twice = \self: Int -> self + self
    def apply = \self: Int -> self + 9000
    
    1 + 1 # infix call
    1.twice # self call
    +(1, 1) # usual call
    1() # apply call
  ```
#### If-else expressions
No parantheses, bro!
```scala
  val a = false
  # if-else is expression
  # if-then style for single-expression
  val b = if a then 1 else 2
  # braces style for multi-expression. last expression is result
  val c = if a {
    foo()
    1
  } else 2
```
#### Loops
for loop is not needed and was removed.
Need imperative programming?
```scala
  var a = 0
  while a < 255 {
    doSomethingLikeFather()
    a = a + 1
  }
```
  

