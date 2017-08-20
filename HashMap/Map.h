#ifndef MAP_H
#define MAP_H

template <class K, class V>
class Map {

	public:
		virtual ~Map() {}
		virtual int size() const = 0;

};


#endif