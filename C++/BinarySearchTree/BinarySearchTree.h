#ifndef BST_H
#define BST_H
#include <iostream>
#include "LinkedQueue.h"

template <class T>
class BinarySearchTree {

	struct Node {
		T data;
		Node * left;
		Node * right;
		Node() : left(nullptr), right(nullptr) {}
		Node(T data) : data(data), left(nullptr), right(nullptr) {}
	};

	public:
		BinarySearchTree();
		~BinarySearchTree();
		void insert(const T& data);
		void remove(const T& data);
		bool contains(const T& data) const;
		T& find(const T& data);
		int size() const;
		void inorder() const;
		void preorder() const;
		void postorder() const;
		void levelorder() const;
		
	private:
		BinarySearchTree<T>::Node * root;
		BinarySearchTree<T>::Node * insert(BinarySearchTree<T>::Node *& node, const T& data);
		BinarySearchTree<T>::Node * remove(BinarySearchTree<T>::Node *& node, const T& data);
		T& find(BinarySearchTree<T>::Node *& node, const T& data);
		bool contains(BinarySearchTree<T>::Node * node, const T& data) const;
		T getMin(const BinarySearchTree<T>::Node * node) const;
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
	deleteTree(node->left);
	deleteTree(node->right);
	delete node;
	node = nullptr;
}

template <class T>
bool BinarySearchTree<T>::contains(const T& data) const {
	return contains(root, data);
}

template <class T>
bool BinarySearchTree<T>::contains(BinarySearchTree<T>::Node * node, const T& data) const {
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
void BinarySearchTree<T>::insert(const T& data) {
	root = insert(root, data);
}

template <class T>
typename BinarySearchTree<T>::Node * BinarySearchTree<T>::insert(BinarySearchTree<T>::Node *& node, const T& data) {
	if(node == nullptr)
		return new BinarySearchTree<T>::Node(data);
			
	if(data < node->data) 
		node->left = insert(node->left, data);
	else if (data > node->data) 
		node->right = insert(node->right, data);
		
	return node;		// return updated node
}

template <class T>
void BinarySearchTree<T>::remove(const T& data) {
	root = remove(root, data);
}

template <class T>
typename BinarySearchTree<T>::Node * BinarySearchTree<T>::remove(BinarySearchTree<T>::Node *& node, const T& data) {
	if(node == nullptr)	// data not found
		return nullptr;	

	if(data < node->data) 
		node->left = remove(node->left, data);
	else if (data > node->data) 
		node->right = remove(node->right, data);
	else { 	
		if(node->left == nullptr || node->right == nullptr) {
			BinarySearchTree<T>::Node * child = node->left == nullptr ? node->right : node->left;
			delete node;
			return child;
		}
		else {
			T min = getMin(node->right);
			node->right = remove(node->right, min);
			node->data = min;
		}
	}
	
	return node;		// return updated node
}

template <class T>
T BinarySearchTree<T>::getMin(const BinarySearchTree<T>::Node * node) const {
	if(node->left == nullptr)
		return node->data;
	else
		return getMin(node->left);
}

template <class T>
T& BinarySearchTree<T>::find(const T& data) {
	return find(root, data);
}

template <class T>
T& BinarySearchTree<T>::find(BinarySearchTree<T>::Node *& node, const T& data) {
	if(node == nullptr) throw std::runtime_error("find(): data not found");
			
	if(data < node->data) 
		return find(node->left, data);
	else if (data > node->data) 
		return find(node->right, data);
	else
		return node->data;
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
		
	LinkedQueue<BinarySearchTree<T>::Node> q;
	q.add( *root );
	
	while( !q.isEmpty() ) {
		
		BinarySearchTree<T>::Node curr = q.front();	
		q.pop();
		
		std::cout << curr.data << " "; 
		
		if(curr.left != nullptr)
			q.add( *curr.left );
		if(curr.right != nullptr)
			q.add( *curr.right );
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