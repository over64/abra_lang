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

literalSeq : literal ('.' literal)* ;

expression : literal #exprLiteral
           | literalSeq #expLiteralSeq
           | '(' expression ')' #exprParen
           | tuple #exprTuple
           | expression tuple #exprApply
           | expression '.' op=(Id | '*' | '/' | '+' | '-' | '>' | '<' | '<=' | '>=' | '==' | '!=' | '||' | '&&' ) tuple #exprDirectCall
           | block #exprBlock
           | lambdaBlock #exprLambda
           | op=('!' | '-') expression #exprUnaryCall
           | expression op=('*' | '/') expression #exprInfixCall
           | expression op=('+' | '-' | Id) expression #exprInfixCall
           | expression op=('>' | '<' | '<=' | '>=') expression #exprInfixCall
           | expression op=('==' | '!=') expression #exprInfixCall
           | expression op=('||' | '&&') expression #exprInfixCall
           | 'if' NL* cond=expression NL* (('then' NL* then_expr=expression) | then_block=block) (NL* 'else' NL* (else_expr=expression | else_block=block))? #exprIfElse
           | 'while' NL* cond=expression NL* (('then' NL* then_expr=expression) | then_block=block) #exprWhile
           ;

store : literalSeq '=' expression ;

fnArg: ('self' | Id) (':' typeHint)? ;
block : '{' (fnArg (',' fnArg)* '->')? NL* (blockBody (NL | ';')+)* '}' ;
blockBody : (variable | store | expression)  ;
lambdaBlock : '\\' (fnArg (',' fnArg)*)? '->' NL* expression ;

tuple : '(' (expression (',' expression)*)? ')' ;

scalarTypeHint : Id ;
fnTypeHintField: ('self' | Id) ':' typeHint ;
fnTypeHint : '(' (fnTypeHintField (',' fnTypeHintField)*)? ')' '->' typeHint ;

typeHint : scalarTypeHint
         | fnTypeHint
         ;

variable : valVar=('val' | 'var') NL* Id ( ':' typeHint)? NL* '=' NL* expression ;

scalarType : 'type' Id '=' LlLiteral ;

typeField : 'self'? ('self' | Id) ':' typeHint ;

factorType : 'type' Id '=' '(' typeField (',' typeField)* ')' ;

type : scalarType
     | factorType
     ;

function : 'def' name=('self' | Id | '*' | '/' | '+' | '-' | '>' | '<' | '<=' | '>=' | '==' | '!=' | '||' | '&&')
    (':' fnTypeHint)? '=' (lambdaBlock | (block ':' typeHint) | LlLiteral) ;

level1: type | function;
module: NL* (level1 (NL+ level1)* NL*)? ;