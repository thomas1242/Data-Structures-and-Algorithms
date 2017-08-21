#ifndef LINKEDLISTSTACK_H
#define LINKEDLISTSTACK_H

#include <iostream>
#include <string>
#include "Stack.h"
using namespace std;

template <class T>
class LinkedStack : Stack<T> {

	struct Node {
	        T data;
	        Node * next;

	       	Node(T d) {
	       		data = d;
	       		next = NULL;
	    	}
	    	Node(Node *& node) {
	       		data = node->data;
	       		next = NULL;
	       	}
	};

	public: 
		LinkedStack();								// constructor
		LinkedStack(LinkedStack<T> & copyList);		// copy constructor
		~LinkedStack();								// destructor
		T& top() const;								// Stack API
		void push(const T& theElement);
		void pop();
		bool isEmpty() const;
		int size() const;		

	private:
		LinkedStack<T>::Node * head;	// pointer to top of stack
		int getLength(LinkedStack<T>::Node * node) const;
		typename LinkedStack<T>::Node * firstNode();

};

template <class T>
LinkedStack<T>::LinkedStack() {
	head = NULL;
}

template <class T>
LinkedStack<T>::LinkedStack(LinkedStack<T> & copyList) {
	if(copyList.firstNode() == NULL) {
		head = NULL;
		return;
	}

	LinkedStack<T>::Node * sourceNode = copyList.firstNode();
	head = new Node(sourceNode);

	LinkedStack<T>::Node * temp = head;

	while(sourceNode->next != NULL) {
		temp->next = new Node(sourceNode->next);
		temp = temp->next;
		sourceNode = sourceNode->next;
	}
}

template <class T>
LinkedStack<T>::~LinkedStack() {
	LinkedStack<T>::Node * next;
	while(head != NULL) {
		next = head->next;
		delete head;
		head = next;
	}
}

template <class T>
void LinkedStack<T>::push(const T& theElement) {
	LinkedStack<T>::Node * newNode = new LinkedStack<T>::Node(theElement);
	newNode->next = head;
	head = newNode;
}

template <class T>
void LinkedStack<T>::pop() {
	if(size() == 0)
		throw std::runtime_error("pop() : empty stack");

	LinkedStack<T>::Node * oldHead = head;
	head = head->next;
	delete oldHead;
}

template <class T>
T& LinkedStack<T>::top() const {
	if(size() == 0)
		throw std::runtime_error("top() : empty stack");

	return head->data;
}

template<class T>
bool LinkedStack<T>::isEmpty() const {
	return size() == 0;
}

template<class T>
int LinkedStack<T>::size() const {
	if(head == NULL)
		return 0;
	else
		return getLength( head );
}

template<class T>
int LinkedStack<T>::getLength(LinkedStack<T>::Node * node) const {
	if(node == NULL)
		return 0;
	else
		return 1 + getLength( node->next );
}

template <class T>
typename LinkedStack<T>::Node * LinkedStack<T>::firstNode() {
	return head;
}

#endif
