#include "stdafx.h"
#include <iostream>
#include <string>
#include <fstream>

using namespace std;

/*
	This program is designed to showcase two major elements:
	1. The friend function is used here to allow an outside function access to the weatherReport object's private data. 
	2. Input from a large .txt file is automatically read through the use of the fstream functionality
*/

class weatherReport
{
private:
	int dayOfMonth;
	int highTemp;
	int lowTemp;
	int amountRain;
	int amountSnow;

	int totalRain;
	int totalSnow; 

public:
	void displayWeatherReport();
	void displayFinalWeatherReport();

	void setDate(int);
	void setHigh(int);
	void setLow(int);
	void setRain(int);
	void setSnow(int);
		
	friend weatherReport editFinalReport(weatherReport &finalReport, weatherReport);

	friend void setWeatherValues(weatherReport[], int);

	weatherReport(int day = 99, int high = -9999, int low = 9999, int rain = 0, int snow = 0)
	{
		dayOfMonth = day;
		highTemp = high;
		lowTemp = low;
		amountRain = rain;
		amountSnow = snow;
		totalRain = 0;
		totalSnow = 0;
	}

};

int main()
{
	weatherReport finalReport(0);

	const int numReports = 30; //This variable can be changed for different numbers of reports
	weatherReport reportList[numReports];

	cout << "Processing " << numReports << " weather reports... " << endl << endl;

	setWeatherValues(reportList, numReports);

	for (int i = 0; i < numReports; i++) //Loop for comparing weatherReports to the finalReport
	{
		//reportList[i].displayWeatherReport(); //If you want the program to print all 30 weather reports as well, uncomment this line
		editFinalReport(finalReport, reportList[i]);
	}
	
	finalReport.displayFinalWeatherReport();

	return 0;
}

void setWeatherValues(weatherReport reportList[], int numReports)
{ 
	//Variables that temporarily store the data of each object
	int dayOfMonth;
	int highTemp;
	int lowTemp;
	int amountRain;
	int amountSnow;

											//Depending on usage, the project may not be able to connect with the data file
	ifstream inFile("FriendsWeatherReports\\");	//Ensure that this is the name of the project the file is run from

	inFile.open("Data.txt");

	if (!inFile.good()) //Ensures that file has been found
		cout << "File could not be read." << endl;
	else
	{
		int i = 0;
		while (inFile >> dayOfMonth >> highTemp >> lowTemp >> amountRain >> amountSnow  && i < numReports)
		{
			//cout << dayOfMonth << endl << highTemp << endl << lowTemp << endl << amountRain << endl << amountSnow;
			reportList[i].setDate(dayOfMonth);
			reportList[i].setHigh(highTemp);
			reportList[i].setLow(lowTemp);
			reportList[i].setRain(amountRain);
			reportList[i].setSnow(amountSnow);
			i++;
		}

		inFile.close();
	}
	
}

//Set functions for access issues
void weatherReport::setDate(int day)
{
	dayOfMonth = day;
}

void weatherReport::setHigh(int high)
{
	highTemp = high;
}

void weatherReport::setLow(int low)
{
	lowTemp = low;
}

void weatherReport::setRain(int rain)
{
	amountRain = rain;
}

void weatherReport::setSnow(int snow)
{
	amountSnow = snow;
}

void weatherReport::displayWeatherReport()
{
	cout << "Day " << dayOfMonth << endl;
	cout << "High temperature: " << highTemp << " degrees Fahrenheit" << endl;
	cout << "Low temperature: " << lowTemp << " degrees Fahrenheit" << endl;
	cout << "Rain: " << amountRain << " inches" << endl;
	cout << "Snow: " << amountSnow << " inches" << endl;
	cout << endl;
}

void weatherReport::displayFinalWeatherReport() //The final report needs a special display function
{
	cout << "Monthly Report: " << endl;
	cout << "Highest temperature: " << highTemp << " degrees Fahrenheit" << endl;
	cout << "Lowest temperature: " << lowTemp << " degrees Fahrenheit" << endl;
	cout << "Total rain: " << totalRain << " inches" << endl;
	cout << "Total snow: " << totalSnow << " inches" << endl;
	cout << endl;
}

weatherReport editFinalReport(weatherReport &finalReport, weatherReport checkedReport)
{ //Imports objects from the ReportList array and compares them one at a time
	if (checkedReport.highTemp > finalReport.highTemp)
		finalReport.highTemp = checkedReport.highTemp;

	if (checkedReport.lowTemp < finalReport.lowTemp)
		finalReport.lowTemp = checkedReport.lowTemp;
		
	finalReport.totalRain += checkedReport.amountRain;
	finalReport.totalSnow += checkedReport.amountSnow;

	return finalReport;
}