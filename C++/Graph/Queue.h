#ifndef QUEUE_H
#define QUEUE_H

template <class T>
class Queue {

	public: 
		virtual ~Queue() {}
		virtual bool isEmpty() const = 0;
		virtual int size() const = 0;		
		virtual T& front() const = 0;
		virtual void pop() = 0;
		virtual void add(const T& theElement) = 0;
};

#endif
