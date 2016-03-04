#include "stdafx.h"
#include <iostream>
#include <stdio.h>
#include <iomanip>
#include <cstdlib> // contains atoll() on non-MS OS

using namespace std;

/*
This program implements a function that is already available in standard C++ libraries, but it's always good to know how something is done rather than just using it and not asking questions.
*/

long long pow(long long x, unsigned int n)
{
	if (n == 0) 
		return 1;

	if (n == 1)
		return x;

	long long f1 = 1, f2 = x;

	int index = n;

	while (index > 0)
	{
		if (index % 2 == 1)
		{
			f1 = f2 * f1;
			index--;
		}
		f2 = f2*f2;
		index = index / 2;
	}

	return f2;
}

	/*
	if (n == 0)
		return 1;

	if (n == 1)
		return x;

	if (n % 2 == 0)
		return pow(x*x, n / 2);
	else
		return pow(x*x, n / 2) * x;

		*/


// -----------------------------

int main()//int argc, char *argv[])
{
	int a;
	cout << "Enter base: ";
	cin >> a;

	int b;
	cout << "Enter exponent: ";
	cin >> b;
	/*
	if (argc < 3)
	{
		cerr << argv[0] << ": needs 2 numbers." << endl;
		return 1;
	}
	*/
	long x = a;
	if (x < 0)
	{
		cerr << "base has to be greater than or equal to 0." << endl;
		return 2;
	}

	int n = b;
	if (n < 0)
	{
		cerr << "exponent has to be greater than or equal to 0." << endl;
		return 3;
	}

	cout << x << " ^ " << n << " = " << pow(x, n) << endl;

	return 0;
}