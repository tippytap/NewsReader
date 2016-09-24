import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 * NewsReader.java - Requests news content from The Guardian
 * @author erikmiller
 * @version 1.0
 */

public class NewsReader {

	private NewsCollection newsCollection;
	
	private final String KEY = "&api-key=ss4qgw9py4fmjadf8pm82cua";

	private final String BASIC_REQUEST = "http://content.guardianapis.com/search?";
	
	private String section;

	private final String SHOW_FIELDS = "&show-fields=";
	
	private final String PAGE_SIZE = "&page-size=15";
	
	private final String SHOW_TAGS = "&show-tags=";
	
	private final String[] TAGS = new String[]{"contributor,", "keyword"};

	private final String[] PARAMS = new String[]{"&format=json", "&order-by=newest"};

	private final String[] FIELDS = new String[]{"body,", "headline,", "thumbnail"};
	
	/**
	 * Makes a request to the Guardian API
	 * @param String the search query to use
	 * @throws MalformedURLException
	 * @throws IOException
	 * */
	public void makeRequest(String query) throws java.net.MalformedURLException, java.io.IOException{
		
		newsCollection = new NewsCollection();
		
		section = "section=" + query;

		String requestStr = this.buildRequestStr();

		try{
			URL service = new URL(requestStr);
			String result = IOUtils.toString(service, "UTF-8");
			Object value = JSONValue.parse(result);
			JSONObject json = (JSONObject) value;

			JSONObject response = (JSONObject)json.get("response");
			
			String status = (String)response.get("status");
			
			JSONArray results = (JSONArray)response.get("results");
			
			this.makeNewsItem(results, status);
		}
		catch(Exception e){
			System.out.println("boop");
		}
	}
	
	/**
	 * Builds the news items for the requested content
	 * @param JSONArray the array of search results
	 * @param String the status of the results
	 */
	private void makeNewsItem(JSONArray results, String status){
		Iterator iter = results.iterator();
		
		int id = 0;
		
		if(status.equals("ok")){
			while(iter.hasNext()){
				JSONObject next = (JSONObject)iter.next();
				String type = (String) next.get("type");
				if(!type.equals("interactive")){
					JSONObject fields = (JSONObject) next.get("fields");
					String headline = (String)fields.get("headline");
					String thumbnail = (String)fields.get("thumbnail");
					String body = (String)fields.get("body");
					String url = (String)next.get("webUrl");
					String contributor = "";
					String typeTg = "";
					
					JSONArray tags = (JSONArray) next.get("tags");
					Iterator it = tags.iterator();
					while(it.hasNext()){
						JSONObject nxt = (JSONObject)it.next();
						contributor = (String)nxt.get("webTitle");
						typeTg = (String)nxt.get("type");
					}
					NewsItem item = new NewsItem(headline, thumbnail, body, id, contributor, typeTg, url);
					newsCollection.addNewsItem(item);
					id++;
				}
			}
		}
	}

	/**
	 * Builds and returns a query string to make a request to the API
	 * @return the query string to use in the request to the API
	 */
	private String buildRequestStr(){

		String request = BASIC_REQUEST + section + KEY;

		for(String p : PARAMS)
			request += p;

		request += SHOW_FIELDS;

		for(String f : FIELDS)
			request += f;
		
		request += PAGE_SIZE;
		
		request += SHOW_TAGS;
		
		for(String t : TAGS)
			request += t;

		return request;
	}
	
	/**
	 * Returns the collection of news items
	 * @return NewsCollection the collection of news items
	 */
	public NewsCollection getCollection(){
		return newsCollection;
	}
	
}
