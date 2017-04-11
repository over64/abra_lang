all:
	java -jar ../../target/scala-2.11/kadabra.jar -I ../ -l GL,SOIL,SDL2,kazmath main.abra
	cp cube.obj target
	cp cube.mtl target
	cp tex.png  target
clean:
	rm -r target