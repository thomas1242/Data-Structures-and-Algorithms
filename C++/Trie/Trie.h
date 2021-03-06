#ifndef TRIE_H
#define TRIE_H

#include <string>
#include <iostream>

struct Node {
	Node ** children;	// pointer to array of child nodes
	bool isWord;		// does this node mark end of prefix
	char c;				// character associated with this node
	
	Node() : Node(' ') {}

	Node(char character) : isWord(false), c(character) {
		children = new Node * [26];
		for(int i = 0; i < 26; i++) 
			children[i] = nullptr;
	}

	~Node() { delete[] children; }
};

class Trie {
	
	public:
		Trie();
		~Trie();
		void insertWord(const std::string & word);
		bool containsWord(const std::string & word) const;
		void printAll() const;

	private:
		Node * root;
		void printAll(const Node * node, std::string prefix) const;
		void deleteTree(Node *& node);
};

Trie::~Trie() {
	deleteTree(root);
}

void Trie::deleteTree(Node *& node) {
	if(node == nullptr) 
		return;
	for(int i = 0; i < 26; i++) 
		deleteTree( node->children[i] );
	delete node;
}

Trie::Trie() {
	root = new Node(' ');
}

void Trie::insertWord(const std::string & word) {

	Node * temp = root;
	for(unsigned int i = 0; i < word.length(); i++) {
		char c = word.at(i);
		int index = c - 'a';	

		if(temp->children[index] == NULL)
			temp->children[index] = new Node( c );
		temp = temp->children[index];
	}

	temp->isWord = true;
}

bool Trie::containsWord(const std::string & word) const {

	Node * temp = root;
	for(unsigned int i = 0; i < word.length(); i++) {
		char c = word.at(i);
		int index = c - 'a';	

		if(temp->children[index] == NULL)
			return false;
		temp = temp->children[index];
	}

	return temp->isWord;
}

void Trie::printAll() const {
	printAll(root, "");
}

void Trie::printAll(const Node * node, std::string prefix) const {
	if(node == NULL)
		return;

	prefix = prefix + node->c;	// update prefix associated with this node

	if(node->isWord == true)
		std::cout << prefix << ", ";

	for(int i = 0; i < 26; i++) 
		printAll(node->children[i], prefix);
}

#endif
