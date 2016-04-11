
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

// This program functions as a rudimentary UNIX command implemented in C
// It works in the form of the Caesar cipher, where each letter is rotated by a certain amount according to a key

// Takes an individual character and rotates it
char rotateChar(char inputText, int n)
{
	char compare;
	compare = inputText;
	if (compare > 96 && compare < 123)
		// Lowercase letters
	{
		compare = (((compare - 97 + n) % 26) + 97);
	}

	// 97 is the 'key' for lowercase. Subtract it first to simplify the equation
	// Then add it when done for the proper ASCII value

	else if (compare > 65 && compare < 91)
		// Uppercase letters
	{
		compare = (((compare - 65 + n) % 26) + 65);
	}

	return compare;
}

// Breaks a string down into chars and sends them to rotateChar
char *rotateStr(char *inputText, int n)
{
	// Some compilers seem to have issues with strlen. Replace it later? 
	int len = strlen(inputText);

	char* temp = inputText;

	// This could be a for loop, but instead I've used a modified while loop for compatibility purposes
	int i = 0;
	while (i < len)
	{
		temp[i] = rotateChar(inputText[i], n);
		++i;
	}
	return temp;
}

// Inputs characters from a file one by one and sends them to the rotator
char * readFromFile(char *fileName, int n)
{
	FILE *fp = fopen(fileName, "r");

	if (!fp)
	{
		printf("Something is wrong with that file.\n");
		fclose(fp);
		return 2;
	}

	static char temp[100] = { '\0' };
	// This variable HAS to be static, otherwise the main function can't find it
	// Thanks, C.
	// Initialized as an array of null chars, because cstrings need to know when they end

	int c;

	int i = 0;
	while ((c = fgetc(fp)) != EOF)
	{
		temp[i] = rotateChar(c, n);
		++i;
	}

	return temp;

	fclose(fp);
}

// Continuously receives input from the user
// Can be escaped pressing ctrl-c
void contInput(int n)
{
	printf("Press ctrl-c to finish.\n");
	char temp[100];

	// Will loop until cancelled
	while (1)
	{
		fgets(temp, 100, stdin);
		printf("%s", rotateStr(temp, n));
	}

	return 0;
}

int main(int argc, char *argv[])
{

	int n = 13;
	// By default the rotation key is 13
	// May be modified later in the program by [ -n ] parameter

	// Decide what to do based on how many input parameters are received
	switch (argc)
	{
	case 1:
		// One argument: rotn
		// Inputs text entered and rotates it until cancelled with ctrl-c
		contInput(13);
		break;

	case 2:
		// Two arguments: rotn [-n] || rotn [input-file]

		printf("\0"); // Because otherwise C thinks the next line is a label for goto
		char *temp = argv[1];

		// Continuous input with a key parameter
		if (temp[0] == '-')
		{
			temp++; // Increment the string for the parameter. Goes from '-n' to 'n' for processing

			n = (atoi(temp) % 26);

			// Throws an error if key is 0, 26, or not an integer.
			if (n == 0)
			{
				printf("Received key is invalid. Enter a better integer.\n");
				return 2;
			}

			contInput(n);
		}

		// It may look like this should be in an else statement, but due to the contInput(n) statement, this can only be run if the preceding block wasn't run
		// Takes in text from the file and reprints it, rotated
		char* inputText = readFromFile(argv[1], n);

		printf("%s\n", inputText); // Maybe can put readFromFile directly in here?

		break;

	case 3:
		// Three arguments: rotn [-n] [input-file] || rotn [input-file [output-file]]
		printf("\0");
		temp = argv[1];

		// Key parameter detected
		// Input from file and reprint it rotated by key
		if (temp[0] == '-')
		{
			temp++;

			n = (atoi(temp) % 26);

			// Throws an error if key is 0, 26, or not an integer.
			if (n == 0)
			{
				printf("Received key is invalid. Enter a better integer.\n");
				return 2;
			}

			char* inputText = readFromFile(argv[2], n);

			printf("%s\n", inputText);
			break;
		}

		// No key parameter
		// Input from file, output to file, rotated

		inputText = readFromFile(argv[1], n);

		FILE *fp;
		int c;

		fp = fopen(argv[2], "w+"); // W+ signifies that the file will be created if it doesn't already exist

		int i = 0;
		while (i < strlen(inputText))
		{
			fputc(inputText[i], fp);
			++i;
		}

		printf("Wrote %s to file %s\n", argv[1], argv[2]);

		fclose(fp);

		break;

	case 4:
		// Four arguments: rotn [-n] [input-file [output-file]]
		// Input from file, output to file, rotated by given key
		temp = argv[1];
		temp++;

		n = (atoi(temp) % 26);

		// Throws an error if key is 0, 26, or not an integer.
		if (n == 0)
		{
			printf("Received key is invalid. Enter a better integer.\n");
			return 2;
		}

		inputText = readFromFile(argv[2], n);

		fp = fopen(argv[3], "w+");

		i = 0;
		while (i < strlen(inputText))
		{
			fputc(inputText[i], fp);
			++i;
		}

		printf("Wrote %s to file %s\n", argv[2], argv[3]);

		fclose(fp);

		break;

	default:
		// Invalid usage. Throw an error and quit. 
		printf("Invalid usage. Correct form is: rotn [-n] [input-file [output-file]]\n");
		return 2;
	}

	return 0;
}



