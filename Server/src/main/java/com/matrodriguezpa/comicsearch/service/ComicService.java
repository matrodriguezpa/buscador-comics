package com.matrodriguezpa.comicsearch.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class ComicService {

    private static final String BASE_URL = "https://getcomics.info/page/";

    // Example method to scrape latest comics
    public CompletableFuture<List<Map<String, Object>>> getLatestComics(int page) {
        String url = BASE_URL + page;
        return scrapeComics(url);
    }

    // Example method to scrape Marvel comics
    public CompletableFuture<List<Map<String, Object>>> getMarvelComics(int page) {
        String url = "https://getcomics.info/cat/marvel/page/" + page;
        return scrapeComics(url);
    }

    // Example method to scrape DC comics
    public CompletableFuture<List<Map<String, Object>>> getDCComics(int page) {
        String url = "https://getcomics.info/cat/dc/page/" + page;
        return scrapeComics(url);
    }

    // Search for comics
    public CompletableFuture<List<Map<String, Object>>> getComicsThroughSearch(String query, int page) {
        String url = "https://getcomics.info/page/" + page + "/?s=" + query.replace(" ", "+");
        return scrapeComics(url);
    }

    // Common method to scrape comics
    private CompletableFuture<List<Map<String, Object>>> scrapeComics(String url) {
        return CompletableFuture.supplyAsync(() -> {
            List<Map<String, Object>> comicsList = new ArrayList<>();

            try {
                Document doc = Jsoup.connect(url).get();
                Elements articles = doc.select("article");

                for (Element article : articles) {
                    // Fetch basic comic data
                    String coverPage = article.select("img").attr("src");
                    String detailsUrl = article.select("a").attr("href");

                    // Only include individual comics, not bundles
                    if (isValidComic(article)) {
                        // Create a new comic entry
                        Map<String, Object> comicData = new HashMap<>();
                        comicData.put("coverPage", coverPage);
                        comicData.put("detailsUrl", detailsUrl);

                        // Fetch detailed information about the comic
                        try {
                            Document detailDoc = Jsoup.connect(detailsUrl).get();
                            String title = detailDoc.select(".post-info h1").text().trim();
                            String description = detailDoc.select(".post-contents p").first() != null ?
                                    detailDoc.select(".post-contents p").first().text().trim() : "No description available";

                            comicData.put("title", title);
                            comicData.put("description", description);
                            comicData.put("downloadLinks", scrapeDownloadLinks(detailDoc));
                        } catch (IOException e) {
                            System.err.println("Error fetching comic details: " + e.getMessage());
                        }

                        // Add the comic data to the list
                        comicsList.add(comicData);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return comicsList;
        });
    }

    // Method to check if the comic is valid (not a bundle)
    private boolean isValidComic(Element article) {
        // You can add your own logic to determine if the comic is valid
        return article.select(".post-info").text().length() > 0; // Example validation
    }

    // Method to scrape download links
    private Map<String, String> scrapeDownloadLinks(Document doc) {
        Map<String, String> downloadLinks = new HashMap<>();
        Elements downloadElements = doc.select(".aio-pulse a");

        for (Element downloadElement : downloadElements) {
            String title = downloadElement.attr("title").replaceAll(" ", "").toUpperCase();
            String link = downloadElement.attr("href");
            downloadLinks.put(title, link);
        }

        return downloadLinks;
    }

    // Info scraper method (to parse additional info)
    private Map<String, String> infoScraper(String str) {
        Map<String, String> info = new HashMap<>();
        if (str.length() != 0) {
            String[] infoPairs = str.split(",");
            for (String pair : infoPairs) {
                String[] keyValue = pair.split(":");
                if (keyValue.length == 2) {
                    info.put(keyValue[0].trim().replace("'", "").replace(" ", ""), keyValue[1].trim().replace("'", ""));
                }
            }
        }
        return info;
    }
}
