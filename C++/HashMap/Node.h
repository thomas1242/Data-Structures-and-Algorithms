#ifndef NODE_H
#define NODE_H

template <class K, class V>
struct Node {
		K key;
		V val;
		Node * next;
		Node() {
			next = NULL;
		}
		Node(K k, V v) {
			key = k;
			val = v;
			next = NULL;
		}
};

#endif