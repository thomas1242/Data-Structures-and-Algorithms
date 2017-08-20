#include <iostream>
#include "LinkedQueue.h"

int main() {

	LinkedQueue<int> * q = new LinkedQueue<int>();	
	bool res = true;

	q->add(1);
	q->add(2);
	q->add(3);
	res &= q->front() == 1;

	q->pop();
	res &= q->front() == 2;

	q->pop();
	q->pop();
	res &= q->isEmpty();

	q->add(3);
	res &= q->size() == 1;

	if(res)	std::cout << "\033[1;32mPASS\033[0m" << std::endl;
	else 	std::cout << "\033[1;31mFAIL\033[0m" << std::endl;

	q->~LinkedQueue();	
	
	return 0;
}