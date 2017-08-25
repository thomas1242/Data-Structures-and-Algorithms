#ifndef GRAPH_H
#define GRAPH_H
#include <iostream>
#include "HashMap.h"
#include "LinkedList.h"

template <class T>
class Graph {

	struct Node {
		T data;
		bool visited;
		LinkedList<Node*> adjacent;
		Node() {}
		Node(const T& d) : data(d), visited(false) {}
	};

	public:
		Graph();
		~Graph();
		void addEdge(const T& v1, const T& v2);
		bool DFS(const T& v1, const T& v2) const;
		
	private:
		HashMap<T, Graph<T>::Node> map;
};

template <class T>
Graph<T>::Graph() {}

template <class T>
Graph<T>::~Graph() {}

template <class T>					// directed graph
void Graph<T>::addEdge(const T& v1, const T& v2) {	// add edge from v1 to v2
	if(map.find(v1) == NULL)	
		map.insert(v1, Graph<T>::Node(v1));
	if(map.find(v2) == NULL)
		map.insert(v2, Graph<T>::Node(v2));
										
	map.find(v1)->adjacent.append( map.find(v2) );
}

template <class T>
bool Graph<T>::DFS(const T& v1, const T& v2) const {
	if(v1 == v2)
		return true;

	Graph<T>::Node * node = map.find(v1);
	node->visited = true;

	for(int i = 0; i < node->adjacent.size(); i++) 
		if(node->adjacent.get(i)->visited == false) 
			return DFS(node->adjacent.get(i)->data, v2);
		
	return false;
}

#endif
