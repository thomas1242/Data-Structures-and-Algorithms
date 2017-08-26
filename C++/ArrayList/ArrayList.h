#ifndef ARRAYLIST_H
#define ARRAYLIST_H
#include <iostream>
#include <string>
#include "LinearList.h"

template<class T>
void resize1DArray(T *& arr, int oldLength, int newLength) {
	if(newLength < 0) 
		throw std::runtime_error("new length must be >= 0");
	T * newArr = new T[newLength];
	for(int i = 0; i < oldLength; i++)
		newArr[i] = arr[i];
	delete[] arr;
	arr = newArr;
}

template <class T>
class ArrayList : public LinearList<T> {

	public: 
		ArrayList() : ArrayList(10) {}	
		ArrayList(int initCapacity);
		~ArrayList();	
		T& get(int index) const;	// inherited vritual functions
		void append(const T& theElement);
		void insert(int index, const T& theElement);
		void remove(int index);
		bool isEmpty() const;	
		int size() const;	
		std::string to_string() const;	

	private:
		T * arr;			// pointer to 1D array 
		int listLength;
		int arrayLength;
		
};

template<class T>
ArrayList<T>::ArrayList(int initCapacity) : listLength(0), arrayLength(initCapacity) {
	arr = new T[initCapacity];
}

template<class T>
ArrayList<T>::~ArrayList() {
	delete [] arr;
}

template<class T>
T& ArrayList<T>::get(int index) const {
	if(index < 0 || index >= listLength)
		throw std::runtime_error("ArrayList.get(): Invalid position");
	return arr[index];
}

template<class T>
void ArrayList<T>::insert(int index, const T& theElement) {
	if(index < 0 || index > listLength)
		throw std::runtime_error("ArrayList.insert(): Invalid position");

	if(listLength == arrayLength) {
		resize1DArray(arr, arrayLength, arrayLength * 2);
		arrayLength *= 2;
	}

	for(int i = arrayLength - 1; i > index; i--) 
		arr[i] = arr[i - 1];
	arr[index] = theElement;
	listLength++;
}

template<class T>
void ArrayList<T>::remove(int index) {
	if(index < 0 || index >= listLength)
		throw std::runtime_error("ArrayList.remove(): Invalid position");
	for(int i = index + 1; i < listLength; i++) 
		arr[i - 1] = arr[i];
	listLength--;
}

template<class T>
void ArrayList<T>::append(const T& theElement) {
	if(listLength == arrayLength) {
		resize1DArray(arr, arrayLength, arrayLength * 2);
		arrayLength *= 2;
	}
	arr[listLength++] = theElement;
}

template<class T>
std::string ArrayList<T>::to_string() const{
	std::string res;
	for(int i = 0; i < listLength; i++) 
		res += to_string(arr[i]) + " ";
	return res + '\n';
}

template<class T>
bool ArrayList<T>::isEmpty() const {
	return listLength == 0;
}

template<class T>
int ArrayList<T>::size() const {
	return listLength;
}

#endif
