#include "stdafx.h"
#include <iostream>
#include <vector>
#include <cstdlib>
#include <ctime>
#include <string>


using namespace std;

/*
	Demonstrates the implementation of Shellsort in C++. It also uses a clock to indicate the efficiency of the sorting method. 
*/

// Shellsort
template <typename Comparable>
unsigned long shellsort(vector<Comparable> & a)
{
	unsigned long counter = 0;

	int gapArray[20];

	//Hibbard increments

	for (int i = 19; i >= 0; i--)
	{
		gapArray[i] = (pow(2, i) - 1);
	}

	/*
	//Ciura increments, extended using Tokuda's sequence
	// Manually sets first 9 values to known best numbers
	gapArray[0] = 1;
	gapArray[1] = 4;
	gapArray[2] = 10;
	gapArray[3] = 23;
	gapArray[4] = 57;
	gapArray[5] = 132;
	gapArray[6] = 301;
	gapArray[7] = 701;
	gapArray[8] = 1750;

	// Generates the remaining values based on Dovgopol's 1750, using Tokada's sequence
	int tempPow = 1;
	for (int i = 9; i < 20; i++)
	{
	//gapArray[i] = (gapArray[i + 1] * 2.25);
	gapArray[i] = 1750 * pow(2.25, tempPow);
	tempPow++;
	}
	*/

	//Prints out the gap values used for bugtesting
	/*
	for (int i = 0; i <= 19; i++)
	{
	cout << gapArray[i] << endl;
	}
	*/

	for (int p = 0; p < 20; p++)
	for (unsigned int i = gapArray[p]; i < a.size(); i++)
	{
		Comparable tmp = a[i];
		unsigned int j = i;

		for (; j >= gapArray[p]; j -= gapArray[p])
		{
			counter++;
			if (!(tmp < a[j - gapArray[p]])) break;
			a[j] = a[j - gapArray[p]];
		}

		a[j] = tmp;
	}
	return counter;
}

const int N = 1000000;

int main()
{
	vector<int> rnumbers;
	clock_t start, finish;
	double duration;

	srand(42);
	start = clock();

	cout << "Sorting " << N << " numbers." << endl;

	for (int i = 0; i < N; i++)
		rnumbers.push_back(rand());

	finish = clock();
	duration = (double)(finish - start) / CLOCKS_PER_SEC;

	cout << "Initializing vector: " << duration << " seconds." << endl;

	start = clock();

	unsigned long comp = shellsort(rnumbers);

	finish = clock();
	duration = (double)(finish - start) / CLOCKS_PER_SEC;

	cout << "Sorting vector: " << duration << " seconds." << endl;
	cout << "Number of comparisons: " << comp << endl;

	// For printing sorted vector. Best used with small vector sizes
	/*
	cout << "Sorted vector: " << endl;

	for (int i = 0; i < rnumbers.size(); i++)
	{
	cout << rnumbers[i] << endl;
	}
	*/

	return 0;
}