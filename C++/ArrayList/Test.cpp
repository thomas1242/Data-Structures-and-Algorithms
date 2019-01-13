#include <iostream>
#include <string>
#include "ArrayList.h"

template <typename T>
ArrayList<T> f(const ArrayList<T>& list) {
	ArrayList<T> tmp(list);						// call copy constructor
	return tmp;									// call move assignment
}

template <typename T>
ArrayList<T> f1(ArrayList<T> list) {			// call copy constructor
	ArrayList<T> tmp(list);						// call copy constructor
	return tmp;									// call move assignment
}


int main() {
	ArrayList<int> list1;
	ArrayList<int> list2 = list1;	// call copy constructor

	for(int n : {2, 4, 6, 8}) list1.append(n);
	for(int n : {1, 3, 5, 7}) list2.append(n);

	std::cout << "list1: " << list1.to_string()  << std::endl;
	std::cout << "list2: " << list2.to_string() << std::endl;

	list2 = f1(list2); 

	bool res = true;
	res &= list2.get(0) == 1;
	res &= list2.get( list2.size() - 1 ) == 7;

	list2.remove(0);
	res &= list2.get(0) == 3;
	res &= list2.get(2) == 7;

	list2.insert(0, 15);
	res &= list2.get(0) == 15;

	if(res)	std::cout << "\033[1;32mPASS\033[0m" << std::endl;
	else 	std::cout << "\033[1;31mFAIL\033[0m" << std::endl;
	return 0;
}
