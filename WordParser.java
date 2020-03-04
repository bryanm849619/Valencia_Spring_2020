package application;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class WordParser
{
	private String filePath, fileData, parseDelimiters;
	private StringTokenizer strTokens;
	private List <MyPair> occurenceList;
	private boolean foundToken;
	
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
	
	public List<MyPair> getOccurenceList() 
	{
		return occurenceList;
	}

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
	
	private String readHtmlFile(String fileName) throws IOException
	{
		String content = new String(Files.readAllBytes(Paths.get(fileName)));
		return content;
	}

}
