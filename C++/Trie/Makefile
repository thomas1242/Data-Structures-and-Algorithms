CXXFLAGS = -std=c++11 -Wall

all: Main.o
	g++ -o Main Main.o
	./Main

Main.o: Main.cpp Trie.h

clean: 
	rm *.o Main
