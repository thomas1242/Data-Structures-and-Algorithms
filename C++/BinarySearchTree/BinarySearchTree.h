#ifndef BST_H
#define BST_H

struct Node {
	int data;
	Node * left;
	Node * right;
	Node() {
		left = NULL;
		right = NULL;
	}
	Node(int d) {
		data = d;
		left = NULL;
		right = NULL;	
	}

};

class BinarySearchTree {

	public:
		BinarySearchTree();
		~BinarySearchTree();
	private:
		Node * root;

};

BinarySearchTree::BinarySearchTree() {
	root = new Node();
}

BinarySearchTree::~BinarySearchTree() {
}

#endif
