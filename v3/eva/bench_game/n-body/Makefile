all: run-nbody
# run -tree-opt

compile-nbody:
	java -jar /home/devteam/build/eva_lang/v3/target/scala-2.13/eva.jar nbody.eva
# compile-tree-opt:
# 	java -jar /home/devteam/build/eva_lang/v3/target/scala-2.13/eva.jar nbody.eva

run-nbody: compile-nbody
	time -v target/nbody
# run-tree-opt: compile-tree-opt
# 	time -v target/treeWithPool
