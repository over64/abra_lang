lexer grammar M2LexerForIDE;
@members {
    boolean start_llvm_ws = false;
}

MINUS : '-' ;
PLUS : '+' ;
MUL : '*' ;
DIV : '/' ;
EXCL : '!' ;
DOT : '.' ;
RB : ')' ;
LB : '(' ;
COMMA : ',' ;
MORE_ : '>' ;
MORE_EQ : '>=' ;
LESS : '<' ;
LESS_EQ : '<=' ;
EQ : '=' ;
EQEQ : '==' ;
NOTEQ : '!=' ;
SEMI : ';' ;
IF : 'if' ;
DO : 'do' ;
ELSE : 'else' ;
CBO : '{' ;
DOLLAR_CBO : '$(' ;
CBC : '}' ;
LOGIC_OR : '||' ;
LOGIC_AND : '&&' ;
WHILE : 'while';
VAL : 'val' ;
VAR : 'var' ;
CON : ':' ;
ARROW_RIGHT : '->' ;
TYPE : 'type' ;
BACK_SLASH : '\\' ;
SELF : 'self' ;
MATCH_SELF : '$self' ;
DEF : 'def' ;
IMPORT : 'import' ;
WITH : 'with' ;
MATCH: 'match';
OF: 'of';
RETURN: 'return';
REF: 'ref';
DASH: '_';
VERT_LINE: '|';
BRACKET_LEFT: '[';
BRACKET_RIGTH: ']';

LlBegin : 'llvm' { start_llvm_ws = true; } -> pushMode(llvm) ;

WS : [ \t]+ ;
NL : '\r'? '\n' ;
COMMENT : '#' ~[\r\n]* ;


IntLiteral   : '-'? ('0' | NonZeroDigit Digit*) ;
HexLiteral   : '0' [xX] HexDigit+ ;
FloatLiteral : '-'? (Digit+ '.' Digit+ ExponentPart? | Digit+ ExponentPart) ;
BooleanLiteral   :  'true' | 'false';

StringLiteral    : '\'' StringElement* '\'' ;
VarId  : [\p{Ll}]+ ([\p{Lu}\p{Ll}] | '_' | Digit)* ;
TypeId  : [\p{Lu}]+ ([\p{Lu}\p{Ll}] | '_' | Digit)* ;
MatchId: '$' VarId ;

mode llvm ;
LLVM_NL: '\r'? '\n' { start_llvm_ws = true; System.out.println("nl");  };
LLVM_WS: [ \t]+ { start_llvm_ws = true; System.out.println("ws");  };
IrLine: ~[ \t\r\n\\.]+ { start_llvm_ws = false; System.out.println("ir_line"); };
LL_End: { start_llvm_ws ==  true }? '.' { popMode(); System.out.println("ir_end"); };
LL_Dot: '.' { System.out.println("ir_dot"); } ;
//IrLiteral: (LLVM_NL | LLVM_WS | IrLine | LL_Dot)* LL_End ;

fragment StringElement    :  Char
                          |  CharEscapeSeq
                          |  NL ;
fragment Char             :  ~[\\'\n];
fragment CharEscapeSeq    : '\\' ('b' | 't' | 'n' | 'f' | 'r' | '\'' | '\\') ;
fragment ExponentPart     :  ('E' | 'e') ('+' | '-')? Digit+ ;
fragment Digit            :  '0' | NonZeroDigit ;
fragment NonZeroDigit     :  '1' .. '9' ;
fragment HexDigit         :   [0-9a-fA-F] ;