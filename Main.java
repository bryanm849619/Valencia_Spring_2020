package application;
	
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;


public class Main extends Application
{
	// static function that will read a .html file using the Files and Paths library
	// will return the content read from the file 
	static String readHtmlFile(String fileName) throws IOException
	{
		String content = new String(Files.readAllBytes(Paths.get(fileName)));
		return content;
	}
	
	@Override
	public void start(Stage primaryStage) throws IOException
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
		
		primaryStage.setTitle("Fequency Chart");
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		final BarChart<String,Number> bc = 
		new BarChart<String,Number>(xAxis,yAxis);
		bc.setTitle("Frequency Summary");
		xAxis.setLabel("Word");       
		yAxis.setLabel("Count");
		
		XYChart.Series <String, Number>series1 = new XYChart.Series<>();
        series1.setName(pairedList.get(0).getToken());       
		series1.getData().add(new Data<String, Number>(pairedList.get(0).getToken(), pairedList.get(0).getCount()));
		
		XYChart.Series <String, Number>series2 = new XYChart.Series<>();
		series2.setName(pairedList.get(1).getToken());       
		series2.getData().add(new Data<String, Number>(pairedList.get(1).getToken(), pairedList.get(1).getCount()));
		
		XYChart.Series <String, Number>series3 = new XYChart.Series<>();
		series3.setName(pairedList.get(2).getToken());       
		series3.getData().add(new Data<String, Number>(pairedList.get(2).getToken(), pairedList.get(2).getCount()));
		
		XYChart.Series <String, Number>series4 = new XYChart.Series<>();
		series4.setName(pairedList.get(3).getToken());       
		series4.getData().add(new Data<String, Number>(pairedList.get(3).getToken(), pairedList.get(3).getCount()));

		XYChart.Series <String, Number>series5 = new XYChart.Series<>();
        series5.setName(pairedList.get(4).getToken());       
		series5.getData().add(new Data<String, Number>(pairedList.get(4).getToken(), pairedList.get(4).getCount()));
		
		XYChart.Series <String, Number>series6 = new XYChart.Series<>();
		series6.setName(pairedList.get(5).getToken());       
		series6.getData().add(new Data<String, Number>(pairedList.get(5).getToken(), pairedList.get(5).getCount()));
		
		XYChart.Series <String, Number>series7 = new XYChart.Series<>();
        series7.setName(pairedList.get(6).getToken());       
		series7.getData().add(new Data<String, Number>(pairedList.get(6).getToken(), pairedList.get(6).getCount()));
		
		XYChart.Series <String, Number>series8 = new XYChart.Series<>();
		series8.setName(pairedList.get(7).getToken());       
		series8.getData().add(new Data<String, Number>(pairedList.get(7).getToken(), pairedList.get(7).getCount()));
		
		XYChart.Series <String, Number>series9 = new XYChart.Series<>();
		series9.setName(pairedList.get(8).getToken());       
		series9.getData().add(new Data<String, Number>(pairedList.get(8).getToken(), pairedList.get(8).getCount()));
		
		XYChart.Series <String, Number>series10 = new XYChart.Series<>();
		series10.setName(pairedList.get(9).getToken());       
		series10.getData().add(new Data<String, Number>(pairedList.get(9).getToken(), pairedList.get(9).getCount()));

		XYChart.Series <String, Number>series11 = new XYChart.Series<>();
		series11.setName(pairedList.get(10).getToken());       
		series11.getData().add(new Data<String, Number>(pairedList.get(10).getToken(), pairedList.get(10).getCount()));
		
		XYChart.Series <String, Number>series12 = new XYChart.Series<>();
		series12.setName(pairedList.get(11).getToken());       
		series12.getData().add(new Data<String, Number>(pairedList.get(11).getToken(), pairedList.get(11).getCount()));
		
		XYChart.Series <String, Number>series13 = new XYChart.Series<>();
		series13.setName(pairedList.get(12).getToken());       
		series13.getData().add(new Data<String, Number>(pairedList.get(12).getToken(), pairedList.get(12).getCount()));
		
		XYChart.Series <String, Number>series14 = new XYChart.Series<>();
		series14.setName(pairedList.get(13).getToken());       
		series14.getData().add(new Data<String, Number>(pairedList.get(13).getToken(), pairedList.get(13).getCount()));

		XYChart.Series <String, Number>series15 = new XYChart.Series<>();
		series15.setName(pairedList.get(14).getToken());       
		series15.getData().add(new Data<String, Number>(pairedList.get(14).getToken(), pairedList.get(14).getCount()));
		
		XYChart.Series <String, Number>series16 = new XYChart.Series<>();
		series16.setName(pairedList.get(15).getToken());       
		series16.getData().add(new Data<String, Number>(pairedList.get(15).getToken(), pairedList.get(15).getCount()));
		
		XYChart.Series <String, Number>series17 = new XYChart.Series<>();
		series17.setName(pairedList.get(16).getToken());       
		series17.getData().add(new Data<String, Number>(pairedList.get(16).getToken(), pairedList.get(16).getCount()));
		
		XYChart.Series <String, Number>series18 = new XYChart.Series<>();
		series18.setName(pairedList.get(17).getToken());       
		series18.getData().add(new Data<String, Number>(pairedList.get(17).getToken(), pairedList.get(17).getCount()));
		
		XYChart.Series <String, Number>series19 = new XYChart.Series<>();
		series19.setName(pairedList.get(18).getToken());       
		series19.getData().add(new Data<String, Number>(pairedList.get(18).getToken(), pairedList.get(18).getCount()));
		
		XYChart.Series <String, Number>series20 = new XYChart.Series<>();
		series20.setName(pairedList.get(19).getToken());       
		series20.getData().add(new Data<String, Number>(pairedList.get(19).getToken(), pairedList.get(19).getCount()));
		
		Scene scene  = new Scene(bc,800,600);
		bc.getData().addAll(series1, series2, series3, series4, series5, series6,series7, series8,series9, 
							series10,series11, series12,series13, series14,series15, series16, series17, series18, series19, series20);
		primaryStage.setScene(scene);
		primaryStage.show();
 
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
}
