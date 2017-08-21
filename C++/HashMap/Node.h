#ifndef NODE_H
#define NODE_H

#include "Pair.h"

template <class K, class V>
struct Node {
		Pair<K, V> pair;
		Node * next;
		Node() {
			next = NULL;
		}
		Node(const Pair<K, V> & p) {
			pair = p;
			next = NULL;
		}
};

#endif