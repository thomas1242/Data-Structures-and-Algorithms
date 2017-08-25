CXXFLAGS = -std=c++11 -Wall

all: 	Main.o
	g++ -o Main Main.o 
	./Main

Main.o: Main.cpp Graph.h LinearList.h LinkedList.h Queue.h LinkedQueue.h Map.h HashMap.h

clean:
	rm *.o Main
