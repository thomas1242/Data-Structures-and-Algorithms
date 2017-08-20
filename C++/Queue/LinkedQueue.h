#ifndef LINKEDQUEUE_H
#define LINKEDQUEUE_H

#include <iostream>
#include <string>
#include "Queue.h"
using namespace std;

template <class T>
class LinkedQueue {

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
		LinkedQueue();								// constructor
		LinkedQueue(LinkedQueue<T> & copyList);		// copy constructor
		~LinkedQueue();								// destructor
		T& front() const;								// Stack API
		void add(const T& theElement);
		void pop();
		bool isEmpty() const;
		int size() const;		

	private:
		LinkedQueue<T>::Node * head;	// pointer to front of queue
		LinkedQueue<T>::Node * tail;	// pointer to end of queue

		int getLength(LinkedQueue<T>::Node * node) const;
		typename LinkedQueue<T>::Node * firstNode();

};

template <class T>
LinkedQueue<T>::LinkedQueue() {
	head = NULL;
	tail = NULL;
}

template <class T>
LinkedQueue<T>::LinkedQueue(LinkedQueue<T> & copyList) {
	if(copyList.firstNode() == NULL) {
		head = NULL;
		tail = NULL;
		return;
	}

	LinkedQueue<T>::Node * sourceNode = copyList.firstNode();
	head = new Node(sourceNode);

	LinkedQueue<T>::Node * temp = head;

	while(sourceNode->next != NULL) {
		temp->next = new Node(sourceNode->next);
		temp = temp->next;
		sourceNode = sourceNode->next;
	}
}

template <class T>
LinkedQueue<T>::~LinkedQueue() {
	LinkedQueue<T>::Node * next;
	while(head != NULL) {
		next = head->next;
		delete head;
		head = next;
	}
}

template <class T>
void LinkedQueue<T>::add(const T& theElement) {
	LinkedQueue<T>::Node * newNode = new LinkedQueue<T>::Node(theElement);
	if(head == NULL) {
		head = newNode;
		tail = head;
	}
	else {
		tail->next = newNode;
		tail = tail->next;
	}
}

template <class T>
void LinkedQueue<T>::pop() {
	if(size() == 0)
		throw std::runtime_error("pop() : empty stack");

	LinkedQueue<T>::Node * oldHead = head;
	head = head->next;
	delete oldHead;
	if(head == NULL)
		tail = NULL;
}

template <class T>
T& LinkedQueue<T>::front() const {
	if(size() == 0)
		throw std::runtime_error("top() : empty stack");

	return head->data;
}

template<class T>
bool LinkedQueue<T>::isEmpty() const {
	return size() == 0;
}

template<class T>
int LinkedQueue<T>::size() const {
	if(head == NULL)
		return 0;
	else
		return getLength( head );
}

template<class T>
int LinkedQueue<T>::getLength(LinkedQueue<T>::Node * node) const {
	if(node == NULL)
		return 0;
	else
		return 1 + getLength( node->next );
}

template <class T>
typename LinkedQueue<T>::Node * LinkedQueue<T>::firstNode() {
	return head;
}

#endif