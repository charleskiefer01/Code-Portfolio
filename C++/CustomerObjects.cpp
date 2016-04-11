#include "stdafx.h"
#include <iostream>
#include <string>
using namespace std;

/*
	A simple program to implement customer objects
	Each customer has its own set of data
	The data can be changed through the use of
	custom 'set' functions
*/

class customer
{
	//Variables are private, functions are public
private:
	int idNumber;
	string firstName;
	string lastName;
	double creditLimit;

public:
	void setidNumber(int newid);
	void setFirstName(string newFirst);
	void setLastName(string newLast);
	void setCreditLimit(double newLimit);

	int getidNumber();
	string getFirstName();
	string getLastName();
	double getCreditLimit();
	void display();
};

int main()
{
	// Number of customers to be created
	// Change this value to create more customer objects
	const int arraySize = 2;

	//This block initializes only one customer object and displays it
	//for testing purposes
	/*
	customer Customer1;

	Customer1.setidNumber(54127);
	Customer1.setFirstName("Chad");
	Customer1.setLastName("Chazwell");
	Customer1.setCreditLimit(5000);

	Customer1.display(Customer1);
	*/

	customer customerList[arraySize];

	string tempString;
	int tempInt;
	double tempDouble;

	for (int i = 0; i < arraySize; i++)
	{
		cout << "Enter customer #" << (i + 1) << " first name: ";
		cin >> tempString;
		customerList[i].setFirstName(tempString);

		cout << "Enter customer #" << (i + 1) << " last name: ";
		cin >> tempString;
		customerList[i].setLastName(tempString);

		cout << "Enter customer #" << (i + 1) << " ID number: ";
		cin >> tempInt;
		customerList[i].setidNumber(tempInt);

		cout << "Enter customer #" << (i + 1) << " credit limit: ";
		cin >> tempDouble;
		customerList[i].setCreditLimit(tempDouble);

		cout << endl;
	}

	// Display all new customers when finished to confirm correct values
	for (int j = 0; j < arraySize; j++)
	{
		customerList[j].display();
	}

	return 0;
}

void customer::setidNumber(int newid)
{
	idNumber = newid;
}

void customer::setFirstName(string newFirst)
{
	firstName = newFirst;
}

void customer::setLastName(string newLast)
{
	lastName = newLast;
}

void customer::setCreditLimit(double newLimit)
{
	if (newLimit > 10000)
	{
		cout << "Limit maximum is 10,000." << endl;
		cout << "Limit for " << customer::getFirstName() << " " + customer::getLastName() << " has been set to 10,000." << endl << endl;
		creditLimit = 10000;
	}

	else
		creditLimit = newLimit;
}

string customer::getFirstName()
{
	return firstName;
}

string customer::getLastName()
{
	return lastName;
}

int customer::getidNumber()
{
	return idNumber;
}

double customer::getCreditLimit()
{
	return creditLimit;
}

void customer::display()
{
	cout << "--Customer data--" << endl;
	cout << "Name: " << customer::getFirstName() << " " << customer::getLastName() << endl;
	cout << "Identification number: " << customer::getidNumber() << endl;
	cout << "Credit limit: " << customer::getCreditLimit() << endl << endl;
}
