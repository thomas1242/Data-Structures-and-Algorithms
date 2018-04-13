#include <iostream>
#include "BinarySearchTree.h"

int main() {

	BinarySearchTree<int> * bst = new BinarySearchTree<int>();	
	
	bool res = true;			// test BST API

	bst->insert(1);
	bst->insert(10);
	bst->insert(-5);
	bst->insert(-7);
	bst->insert(3);
	
	bst->inorder();
	bst->preorder();
	bst->postorder();
	bst->levelorder();
				
	res &= bst->contains(1);
	res &= bst->contains(10);
	res &= bst->contains(-5);
	res &= bst->contains(-7);	

	res &= bst->size() == 5;
	bst->remove(1);
	bst->remove(10);
	bst->remove(-5);
	bst->remove(-7);
	res &= bst->size() == 1;

	res &= !bst->contains(1);
	res &= !bst->contains(10);
	res &= !bst->contains(-5);
	res &= !bst->contains(-7);	
	
	if(res)	std::cout << "\033[1;32mPASS\033[0m" << std::endl;
	else 	std::cout << "\033[1;31mFAIL\033[0m" << std::endl;

	delete bst;
	
	return 0;
}

