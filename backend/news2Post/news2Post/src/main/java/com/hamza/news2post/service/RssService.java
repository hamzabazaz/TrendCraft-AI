package com.hamza.news2post.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.net.URL;
@Service
public class RssService {
	/**
     * Fetches Google News RSS for the given topic. Limits results to maxItems.
     */
    public List<SyndEntry> fetchGoogleNews(String topic, int maxItems) throws Exception {
        String encoded = java.net.URLEncoder.encode(topic, java.nio.charset.StandardCharsets.UTF_8);
        String url = "https://news.google.com/rss/search?q=" + encoded + "&hl=en-US&gl=US&ceid=US:en";
        URL feedUrl = new URL(url);
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(feedUrl));
        return feed.getEntries().stream().limit(maxItems).collect(Collectors.toList());
    }

}
