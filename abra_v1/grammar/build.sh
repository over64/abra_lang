#!/usr/bin/env bash
export CLASSPATH=".:/usr/local/lib/antlr-4.5.3-complete.jar:$CLASSPATH"
rm *.java
rm *.class
antlr4 M2Lexer.g4
antlr4 M2Parser.g4
javac M2*.java