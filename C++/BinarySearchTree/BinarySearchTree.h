#ifndef BST_H
#define BST_H

#include <iostream>

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
		void insert(const int & data);
		void inorder() const;
	private:
		Node * root;
		Node * insert(Node * node, const int & data);
		void inorder(const Node * node) const;

};

void BinarySearchTree::insert(const int & data) {
	insert(root, data);
}

Node * BinarySearchTree::insert(Node * node, const int & data) {
	if(node == NULL)
		return new Node(data);
	
	if(data < node->data)
		root->left = insert(root->left, data);
	else 
		root->right = insert(root->right, data);
}

BinarySearchTree::BinarySearchTree() {
	root = new Node();
}

BinarySearchTree::~BinarySearchTree() {
}

void BinarySearchTree::inorder() const {
	inorder(root);
}


void BinarySearchTree::inorder(const Node * node) const {
	if(node == NULL)
		return;

	inorder(node->left);
	std::cout << node->data << std::endl;
	inorder(node->right);
}

#endif
