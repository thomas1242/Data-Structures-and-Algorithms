all: Main

Main: Main.o
	g++ -o Test Main.o
	./Test

Main.o: Main.cpp Map.h HashMap.h Node.h Pair.h

clean:
	rm *.o Test
