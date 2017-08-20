all: Test

Test: Test.o
	g++ -o Test Test.o
	./Test

Test.o: Test.cpp LinkedQueue.h Queue.h

clean:
	rm *.o Test
