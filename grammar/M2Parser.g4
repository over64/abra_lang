parser grammar M2Parser;
options {
    tokenVocab=M2Lexer;
}

literal           : IntLiteral
                  | FloatLiteral
                  | BooleanLiteral
                  | StringLiteral
                  | Id
                  | 'self'
                  ;

realdId: Id | 'self' ;

expression : literal #exprLiteral
           | '(' expression ')' #exprParen
           | tuple #exprTuple
           | expression '.' op=(Id | '*' | '/' | '+' | '-' | '>' | '<' | '<=' | '>=' | '==' | '!=' | '||' | '&&') tuple #exprSelfCall
           | expression '.' op=Id #exprProp
           | expression tuple #exprApply
           | block #exprBlock
           | lambdaBlock #exprLambda
           | op='!' expression #exprUnaryCall
           | expression op=('*' | '/') expression #exprInfixCall
           | expression op=('+' | '-' | Id) expression #exprInfixCall
           | expression op=('>' | '<' | '<=' | '>=') expression #exprInfixCall
           | expression op=('==' | '!=') expression #exprInfixCall
           | expression op=('||' | '&&') expression #exprInfixCall
           | 'if' NL* cond=expression NL* (then_block=block | ('then' NL* then_stat=if_stat)) (NL* 'else' NL* (else_block=block | else_stat=if_stat))? #exprIfElse
           | 'while' NL* cond=expression NL* then_block=block #exprWhile
           ;

if_stat : expression | store ;

store : realdId ('.' realdId)* tuple? '=' expression ;

fnArg: ('self' | Id) (':' typeHint)? ;
block : '{' (fnArg (',' fnArg)* '->')? NL* blockBody* NL* '}' ;
blockBody : (variable | store | expression) ';'? NL* ;
lambdaBlock : '\\' (fnArg (',' fnArg)*)? '->' NL* (expression | store) ;

tuple : '(' (expression (',' NL* expression)*)? ')' ;

scalarTypeHint : (modVar=Id '.')? typeName=Id ;
fnTypeHintField: ('self' | Id) ':' typeHint ;
fnTypeHint : '(' (fnTypeHintField (',' fnTypeHintField)*)? ')' '->' typeHint ;

typeHint : scalarTypeHint
         | fnTypeHint
         ;

variable : valVar=('val' | 'var') NL* Id ( ':' typeHint)? NL* '=' NL* expression ;

scalarType : 'type' Id '=' LlLiteral ;

typeField : 'self'? ('self' | Id) ':' typeHint ;

factorType : 'type' Id '=' '(' NL* typeField (',' NL* typeField)* NL*')' ;

type : scalarType
     | factorType
     ;

function : 'def' name=('self' | Id | '!' | '*' | '/' | '+' | '-' | '>' | '<' | '<=' | '>=' | '==' | '!=' | '||' | '&&')
    (':' fnTypeHint)? '=' NL* (lambdaBlock | (block ':' typeHint) | LlLiteral | expression) ;

import_ : 'import' pkgName+=Id ('.' pkgName+=Id)* ('with' tid+=Id (',' tid+=Id)*)?;

level1: type | function;
module: NL* (import_ (NL+ import_)* NL*)? (level1 (NL+ level1)* NL*)? ;