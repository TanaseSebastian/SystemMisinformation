package Control;
import java.net.*;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
public class Ricerca {
	
	public class URLConnectionReader {
	    public static void main(String[] args) throws Exception {
	    	Document page = Jsoup.connect("https://www.scrapingdog.com/blog/").get();
	    	Elements pageElements = page.select("div.blog-header a h2");
	    	ArrayList<String> blogHeadings = new ArrayList<>();
	    	//loop through the fetched page elements adding them to the blogHeadings array list

	    	for (Element e:pageElements) {

	    	blogHeadings.add("Heading: " + e.text());

	    	}
	    	//print out the array list

	    	for (String s : blogHeadings) {

	    	System.out.println(s);

	    	}
	    }
	}
}
