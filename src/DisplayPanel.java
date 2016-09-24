import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.awt.datatransfer.*;
import java.awt.Toolkit;


/**
 * DisplayPanel.java - Displays the article content to the user
 * @author erikmiller
 * @version 1.0
 */

public class DisplayPanel{
	
	private VBox vbox;
	
	private WebView webView;
	
	private WebEngine engine;
	
	/**
	 * Displays an article to the user
	 * @param NewsReader the reader that makes requests 
	 */
	public DisplayPanel(NewsReader reader){
		
		vbox = new VBox();
		webView = new WebView();
		engine = webView.getEngine();
		vbox.getChildren().add(webView);
		
	}
	
	/**
	 * Builds an html document to display in a WebView
	 * @param NewsItem the article to display
	 */
	private String buildHTML(NewsItem item){
		
		String html = "<html>"
					  + "<head><link rel='stylesheet' href='"+ getClass().getResource("style.css") +"'></head>"
					  + "<h1>" + item.getHeadline() + "</h1>"
					  + "<p class='author'><em>Written by: <span class='contributor'>" + item.getContributor() + "</span>&nbsp;&nbsp;|&nbsp;&nbsp;" + item.getType() + "</em></p>"
					  + item.getBody()
					  + "</html>";
		
		return html;
	}
	
	/**
	 * Returns the layout root
	 * @return VBox the layout root object
	 */
	public VBox getVBox(){
		return vbox;
	}
	
	/**
	 * Displays one article in a WebView
	 * @param NewsItem the article to display
	 */
	public void displayNewsItem(NewsItem item){

		Button btn = new Button("Copy link");
		btn.setId("copy-link");
		btn.setPrefWidth(News.WIDTH / 2);
		
		btn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				StringSelection selection = new StringSelection(item.getUrl());
				Clipboard cp = Toolkit.getDefaultToolkit().getSystemClipboard();
				cp.setContents(selection, null);
				
			}
			
		});
		
		if(vbox.getChildren().size() < 2)
			vbox.getChildren().add(btn);
		else{
			vbox.getChildren().remove(1);
			vbox.getChildren().add(btn);
		}
		String html = this.buildHTML(item);
		
		engine.loadContent(html);

		
	}
	
}
