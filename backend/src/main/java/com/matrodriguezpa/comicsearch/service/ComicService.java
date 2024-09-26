package com.matrodriguezpa.comicsearch.service;

import com.matrodriguezpa.comicsearch.model.ComicIssue;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service
public class ComicService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${comicvine.api.url}")
    private String comicvineApiUrl;

    @Value("${comicvine.api.key}")
    private String comicvineApiKey;

    public ComicService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public List<ComicIssue> getComicsByDate(String date) {
        String url = String.format("%sissues/?api_key=%s&filter=store_date:%s&sort=store_date:desc&format=json",
                comicvineApiUrl, comicvineApiKey, date);

        System.out.println("Requesting: " + url);

        ResponseEntity<String> response;
        try {
            response = restTemplate.getForEntity(url, String.class);
        } catch (Exception e) {
            throw new RuntimeException("Error during API request: " + e.getMessage(), e);
        }

        if (response.getStatusCode() == HttpStatus.OK) {
            try {
                JsonNode jsonNode = objectMapper.readTree(response.getBody());
                return objectMapper.convertValue(jsonNode.get("results"), new TypeReference<List<ComicIssue>>() {
                });
            } catch (IOException e) {
                throw new RuntimeException("Failed to parse response: " + e.getMessage(), e);
            }
        } else {
            throw new RuntimeException("Failed to fetch comics: " + response.getStatusCode());
        }
    }

    public List<ComicIssue> getComicsByPublisher(String publisherName) {
        String url = String.format("%sissues/?api_key=%s&filter=publisher:%s&sort=store_date:desc&format=json",
                comicvineApiUrl, comicvineApiKey, publisherName);

        System.out.println("Requesting: " + url);

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        System.out.println("Response: " + response.getBody());

        if (response.getStatusCode() == HttpStatus.OK) {
            try {
                JsonNode jsonNode = objectMapper.readTree(response.getBody());
                return objectMapper.convertValue(jsonNode.get("results"), new TypeReference<List<ComicIssue>>() {
                });
            } catch (IOException e) {
                throw new RuntimeException("Failed to parse response: " + e.getMessage(), e);
            }
        } else {
            throw new RuntimeException("Failed to fetch comics: " + response.getStatusCode());
        }
    }
}
