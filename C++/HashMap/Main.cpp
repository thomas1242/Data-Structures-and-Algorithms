#include <iostream>
#include "HashMap.h"
#include <string>
using namespace std;

int main() {

	// HashMap<int, string> hashMap;
	HashMap<int, std::string> * hashMap_ptr = new HashMap<int, std::string>();

	bool res = true;

	//

	if(res)	std::cout << "\033[1;32mPASS\033[0m" << std::endl;
	else 	std::cout << "\033[1;31mFAIL\033[0m" << std::endl;

	// hashMap.~HashMap();	
	hashMap_ptr->~HashMap();	
	
	return 0;
}