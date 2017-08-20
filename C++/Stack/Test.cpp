#include <iostream>
#include "LinkedStack.h"

int main() {

	LinkedStack<int> * stack = new LinkedStack<int>();	
	bool res = true;

	stack->push(1);
	stack->push(2);
	stack->push(3);
	res &= stack->top() == 3;

	stack->pop();
	res &= stack->top() == 2;

	stack->pop();
	stack->pop();
	res &= stack->isEmpty();

	stack->push(3);
	res &= stack->size() == 1;

	if(res)	std::cout << "\033[1;32mPASS\033[0m" << std::endl;
	else 	std::cout << "\033[1;31mFAIL\033[0m" << std::endl;

	stack->~LinkedStack();	
	
	return 0;
}