/*
	Implements a hash table that takes a key and a value
	The key is used to map the value to a spot on the table
	
*/

#include <iostream>
#include "stdafx.h"
#include <cstdlib>

class HashEntry {
private:
	int key;
	int value;
public:
	HashEntry(int key, int value) {
		this->key = key;
		this->value = value;
	}

	int getKey() {
		return key;
	}

	int getValue() {
		return value;
	}
};

class HashMap {
private:
	HashEntry **table;
	int tableSize;
public:
	HashMap(int size) {
		tableSize = size;
		table = new HashEntry*[tableSize];
		for (int i = 0; i < tableSize; i++)
			table[i] = NULL;
	}

	int get(int key) {
		int hash = (key % tableSize);
		while (table[hash] != NULL && table[hash]->getKey() != key)
			hash = (hash + 1) % tableSize;
		if (table[hash] == NULL)
			return -1;
		else
			return table[hash]->getValue();
	}

	void put(int value) {
		int key = value;
		int hash = (value % tableSize);
		while (table[hash] != NULL && table[hash]->getKey() != key)
			hash = (hash + 1) % tableSize;
		if (table[hash] != NULL)
			delete table[hash];
		table[hash] = new HashEntry(key, value);
	}

	~HashMap() {
		for (int i = 0; i < tableSize; i++)
			if (table[i] != NULL)
				delete table[i];
		delete[] table;
	}
};

int main()
{
	int arr[] = { 1, 2, 3, 4, 5, 5, 4, 3, 2, 8, 6, 3, 5, 7, 3, 2 };

	HashMap newTable((sizeof(arr) / sizeof(arr[0])));
	printf ("%d", (sizeof(arr) / sizeof(arr[0])));
	
    return 0;
}

