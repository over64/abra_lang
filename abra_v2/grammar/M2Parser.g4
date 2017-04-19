parser grammar M2Parser;
options {
    tokenVocab=M2Lexer;
}

literal           : IntLiteral
                  | HexLiteral
                  | FloatLiteral
                  | BooleanLiteral
                  | StringLiteral
                  ;

id: Id | 'self' ;

expression : literal #exprLiteral
           | id #exprId
           | '(' expression ')' #exprParen
           | tuple #exprTuple
           | '&' expression #exprRef
           | '*' expression #exprDeref
           | expression '.' op=(Id | '*' | '/' | '+' | '-' | '>' | '<' | '<=' | '>=' | '==' | '!=' | '||' | '&&') tuple #exprSelfCall
           | expression '.' op=Id #exprProp
           | expression tuple #exprCall
           | block #exprBlock
           | lambdaBlock #exprLambda
           | op='!' expression #exprUnaryCall
           | expression op=('*' | '/') expression #exprInfixCall
           | expression op=('+' | '-' | Id) expression #exprInfixCall
           | expression op=('>' | '<' | '<=' | '>=') expression #exprInfixCall
           | expression op=('==' | '!=') expression #exprInfixCall
           | expression op=('||' | '&&') expression #exprInfixCall
           | 'if' NL* cond=expression NL* ('then' NL* then_stat=if_stat) (NL* 'else' NL* else_stat=if_stat)? #exprIfElse
           | 'while' NL* cond=expression NL* then_block=block #exprWhile
           | 'match' NL* expr=expression NL* (matchCase NL*)+ #exprMatch
           ;

matchDash: '_' ;
matchId: MatchId | '$self' ;
matchType: id NL* ':' NL* scalarTh ;
matchBracketsExpr: '${' expression '}' ;
matchExpression: literal | matchId | matchBracketsExpr ;
destruct: (id '=')? scalarTh '(' (matchOver (',' NL* matchOver)*)? ')' ;
bindVar: id ;
matchOver: matchDash | bindVar | matchExpression | destruct | matchType ;
matchCase: 'of' NL* matchOver NL* ('if' NL* cond=expression NL*)? '->' NL* onMatch=expression ;

if_stat : expression | store ;

store : id ('.' id)* tuple? '=' expression ;

fnArg: ('self' | Id) (':' typeHint)? ;
block : '{' (fnArg (',' fnArg)* '->')? NL* blockBody* NL* '}' ;
blockBody : (variable | store | expression) ';'? NL* ;
lambdaBlock : '\\' (fnArg (',' fnArg)*)? '->' NL* (expression | store) ;

tuple : '(' (expression (',' NL* expression)*)? ')' ;

fieldTh: ('self' | Id) ':' typeHint ;
scalarTh : '*'? typeName=Id ('[' tparams+=Id (',' tparams+=Id)* ']')?;
fnTh : '(' (fieldTh (',' fieldTh)*)? ')' '->' typeHint ;
structTh: '(' typeHint (',' typeHint)* ')' ;
unionTh: scalarTh ('|' scalarTh)+ ;

typeHint : scalarTh
         | fnTh
         | structTh
         | unionTh
         ;

variable : valVar=('val' | 'var') NL* Id ( ':' typeHint)? NL* '=' NL* expression ;
typeProlog: 'type' Id ('[' tparams+=Id (',' tparams+=Id)* ']')? '=' ;
scalarType : typeProlog LlLiteral ;
typeField : 'self'? ('self' | Id) ':' typeHint ;
structType : typeProlog '(' NL* typeField (',' NL* typeField)* NL*')' ;
unionType: typeProlog scalarTh ('|' scalarTh)+ ;

type : scalarType
     | structType
     | unionType
     ;

function : 'def' name=('self' | Id | '!' | '*' | '/' | '+' | '-' | '>' | '<' | '<=' | '>=' | '==' | '!=' | '||' | '&&')
    (':' fnTh)? '=' NL* (lambdaBlock | (block ':' typeHint) | LlLiteral | expression) ;


level1: type | function;
module: NL* (level1 (NL+ level1)* NL*)? ;