package grammar;
%%
%public
%class EvaLexer
%type long
%char
%scanerror LexicalException
%unicode
%state IDENT
%state STRING
%state COMMENT
%state NATIVE_IDENT
%state NATIVE

%{
  private long lastIdent        = 0;
  private long identBegin       = 0;
  private long commentBegin     = 0;
  private long strBegin         = 0;
  private long nativeBegin      = 0;
  private long nativeIdentBegin = 0;

  public enum Lex {
    IDENT, COMMENT,
    DOT, COMMA, CON, V_LINE, EQ, R_ARROW,
    L_PAREN, R_PAREN, L_BRACKET, R_BRACKET,
    LOGIC_NOT, LOGIC_OR, LOGIC_AND,
    CMP_MORE, CMP_LESS, CMP_MORE_EQ, CMP_LESS_EQ, CMP_EQEQ, CMP_NOTEQ,
    BIN_MINUS, BIN_PLUS, BIN_MUL, BIN_DIV,
    IMPORT, TYPE, DEF, WITH,
    IF, DO, ELSE, IS, UNLESS,
    WHILE, BREAK, CONTINUE, RETURN,
    ID, TYPE_ID,
    LIT_NONE, LIT_BOOL, LIT_INT, LIT_FLOAT, LIT_STRING,
    NATIVE
  }

  public static class LexicalException extends Exception {
    public LexicalException(String message) {
      super(message);
    }
  }

  // FIXME: IllegalCharException?

  public static class UnterminatedStringException extends LexicalException {
    public final long at;
    public UnterminatedStringException(long at) {
      super("Unterminated string literal at " + at);
      this.at = at;
    }
  }

  public static class Emitter {
      public static final long EOF = 0xFF;
      public static long encode(Lex token, long start, long len) {
        return (start << 32) | (len << 8) | token.ordinal();
      }

      public static Lex decodeLex(long encoded) {
        return Lex.values()[(int) (encoded & 0xFFL)];
      }

      public static int decodeStart(long encoded) {
        return (int) ((encoded & 0xFFFFFFFF00000000L) >> 32);
      }
      public static int decodeLen(long encoded) {
        return (int) ((encoded & 0x00000000FFFFFF00L) >> 8);
      }
  }

  private long emit(Lex token) {
      return Emitter.encode(token, this.yychar, this.zzMarkedPos - this.zzStartRead);
  }

  private long emitIdent() {
    lastIdent = this.yychar - identBegin;
    return Emitter.encode(Lex.IDENT, identBegin, this.yychar - identBegin);
  }

  private long emitString() {
    return Emitter.encode(Lex.LIT_STRING, strBegin, this.yychar - strBegin);
  }

  private long emitComment() {
      return Emitter.encode(Lex.COMMENT, commentBegin, this.yychar - commentBegin);
  }

  private long emitNative() {
//      System.out.println("emit native nb=" + nativeBegin + " len= " + (this.yychar - nativeBegin - 1));
    return Emitter.encode(Lex.NATIVE, nativeBegin, nativeIdentBegin - nativeBegin - 1);
  }

%}

NonZeroDigit = [1-9]
Digit        = [0-9]
HexDigit     = [0-9a-fA-F]
ExpPart      = [Ee] [\+-]? [0-9]+
NL           = \r | \n | \r\n
WS           = [ \t\f]
Id           = [\p{Ll}]+ ([\p{Lu}\p{Ll}] | [0-9])*
TypeId       = [\p{Lu}]+ ([\p{Lu}\p{Ll}] | [0-9])*

%%
<YYINITIAL> {
  "."        { return emit(Lex.DOT);     }
  ","        { return emit(Lex.COMMA);   }
  ":"        { return emit(Lex.CON);     }
  "|"        { return emit(Lex.V_LINE);  }
  "="        { return emit(Lex.EQ);      }
  "->"       { return emit(Lex.R_ARROW); }

  "("        { return emit(Lex.L_PAREN);   }
  ")"        { return emit(Lex.R_PAREN);   }
  "["        { return emit(Lex.L_BRACKET); }
  "]"        { return emit(Lex.R_BRACKET); }

  "!"        { return emit(Lex.LOGIC_NOT);  }
  "||"       { return emit(Lex.LOGIC_OR);   }
  "&&"       { return emit(Lex.LOGIC_AND);  }

  ">"        { return emit(Lex.CMP_MORE);    }
  "<"        { return emit(Lex.CMP_LESS);    }
  ">="       { return emit(Lex.CMP_MORE_EQ); }
  "<="       { return emit(Lex.CMP_LESS_EQ); }
  "=="       { return emit(Lex.CMP_EQEQ);    }
  "!="       { return emit(Lex.CMP_NOTEQ);   }

  "+"        { return emit(Lex.BIN_PLUS);  }
  "-"        { return emit(Lex.BIN_MINUS); }
  "*"        { return emit(Lex.BIN_MUL);   }
  "/"        { return emit(Lex.BIN_DIV);   }

  "import"   { return emit(Lex.IMPORT); }
  "type"     { return emit(Lex.TYPE);   }
  "def"      { return emit(Lex.DEF);    }
  "with"     { return emit(Lex.WITH);   }

  "if"       { return emit(Lex.IF);     }
  "do"       { return emit(Lex.DO);     }
  "else"     { return emit(Lex.ELSE);   }
  "is"       { return emit(Lex.IS);     }
  "unless"   { return emit(Lex.UNLESS); }

  "while"    { return emit(Lex.WHILE);    }
  "break"    { return emit(Lex.BREAK);    }
  "continue" { return emit(Lex.CONTINUE); }
  "return"   { return emit(Lex.RETURN);   }

  "none"      { return emit(Lex.LIT_NONE);   }
  "true"      { return emit(Lex.LIT_BOOL); }
  "false"     { return emit(Lex.LIT_BOOL);   }
  -? 0 | ({NonZeroDigit} {Digit}*)   { return emit(Lex.LIT_INT);   }
  0 [xX] {HexDigit}+                 { return emit(Lex.LIT_INT);   }
  -? (({NonZeroDigit} {Digit}) | ({Digit}+ \. {Digit}+)) {ExpPart}? { return emit(Lex.LIT_FLOAT); }

  {NL}       { identBegin   = yychar + 1; yybegin(IDENT);   }
  '          { strBegin     = yychar + 1; yybegin(STRING);  }
  "native"   { nativeBegin  = yychar + 6; yybegin(NATIVE);  }
  "--"       { commentBegin = yychar + 2; yybegin(COMMENT); }

  {Id}       { return emit(Lex.ID);      }
  {TypeId}   { return emit(Lex.TYPE_ID); }
  {WS}       { /* ignore */ }
}

<IDENT> {
  {WS}      { /* consume */ }
  [^]       { yybegin(YYINITIAL); yypushback(1); return emitIdent(); }
  // eof ?
}

<STRING> {
  '         { yybegin(YYINITIAL); return emitString(); }
  [^]       { /* consume */ }
  <<EOF>>   { throw new UnterminatedStringException(yychar); }
}

<COMMENT> {
  {NL}      { identBegin = yychar + 1; yybegin(IDENT); return emitComment(); }
  [^]       { /* consume */}
  // eof ?
}

<NATIVE_IDENT> {
  {WS}      { /* consume */ }
  {NL}      { nativeIdentBegin = yychar + 1; }
  [^]       {
    long newIdentSize = yychar - nativeIdentBegin;
//    System.out.println("new ident size =" + newIdentSize);
    if(newIdentSize <= lastIdent) {
        identBegin = nativeIdentBegin;
        yypushback(1);
        yybegin(IDENT);
        return emitNative();
    } else yybegin(NATIVE);
  }
}

<NATIVE> {
  {NL}      { nativeIdentBegin = yychar + 1; yybegin(NATIVE_IDENT);  }
  [^]       { /* consume */ }
  <<EOF>>   { yybegin(YYINITIAL); return Emitter.encode(Lex.NATIVE, nativeBegin, this.yychar - nativeBegin); }
}

<<EOF>> { return Emitter.EOF; }
[^]     { throw new LexicalException("Illegal character <" + yytext() + "> at " + yychar); }