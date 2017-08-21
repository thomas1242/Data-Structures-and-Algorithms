#include <iostream>
#include <string>
#include "HashMap.h"

using namespace std;

int main() {

	HashMap<int, string> * hashMap_ptr = new HashMap<int, string>();
	bool res = true;

	hashMap_ptr->insert(5, "five");
	hashMap_ptr->insert(7, "seven");
	hashMap_ptr->insert(3, "three");
	hashMap_ptr->insert(2, "two");
	res &= *hashMap_ptr->find(7) == "seven";

	hashMap_ptr->erase(7);
	res &= hashMap_ptr->find(7) == NULL;

	hashMap_ptr->insert(7, "seven");
	hashMap_ptr->insert(7, "ten");			// erase any existing <K, V> pair 
	res &= *hashMap_ptr->find(7) == "ten";

	if(res)	std::cout << "\033[1;32mPASS\033[0m" << std::endl;
	else 	std::cout << "\033[1;31mFAIL\033[0m" << std::endl;

	hashMap_ptr->~HashMap();	
	
	return 0;
}