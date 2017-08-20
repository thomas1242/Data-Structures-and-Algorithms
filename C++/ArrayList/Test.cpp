#include <iostream>
#include <string>
#include "ArrayList.h"
using namespace std;

int main() {

	ArrayList<int> * list = new ArrayList<int>();
	list->append(1);
	list->append(3);
	list->append(5);
	list->append(7);
	list->print();

	list->remove(2);
	list->print();

	list->append(77);
	list->print();

	list->insert(0, -1);
	list->print();

	list->insert(3, 3);
	list->print();

	list->remove(5);
	list->print();
	return 0;
}