import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * SectionPanel.java - provides buttons to load various content from The Guardian
 * @author erikmiller
 * @version 1.0
 */
public class SectionPanel {
	
	private FlowPane pane;
	
	private View view;
	
	private final String[] SECTIONS = new String[]{
			"us-news",
			"world",
			"commentisfree",
			"football",
			"technology",
			"culture",
			"lifeandstyle",
			"fashion",
			"business",
			"travel",
			"environment",
			"science"
	};
	
	private final String[] SECTION_NAMES = new String[]{
			"US",
			"World",
			"Opinion",
			"Soccer",
			"Tech",
			"Arts",
			"Lifestyle",
			"Fashion",
			"Business",
			"Travel",
			"Environment",
			"Science"
	};

	/**
	 * Provides buttons for the user to choose the section of content they want
	 * @param View view object to use if need to reformat the interface
	 */
	public SectionPanel(View view){
		
		this.view = view;
		
		pane = new FlowPane();
		pane.setMaxWidth(News.WIDTH);
		
		for(int i = 0; i < SECTIONS.length; i++){
			Button btn = new Button(SECTION_NAMES[i]);
			btn.setPrefHeight(20);
			int pos = i;
			EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent e) {
					view.removePanels(SECTIONS[pos]);
				}
				
			};
			btn.setOnAction(handler);
			pane.getChildren().add(btn);
		}
		
	}
	
	/**
	 * Returns the root layout element
	 * @return FlowPane pane
	 */
	public FlowPane getPane(){
		return pane;
	}
	
}
