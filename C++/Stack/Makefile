all: Test

Test: Test.o
	g++ -o Test Test.o
	./Test

Test.o: Test.cpp LinkedStack.h Stack.h

clean:
	rm *.o Test
