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
		LinkedList<Node> * adjacent;
		Node() {
			adjacent = nullptr;
			visited = false;
		}
		Node(T data) {
			this->data = data;
			adjacent = new LinkedList<Node>();
			visited = false;
		}
	};

	public:
		Graph();
		~Graph();
		void addEdge(const T& v1, const T& v2);
		bool DFS(const T& v1, const T& v2);
		
	private:
		HashMap<T, Graph<T>::Node*> * map;


};

template <class T>
Graph<T>::Graph() {
	map = new HashMap<T, Graph<T>::Node*>();
}

template <class T>
Graph<T>::~Graph() {
	map.~Map<T, Graph<T>::Node*>();
}

template <class T>									// directed graph
void Graph<T>::addEdge(const T& v1, const T& v2) {	// add edge from v1 to v2

	if(map->find(v1) == NULL)	
		map->insert(v1, new Graph<T>::Node(v1));
	if(map->find(v2) == NULL)
		map->insert(v2, new Graph<T>::Node(v2));
										
	(*map->find(v1))->adjacent->append( *(*map->find(v2)) ); // oh god
}

template <class T>
bool Graph<T>::DFS(const T& v1, const T& v2) {
	if(v1 == v2)
		return true;

	Graph<T>::Node * node = *(map->find(v1));
	node->visited = true;

	for(int i = 0; i < node->adjacent->size(); i++) 
		if(node->adjacent->get(i).visited == false) 
			return DFS(node->adjacent->get(i).data, v2);
		
	return false;
}


#endif






















































