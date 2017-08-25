#ifndef LINEARLIST_H
#define LINEARLIST_H

template <class T>
class LinearList {

	public: 
		virtual ~LinearList() {}
		virtual bool isEmpty() const = 0;
		virtual int size() const = 0;		
		virtual T& get(int index) const = 0;
		virtual void remove(int index) = 0;
		virtual void insert(int index, const T& theElement) = 0;
		virtual void append(const T& theElement) = 0;

};

#endif
