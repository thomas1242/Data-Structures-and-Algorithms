#ifndef HASHMAP_H
#define HASHMAP_H

#include "Map.h"
#include <set>
#include <iostream>
#include <functional>

template <class K, class V>
class HashMap : public Map<K, V> {

	struct Node {
		K key;
		V value;
		Node * next;
		Node(const K& k, const V& v) : key(k), value(v), next(nullptr) {}
	};

	public:
		HashMap();
		~HashMap();
		int size() const;
		V* find(const K& key) const;
		void insert(const K& key, const V& value);
		void erase(const K& key);
		std::set<K> keySet();
		std::set<V> values();
		std::set<std::pair<K, V>> entrySet();

	private:
		HashMap<K, V>::Node ** buckets;	// adjacency list
		void resize();
		int numEntries = 0;
		int arrLength  = 8;
		double loadFactor = 0.75;
		std::hash<K> hash_fn;
};

template <class K, class V>
HashMap<K, V>::HashMap() { 
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

	delete[] buckets;				// delete array of pointers
}

template <class K, class V>
void HashMap<K, V>::insert(const K& key, const V& val) {
	if(numEntries > loadFactor * arrLength) 
		resize();

	int bucket = (int) hash_fn(key) % arrLength; 

	if(buckets[bucket] == nullptr)
		buckets[bucket] = new HashMap<K, V>::Node(key, val);
	else {
		HashMap<K, V>::Node * temp = buckets[bucket];
		while(temp->key == key && temp->next != nullptr) 
			temp = temp->next;
		if(temp->key == key) {
			temp->value = val;
			return;
		}
		else
			temp->next = new HashMap<K, V>::Node(key, val);
	}

	++numEntries;
}

template <class K, class V>
void HashMap<K, V>::resize() {
	std::set<std::pair<K, V>> entries = entrySet();

	delete[] buckets;
	buckets = new HashMap<K, V>::Node * [arrLength *= 2];
	for(int i = 0; i < arrLength; i++) 
		buckets[i] = nullptr;

	numEntries = 0;
	for(std::pair<K, V> entry : entries)
		insert(entry.first, entry.second);
}

template <class K, class V>
V* HashMap<K, V>::find(const K& key) const {
	int bucket = (int) hash_fn(key) % arrLength;

	HashMap<K, V>::Node * temp = buckets[bucket];
	while(temp != nullptr && temp->key != key)
		temp = temp->next;
	if(temp == nullptr)
		return nullptr;
	else
		return &(temp->value);
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
			HashMap<K, V>::Node * del = temp->next;
			temp->next = temp->next->next;
			delete del;
		}
	}

	numEntries--;
}

template <class K, class V>
std::set<K> HashMap<K, V>::keySet() {
	std::set<K> keys;
	for(int i = 0; i < arrLength; i++) { 
		HashMap<K, V>::Node * temp = buckets[i];
		while(temp != nullptr) {
			keys.insert(temp->key);
			temp = temp->next;
		} 
	}
	return keys;
}

template <class K, class V>
std::set<V> HashMap<K, V>::values() {
	std::set<V> values;
	for(int i = 0; i < arrLength; i++) { 
		HashMap<K, V>::Node * temp = buckets[i];
		while(temp != nullptr) {
			values.insert(temp->value);
			temp = temp->next;
		} 
	}
	return values;
}

template <class K, class V>
std::set<std::pair<K, V>> HashMap<K, V>::entrySet() {
	std::set< std::pair<K, V> > entries;
	for(int i = 0; i < arrLength; i++) { 
		HashMap<K, V>::Node * temp = buckets[i];
		while(temp != nullptr) {
			entries.insert(std::make_pair(temp->key, temp->value));
			temp = temp->next;
		} 
	}
	return entries;
}

template <class K, class V>
int HashMap<K, V>::size() const {
	return numEntries;
}

#endif
