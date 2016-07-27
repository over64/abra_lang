#!/bin/bash
echo -n "1" | grun Hello expr -tree
echo -n "a" | grun Hello expr -tree
echo -n "a.b" | grun Hello expr -tree
echo -n "1 + 1" | grun Hello expr -tree
echo -n "a + b" | grun Hello expr -tree
echo -n "a xor b" | grun Hello expr -tree
echo -n "foo()" | grun Hello expr -tree
echo -n "bar.foo()" | grun Hello expr -tree
echo -n "bar.foo(1,1)" | grun Hello expr -tree
echo -n "bar.foo(1 + 1)" | grun Hello expr -tree
echo -n "(1+1).shl(1 + 1)" | grun Hello expr -tree
echo -n "(1 + 1).shl(1 + 1)" | grun Hello expr -tree
echo -n "(1).shl(1 + 1)" | grun Hello expr -tree
echo -n "(1).shl(1 + 1)" | grun Hello expr -tree
echo -n "(1).shl" | grun Hello expr -tree
echo -n "(1).+(1)" | grun Hello expr -tree
echo -n "1.shl(1)" | grun Hello expr -tree
echo -n "1.shl()" | grun Hello expr -tree
echo -n "1 xor b * 4" | grun Hello expr -tree
echo -n "1 shl 1 * 3 == 5 * 5" | grun Hello expr -tree
echo -n "1.a.b" | grun Hello expr -tree
echo -n "1(1+1)" | grun Hello expr -tree
echo -n "+(1+1)" | grun Hello expr -tree
echo -n "(1+1)" | grun Hello expr -tree