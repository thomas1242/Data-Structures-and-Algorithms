#ifndef BST_H
#define BST_H
#include <iostream>
#include "LinkedQueue.h"

struct Node {
	int data;
	Node * left = nullptr;
	Node * right = nullptr;
	Node() {}
	Node(int data) {
		this->data = data;
	}
};

class BinarySearchTree {

	public:
		BinarySearchTree();
		~BinarySearchTree();
		void insert(const int & data);
		void remove(const int & data);
		bool contains(const int & data) const;
		int size() const;
		void inorder() const;
		void preorder() const;
		void postorder() const;
		void levelorder() const;
		
	private:
		Node * root;
		Node * insert(Node *& node, const int & data);
		Node * remove(Node *& node, const int & data);
		bool contains(Node * node, const int & data) const;
		int getMin(const Node * node) const;
		int size(const Node * node) const;
		void deleteTree(Node *& node);
		void inorder(const Node * node) const;
		void preorder(const Node * node) const;
		void postorder(const Node * node) const;
};

BinarySearchTree::BinarySearchTree() {
	root = nullptr;
}

BinarySearchTree::~BinarySearchTree() {
	deleteTree(root);
}

void BinarySearchTree::deleteTree(Node *& node) {
	if(node == nullptr)
		return;
		
	Node * left = node->left;
	Node * right = node->right;
	
	delete node;
	node = nullptr;
	
	deleteTree(left);
	deleteTree(right);
}

bool BinarySearchTree::contains(const int & data) const {
	return contains(root, data);
}

bool BinarySearchTree::contains(Node * node, const int & data) const {
	if(node == nullptr)
		return false;
			
	if(data < node->data) 
		return contains(node->left, data);
	else if (data > node->data) 
		return contains(node->right, data);
	else
		return true;
}

void BinarySearchTree::insert(const int & data) {
	root = insert(root, data);
}

Node * BinarySearchTree::insert(Node *& node, const int & data) {
	if(node == nullptr)
		return new Node(data);
			
	if(data < node->data) 
		node->left = insert(node->left, data);
	else if (data > node->data) 
		node->right = insert(node->right, data);
		
	return node;		// return updated node
}

void BinarySearchTree::remove(const int & data) {
	root = remove(root, data);
}

Node * BinarySearchTree::remove(Node *& node, const int & data) {
	if(node == nullptr)	// data not found
		return nullptr;	
			
	if(data < node->data) 
		node->left = remove(node->left, data);
	else if (data > node->data) 
		node->right = remove(node->right, data);
	else { 				
		if(node->left == nullptr)
			return node->right;
		else if(node->right == nullptr)
			return node->left;
		else {
			int min = getMin(node->right);
			node->right = remove(node->right, min);
			node->data = min;
		}
	}
		
	return node;		// return updated node
}

int BinarySearchTree::getMin(const Node * node) const {
		if(node->left == nullptr)
			return node->data;
		else
			return getMin(node->left);
}

void BinarySearchTree::inorder() const {
	std::cout << "Inorder traversal: ";
	inorder(root);
	std::cout << std::endl;
}

void BinarySearchTree::inorder(const Node * node) const {
	if(node == nullptr)
		return;

	inorder(node->left);
	std::cout << node->data << " ";
	inorder(node->right);
}

void BinarySearchTree::preorder() const {
	std::cout << "Preorder traversal: ";
	preorder(root);
	std::cout << std::endl;
}

void BinarySearchTree::preorder(const Node * node) const {
	if(node == nullptr)
		return;

	std::cout << node->data << " ";
	preorder(node->left);
	preorder(node->right);
}

void BinarySearchTree::postorder() const {
	std::cout << "Postorder traversal: ";
	postorder(root);
	std::cout << std::endl;
}

void BinarySearchTree::postorder(const Node * node) const {
	if(node == nullptr)
		return;

	postorder(node->left);
	postorder(node->right);
	std::cout << node->data << " ";
}

void BinarySearchTree::levelorder() const {
	std::cout << "Levelorder traversal: ";
	
	if(root == nullptr)
		return;
		
	Queue<Node> * q = new LinkedQueue<Node>();
	q->add( *root );
	
	while( !q->isEmpty() ) {
		
		Node curr = q->front();	
		q->pop();
		
		std::cout << curr.data << " "; 
		
		if(curr.left != nullptr)
			q->add( *curr.left );
		if(curr.right != nullptr)
			q->add( *curr.right );
	}
	
	std::cout << std::endl;
}


int BinarySearchTree::size() const {
	return size(root);
}

int BinarySearchTree::size(const Node * node) const {
	if(node == nullptr)
		return 0;
	else
		return 1 + size(node->left) + size(node->right);
}

#endif
