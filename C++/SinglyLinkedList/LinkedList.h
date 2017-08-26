#ifndef LINKEDLIST_H
#define LINKEDLIST_H

#include <iostream>
#include <string>
#include "LinearList.h"

template <class T>
class LinkedList : public LinearList<T> {

	struct Node {
	        T data;
	        Node * next;
	       	Node(T d) : data(d), next(nullptr) {}
	    	Node(Node *& node) : Node(node->data) {}
	};

	public: 
		LinkedList();	
		LinkedList(LinkedList<T> & list);
		~LinkedList();	
		T& get(int index) const;	// inherited pure virtual functions
		void append(const T& theElement);
		void insert(int index, const T& theElement);
		void remove(int index);
		bool isEmpty() const;	
		int size() const;	
		T& pop();				  	// additional methods
		void print() const;	
		void reverse();
		typename LinkedList<T>::Node * firstNode();

	private:
		LinkedList<T>::Node * head;	
		LinkedList<T>::Node * tail; 
		int getLength(LinkedList<T>::Node * node) const;
};

template <class T>
LinkedList<T>::LinkedList() : head(nullptr), tail(nullptr) {}

template <class T>
LinkedList<T>::~LinkedList() {
	LinkedList<T>::Node * next;
	while(head != nullptr) {
		next = head->next;
		delete head;
		head = next;
	}
}

template <class T>
LinkedList<T>::LinkedList(LinkedList<T> & list) {
	if( list.firstNode() == nullptr )
		return;

	LinkedList<T>::Node * sourceNode = list.firstNode();
	head = new Node(sourceNode);

	LinkedList<T>::Node * temp = head;

	while(sourceNode->next != nullptr) {
		temp->next = new Node(sourceNode->next);
		temp = temp->next;
		sourceNode = sourceNode->next;
	}
}

template <class T>
typename LinkedList<T>::Node * LinkedList<T>::firstNode() {
	return head;
}


template <class T>
T& LinkedList<T>::get(int index) const {
	if(index < 0 || index >= getLength(head))
		throw std::runtime_error("get() : index out of bounds");

	if(index == 0)
		return head->data;

	LinkedList<T>::Node * temp = head;
	for(int i = 0; i < index; i++)
		temp = temp->next;

	return temp->data;
}


template <class T>
T& LinkedList<T>::pop() {
	if ( head == nullptr ) 
		throw std::runtime_error("pop() : empty list");
	else {
		LinkedList<T>::Node * temp = head;
		head = head->next;
		if( head == nullptr )	// update tail
			tail = nullptr;
		return temp->data;
	}
}

template <class T>
void LinkedList<T>::insert(int index, const T& theElement) {
	if(index < 0 || index > getLength(head))
		throw std::runtime_error("index out of bounds");

	LinkedList<T>::Node * newNode = new LinkedList<T>::Node(theElement);

	if(index == 0) {
		newNode->next = head;
		head = newNode;
	}
	else {
		LinkedList<T>::Node * temp = head;
		for(int i = 0; i < index - 1; i++)
			temp = temp->next;
		newNode->next = temp->next;
		temp->next = newNode;
		if( newNode->next == nullptr )
			tail = newNode;
	}
}

template <class T>
void LinkedList<T>::append(const T& theElement) {
	if( head == nullptr ) {
		head = new LinkedList<T>::Node(theElement);
		tail = head;
	}
	else {
		tail->next = new LinkedList<T>::Node(theElement);
		tail = tail->next;
	}	
}

template <class T>
void LinkedList<T>::remove(int index) {
	if(index < 0 || index >= getLength(head))
		throw std::runtime_error("index out of bounds");
	if(index == 0) {
		LinkedList<T>::Node * del = head;
		head = head->next;
		delete del;
	}
	else {
		LinkedList<T>::Node * temp = head;
		for(int i = 0; i < index - 1; i++)
			temp = temp->next;
		LinkedList<T>::Node * del = temp->next;
		temp->next = temp->next->next;;
		delete del;
	}
}

template<class T>
bool LinkedList<T>::isEmpty() const {
	return head == nullptr;
}

template<class T>
int LinkedList<T>::size() const {
	return getLength(head);
}

template<class T>
int LinkedList<T>::getLength(LinkedList<T>::Node * node) const {
	if( node == nullptr )
		return 0;
	else
		return 1 + getLength( node->next );
}

template<class T>
void LinkedList<T>::print() const {
	LinkedList<T>::Node * temp = head;
	while(temp != nullptr) {
		std::cout << temp << " ";
		temp = temp->next;
	}
	std::cout << std::endl;
}

template<class T>
void LinkedList<T>::reverse() {

	LinkedList<T>::Node * curr = head;
	LinkedList<T>::Node * prev = nullptr;
	LinkedList<T>::Node * next = nullptr;

	while(curr != nullptr) {
		next = curr->next;
		curr->next = prev;
		prev = curr;
		curr = next;;
	}

	head = prev;
}

#endif