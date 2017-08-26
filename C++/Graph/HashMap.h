#ifndef HASHMAP_H
#define HASHMAP_H

#include "Map.h"
#include <iostream>
#include <functional>

template <class K, class V>
class HashMap : public Map<K, V> {

	struct Node {
		K key;
		V value;
		Node * next;
		Node() : key(), value(), next(nullptr) {}
		Node(const K& k, const V& v) : next(nullptr) {
			key = k;	
			value = v;
		}
		// ~Node() { if(next) delete next; }
	};

	public:
		HashMap();
		~HashMap();
		int size() const;
		V& find(const K& key) const;
		void insert(const K& key, const V& value);
		void erase(const K& key);
		bool contains(const K& key) const;
		V ** getValues();

	private:
		HashMap<K, V>::Node ** buckets;	// pointer to adjacency list
		int arrLength;
		std::hash<K> hash_fn;
};

template <class K, class V>
HashMap<K, V>::HashMap() {
	arrLength = 8;
	buckets = new HashMap<K, V>::Node * [arrLength];
	for(int i = 0; i < arrLength; i++)
		buckets[i] = nullptr;
}

template <class K, class V>
HashMap<K, V>::~HashMap() {
	HashMap<K, V>::Node * curr;
	HashMap<K, V>::Node * next;

	for(int i = 0; i < arrLength; i++) {
		curr = buckets[i];
		while(curr != nullptr) {	// delete nodes at bucket i
			next = curr->next;
			delete curr;
			curr = next;
		}
	}

	delete[] buckets;	// delete array of pointers
}

template <class K, class V>
void HashMap<K, V>::insert(const K& key, const V& val) {

	int bucket = (int) hash_fn(key) % arrLength;

	if(buckets[bucket] == nullptr)
		buckets[bucket] = new HashMap<K, V>::Node(key, val);
	else {
		HashMap<K, V>::Node * temp = buckets[bucket];
		while(temp->key == key && temp->next != nullptr) 
			temp = temp->next;
		if(temp->key == key) {
			erase(key);
			insert(key, val);
		}
		else
			temp->next = new HashMap<K, V>::Node(key, val);
	}
}

template <class K, class V>
V& HashMap<K, V>::find(const K& key) const {
	int bucket = (int) hash_fn(key) % arrLength;

	HashMap<K, V>::Node * temp = buckets[bucket];
	while(temp != nullptr && temp->key != key)
		temp = temp->next;
	if(temp == nullptr)
		throw std::runtime_error("cannot find key");
	else
		return temp->value;
}

template <class K, class V>
bool HashMap<K, V>::contains(const K& key) const {
	int bucket = (int) hash_fn(key) % arrLength;

	HashMap<K, V>::Node * temp = buckets[bucket];
	while(temp != nullptr && temp->key != key)
		temp = temp->next;
	if(temp == nullptr)
		return false;
	else
		return true;
}

template <class K, class V>
void HashMap<K, V>::erase(const K& key) {
	int bucket = (int) hash_fn(key) % arrLength;

	HashMap<K, V>::Node * temp = buckets[bucket];

	if(temp->key == key) {
		buckets[bucket] = buckets[bucket]->next;
		delete temp;
	}
	else {
		while(temp->next != nullptr && temp->next->key != key)
			temp = temp->next;
		if(temp->next == nullptr)
			return;	// not found
		else {
			HashMap<K, V>::Node * tmp = temp->next;
			temp->next = temp->next->next;
			delete tmp;
		}
	}
}

template <class K, class V>
int HashMap<K, V>::size() const {
	int sum = 0;
	for(int i = 0; i < arrLength; i++) {
		int len = 0;
		HashMap<K, V>::Node * temp = buckets[i];
		while(temp != nullptr) {
			len++;
			temp = temp->next;
		}
		sum += len;
	}
	return sum;
}

template <class K, class V>
V ** HashMap<K, V>::getValues() {
	V ** vals = new V * [ size() ];

	int n = 0;
	HashMap<K, V>::Node * curr;
	for(int i = 0; i < arrLength; i++) {
		curr = buckets[i];
		while(curr != nullptr) {	
			vals[n++] = &(curr->value);	
			curr = curr->next;
		}
	}
	
	return vals;
}

#endif