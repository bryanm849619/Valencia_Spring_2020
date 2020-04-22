package application;

import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Server extends Application 
{
  @Override
  /**  
	 *  a override method for javaFX to render a window with a stage and scene
	 *  @param primaryStage  the primary stage for the window and scenes.
	 */
  public void start(Stage primaryStage) 
  {
	 
    // Text area for displaying contents
    TextArea ta = new TextArea();
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(new ScrollPane(ta), 450, 200);
    primaryStage.setTitle("Server"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    new Thread( () -> {
      try {
        // Create a server socket
        ServerSocket serverSocket = new ServerSocket(8000);
        Platform.runLater(() ->
          ta.appendText("Server started at " + new Date() + '\n'));
  
        // Listen for a connection request
        Socket socket = serverSocket.accept();
  
        // Create data input and output streams
        DataInputStream inputFromClient = new DataInputStream(
          socket.getInputStream());
        DataOutputStream outputToClient = new DataOutputStream(
          socket.getOutputStream());
        
        ObjectInputStream serializedObject = new ObjectInputStream(inputFromClient);
  
        while (true) {
        	int menuInput = inputFromClient.readInt();
        	List <MyPair> myList;
        	Integer index = 1;
        	
        	if (menuInput == 3)
        	{
        		myList = (List <MyPair>) serializedObject.readObject();

        		for (MyPair iter : myList)
        		{
        			if (index > 20)
        				break;
        			
        			ta.appendText(index.intValue() + ":\t" + iter.getToken() + "\t\t" + iter.getCount() + "\n");
        			index++;
        		}
        	}
       
        	Platform.runLater(() -> {
                ta.appendText("Menu option received from client: " 
                  + menuInput + '\n');

          });
        }
      }
      catch(IOException ex) {
        ex.printStackTrace();
      } catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }).start();
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}

