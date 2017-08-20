#include <iostream>
#include "SinglyLinkedList.h"

int main() {
	SinglyLinkedList<int> * list = new SinglyLinkedList<int>();

	bool res = true;

	list->append(1);
	list->append(3);
	list->append(5);
	list->append(7);
	
	res &= list->get(0) == 1;
	res &= list->get(2) == 5;

	list->remove(0);
	res &= list->get(0) == 3;
	res &= list->get(2) == 7;

	list->insert(0, 15);
	res &= list->get(0) == 15;

	if(res)	std::cout << "\033[1;32mPASS\033[0m" << std::endl;
	else 	std::cout << "\033[1;31mFAIL\033[0m" << std::endl;
	
	return 0;
}