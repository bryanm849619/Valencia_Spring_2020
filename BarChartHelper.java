package application;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.stage.Stage;

public class BarChartHelper 
{
	private Stage barChartStage;
	private CategoryAxis xAxis;
	private NumberAxis yAxis;
	private BarChart<String, Number> barChart;
	List <XYChart.Series<String, Number>> seriesList;
	Scene chartScene;
	int seriesCount;
	
	BarChartHelper(Stage stage, int count)
	{
		seriesList = new ArrayList<XYChart.Series<String, Number>>();
		seriesCount = count;
		barChartStage = stage;
		xAxis = new CategoryAxis();
		yAxis = new NumberAxis();
		xAxis.setLabel("Word");       
		yAxis.setLabel("Count");
		barChart = new BarChart<String, Number>(xAxis, yAxis);
		barChart.setTitle("Frequency Summary");
		chartScene = new Scene(barChart, 800, 600);
	}
	
	void populateChart(WordParser parser)
	{
		for (int i = 0; i < seriesCount; i++)
		{
			XYChart.Series<String, Number> seriesToAdd = new XYChart.Series<>();
			seriesToAdd.setName(parser.getOccurenceList().get(i).getToken());
			seriesToAdd.getData().add(new Data<String, Number>(parser.getOccurenceList().get(i).getToken(), 
															   parser.getOccurenceList().get(i).getCount()));
			
			seriesList.add(seriesToAdd);
		}
	}
	
	void displayChart()
	{
		barChart.getData().addAll(seriesList);
		barChartStage.setScene(chartScene);
		barChartStage.show();
	}
}
