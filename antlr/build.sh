#!/usr/bin/env bash
export CLASSPATH=".:/usr/local/lib/antlr-4.5.3-complete.jar:$CLASSPATH"
antlr4 Hello.g4
javac Hello*.java