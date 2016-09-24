import java.awt.Dimension;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * InputPanel.java - Provides a list of buttons for the user to control what content they see
 * @author erikmiller
 * @version 1.0
 */

public class InputPanel {
	
	private List<NewsButton> articles;
	
	private VBox vbox;
	
	private HBox searchPanel;
	
	private ScrollPane sp;
	
	private TextField search;
	
	private View view;
	
	private final EventHandler<ActionEvent> btnHandler = new EventHandler<ActionEvent>(){
		@Override
		public void handle(ActionEvent e){
			view.displayArticle(e.getSource().toString());
		}
	};

	/**
	 * Provides a list of buttons for the user to choose which content to display
	 * @param View the view to use while displaying content
	 */
	public InputPanel(View view){
		
		this.view = view;
		
		this.vbox = new VBox();
		
		this.vbox.setSpacing(1.0);
		
		this.vbox.setId("inputPanel");
		
		searchPanel = new HBox();
		
		articles = new LinkedList<NewsButton>();
		
	}
	
	/**
	 * Makes buttons that represent news articles
	 * @param NewsCollection the collection of articles to display
	 */
	public void makeButtons(NewsCollection collection){
		
		sp = new ScrollPane();
		
		VBox panel = new VBox(); 
		VBox.setVgrow(sp, Priority.ALWAYS);
		
		Iterator<NewsItem> iter = collection.iterator();
		
		int pos = 0;
		
		while(iter.hasNext()){
			NewsItem next = iter.next();
			NewsButton btn = new NewsButton(next.getHeadline(), pos, next.getThumbnail());
			btn.setOnAction(btnHandler);
			articles.add(btn);
			pos++;
		}
		
		for(Button btn : articles)
			panel.getChildren().add(btn);
		
		sp.setVmax(500);
		sp.setPrefSize(News.WIDTH/2, News.HEIGHT/2);
		sp.setContent(panel);
		
		vbox.getChildren().add(sp);
		
		articles.get(0).fire();
		
	}
	
	/**
	 * Returns the root layout object
	 * @return VBox the root layout object
	 */
	public VBox getVBox(){
		return vbox;
	}
	
}
