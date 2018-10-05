#include <iostream>
#include <string>
#include "HashMap.h"

using namespace std;

int main() {

	HashMap<int, string> * hashMap_ptr = new HashMap<int, string>();
	bool res = true;

	hashMap_ptr->insert(5, "five");
	hashMap_ptr->insert(7, "seven");
	hashMap_ptr->insert(3, "three_1");
	hashMap_ptr->insert(3, "three_2");
	hashMap_ptr->insert(2, "two");

	std::cout << "size: " << hashMap_ptr->size() << std::endl;

	res &= *hashMap_ptr->find(3) != "three_1";
	res &= *hashMap_ptr->find(3) == "three_2";

	res &= *hashMap_ptr->find(7) == "seven";
	res &= hashMap_ptr->size() == 4;

	hashMap_ptr->erase(7);
	res &= hashMap_ptr->find(7) == NULL;

	hashMap_ptr->insert(7, "seven");
	hashMap_ptr->insert(7, "ten");			// erase any existing <K, V> pair 
	res &= *hashMap_ptr->find(7) == "ten";

	HashMap<string, int> * map = new HashMap<string, int>();
	map->insert("one" , 1);
	map->insert("one" , *map->find("one") + 1);
	res &= *map->find("one") == 2;

	if(res)	std::cout << "\033[1;32mPASS\033[0m" << std::endl;
	else 	std::cout << "\033[1;31mFAIL\033[0m" << std::endl;

	delete hashMap_ptr;
	
	return 0;
}
