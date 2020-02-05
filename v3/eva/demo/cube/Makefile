all: run

compile:
	java -jar /home/devteam/build/eva_lang/v3/target/scala-2.13/eva.jar cube.eva --linker-flags="-lSDL2 -lSOIL -lGL -lm -lkazmath"
run: compile
	sh -c target/cube
