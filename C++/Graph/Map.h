#ifndef MAP_H
#define MAP_H

template <class K, class V>
class Map {

	public:
		virtual ~Map() {}
		virtual int size() const = 0;
		virtual void insert(const K& key, const V& val) = 0;
		virtual V* find(const K& k) const = 0;
		virtual void erase(const K& key) = 0;

};


#endif