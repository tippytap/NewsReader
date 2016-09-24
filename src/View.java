import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;


/**
 * View.java - Acts as a controller for all visual elements of the program
 * @author erikmiller
 * @version 1.0
 */

public class View {
	
	private GridPane root;
	
	private NewsCollection collection;
	
	private NewsReader reader;
	
	private InputPanel inputPanel;
	
	private DisplayPanel displayPanel;
	
	private SectionPanel sectionPanel;

	/**
	 * Handles high level tasks for displaying all news content
	 * @param NewsReader a NewsReader object to make requests from
	 */
	public View(NewsReader reader){
		
		root = new GridPane();
		
		sectionPanel = new SectionPanel(this);
		
		root.add(sectionPanel.getPane(), 0, 0, 2, 1);
		
		this.reader = reader;
		
		this.makeRequest("us-news");
		
		this.makeButtons();
		
	}
	
	/**
	 * Makes new display panels and makes a new request for content
	 * @param String the search query to use to make the request
	 */
	public void makeRequest(String query){
		
		inputPanel = new InputPanel(this);
		displayPanel = new DisplayPanel(reader);
		
		try {
			reader.makeRequest(query);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		collection = reader.getCollection();
		
		root.add(inputPanel.getVBox(), 0, 2);
		root.add(displayPanel.getVBox(), 1, 2);
	}
	
	/**
	 * Removes the old news content from the layout and makes a new request
	 * @param String the search query to use to make the request 
	 */
	public void removePanels(String query){
		ObservableList<Node> p = root.getChildren();
		while(p.size() > 1){
			p.remove(p.size() - 1);
		}
		this.makeRequest(query);
		this.makeButtons();
	}
	
	/**
	 * Parses and finds the correct NewsItem to display
	 * @param String the id of the NewsItem to display
	 */
	public void displayArticle(String idStr){
		Scanner scan = new Scanner(idStr);
		int id = scan.nextInt();
		for(NewsItem item : collection){
			if(id == item.getId()){
				displayPanel.displayNewsItem(item);
			}
		}
		scan.close();
		
	}
	
	/**
	 * Tells the input panel to make the buttons for the news headlines
	 */
	public void makeButtons(){
		inputPanel.makeButtons(collection);
	}
	
	/**
	 * Returns the root object for the layout
	 * @return GridPane root 
	 */
	public GridPane getRoot(){
		return root;
	}
	
}
