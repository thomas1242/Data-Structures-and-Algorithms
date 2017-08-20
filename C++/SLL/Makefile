all: Test

Test: Test.o
	g++ -o Test Test.o
	./Test

Test.o: Test.cpp SinglyLinkedList.h LinearList.h

clean:
	rm *.o Test
