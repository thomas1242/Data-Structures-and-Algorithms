CXXFLAGS = -std=c++11 -Wall

all: Test

Test: Test.o
	g++ -o Test Test.o
	./Test

Test.o: Test.cpp ArrayList.h LinearList.h

clean:
	rm *.o Test
