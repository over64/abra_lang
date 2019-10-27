all: run-tree run-tree-opt

compile-tree:
	java -jar /home/devteam/build/eva_lang/v3/target/scala-2.13/eva.jar tree.eva
compile-tree-opt:
	java -jar /home/devteam/build/eva_lang/v3/target/scala-2.13/eva.jar treeWithPool.eva

run-tree: compile-tree
	time -v target/tree
run-tree-opt: compile-tree-opt
	time -v target/treeWithPool