#ifndef STACK_H
#define STACK_H

template <class T>
class Stack {

	public: 
		virtual ~Stack() {}
		virtual bool isEmpty() const = 0;
		virtual int size() const = 0;		
		virtual T& top() const = 0;
		virtual void pop() = 0;
		virtual void push(const T& theElement) = 0;

};

#endif