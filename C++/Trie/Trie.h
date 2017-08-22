#ifndef TRIE_H
#define TRIE_H
#include <string>
#include <iostream>

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
		void insertWord(const std::string & word);
		bool contains(const std::string & word) const;
		void printAll() const;

	private:
		Node * root;
		void printAll(const Node * node, std::string prefix) const;
};

Trie::Trie() {
	root = new Node(' ');
}

void Trie::printAll() const {
	printAll(root, "");
}

void Trie::printAll(const Node * node, std::string prefix) const {
	if(node == NULL)
		return;

	prefix = prefix + node->c;	// update prefix associated with this node

	if(node->isWord == true)
		std::cout << prefix << std::endl;

	for(int i = 0; i < 26; i++) 
		printAll(node->children[i], prefix);
}



void Trie::insertWord(const std::string & word) {

	Node * temp = root;
	for(int i = 0; i < word.length(); i++) {
		char c = word.at(i);
		int index = c - 'a';	

		if(temp->children[index] == NULL)
			temp->children[index] = new Node( c );
		temp = temp->children[index];
	}

	temp->isWord = true;
}


bool Trie::contains(const std::string & word) const {

	Node * temp = root;
	for(int i = 0; i < word.length(); i++) {
		char c = word.at(i);
		int index = c - 'a';	

		if(temp->children[index] == NULL)
			return false;
		temp = temp->children[index];
	}

	if(temp->isWord == true)
		return true;
	else
		return false;	
}














Trie::~Trie() {	// TODO : visit each Node and call its destructor
}



#endif
