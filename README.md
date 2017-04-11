![ABRA](https://raw.githubusercontent.com/over64/abra_lang/master/abra.png)
### ABRA lang:
Research platform for design language with new memory management and modularity concepts
#### Iteration 1 results
  - language built on LLVM
  - basic language constructions
    - type declarations
    - variable declarations
    - control flow
    - rich call syntax / expressions / operator priority / natural operator overloading
  - stack-based closures
  - type system: ADT: sum, addition; fn pointers
  - pattern matching
  - module system
  - FFI to C via LLVM IR
  
  see docs in abra_v1/README.md
#### Iteration 2 plans
 - c-like memory layout: pointers
 - memory management
 - allocators API: stack-based in priority
 - type parametrization (generic programming)
 - better closures
 - simpler codegen
 
 see docs in abra_v2/README.md
