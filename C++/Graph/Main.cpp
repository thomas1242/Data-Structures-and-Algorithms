#include <iostream>
#include "Graph.h"

int main() {
				
	Graph<int> * graph1 = new Graph<int>();	
	graph1->addEdge(1, 5);
	graph1->addEdge(1, 3);
	graph1->addEdge(1, 9);
	graph1->addEdge(5, 9);
	graph1->addEdge(9, 5);
	graph1->addEdge(9, 15);
	graph1->addEdge(15, 20);

	Graph<std::string> * graph2 = new Graph<std::string>();	
	graph2->addEdge("one", "five");
	graph2->addEdge("one", "three");
	graph2->addEdge("one", "nine");
	graph2->addEdge("five", "nine");
	graph2->addEdge("nine", "five");
	graph2->addEdge("nine", "fifteen");
	graph2->addEdge("fifteen", "twenty");



	bool res = true;

	res &= graph1->DFS(1, 15);
	// res &= graph2->DFS("one", "twenty");
	
	if(res)	std::cout << "\033[1;32mPASS\033[0m" << std::endl;
	else 	std::cout << "\033[1;31mFAIL\033[0m" << std::endl;
	
	graph1->~Graph();
	graph2->~Graph();

	return 0;
}

