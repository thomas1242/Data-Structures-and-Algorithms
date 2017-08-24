#ifndef BST_H
#define BST_H
#include <iostream>
#include "LinkedQueue.h"

template <class T>
class BinarySearchTree {

	struct Node {
		T data;
		Node * left = nullptr;
		Node * right = nullptr;
		Node() {}
		Node(T data) {
			this->data = data;
		}
	};

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
		BinarySearchTree<T>::Node * root;
		BinarySearchTree<T>::Node * insert(BinarySearchTree<T>::Node *& node, const int & data);
		BinarySearchTree<T>::Node * remove(BinarySearchTree<T>::Node *& node, const int & data);
		bool contains(BinarySearchTree<T>::Node * node, const int & data) const;
		int getMin(const BinarySearchTree<T>::Node * node) const;
		int size(const BinarySearchTree<T>::Node * node) const;
		void deleteTree(BinarySearchTree<T>::Node *& node);
		void inorder(const BinarySearchTree<T>::Node * node) const;
		void preorder(const BinarySearchTree<T>::Node * node) const;
		void postorder(const BinarySearchTree<T>::Node * node) const;
};

template <class T>
BinarySearchTree<T>::BinarySearchTree() {
	root = nullptr;
}

template <class T>
BinarySearchTree<T>::~BinarySearchTree() {
	deleteTree(root);
}

template <class T>
void BinarySearchTree<T>::deleteTree(BinarySearchTree<T>::Node *& node) {
	if(node == nullptr)
		return;
		
	BinarySearchTree<T>::Node * left = node->left;
	BinarySearchTree<T>::Node * right = node->right;
	
	delete node;
	node = nullptr;
	
	deleteTree(left);
	deleteTree(right);
}

template <class T>
bool BinarySearchTree<T>::contains(const int & data) const {
	return contains(root, data);
}

template <class T>
bool BinarySearchTree<T>::contains(BinarySearchTree<T>::Node * node, const int & data) const {
	if(node == nullptr)
		return false;
			
	if(data < node->data) 
		return contains(node->left, data);
	else if (data > node->data) 
		return contains(node->right, data);
	else
		return true;
}

template <class T>
void BinarySearchTree<T>::insert(const int & data) {
	root = insert(root, data);
}

template <class T>
typename BinarySearchTree<T>::Node * BinarySearchTree<T>::insert(BinarySearchTree<T>::Node *& node, const int & data) {
	if(node == nullptr)
		return new BinarySearchTree<T>::Node(data);
			
	if(data < node->data) 
		node->left = insert(node->left, data);
	else if (data > node->data) 
		node->right = insert(node->right, data);
		
	return node;		// return updated node
}

template <class T>
void BinarySearchTree<T>::remove(const int & data) {
	root = remove(root, data);
}

template <class T>
typename BinarySearchTree<T>::Node * BinarySearchTree<T>::remove(BinarySearchTree<T>::Node *& node, const int & data) {
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

template <class T>
int BinarySearchTree<T>::getMin(const BinarySearchTree<T>::Node * node) const {
	if(node->left == nullptr)
		return node->data;
	else
		return getMin(node->left);
}

template <class T>
void BinarySearchTree<T>::inorder() const {
	std::cout << "Inorder traversal: ";
	inorder(root);
	std::cout << std::endl;
}

template <class T>
void BinarySearchTree<T>::inorder(const BinarySearchTree<T>::Node * node) const {
	if(node == nullptr) 
		return;

	inorder(node->left);
	std::cout << node->data << " ";
	inorder(node->right);
}

template <class T>
void BinarySearchTree<T>::preorder() const {
	std::cout << "Preorder traversal: ";
	preorder(root);
	std::cout << std::endl;
}

template <class T>
void BinarySearchTree<T>::preorder(const BinarySearchTree<T>::Node * node) const {
	if(node == nullptr) 
		return;

	std::cout << node->data << " ";
	preorder(node->left);
	preorder(node->right);
}

template <class T>
void BinarySearchTree<T>::postorder() const {
	std::cout << "Postorder traversal: ";
	postorder(root);
	std::cout << std::endl;
}

template <class T>
void BinarySearchTree<T>::postorder(const BinarySearchTree<T>::Node * node) const {
	if(node == nullptr) 
		return;

	postorder(node->left);
	postorder(node->right);
	std::cout << node->data << " ";
}

template <class T>
void BinarySearchTree<T>::levelorder() const {
	std::cout << "Levelorder traversal: ";
	
	if(root == nullptr) 
		return;
		
	Queue<BinarySearchTree<T>::Node> * q = new LinkedQueue<BinarySearchTree<T>::Node>();
	q->add( *root );
	
	while( !q->isEmpty() ) {
		
		BinarySearchTree<T>::Node curr = q->front();	
		q->pop();
		
		std::cout << curr.data << " "; 
		
		if(curr.left != nullptr)
			q->add( *curr.left );
		if(curr.right != nullptr)
			q->add( *curr.right );
	}
	
	std::cout << std::endl;
}


template <class T>
int BinarySearchTree<T>::size() const {
	return size(root);
}

template <class T>
int BinarySearchTree<T>::size(const BinarySearchTree<T>::Node * node) const {
	if(node == nullptr)
		return 0;
	else
		return 1 + size(node->left) + size(node->right);
}

#endif
