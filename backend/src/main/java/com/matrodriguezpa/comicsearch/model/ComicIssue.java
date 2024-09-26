package com.matrodriguezpa.comicsearch.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ComicIssue {
    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String title;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("publisher")
    private Publisher publisher;

    // Getters and setters...

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public static class Publisher {
        @JsonProperty("name")
        private String name;

        // Getters and setters...
        
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
