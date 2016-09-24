import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * NewsButton.java - Represents a link to a news item
 * @author erikmiller
 * @version 1.0
 */

public class NewsButton extends Button{

	private int id;
	private ImageView image;
	
	private final int WIDTH = 330;
	
	/**
	 * Represents a link to a news article
	 * @param String the text of the button
	 * @param int the id of the button
	 * @param String the thumbnail of the NewsItem
	 */
	public NewsButton(String str, int id, String thumbnail){
		super(str);
		if(thumbnail != null){
			this.makeImageView(thumbnail);
			super.setGraphic(image);
		}
		this.id = id;
		super.setWrapText(true);
		super.setPrefWidth(WIDTH);
	}
	
	/**
	 * Makes an ImageView object with the thumbnail
	 * @param String the thumbnail of the NewsItem
	 */
	private void makeImageView(String thumbnail){
		image = new ImageView(thumbnail);
		image.setPreserveRatio(true);
		image.setSmooth(true);
		image.setFitWidth(WIDTH / 3);
	}
	
	/**
	 * Returns the id of the button to use when a button is clicked
	 * @return int the id of the button
	 */
	public int getBtnId(){
		return id;
	}
	
	/**
	 * Returns a string representation of this class
	 * @return String the id of this button in string form
	 */
	@Override
	public String toString(){
		return "" + id;
	}
	
}
