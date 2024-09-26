package com.matrodriguezpa.comicsearch.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class ComicService {

    private static final String BASE_URL = "https://getcomics.info/page/";

    // Example method to scrape latest comics
    public List<Map<String, Object>> getLatestComics(int page) {
        String url = BASE_URL + page;
        return scrapeComics(url);
    }

    // Example method to scrape Marvel comics
    public List<Map<String, Object>> getMarvelComics(int page) {
        String url = "https://getcomics.info/cat/marvel/page/" + page;
        return scrapeComics(url);
    }

    // Example method to scrape DC comics
    public List<Map<String, Object>> getDCComics(int page) {
        String url = "https://getcomics.info/cat/dc/page/" + page;
        return scrapeComics(url);
    }

    // Search for comics
    public List<Map<String, Object>> getComicsThroughSearch(String query, int page) {
        String url = "https://getcomics.info/page/" + page + "/?s=" + query.replace(" ", "+");
        return scrapeComics(url);
    }

    // Common method to scrape comics
    private List<Map<String, Object>> scrapeComics(String url) {
        List<Map<String, Object>> comicsList = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(url).get();
            Elements articles = doc.select("article");

            for (Element article : articles) {
                Map<String, Object> comicData = new HashMap<>();

                String title = article.select(".post-info h1").text();
                String coverPage = article.select("img").attr("src");

                // Check for the presence of the description element
                Element descriptionElement = article.select(".post-contents p").first();
                String description = descriptionElement != null ? descriptionElement.text() : "No description available";

                String detailsUrl = article.select("a").attr("href");

                comicData.put("title", title);
                comicData.put("coverPage", coverPage);
                comicData.put("description", description);
                comicData.put("detailsUrl", detailsUrl);

                comicsList.add(comicData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return comicsList;
    }

}
