package application;

import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Client extends Application 
{
	/**
	 * toServer is a data stream for things being sent out to the server
	 */
  DataOutputStream toServer = null;
  /**
   * fromServer is a data stream for things being sent in from the server
   */
  DataInputStream fromServer = null;
  /**
	* serializedObject is a object stream for class objects to be sent to server
    */
  ObjectOutputStream serializedObject;

  @Override 
  /**  
	 *  
	 *  A method for javaFX to render its graphics
	 *  @param primaryStage is the stage this window will render its graphics on
	 *  @see Stage
	 */
  public void start(Stage primaryStage) throws Exception 
  {
    // Panel p to hold the label and text field
    BorderPane paneForTextField = new BorderPane();
    paneForTextField.setPadding(new Insets(5, 5, 5, 5)); 
    paneForTextField.setStyle("-fx-border-color: green");
    //paneForTextField.setLeft(new Label("Enter a number: "));
    paneForTextField.setLeft(new Label("Menu\nOption 1: Populate MySql Database Table\nOption 2: Retrieve MySql Database Table\n" + 
    									  "Option 3: Display Data from Local Server"));
    
    TextField tf = new TextField();
    tf.setAlignment(Pos.BOTTOM_RIGHT);
    //paneForTextField.setCenter(tf);
    paneForTextField.setBottom(tf);
    
    BorderPane mainPane = new BorderPane();
    // Text area to display contents
    TextArea ta = new TextArea();
    mainPane.setCenter(new ScrollPane(ta));
    mainPane.setTop(paneForTextField);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(mainPane, 450, 200);
    primaryStage.setTitle("Client"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    
    // Initialize my word parser.
    WordParser parser = new WordParser("C:\\Users\\bmchu\\Documents\\Valencia\\Software Development I\\Module Two\\Macbeth Entire Play.html", 
			   " !-=\";,.:;<>/ \t \n \r 1234567890?|");

    // populate and sort the word occurences
    parser.generateSortedOccurences();
    
    // connect to MySql Database
    Connection myConnection = getConnection();
    List <MyPair> myList = parser.getOccurenceList();
    
    // some queries for MySql
    String queryPost = "INSERT INTO word VALUES(?,?,?)";
    String queryRetrieve = "SELECT * FROM word";
    
    tf.setOnAction(e -> {
      try
      {
    	  // get the user input
    	  int menuInput = Integer.parseInt(tf.getText().trim());
    	  
    	  toServer.writeInt(menuInput);
    	  toServer.flush();
    	  
    	  
    	  switch (menuInput)
    	  {
	    	  case 1:
	    		  	ta.appendText("Beginning to populate MySql Database\n");
	    		  	// this will be the PK index
	    		 	Integer index = 1;
	    			for (MyPair iter : myList)
	    			{
	    				// create the mysql insert prepared statement
	    			    PreparedStatement preparedStmt = myConnection.prepareStatement(queryPost);
	    			    
	    			    preparedStmt.setInt(1, index.intValue());
	    			    preparedStmt.setString(2, iter.getToken());
	    			    preparedStmt.setInt(3, iter.getCount());
	    		
	    			    // execute the prepared statement
	    				preparedStmt.executeUpdate();
	    				preparedStmt.close();
	    				
	    				index++;
	    			}
	    			ta.appendText("Finished populating MySql Database\n");
	    		  break;
	    	  case 2:
	    		  	PreparedStatement prpStmt = myConnection.prepareStatement(queryRetrieve);
	    			ResultSet queryResult = prpStmt.executeQuery();
	    			
	    			while (queryResult.next())
	    			{
	    				ta.appendText(queryResult.getInt(1) + ":\t" + queryResult.getString(2) + "\t\t" + queryResult.getInt(3) + "\n");
	    			}
	    		  break;
	    	  case 3:
	    		  ta.appendText("Word occurences will be displayed on Server using ObjectOutputStreams\n");
	    		  serializedObject.writeObject(myList);
	    		  toServer.flush();
	    		  break;
	    	  default:
	    		  ta.appendText("Enter a valid option from the menu\n");
    			  
    	  }
      }
      catch (IOException ex) {
        System.err.println(ex);
      } catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    });
  
    try {
      // Create a socket to connect to the server
      Socket socket = new Socket("localhost", 8000);
      // Socket socket = new Socket("130.254.204.36", 8000);
      // Socket socket = new Socket("drake.Armstrong.edu", 8000);

      // Create an input stream to receive data from the server
      fromServer = new DataInputStream(socket.getInputStream());

      // Create an output stream to send data to the server
      toServer = new DataOutputStream(socket.getOutputStream());
      
      serializedObject = new ObjectOutputStream(toServer);
    }
    catch (IOException ex) {
      ta.appendText(ex.toString() + '\n');
    }
  }
  /**  
	 *  A method to initialize a connection to a MySql server that contains a database ready to use.
	 *  @return a Connection that is connected and ready to access a database from MySql
	 */
  public static Connection getConnection() throws Exception
	{
		try
		{
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/wordoccurrences";
			String username = "bmstudent";
			String password = "password";
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connected");
			return conn;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return null;
	}
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
