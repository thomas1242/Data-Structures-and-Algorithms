#include <iostream>
#include <string>
#include "ArrayList.h"

int main() {
	ArrayList<int> list1 = {1, 2, 3, 4, 5};
	ArrayList<int> list2 = list1;	  
		  
	for(auto iter = list1.begin(); iter != list1.end(); ++iter)
		std::cout << *iter  << " ";

	return 0;
}
