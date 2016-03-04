
#include "stdafx.h"
#include <iostream>
#include <stdio.h>
#include <iomanip>
#include <string>
using std::setw;
using namespace std;

/*
	This program implements a polymorphic collection of objects. 
	The Book superclass provides basic data and functionality to its children, Fiction and Nonfiction
	The most notable difference between the objects is that they each have a unique display() function
*/

class Book
{
private:
	string title;
	string author;

public: 
	string getTitle();
	string getAuthor();

	void setTitle(string);
	void setAuthor(string);

	void displayBook();
};

class Fiction : public Book
{
private:
	int readingLevel;

public:
	int getReadingLevel();

	void setReadingLevel(int);

	void displayFiction();
};

class NonFiction : public Book
{
private:
	int numPages;

public: 
	int returnNumPages();

	void setNumPages(int);

	void displayNonFiction();
};


int main()
{
	Book testBook;
	testBook.setAuthor("Chad Marchan");
	testBook.setTitle("I Wrote a Book!");

	testBook.displayBook();


	Fiction testFiction;
	testFiction.setAuthor("Gerald Burrows");
	testFiction.setTitle("This Book is Fake");
	testFiction.setReadingLevel(7);

	testFiction.displayFiction();

	NonFiction testNonFiction;
	testNonFiction.setAuthor("Stephan Lanham");
	testNonFiction.setTitle("Totally Not a Fake Book");
	testNonFiction.setNumPages(451);

	testNonFiction.displayNonFiction();

	return 0;
}

// ---------------------------------
//Book functions
string Book::getAuthor()
{
	return author;
}

string Book::getTitle()
{
	return title;
}

void Book::setAuthor(string authorName)
{
	author = authorName;
}

void Book::setTitle(string titleName)
{
	title = titleName;
}

void Book::displayBook()
{
	cout << title << ", written by " << author << ": " << endl;
 }

// ---------------------------------
//Fiction functions

void Fiction::setReadingLevel(int level)
{
	readingLevel = level;
}

void Fiction::displayFiction()
{
	displayBook();
	cout << "Reading level " << readingLevel << endl << endl;
}

// ---------------------------------
//NonFiction functions

void NonFiction::setNumPages(int pages)
{
	numPages = pages;
}

void NonFiction::displayNonFiction()
{
	displayBook();
	cout << numPages << " pages" << endl << endl;
}

