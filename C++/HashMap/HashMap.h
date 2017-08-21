#ifndef HASHMAP_H
#define HASHMAP_H

#include "Map.h"
#include "Node.h"
#include <string>

template <class K, class V>
class HashMap : public Map<K, V> {
	public:
		HashMap();
		~HashMap();
		int size() const;
	private:
		Node<K, V> ** buckets;	// pointer to adjacency list
		int numEntries;
		int arrLength;
};

template <class K, class V>
HashMap<K, V>::HashMap() {
	numEntries = 0;
	arrLength = 8;
	buckets = new Node<K, V> * [arrLength];
	for(int i = 0; i < arrLength; i++)
		buckets[i] = NULL;
}

template <class K, class V>
HashMap<K, V>::~HashMap() {

}

template <class K, class V>
int HashMap<K, V>::size() const {
	return 0;
}






#endif