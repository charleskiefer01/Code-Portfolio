#include "stdafx.h"
#include <iostream>
#include <string>
using namespace std;

/*
	A more complex implementation of the CustomerObjects program
	This allows for unique students to be created and their grades set
	Each Person object 'has-a' StudentGrade object which they take their grades from
*/

class Person
{
private:
	string firstName;
	string lastName;
	string idNumber; // ID is a string instead of an int this time, since it can be 'X'

public:
	void displayPerson();

	Person(string first = "X", string last = "X", string id = "X")
	{
		firstName = first;
		lastName = last;
		idNumber = id;
	}

};


class StudentGrade
{
private:
	Person student;
	double testScore;
	double possiblePoints;
	char letterGrade;

public:
	void setLetterGrade();
	void displayGrades();

	StudentGrade(Person personUsed, double curScore, double points = 100)
	{
		student = personUsed;
		testScore = curScore;
		possiblePoints = points;
	}

};

int main()
{
	// Set of test objects to display functionality
	Person person1("Al", "Allison", "A95"); // Without these initial values, it defaults to 'X'
	StudentGrade student1(person1, 95); // This uses the default possiblePoints value of 100
	student1.displayGrades();

	Person person2("Bob", "Bobertson", "B83");
	StudentGrade student2(person2, 83);
	student2.displayGrades();

	Person person3("Chad", "Chadderson", "C71");
	StudentGrade student3(person3, 55, 75); // This has a possiblePoints value of 75
	student3.displayGrades();

	Person person4("Dan", "Dannerson", "D12");
	StudentGrade student4(person4, 12);
	student4.displayGrades();

	return 0;
}

void Person::displayPerson()
{
	cout << "Name: " << firstName << " " << lastName << endl;
	cout << "Identification number: " << idNumber << endl;
}

void StudentGrade::setLetterGrade()
{
	if (possiblePoints == 0)
	{
		possiblePoints = 1;
	}

	double temp = testScore / possiblePoints; // Temp value holds the divided value so it doesn't need to be performed every time

	// Attempted doing this with a switch, but converting everything into a rounded value is too much trouble
	if (temp >= 0.9)
		letterGrade = 'A';
	else if (temp >= 0.8)
	{
		letterGrade = 'B';
	}
	else if (temp >= 0.7)
	{
		letterGrade = 'C';
	}
	else
	{
		letterGrade = 'D';
	}
}

void StudentGrade::displayGrades()
{
	student.Person::displayPerson(); // Calls the Person object's display for names and ID #
	cout << "Points: " << testScore << " out of " << possiblePoints << endl;
	setLetterGrade();
	cout << "Letter Grade: " << letterGrade << endl << endl;
}