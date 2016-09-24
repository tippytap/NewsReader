import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.geometry.*;

/**
 * News.java - Newsreader for the Guardian News Publication
 * @author erikmiller
 * @version 1.0
 */
public class News extends Application {
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 600;

	/**
	 * Starts running the program
	 * @throws Exception
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("News from The Guardian");
		
		NewsReader reader = new NewsReader();

		View view = new View(reader);
		
		Scene scene = new Scene(view.getRoot(), WIDTH, HEIGHT);
		
		primaryStage.setScene(scene);
		try{
			scene.getStylesheets().add(News.class.getResource("app.css").toExternalForm());
		}
		catch(Exception e){
			System.out.println("boop");
//			e.printStackTrace();
		}
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		launch(args);

	}

}
