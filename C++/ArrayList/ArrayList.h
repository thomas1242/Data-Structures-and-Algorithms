#ifndef ARRAYLIST_H
#define ARRAYLIST_H
#include <iostream>
#include <string>
#include "LinearList.h"
using namespace std;

template<class T>
void resize1DArray(T *& arr, int oldLength, int newLength) {
	if(newLength < 0) 
		throw runtime_error("new length must be >= 0");
	T * temp = new T[newLength];
	for(int i = 0; i < oldLength; i++)
		temp[i] = arr[i];
	delete[] arr;
	arr = temp;
}

template <class T>
class ArrayList : public LinearList<T> {
	public: 
		// constructor and destructor
		ArrayList();
		~ArrayList();

		// inherited virtual methods
		bool isEmpty() const;	// illegal to write to class member data
		int size() const;		// pure virtual function has no implementation
		T& get(int index) const;
		void remove(int index);
		void append(const T& theElement);
		void insert(int index, const T& theElement);

		// additional methods
		void print();

	private:
		T * arr;	// 1D array 
		int sizee;
		int arrLength;
};

// constructor
template<class T>
ArrayList<T>::ArrayList() {
	sizee = 0;
	arrLength = 8;
	arr = new T[8];
}

template<class T>
ArrayList<T>::~ArrayList() {
	delete [] arr;
}

template<class T>
T& ArrayList<T>::get(int index) const {
	if(index < 0 || index >= sizee)
		throw runtime_error("ArrayList.get(): Invalid position");
	return arr[index];
}

template<class T>
void ArrayList<T>::insert(int index, const T& theElement) {
	if(index < 0 || index > sizee)
		throw runtime_error("ArrayList.insert(): Invalid position");

	if(sizee == arrLength) {
		resize1DArray(arr, arrLength, arrLength * 2);
		arrLength *= 2;
	}

	for(int i = arrLength - 1; i > index; i--) 
		arr[i] = arr[i - 1];
	arr[index] = theElement;
	sizee++;
}

template<class T>
void ArrayList<T>::remove(int index) {
	if(index < 0 || index >= sizee)
		throw runtime_error("ArrayList.remove(): Invalid position");
	for(int i = index + 1; i < sizee; i++) 
		arr[i - 1] = arr[i];
	sizee--;
}

template<class T>
void ArrayList<T>::append(const T& theElement) {
	if(sizee == arrLength) {
		resize1DArray(arr, arrLength, arrLength * 2);
		arrLength *= 2;
	}
	arr[sizee++] = theElement;
}

template<class T>
bool ArrayList<T>::isEmpty() const {
	return sizee == 0;
}

template<class T>
int ArrayList<T>::size() const {
	return sizee;
}

template<class T>
void ArrayList<T>::print() {
	for(int i = 0; i < sizee; i++) 
		cout << arr[i] << " ";
	cout << endl;
}

#endif