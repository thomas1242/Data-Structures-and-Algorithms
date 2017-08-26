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

	Graph<char> graph10;				// successfully runs on Linux, but not MacOS
	graph10.addEdge('1', '5');
	graph10.addEdge('1', '3');
	graph10.addEdge('1', '9');
	graph10.addEdge('5', '9');
	graph10.addEdge('9', '5');
	graph10.addEdge('9', '7');
	graph10.addEdge('7', '8');
	
	
	/*
	Graph<const char *> graph2;				// successfully runs on Linux, but not MacOS
	graph2.addEdge("one", "five");
	graph2.addEdge("one", "three");
	graph2.addEdge("one", "nine");
	graph2.addEdge("five", "nine");
	graph2.addEdge("nine", "five");
	graph2.addEdge("nine", "fifteen");
	graph2.addEdge("fifteen", "twenty");
	*/
	
	// Graph<std::string> graph3;			// successfully runs on MacOS, but not Linux
	// graph3.addEdge("one", "five");
	// graph3.addEdge("one", "three");
	// graph3.addEdge("one", "nine");
	// graph3.addEdge("five", "nine");
	// graph3.addEdge("nine", "five");
	// graph3.addEdge("nine", "fifteen");
	// graph3.addEdge("fifteen", "twenty");

	bool res = true;
	res &= graph1.DFS(1, 20);
	graph1.clearVisited();
	res &= graph1.BFS(1, 20);

	res &= graph10.DFS('1', '8');
	graph10.clearVisited();
	res &= graph10.BFS('1', '8');
	
	if(res)	std::cout << "\033[1;32mPASS\033[0m" << std::endl;
	else 	std::cout << "\033[1;31mFAIL\033[0m" << std::endl;

	return 0;
}

