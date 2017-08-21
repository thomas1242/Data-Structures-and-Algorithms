#ifndef TRIE_H
#define TRIE_H

struct Node {
	Node ** children;	// pointer to array of child nodes
	bool isWord;		// does this node mark end of prefix
	char c;			// character associated with this node
	Node() {
		isWord = false;
		children = new Node * [26];
	}
	Node(char character) {
		c = character;
		isWord = false;
		children = new Node * [26];
	}
};

class Trie {
	
	public:
		Trie();
		~Trie();

	private:
		Node * root;


};

Trie::Trie() {
	root = new Node(' ');
}

Trie::~Trie() {
}



#endif
