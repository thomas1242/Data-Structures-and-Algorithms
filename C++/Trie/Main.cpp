#include <iostream>
#include "Trie.h"

int main() {

	Trie * trie = new Trie();

	std::string words[] = {"zap", "zoo", "tea", "ted", "tent", "app", "apple", "ban"};
	for(int i = 0; i < 8; i++) 
		trie->insertWord( words[i] );
	trie->printAll();

	bool res = true;
	for(int i = 0; i < 8; i++) 
		res &= trie->containsWord( words[i] );
	res &= !trie->containsWord("te");
	res &= !trie->containsWord("appl");

	if(res)	std::cout << "\n\033[1;32mPASS\033[0m" << std::endl;
	else 	std::cout << "\n\033[1;31mFAIL\033[0m" << std::endl;

	trie->~Trie();

	return 0;
}
