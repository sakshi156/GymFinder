package com.crawler;

import java.io.BufferedReader;
import java.io.IOException; 
import java.io.InputStreamReader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Node;

import com.dao.InsertGym;
 
public class Crawler {
	private InsertGym newRecord;
	private int count;
	private final String userAgent = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; .NET CLR 1.0.3705; .NET CLR 1.1.4322; .NET CLR 1.2.30703)";
	
	public Crawler() {
		 this.newRecord = new InsertGym();
		 this.count = 0;
	}
 
	private void processListPage(String URL, int currentPage) throws IOException {
		Document listPage = null;
		
		try {
		listPage = Jsoup.connect(URL).userAgent(this.userAgent).timeout(10000).get();
		} catch (IOException e1) {
			return;
		}
		
		Elements detailLinks = listPage.select("span.jcn").select("a[href]");
		for(Element link: detailLinks){
				this.processDetailPage(link.attr("abs:href"));					
		}

		Elements nextLinks = listPage.select("div.jpag").select("a[href]");
		for(Element link: nextLinks){
			String url = link.attr("abs:href");
			if(url.contains("page-")) {
				int pageNum = Integer.parseInt(url.substring(url.lastIndexOf("-") + 1).trim());
				if(pageNum > currentPage )
					this.processListPage(link.attr("abs:href"), pageNum );	
			}
		}
	}

	
	private void processDetailPage(String URL) {
		Document detailPage = null;
		try {
			detailPage = Jsoup.connect(URL).userAgent(this.userAgent).timeout(10000).get();
		} catch (IOException e1) {
			return;
		}
		
		String name = detailPage.select("span.fn").text();
		if(name != null && !name.isEmpty()) {
			System.out.println(name);
		}
		String address = detailPage.select("span.jadlt").text();
		String contact = "", website = ""; String hours = "";
		Element contactElement = detailPage.select("a.tel b").first();
		if (contactElement != null)
			contact = contactElement.ownText();
		else {
			contactElement = detailPage.select("a.tel").first();
			if (contactElement != null)
				contact = contactElement.ownText();
		}
		Element websiteElement =  detailPage.select("p.wsurl").select("a").first();
		if (websiteElement != null)
			website = websiteElement.ownText();
		Elements hoursElement = detailPage.select("tr.reset").select("td");
		for (Element td:hoursElement) {
				Element days = td.select("b").first();
				if (days !=null) {
					Node time = days.nextSibling();
					hours = hours + days.ownText() + time.toString();
				}
		}
		this.newRecord.insertGymDetails(name, contact, address, website, hours);
		this.count++;
		
	}
	
	public static void main(String[] args) throws IOException {
		 
		System.out.println("Enter City: ");
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		String city = bufferRead.readLine();
		
		Crawler crawler = new Crawler();
		crawler.processListPage("http://www.justdial.com/" + city + "/Gymnasiums", 1);
		System.out.println("---- Processed " + crawler.count + " records ----");
	}
	
}