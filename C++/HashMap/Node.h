#ifndef NODE_H
#define NODE_H

#include "Pair.h"

template <class K, class V>
struct Node {
		Pair<K, V> pair;
		Node * next;
		Node(const Pair<K, V> & p) {
			pair = p;
			next = NULL;
		}
		int length() {
			int len = 1;
			Node * temp = next;
			while(temp != NULL) {
				len++;
				temp = temp->next;
			}
			return len;
		}
};

#endif