#!/bin/bash

echo "val add" | grun Hello valDef -tree
echo "val _a" | grun Hello valDef -tree
echo "val _a_b" | grun Hello valDef -tree
echo "val val" | grun Hello valDef -tree
echo $?
echo "val var" | grun Hello valDef -tree
echo "val def" | grun Hello valDef -tree
echo
echo
echo

echo "def add = {}" | grun Hello fnDef -tree
echo "def + = {}" | grun Hello fnDef -tree
echo "def :: = {}" | grun Hello fnDef -tree

echo
echo
echo "type A = llvm { i32 }" | grun Hello typeDef -tree