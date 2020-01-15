/*
	BRYAN MCHUGH
	COP 1000C 17516
	PROJECTE
*/
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

// define some constant numbers
#define MIN 1
#define MAX 999

#define TRUE 1
#define FALSE 0

// enumerations for app state
/*
	exit		:	time to leave the app
	query		:	check index
	search		:	check for value
	print 		:	print array
	initialize	:	create menu and prompt user
	main		: 	waiting on user input
	fill 		: 	fill array with random values
	sort		:	sort the values in the array from smallest to largest
*/
enum 
{
	APP_EXIT,
	APP_INITIALIZE,
	APP_IDLE,
	APP_FILL,
	APP_QUERY,
	APP_SEARCH,
	APP_PRINT_ARRAY,
	APP_PRINT_MENU,
	APP_SORT
} ;


/***************************************************FUNCTION DECLARATIONS***************************************************/

// generate a random number
// return 		: random number to generate
int 	genRandNumber();

// print menu so i can use it more then once for redirection
// return 		: appState
int 	printMenu();

// lets initialize and global values and prompt the user on what to do. be sure to set app to idle to wait for user input
// return 		: appState
int		initApp();

// get user input for letters
// return 		: char a user inputs
char	getLetter();

// get user input for numbers and make sure they can only enter a value of 0-49
// hasBoundary	: quick way to see if userinput is for index boundaries or if searching for a value in the elements of an array
// return 		: int a user inputs
int 	getNumber(int hasBoundary);

// fill in the array with random values ranging from 1-999
// array[] 		: array to fill
// return 		: appState
int		fillArray(int array[]);

// print out the array even if it has not been filled
// array[] 		: array to print
// return 		: appState
int 	printArray(int array[]);

// query the array at a specific index and retrieve its value
// array[] 		: array to query
// index		: at what index in the array to seek
// return 		: appState
int 	queryArray(int array[], int index);

// search the whole array for a specific value
// array[] 		: array to search through
// value		: what value to look for
// return 		: appState
int 	searchArray(int array[], int value);

// sort an array using quicksort method
// array[]		: array that will be sorted
// start		: the first index in the array
// end 			: the last index in the array
// return		: appState
int		quickSort(int array[], int start, int end);

// partition an array using the indices as place markers 
// array[]		: array that needs to be partitioned for the sort
// start		: the first index in the array
// end 			: the last index in the array
// return		: the index in the array where the partition is going to take place. (seperation point)
int		partitionArray(int array[], int start, int end);

// swap the two elements at the given indices of an array
// index1		: the index to the first element that needs to be swapped
// index2		: the index to the second element that needs to be swapped
void 	swapElements(int array[], int index1, int index2);
 
/***************************************************APPLICATION***************************************************/

int main ()
{
	// lets start the state to initialize and begin to setup the application.
	int appState = APP_INITIALIZE;
	// have a variable to catch the users input
	char letterInput = 0;
	int  numberInput = 0;
	// our array that is ready to be filled
	int appArray[50] = {0};
	
	do
	{
		// lets reset the input each time we do this so we do not get stuck in a loop :D
		letterInput=0;
		numberInput=0;
		// have to check state before any input for the program to load up first
		switch(appState)
		{	
			case APP_PRINT_MENU: 
				printf("\n\tACTION:Printing Menu...\n");
				appState = APP_INITIALIZE;
				break;
			case APP_INITIALIZE:
				appState = initApp();
				break;
			case APP_IDLE:
				letterInput = getLetter();
				break;
			case APP_FILL:
				appState = fillArray(appArray);
				break;
			case APP_PRINT_ARRAY:
				appState = printArray(appArray);
				break;
			case APP_QUERY:
				printf("\nEnter a index in the array to search for :");
				numberInput = getNumber(TRUE);
				appState = queryArray(appArray, numberInput);
				break;
			case APP_SEARCH:
				printf("\nEnter a number to search for in the array :");
				numberInput = getNumber(FALSE);
				appState = searchArray(appArray, numberInput);
				break;
			case APP_SORT:
				appState = quickSort(appArray, 0, 49);
				break;
			default:
				appState = APP_IDLE;
				break;
		}
			
		switch(letterInput)
		{	
			case 'z':
			case 'Z': 
				appState = APP_EXIT;
				printf("\tACTION:Exit Application\n");
				break;
			case 'p':
			case 'P':
				appState = APP_PRINT_ARRAY;
				printf("\tACTION:Print Array\n");
				break;
			case 'f':
			case 'F':
				appState = APP_FILL;
				printf("\tACTION:Fill Array\n");
				break;
			case 'q':
			case 'Q':
				appState = APP_QUERY;
				printf("\tACTION:Query Array\n");
				break;
			case 's':
			case 'S':
				appState = APP_SEARCH;
				printf("\tACTION:Search Array\n");
				break;
			case 'o':
			case 'O':
				appState = APP_SORT;
				printf("\tACTION:Quick Sort\n");
				break;
		}
		
	} while (appState != APP_EXIT);
	
	return 0;
}

int printMenu()
{
	// lets prompt the user with the menu for the first time
	// Menu allignment
	printf("\t\t\t\tMENU\n");
	printf("Select one of the following options:\n");
	printf("\'F\': Fill the array with random values\n");
	printf("\'P\': Print the array.\n");
	printf("\'Q\': Check index of array for its value\n");
	printf("\'S\': Search the array for a specific value and find its index\n");
	printf("\'O\': Sort the array from smallest to largest\n");
	printf("\'Z\': Terminate Application\n");
	
	// set the appState back to the user input in idle always after prompting a new menu.
	return APP_IDLE;
}

int initApp()
{
	// seed the rand function with the time on the clock to make sure its different everytime
	srand(time(0));
	
	// lets give the user some options and wait for their decision
	return printMenu();
}

int	fillArray(int array[])
{
	// lets set every element in the array to a random number
	for (int i = 0; i < 50; i++)
	{
		array[i] = genRandNumber();
	}
	// lets put the application back in idle and wait for the user input
	return APP_IDLE;
}

int printArray(int array[])
{
	printf("\n");
	// lets see if the user filled the array already
	if (array[0] == 0)
		printf("\nYou have not filled the array in yet. Zero'd out!\n");
		
	// lets print out the array no matter what!
	for (int i = 0; i < 50; i++)
	{
		if (i % 4 == 0)
			printf("\n");
		else if(i == 49)
			printf("Array[%d] = %d\n", i, array[i]);
		else 
			printf("Array[%d] = %d\t\t ", i, array[i]);
	}	
	// put the application back in idle
	return APP_IDLE;
}

int queryArray(int array[], int index)
{
	if (array[index] == 0)
	{
		printf("\nYou have not filled in the array with random values. Back to Main menu\t\t");
		return APP_PRINT_MENU;
	}
	else 
	{
		printf("\nArray[%d] = %d\n", index, array[index]);
		return APP_IDLE;
	}
}

int searchArray(int array[], int value)
{
	// see if we found the value in the array
	int flag = FALSE;
	for (int i = 0; i < 50; i++)
	{
		if (array[i] ==  value)
		{
			printf("\nValue : %d is equal to Array[%d] : %d\n", value, i, array[i]);
			flag = TRUE;
		}
	}
	
	// prompt user if the value was not found
	if (!flag)
	{
		printf("\nValue : %d was not found in the array", value);
		return APP_PRINT_MENU;
	}
	return APP_IDLE;
}

int getNumber(int hasBoundary)
{
	// lets grab the number the user wants to guess
	int userInput=0;
	scanf("%d", &userInput);
	
	if ((userInput < 0 || userInput >= 50) && hasBoundary)
	{
		printf("\nYou entered an invalid index. There is 0-49 indices.\nInput Index :");
		userInput = getNumber(hasBoundary);
	}
	
	return userInput;
}

char getLetter()
{
	// lets grab the number the user wants to guess
	char userInput=0;
	scanf(" %c", &userInput);
	
	if (userInput != 'z' &&
		userInput != 'Z' &&
		userInput != 'f' &&
		userInput != 'F' &&
		userInput != 'p' &&
		userInput != 'P' &&
		userInput != 'q' &&
		userInput != 'Q' &&
		userInput != 's' &&
		userInput != 'S' &&
		userInput != 'o' &&
		userInput != 'O')
		{
			printf("\nInvalid Entry! Try again!\tInput :");
			userInput = getLetter();
		}
	
	return userInput;
}

int genRandNumber()
{
	// lets set our range for the number we will generate
	int range = (MAX-MIN) + 1;
	// lets make a random number within our range!
	int tempRandNbr = rand() % range + 1;
	
	// be sure to return the random number so we can use it later on
	return tempRandNbr;
}

int quickSort(int array[], int start, int end)
{
	// lets create a condition to break out the recursive functionality so we dont go forever
	// this will ensure the will not sort an invalid index and or there is no more elements in the array/partition to sort
	if (start < end)
	{
		// lets partition the array and capture a pivotal point where the partition begins
		int partitionIndex = partitionArray(array, start, end);
		// what this will do is recursively sort anything smaller then the given pivot to the left and anything larger then the pivot to the right
		quickSort(array, start, partitionIndex-1);
		quickSort(array, partitionIndex+1, end);
	}
	
	return APP_IDLE;
}

int	partitionArray(int array[], int start, int end)
{
	// lets set the pivot to the end of the array to have less confusion
	// all test conditions will revolve around this VALUE/ELEMENT for the partition
	int pivot = array[end];
	// lets index where the partition ends up happening at in the array
	int partitionIndex = start;
	
	// lets scan through the array from start to end
	for (int i = start; i < end; i++)
	{
		// since we are doing ascending order we want any value that is less than or equal to the pivot to move to the left of the pivot
		if (array[i] <= pivot)
		{
			// lets rearrange the current element with the element at the given partition index
			swapElements(array, i, partitionIndex);
			// lets be sure to increment the partition index since we just placed element in its partitioned place
			partitionIndex++;
		}
	}
	
	// lets assign the partition index element be the new pivot point(which is the end of the array/partition
	swapElements(array, partitionIndex, end);
	// return the "pivot point"
	return partitionIndex;
}

void swapElements(int array[], int index1, int index2)
{
	// lets have a temporary value to hold the value of the first element 
	int tempValue = array[index1];
	array[index1] = array[index2];
	array[index2] = tempValue;	
}