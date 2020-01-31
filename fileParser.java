package module2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class fileParser 
{	
	// static function that will read a .html file using the Files and Paths library
	// will return the content read from the file 
	static String readHtmlFile(String fileName) throws IOException
	{
		String content = new String(Files.readAllBytes(Paths.get(fileName)));
		return content;
	}
	
	public static void main(String[] args) throws IOException 
	{	
		// string to contain the raw data read from the file path
		String rawData = readHtmlFile("C:\\Users\\bmchu\\Documents\\Valencia\\Software Development I\\Module Two\\Macbeth Entire Play.html");
		
		// remove the html tags 
		rawData = rawData.replaceAll("\\<.*?\\>", " ");
		// convert all text to lower case
		rawData = rawData.toLowerCase();
		
		// delimiters we will use to separate our words
		String delimiters = " !-=\";,.:;<>/ \t \n \r 1234567890?|";
		
		// lets create a list of tokens paired with a incremented count to keep track of the amount of times a token was used in our file
		List <MyPair> pairedList = new ArrayList<MyPair>();
		
		// StringTokenizer class in java that will store all our words(tokens) based on the set delimiters
		
		StringTokenizer strTokens = new StringTokenizer(rawData, delimiters);
		
		// used in loop to determine if this token exists in our list of pairs or not.
		boolean foundToken = false;
		
		while (strTokens.hasMoreTokens())
		{
			// keep track if we found a token or not
			foundToken = false;
			
			// lets retrieve a token
			String element = strTokens.nextToken();
			
			
			// lets iterate through our list to see if this token exists in our list, if not lets add it, otherwise lets increment its counter
			for (MyPair iter : pairedList)
			{
				// found the same token already in the list.
				if (element.equals(iter.getToken()))
				{
					// we found a token
					foundToken = true;
					iter.incrementCount();
				}
			}
			
			// check to see if we found a token, if so continue, if not add this token to our list
			if (foundToken)
				continue;
			else
			{
				MyPair pairToAdd = new MyPair(element.toString());
				pairedList.add(pairToAdd);
			}
		}	
		
		// now lets sort out pairedList
		Collections.sort(pairedList);
		
		// lets print out our list with a numbered order
		Integer counter = 1;
		for (MyPair iter : pairedList)
		{
			System.out.println("# " + counter.toString() + "\t" + iter);
			counter++;
		}
	}
}