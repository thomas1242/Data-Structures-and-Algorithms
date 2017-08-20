all: TestArrayList

TestArrayList: Test.o
	g++ -o TestArrayList Test.o

Test.o: Test.cpp ArrayList.h LinearList.h

clean:
	rm *.o TestArrayList
