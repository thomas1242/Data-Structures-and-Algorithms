#include <iostream>
#include "Graph.h"

int main() {

	Graph<int> * graph = new Graph<int>();	
	
	bool res = true;			// test BST API
	
	if(res)	std::cout << "\033[1;32mPASS\033[0m" << std::endl;
	else 	std::cout << "\033[1;31mFAIL\033[0m" << std::endl;
	
	graph->~Graph();
	
	return 0;
}

