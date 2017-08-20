#ifndef LINEARLIST_H
#define LINEARLIST_H
#include <iostream>
#include <string>
using namespace std;

template <class T>
class LinearList {
	public: 
		virtual ~LinearList() {}
		virtual bool isEmpty() const = 0;	// illegal to write to class member data
		virtual int size() const = 0;		// pure virtual function has no implementation
		virtual T& get(int index) const = 0;
		virtual void remove(int index) = 0;
		virtual void insert(int index, const T& theElement) = 0;
		virtual void append(const T& theElement) = 0;
};

#endif