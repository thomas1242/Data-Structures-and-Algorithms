#ifndef HASHMAP_H
#define HASHMAP_H

#include "Map.h"
#include "Node.h"

template <class K, class V>
class HashMap : public Map<K, V> {
	public:
		HashMap();
		~HashMap();
		int size() const;
		V* find(const K& k);
		void insert(const K& key, const V& val);
		void erase(const K& key);

	private:
		Node<K, V> ** buckets;	// pointer to adjacency list
		int arrLength;
};

template <class K, class V>
HashMap<K, V>::HashMap() {
	arrLength = 8;
	buckets = new Node<K, V> * [arrLength];
	for(int i = 0; i < arrLength; i++)
		buckets[i] = NULL;
}

template <class K, class V>
HashMap<K, V>::~HashMap() {
	for(int i = 0; i < arrLength; i++) 
		delete buckets[i];
	delete[] buckets;
}

template <class K, class V>
void HashMap<K, V>::insert(const K& key, const V& val) {
	Pair<K, V> p(key, val);

	int bucket = (int) std::hash<K>()(key) % arrLength;

	if(buckets[bucket] == NULL)
		buckets[bucket] = new Node<K, V>(p);
	else {
		Node<K, V> * temp = buckets[bucket];
		while(temp->pair.first == key && temp->next != NULL) 
			temp = temp->next;
		if(temp->pair.first == key) {
			erase(key);
			insert(key, val);
		}
		else
			temp->next = new Node<K, V>(p);
	}
}

template <class K, class V>
V* HashMap<K, V>::find(const K& key) {
	int bucket = (int) std::hash<K>()(key) % arrLength;

	Node<K, V> * temp = buckets[bucket];
	while(temp != NULL && temp->pair.first != key)
		temp = temp->next;
	if(temp == NULL)
		return NULL;
	else
		return &(temp->pair.second);
}

template <class K, class V>
void HashMap<K, V>::erase(const K& key) {
	int bucket = (int) std::hash<K>()(key) % arrLength;

	Node<K, V> * temp = buckets[bucket];

	if(temp->pair.first == key) {
		buckets[bucket] = buckets[bucket]->next;
		delete temp;
	}
	else {
		while(temp->next != NULL && temp->next->pair.first != key)
			temp = temp->next;
		if(temp->next == NULL)
			return;	// not found
		else {
			Node<K, V> * tmp = temp->next;
			temp->next = temp->next->next;
			delete tmp;
		}
	}
}

template <class K, class V>
int HashMap<K, V>::size() const {
	return 0;
}






#endif