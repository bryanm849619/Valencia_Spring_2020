import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class WordParserTest2
{

	@Test
	public void test() throws IOException
	{
		WordParser parser = new WordParser("C:\\Users\\bmchu\\Documents\\Valencia\\Software Development I\\Module Two\\Macbeth Entire Play.html", 
				   " !-=\";,.:;<>/ \t \n \r 1234567890?|");
		
		String delimResult = parser.getParseDelimiters();
		
		assertEquals(" !-=\";,.:;<>/ \t \n \r 1234567890?|", delimResult);
	}

}
