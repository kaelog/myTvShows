package net.kaelog.tvshows.utils;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.MasonTagTypes;
import net.htmlparser.jericho.MicrosoftTagTypes;
import net.htmlparser.jericho.Source;

import org.springframework.stereotype.Service;

@Service
public class LinkExtractor {
	private String location;

	private Source source;

	public LinkExtractor() {
		this("http://tvrss.net/shows/");
	}

	public LinkExtractor(String location) {
		MicrosoftTagTypes.register();
		MasonTagTypes.register();

		this.location = location;
		try {
			source = new Source(new URL(location));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public List<String> extractLinks() {
		System.out.println("Parsing " + location + " for links... ");

		List<Element> links = source.getAllElements(HTMLElementName.A);

		List<String> result = new ArrayList<String>();
		for (Element element : links) {
			String href = element.getAttributeValue("href");
			if (href != null) {
				if (href.startsWith("http://tvrss.net/search/")) {
					result.add(element.getContent().toString());
					System.out.println("Adding : "+element.getContent().toString()) ;
				}
			}
		}
		return result;
	}

}
