import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * NewsItem.java - A news article
 * @author erikmiller
 * @version 1.0
 */

public class NewsItem {

	private String headline, thumbnail, bodyStr, contributor, type, url;
	
	private int id;
	
	private Element body;
	
	/**
	 * Represents one news article from the Guardian api
	 * @param String the headline of the article
	 * @param String the url of the thumbnail image
	 * @param String the body content of the article
	 * @param int the id of the news item
	 * @param String the contributor that wrote the article
	 * @param String the type of content it is
	 * @param String the url to the article on the web
	 * */
	public NewsItem(String headline, 
					String thumbnail, 
					String body, 
					int id, 
					String contributor, 
					String type,
					String url){
		
		this.headline = headline;
		this.bodyStr = body;
		this.thumbnail = thumbnail;
		this.contributor = contributor;
		this.type = type;
		this.url = url;
		this.id = id;
		
		this.parseBody();
		
	}
	
	/**
	 * Parses the html for anchor tags and removes their links
	 */
	public void parseBody(){

		Document doc = Jsoup.parseBodyFragment(bodyStr);
		body = doc.body();
		Elements elements = body.children();
		body.select("a").unwrap();
		body.select("iframe").remove();
		body.select("figure").remove();
		
	}
	
	/**
	 * Returns the id of this news item
	 * @return int the id
	 */
	public int getId(){
		return id;
	}
	
	/**
	 * Returns the body element of this news item
	 * @return Element body
	 */
	public Element getBody(){
		return body;
	}
	
	/**
	 * Returns the headline of this news item
	 * @return String headline
	 */
	public String getHeadline(){
		return headline;
	}
	
	/**
	 * Returns the url for the thumbnail of this news item
	 * @return String thumbnail url 
	 */
	public String getThumbnail(){
		return thumbnail;
	}
	
	/**
	 * Returns the url for the article on the web
	 * @return String the url
	 */
	public String getUrl(){
		return url;
	}
	
	/**
	 * Returns the name of the author who wrote the article
	 * @return String contributor
	 */
	public String getContributor(){
		return contributor;
	}
	
	/**
	 * Returns the type of this article
	 * @return String type
	 */
	public String getType(){
		return type;
	}
	
	/**
	 * Returns a string representation of this news item
	 * @return String the headline
	 */
	public String toString(){
		return this.headline;
	}
	
}
