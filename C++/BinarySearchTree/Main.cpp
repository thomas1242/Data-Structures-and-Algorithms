#include <iostream>
#include "BinarySearchTree.h"

int main() {

	BinarySearchTree * bst = new BinarySearchTree();	

	bst->insert(1);
//	bst->insert(3);
//	bst->insert(5);
//	bst->insert(7);
//	bst->insert(10);

	bst->inorder();

	bool res = true;
	
	if(res)	std::cout << "\033[1;32mPASS\033[0m" << std::endl;
	else 	std::cout << "\033[1;31mFAIL\033[0m" << std::endl;

	bst->~BinarySearchTree();

	return 0;
}

