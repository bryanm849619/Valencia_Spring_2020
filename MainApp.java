import java.io.IOException;

public class MainApp
{

	public static void main(String[] args) throws IOException
	{
		WordParser parser = new WordParser("C:\\Users\\bmchu\\Documents\\Valencia\\Software Development I\\Module Two\\Macbeth Entire Play.html", 
										   " !-=\";,.:;<>/ \t \n \r 1234567890?|");

		parser.generateSortedOccurences();
		parser.printSortedOccurences();
	}

}
