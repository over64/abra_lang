parser grammar M2Parser;
options {
    tokenVocab=M2Lexer;
}

literal: IntLiteral
       | HexLiteral
       | FloatLiteral
       | BooleanLiteral
       | StringLiteral
       ;

id: VarId | 'self' ;

expression: literal #exprLiteral
          | id #exprId
          | '(' expression ')' #exprParen
          | tuple #exprTuple
          | '&' expression #exprRef
          | '*' expression #exprDeref
          | expression '.' op=(VarId | '*' | '/' | '+' | '-' | '>' | '<' | '<=' | '>=' | '==' | '!=') tuple #exprSelfCall
          | expression '.' op=VarId #exprProp
          | expression tuple #exprCall
          | lambda #exprLambda
          | hLambda #exprHLambda
          | op='!' expression #exprUnaryCall
          | expression op=('*' | '/') expression #exprInfixCall
          | expression op=('+' | '-' | VarId) expression #exprInfixCall
          | expression op=('>' | '<' | '<=' | '>=') expression #exprInfixCall
          | expression op=('==' | '!=') expression #exprInfixCall
          | expression op=('||' | '&&') expression #exprInfixCall
          | 'if' NL* cond=expression NL* ('then' NL* then_stat=if_stat) (NL* 'else' NL* else_stat=if_stat)? #exprIfElse
          | 'match' NL* expr=expression NL* (matchCase NL*)+ #exprMatch
          ;

if_stat: ('{' blockBody* '}') | expression | store;
tuple: '(' (expression (',' NL* expression)*)? ')' ;


fieldTh: id ':' typeHint ;
scalarTh: ptr='*'? typeName=TypeId ('[' tparams+=TypeId (',' tparams+=TypeId)* ']')?;
fnTh: '(' (args+=typeHint (',' args+=typeHint)*)? ')' '->' ret=typeHint ;
structTh: '(' typeHint (',' typeHint)* ')' ;
nonUnionTh: scalarTh | fnTh | structTh ;
unionTh: nonUnionTh ('|' nonUnionTh)+ ;

typeHint: scalarTh
        | fnTh
        | structTh
        | unionTh
        ;

matchDash: '_' ;
bindVar: id ;
matchId: MatchId | '$self' ;
matchBracketsExpr: '${' expression '}' ;
matchExpression: literal | matchId | matchBracketsExpr ;
destruct: (id '=')? scalarTh '(' (matchOver (',' NL* matchOver)*)? ')' ;
matchType: VarId NL* ':' NL* scalarTh ;
matchOver: matchDash | bindVar | matchExpression | destruct | matchType ;
matchCase: 'of' NL* matchOver NL* ('if' NL* cond=expression NL*)? '->' NL* onMatch=if_stat ;

variable: valVar=('val' | 'var') NL* VarId ( ':' typeHint)? NL* '=' NL* expression ;
store: id ('.' VarId)* tuple? '=' expression ;
while_stat: 'while' NL* cond=expression NL* '{' NL* blockBody* '}' ;

fnArg: id (':' typeHint)? ;
lambda: '{' (fnArg (',' fnArg)* '->')? NL* blockBody* NL* '}' ;
blockBody: (variable | store | while_stat | expression) ';'? NL* ;
hLambda: '\\' (fnArg (',' fnArg)*)? '->' NL* blockBody ;

scalarType: 'type' TypeId '=' lltype ;
typeField: 'self'? VarId ':' typeHint ;
structType: 'type' name=TypeId ('[' tparams+=TypeId (',' tparams+=TypeId)* ']')? '='  '(' NL* typeField (',' NL* typeField)* NL*')' ;
unionType: 'type' name=TypeId ('[' tparams+=TypeId (',' tparams+=TypeId)* ']')? '=' scalarTh ('|' scalarTh)+ ;

type: scalarType
    | structType
    | unionType
    ;

function: 'def' name=(VarId | '!' | '*' | '/' | '+' | '-' | '>' | '<' | '<=' | '>=' | '==' | '!=')
    ('[' tparams+=TypeId (',' tparams+=TypeId)* ']')?
    '=' NL* (hLambda | (lambda ':' typeHint) | (lldef ':' typeHint) | expression) ;

level1: type | function;
module: NL* (level1 (NL+ level1)* NL*)? ;

lltype: LlBegin IrInline LlEnd;
lldef: LlDef (fnArg (',' fnArg)*)? '->' IrInline LlEnd ;