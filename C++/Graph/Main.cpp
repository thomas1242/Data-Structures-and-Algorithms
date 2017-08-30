#include <iostream>
#include "Graph.h"

int main() {
				
	Graph<int> graph1;	
	graph1.addEdge(1, 5);
	graph1.addEdge(1, 3);
	graph1.addEdge(1, 9);
	graph1.addEdge(5, 9);
	graph1.addEdge(9, 5);
	graph1.addEdge(9, 15);
	graph1.addEdge(15, 20);

	Graph<char> graph2;				
	graph2.addEdge('1', '5');
	graph2.addEdge('1', '3');
	graph2.addEdge('1', '9');
	graph2.addEdge('5', '9');
	graph2.addEdge('9', '5');
	graph2.addEdge('9', '7');
	graph2.addEdge('7', '8');

	bool res = true;
	res &= graph1.DFS(1, 20);
	graph1.clearVisited();
	res &= graph1.BFS(1, 20);

	res &= graph2.DFS('1', '8');
	graph2.clearVisited();
	res &= graph2.BFS('1', '8');
	
	if(res)	std::cout << "\033[1;32mPASS\033[0m" << std::endl;
	else 	std::cout << "\033[1;31mFAIL\033[0m" << std::endl;

	return 0;
}

