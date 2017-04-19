lexer grammar M2Lexer ;

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
THEN : 'then' ;
ELSE : 'else' ;
CBO : '{' ;
DOLLAR_CBO : '${' ;
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
DASH: '_';
VERT_LINE: '|';
BRACKET_LEFT: '[';
BRACKET_RIGTH: ']';
AMP: '&';

LlBegin : 'llvm' [ \t\r\n]* '{' -> pushMode(llvm);

WS : [ \t]+ -> skip ; // skip spaces, tabs
NL : '\r'? '\n' ;
COMMENT : '#' ~[\r\n]* -> skip ;

LlLiteral : LlBegin IrInline LlEnd;

IntLiteral   : '-'?  '0' | NonZeroDigit Digit* ;
HexLiteral   : '0' [xX] HexDigit+ ;
FloatLiteral : '-'? Digit+ '.' Digit+ ExponentPart? | Digit+ ExponentPart ;
BooleanLiteral   :  'true' | 'false';

StringLiteral    : '\'' StringElement* '\'' ;
Id  : ('a'..'z' | 'A'..'Z')+ ('a'..'z' | 'A'..'Z' | '_' | Digit)* ;
MatchId: '$' Id ;

mode llvm ;
IrInline : ~[{}]+ ;
LlEnd: CBC -> popMode;

fragment StringElement    :  Char
                          |  CharEscapeSeq
                          |  NL ;
fragment Char             :  ~[\\'\n];
fragment CharEscapeSeq    : '\\' ('b' | 't' | 'n' | 'f' | 'r' | '\'' | '\\') ;
fragment ExponentPart     :  ('E' | 'e') ('+' | '-')? Digit+ ;
fragment Digit            :  '0' | NonZeroDigit ;
fragment NonZeroDigit     :  '1' .. '9' ;
fragment HexDigit         :   [0-9a-fA-F] ;