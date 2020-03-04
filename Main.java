package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;



public class Main extends Application 
{
	@Override
	public void start(Stage primaryStage) throws IOException
	{
		WordParser parser = new WordParser("C:\\Users\\bmchu\\Documents\\Valencia\\Software Development I\\Module Two\\Macbeth Entire Play.html", 
				" !-=\";,.:;<>/ \t \n \r 1234567890?|");
		
		BarChartHelper myChart = new BarChartHelper(primaryStage, 20);
		
		parser.generateSortedOccurences();
		
		myChart.populateChart(parser);
		myChart.displayChart();
		
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}
