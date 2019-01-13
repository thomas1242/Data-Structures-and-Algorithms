#ifndef ARRAYLIST_H
#define ARRAYLIST_H
#include <iostream>
#include <string>
#include "LinearList.h"

template<class T>
void resize1DArray(T *& oldArr, int oldLength, int newLength) {
	if(newLength < 0)
		throw std::length_error("new length must be >= 0");

	T * newArr = new T[newLength];
	for(int i = 0; i < oldLength; i++)
		newArr[i] = oldArr[i];
	delete[] oldArr;
	oldArr = newArr;
}

template <class T>
class ArrayList : public LinearList<T> {

	private:
		T * arr = nullptr;
		int listLength;
		int arrayLength;

	public:
		ArrayList(int initCapacity = 0) : arr{new T[initCapacity]},			// default constructor
										  listLength(initCapacity),
		                                  arrayLength(initCapacity) {}

		ArrayList(const ArrayList<T>& old) : arr{new T[old.arrayLength]},	// copy constructor
											 listLength{old.listLength},
											 arrayLength{old.arrayLength}
		{
            for(int i = 0; i < old.listLength; i++)  arr[i] = old.arr[i];
		}

		ArrayList<T>& operator=(const ArrayList<T>& old) {					// copy assignment
		    delete[] arr;
            arr = new T[old.arrayLength];
            for(int i = 0; i < old.listLength; i++) arr[i] = old.arr[i];
            listLength = old.listLength;
            arrayLength = old.arrayLength;
            return *this;
		}

		ArrayList(ArrayList<T>&& old) : arr{old.arr},						// move constructor
										listLength{old.listLength},
										arrayLength{old.arrayLength}
		{
			old.arr = nullptr;
		}

		ArrayList<T>& operator=(ArrayList<T>&& old) {						// move assignment
			delete[] arr;
            arr = old.arr;
            listLength = old.listLength;
            arrayLength = old.arrayLength;
			old.arr = nullptr;
			return *this;
		}

		~ArrayList() { delete[] arr; };									    // destructor

		T& get(int index) const;											// inherited virtual functions
		T& operator[](int i) { return get(i); }
		void append(const T& theElement);
		void insert(int index, const T& theElement);
		void remove(int index);
		std::string to_string() const;
		bool isEmpty() const { return listLength == 0; };
		int  size() const { return listLength; };

};

template<class T>
T& ArrayList<T>::get(int index) const {
	if(index < 0 || index >= listLength)
		throw std::out_of_range("ArrayList.get(): Invalid position");
	return arr[index];
}

template<class T>
void ArrayList<T>::insert(int index, const T& theElement) {
	if(index < 0 || index > listLength)
		throw std::out_of_range("ArrayList.insert(): Invalid position");

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
		throw std::out_of_range("ArrayList.remove(): Invalid position");
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
		res += std::to_string(arr[i]) + " ";
	return res + '\n';
}

#endif
