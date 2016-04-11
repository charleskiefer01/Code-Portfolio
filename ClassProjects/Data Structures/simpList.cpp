//--------------------------------------------------------------------
//
//  Project 1                                             project1.cpp
//
//  Test program for the operations in the simple list.
// This program was not written by me.
// It's for testing my implementation of a linked list
//
//--------------------------------------------------------------------

#include <iostream>
#include <string>
#include "simpList.h"


int main()
{
  SimpList<int> intList;   // (empty) list of integers

  cout << "Let's build a sorted list of integers." << endl;
  cout << endl << "Uninitialized List: ";
  intList.print();
  cout << endl << "Length: " << intList.size() << endl;

  int intData[] = { 5, 3, -2, 7, 9, -8, 1, -4 };

  for (int i=0; i<8; i++)
    intList.insert( intData[i] );

  cout << endl << "After inserting 8 integers: ";
  intList.print();
  cout << endl << "Length: " << intList.size() << endl;

  //-----------------------------------------------------------------------------
  
  cout << endl << "--- Testing the \"find\" method:" << endl;
  
  int t = 5;
  bool ret = intList.find(t);

  cout << endl << t << " is in the list: "
       << (ret ? "true" : "false") << endl;

  t = 6;
  ret = intList.find(t);

  cout << endl << t << " is in the list: "
       << (ret ? "true" : "false") << endl;
	   
  //-----------------------------------------------------------------------------
  
  cout << endl << "--- Testing the \"remove\" method:" << endl;

  t = 5;
  ret = intList.remove(t);

  cout << endl << t << " has been removed: "
       << (ret ? "true" : "false") << endl;

  cout << endl << "Remaining list: ";
  intList.print();
  cout << endl << "Length: " << intList.size() << endl;
  
  //-----------------------------------------------------------------------------
  
  cout << endl << "--- Testing the \"clear\" method:" << endl << endl;

  intList.clear();
  intList.print();
  cout << endl << "Length: " << intList.size() << endl;

  cout << endl << "--- Testing the \"isEmpty\" predicate:" << endl;

  cout << endl << "The integer list is now empty: "
       << (intList.isEmpty()? "true" : "false") << endl << endl;
	   
  //-----------------------------------------------------------------------------
  
  cout << "Now, let's build a sorted list of strings." << endl;

  string strData[] = { "Maria", "Ann", "Emily", "Vivian",
                       "Beth", "Carla", "Susan" };

  SimpList<string> strList;

  for (int i=0; i<7; i++)
    strList.insert( strData[i] );

  cout << endl << "After inserting 7 names:" << endl;
  strList.print();
  cout << endl << "Length: " << strList.size() << endl;
  
  //-----------------------------------------------------------------------------
  
  cout << endl << "--- Testing the \"remove\" method:" << endl;
  cout << endl << "Bye Carla!" << endl;

  string str = "Carla";
  ret = strList.remove(str);

  cout << endl << str << " has been removed: "
	  << (ret ? "true" : "false") << endl;

  cout << endl << "Remaining list:" << endl;
  strList.print();
  cout << endl << "Length: " << strList.size() << endl;
  
  //-----------------------------------------------------------------------------
  
  cout << endl << "--- Testing the \"insert\" method:" << endl;
  cout << endl << "Bienvenue Pauline!" << endl;

  string nom = "Pauline";
  strList.insert(nom);

  cout << endl << "Extended list:" << endl;
  strList.print();
  cout << endl << "Length: " << strList.size() << endl;

  cout << endl << "--- Auf Wiedersehen!" << endl;
  cout << endl << "End of \"main()\"." << endl
       << "At this point, the destructor is called." << endl;
	 
  return 0;
}

