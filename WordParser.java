package application;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;


public class WordParser implements Serializable
{
	/**
	 * serialVersionUID is a unique identifier for serialization to distinguish itself over crossplatorms and compilers
	 */
	public static final long serialVersionUID = 6706623487760274140L;
	/**  
	 *  filePath is a string that will store the path to the file being opened
	 *  fileData is a string of raw data that will be retrieved from @see #readHtmlFile(String)
	 *  parseDelimiters is a string that will store the delimiters that will be set when parsing 
	 */
	public String filePath, fileData, parseDelimiters;
	/**  
	 *  strTokens is a StringTokenizer to store all the tokens @see StringTokenizer
	 */
	public transient StringTokenizer strTokens;
	/**  
	 *  occurenceList is a List of MyPair that will keep track of the token and the amount of tokens that exist
	 *  @see MyPair
	 *  @see List
	 */
	public List <MyPair> occurenceList;
	/** foundToken is a boolean that will determine when if this token already existed within the occurenceList
	 * @see #occurenceList
	 */
	public boolean foundToken;
	/**  
	 *  
	 *  A Parameterized Constructor to Instantiate a WordParser 
	 *  @param path is a string will be stored for filePath @see {link {@link #filePath}
	 *  @param path is a string will be stored for parseDelimiters @see {link {@link #parseDelimiters}
	 */
	WordParser(String path, String delimiters) throws IOException
	{
		filePath = path;
		parseDelimiters = delimiters;
		foundToken = false;
		occurenceList = new ArrayList<MyPair>();
		
		fileData = readHtmlFile(filePath);
		fileData = fileData.replaceAll("\\<.*?\\>", " ");
		fileData = fileData.toLowerCase();
	}
	/**  
	 *  
	 *  A getter method to retrieve the occurenceList of this WordParser
	 *  @return the list that is storing our pairs
	 */
	public List<MyPair> getOccurenceList() 
	{
		return occurenceList;
	}

	/**  
	 *  
	 *  A utility method that will parse the raw data and sort the occurences that are stored withing the occurence list.
	 *  @see #occurenceList
	 *  @see #strTokens
	 */
	public void generateSortedOccurences()
	{
		strTokens = new StringTokenizer(fileData, parseDelimiters);
		
		while (strTokens.hasMoreTokens())
		{
			// keep track if we found a token or not
			foundToken = false;
			
			// lets retrieve a token
			String element = strTokens.nextToken();
			
			
			// lets iterate through our list to see if this token exists in our list, if not lets add it, otherwise lets increment its counter
			for (MyPair iter : occurenceList)
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
				occurenceList.add(pairToAdd);
			}
		}
		
		// now lets sort out pairedList
		Collections.sort(occurenceList);
	}
	/**  
	 *  
	 *  A utility method that will print out all the word occurrences in the given format
	 */
	public void printSortedOccurences()
	{
		// lets print out our list with a numbered order
		Integer counter = 1;
		for (MyPair iter : occurenceList)
		{
			System.out.println("# " + counter.toString() + "\t" + iter);
			counter++;
		}
	}
	/**  
	 *  
	 *  A method that will read an html file and retrieve all that string data within it as raw data later to be stored and parsed.
	 *  @param fileName is a string that will be the file to be opened.
	 *  @see #fileData
	 *  @throws IOException throw if something goes wrong when opening the file
	 *  @return the raw data of the file that has just been opened
	 */
	public  String readHtmlFile(String fileName) throws IOException
	{
		String content = new String(Files.readAllBytes(Paths.get(fileName)));
		return content;
	}
	
	// JUnit Testing 
	/**  
	 *  
	 *  A method for JUnit Testing that will retrieve the file path of the html file
	 *  @see #filePath
	 *  @return the file path of the file that was opened
	 */
	public String getFilePath() {
		return filePath;
	}

	/**  
	 *  
	 *  A method for JUnit Testing that will retrieve the delimiters that we will use for parsing
	 *  @see #parseDelimiters
	 *  @return the delimiters used for parsing this file
	 */
	public String getParseDelimiters() {
		return parseDelimiters;
	}

}
