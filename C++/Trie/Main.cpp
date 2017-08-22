#include <iostream>
#include "Trie.h"

int main() {

	Trie * trie = new Trie();

	trie->insertWord( "tea" );
	trie->insertWord( "ted" );
	trie->insertWord( "to" );
	trie->insertWord( "ten" );
	trie->insertWord( "tent" );

	trie->printAll();

	bool res = true;

	res &= !trie->contains("te");
	res &= trie->contains("tea");

	if(res)	std::cout << "\033[1;32mPASS\033[0m" << std::endl;
	else 	std::cout << "\033[1;31mFAIL\033[0m" << std::endl;

	trie->~Trie();

	return 0;
}
