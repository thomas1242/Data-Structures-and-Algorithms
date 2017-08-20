#ifndef SINGLYLINKEDLIST_H
#define SINGLYLINKEDLIST_H

#include <iostream>
#include <string>
using namespace std;

template <class T>
struct Node {
    public:
        T data;
        Node* next;
       	Node(T d) {
       		data = d;
       		next = NULL;
       	}
};

template <class T>
class SinglyLinkedList {

	public: 
		SinglyLinkedList();	
		~SinglyLinkedList();	
		T& get(int index) const;	// inherited abstract methods
		void append(const T& theElement);
		void insert(int index, const T& theElement);
		void remove(int index);
		bool isEmpty() const;	
		int size() const;	
		void print() const;	
		T& pop();	// additional methods
		void reverse();

	private:
		Node<T> * head;	// 1D array 
		Node<T> * tail;
		int _size;
		int getLength(Node<T> * node) const;	// helper function
};

template <class T>
SinglyLinkedList<T>::SinglyLinkedList() {
	head = NULL;
	tail = NULL;
	_size = 0;
}

template <class T>
SinglyLinkedList<T>::~SinglyLinkedList() {
	while(head != NULL) {
		next = head->next;
		delete head;
		head = next;
	}
}

template <class T>
T& SinglyLinkedList<T>::get(int index) const {
	if(index < 0 || index >= getLength(head))
		throw std::runtime_error("get() : index out of bounds");

	if(index == 0) 
		return head->data;

	Node<T> * temp = head;
	for(int i = 0; i < index - 1; i++)
		temp = temp->next;

	return temp->data;
}


template <class T>
T& SinglyLinkedList<T>::pop() {
	if (head == NULL) 
		throw std::runtime_error("Cannor pop() : empty list");
	else {
		Node<T> * temp = head;
		head = head->next;
		return temp->data;
	}
}

template <class T>
void SinglyLinkedList<T>::append(const T& theElement) {
	if(head == NULL)
		head = new Node<T>(theElement);
	else {
		Node<T> * temp = head;
		while(temp->next != NULL)
			temp = temp->next;
		temp->next = new Node<T>(theElement);
	}
}

template<class T>
bool SinglyLinkedList<T>::isEmpty() const {
	return head == NULL;
}

template<class T>
int SinglyLinkedList<T>::size() const {
	return getLength(head);
}

template<class T>
int SinglyLinkedList<T>::getLength(Node<T> * node) const {
	if(node == NULL)
		return 0;
	else
		return 1 + getLength( node->next );
}

template<class T>
void SinglyLinkedList<T>::print() const {
	Node<T> * temp = head;
	while(temp != NULL) {
		cout << temp << " ";
		temp = temp->next;
	}
	cout << endl;
}

template<class T>
void SinglyLinkedList<T>::reverse() {
	Node<T> * curr = head;
	Node<T> * prev = NULL;
	Node<T> * next = NULL;
	while(curr != NULL) {
		next = curr->next;
		curr->next = prev;
		prev = curr;
		curr = next;;
	}
	head = prev;
}






#endif