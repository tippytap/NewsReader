import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * NewsCollection.java - A list of news items
 * @author erikmiller
 * @version 1.0
 */

public class NewsCollection implements Iterable<NewsItem> {
	
	private List<NewsItem> list;
	
	/**
	 * Makes a list of NewsItem objects 
	 */
	public NewsCollection(){
		list = new LinkedList<NewsItem>();
	}

	/**
	 * Returns the iterator over the collection
	 * @return Iterator<NewsItem> the iterator over the collection of news items
	 */
	@Override
	public Iterator<NewsItem> iterator() {
		return list.iterator();
	}
	
	/**
	 * Adds a new article to the list of news items
	 * @param NewsItem the article to add to the list
	 */
	public void addNewsItem(NewsItem item){
		if(item != null)
			list.add(item);
	}
	
	/**
	 * Returns the collection of articles
	 * @return List<NewsItem> the list of news articles
	 */
	public List<NewsItem> getList(){
		return list;
	}
	
	/**
	 * Returns the collection as a string
	 * @return String the collection in string form
	 */
	@Override
	public String toString(){
		String result = "";
		
		Iterator<NewsItem> iter = this.iterator();
		
		while(iter.hasNext()){
			result += iter.next();
			if(iter.hasNext())
				result += ", \n";
		}
		
		return result;
	}

}
