CXXFLAGS = -std=c++11

all: Main

Main: Main.o
	g++ -o Test Main.o
	./Test

Main.o: Main.cpp Map.h HashMap.h

clean:
	rm *.o Test
