// Define a grammar called Hello
grammar Hello;

valDef : T_KW_VAL NL* name=VAR_IDENT NL* ':' NL* ttype=TYPE_IDENT NL* '=' NL* init=expr;
varDef : T_KW_VAR NL* name=VAR_IDENT NL* ':' NL* ttype=TYPE_IDENT NL* '=' NL* init=expr;
fnArg : name=VAR_IDENT NL* ':' NL* ttype=TYPE_IDENT;
fnArgs : first=fnArg NL* (',' NL* rest+=fnArg)* NL* '->' ;
llvmFnDef : T_KW_DEF name=(VAR_IDENT | '+' | '-' | '*' | '/' | '::' | '==') NL* '=' NL* 'llvm' NL* '{' NL* args=fnArgs? (body+=~( '{' | '}' ))+ '}' (NL* ':' ret=TYPE_IDENT)? ;
abraFnDef : T_KW_DEF NL* name=(VAR_IDENT | '+' | '-' | '*' | '/' | '::' | '==') NL* '=' NL* '{' NL* args=fnArgs? NL* body=fnBodyDef? NL* '}' (NL* ':' ret=TYPE_IDENT)? ;
fnDef : abra=abraFnDef | llvm=llvmFnDef ;

fnBodyDef : (vals+=valDef | vars+=varDef | expessions+=expr | NL)+;

lang : (types+=typeDef | NL | functions+=fnDef )* ;

structParamDef : name=VAR_IDENT ':' ttype=TYPE_IDENT ;
llvmTypeDef : T_KW_TYPE name=TYPE_IDENT NL* '=' NL* 'llvm' NL* '{' (body+=~('{' | '}'))+ '}' ;
abraTypeDef : T_KW_TYPE name=TYPE_IDENT '=' '(' first=structParamDef (',' rest+=structParamDef)* ')' ;
typeDef : llvm=llvmTypeDef | abra=abraTypeDef ;

access : id1=(VAR_IDENT | UINT | '+' | '-' | '*' | '/' | '::' | '==') ('.' accList+=(VAR_IDENT | '+' | '-' | '*' | '/' | '::' | '=='))* ;
expr : UINT #atomExp
    | acc=access '(' (first=expr (',' rest+=expr)* )? ')' #directCallExp
    | VAR_IDENT #varIdentExp
    | access #accessExp
    | '(' cont=expr ')' ('.' accList+=(VAR_IDENT | '+' | '-' | '*' | '/' | '::' | '=='))+ ('(' (first=expr (',' rest+=expr)* )? ')')? #parenExpCall
    | '(' cont=expr ')' #simpleParen
    | left=expr op=('*' | '/') right=expr #priority1Expr
    | left=expr op=('+' | '-' | '::' | VAR_IDENT) right=expr #priority2Expr
    | left=expr op='==' right=expr #priority3Expr
    ;

// a.b.c()
// c()
// +(1,1)
// (1+1).+(1)
// (1 + 1).shl

// function call
// (a + b + c * 2.+(3) + foo.get()) * 3
// a.+(1) -> fnCall(+, a, 1)
// a + 1 -> fnCall(+, a, 1)
// +(a, 1) -> fnCall(+, a, 1)
// field access

// 1) .
// 2) * /
// 3)
// last) ==

T_KW_VAL : 'val';
T_KW_VAR : 'var';
T_KW_DEF : 'def';
T_KW_TYPE : 'type';
T_PERCENT : '%' ;
T_BL : '[' ;
T_AAA : ']' ;
T_Q1 : '"' ;
T_BS_XXX : '\\' ;
T_BS_XXXX : '@' ;
//T_EQ : '=' ;
//T_CB_OPEN : '{' ;
//T_CB_CLOSE : '}' ;
//T_EQEQ : '==' ;
//T_PLUS : '+' ;
//T_MINUS : '-' ;
//T_MUL : '*' ;
//T_DIV : '/' ;
//T_NOT : '!' ;
//T_CONS : '::' ;

BIN_OP : '+' | '-' | '*' | '/' | '::' | '==';
VAR_IDENT : [_a-z]+[_a-zA-Z0-9]* ;
TYPE_IDENT : [A-Z]+[a-zA-Z0-9]* ;
// LLVM_BLOCK : 'llvm' ('\t' | '\n' | '\r' | ' ')* '{' (~'{' | '}')+ '}' ;
WS : [ \t]+ -> skip ; // skip spaces, tabs
NL : '\r'? '\n' ;
UINT : [0-9]+ ;