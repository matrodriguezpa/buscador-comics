package com.matrodriguezpa.comicsearch.model;

import java.util.Map;

public class Comic {

    private String title;
    private String coverPage;
    private String description;
    private Map<String, String> information;  // Map for holding additional comic information like format, size, year
    private Map<String, String> downloadLinks;  // Map for download links

    // Constructors
    public Comic() {}

    public Comic(String title, String coverPage, String description, Map<String, String> information, Map<String, String> downloadLinks) {
        this.title = title;
        this.coverPage = coverPage;
        this.description = description;
        this.information = information;
        this.downloadLinks = downloadLinks;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverPage() {
        return coverPage;
    }

    public void setCoverPage(String coverPage) {
        this.coverPage = coverPage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, String> getInformation() {
        return information;
    }

    public void setInformation(Map<String, String> information) {
        this.information = information;
    }

    public Map<String, String> getDownloadLinks() {
        return downloadLinks;
    }

    public void setDownloadLinks(Map<String, String> downloadLinks) {
        this.downloadLinks = downloadLinks;
    }
}
