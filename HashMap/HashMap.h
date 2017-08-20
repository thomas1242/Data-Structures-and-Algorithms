#ifndef HASHMAP_H
#define HASHMAP_H

#include "Map.h"
#include <string>

template <class K, class V>
class HashMap : public Map<K, V> {

	public:
		HashMap();
		~HashMap();
		int size() const;

};

template <class K, class V>
HashMap<K, V>::HashMap() {

}

template <class K, class V>
HashMap<K, V>::~HashMap() {

}

template <class K, class V>
int HashMap<K, V>::size() const {
	return 0;
}






#endif