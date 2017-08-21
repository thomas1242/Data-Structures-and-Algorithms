#ifndef PAIR_H
#define PAIR_H

template <class K, class V>
struct Pair {
	K first;
	V second;
	Pair() {}
	Pair(const K& k, const V& v) {
		first = k;
		second = v;
	}
};

#endif