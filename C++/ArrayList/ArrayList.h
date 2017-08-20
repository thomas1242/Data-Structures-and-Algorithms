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
		ArrayList(int initCapacity = 10);	
		~ArrayList();	
		T& get(int index) const;	// inherited abstract methods
		void append(const T& theElement);
		void insert(int index, const T& theElement);
		void remove(int index);
		bool isEmpty() const;	
		int size() const;	
		string to_string() const;	

	private:
		T * arr;	// 1D array 
		int _size;
		int length;
};

template<class T>
ArrayList<T>::ArrayList(int initCapacity) {
	_size = 0;
	length = initCapacity;
	arr = new T[initCapacity];
}

template<class T>
ArrayList<T>::~ArrayList() {
	delete [] arr;
}

template<class T>
string ArrayList<T>::to_string() const{
	string res;
	for(int i = 0; i < _size; i++) 
		res += std::to_string(arr[i]) + " ";
	return res + '\n';
}

template<class T>
T& ArrayList<T>::get(int index) const {
	if(index < 0 || index >= _size)
		throw runtime_error("ArrayList.get(): Invalid position");
	return arr[index];
}

template<class T>
void ArrayList<T>::insert(int index, const T& theElement) {
	if(index < 0 || index > _size)
		throw runtime_error("ArrayList.insert(): Invalid position");

	if(_size == length) {
		resize1DArray(arr, length, length * 2);
		length *= 2;
	}

	for(int i = length - 1; i > index; i--) 
		arr[i] = arr[i - 1];
	arr[index] = theElement;
	_size++;
}

template<class T>
void ArrayList<T>::remove(int index) {
	if(index < 0 || index >= _size)
		throw runtime_error("ArrayList.remove(): Invalid position");
	for(int i = index + 1; i < _size; i++) 
		arr[i - 1] = arr[i];
	_size--;
}

template<class T>
void ArrayList<T>::append(const T& theElement) {
	if(_size == length) {
		resize1DArray(arr, length, length * 2);
		length *= 2;
	}
	arr[_size++] = theElement;
}

template<class T>
bool ArrayList<T>::isEmpty() const {
	return _size == 0;
}

template<class T>
int ArrayList<T>::size() const {
	return _size;
}

#endif