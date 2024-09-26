package com.matrodriguezpa.comicsearch.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matrodriguezpa.comicsearch.service.ComicService;

@CrossOrigin(origins = "http://localhost:3000") // This allows your frontend to make API requests
@RestController
@RequestMapping("/api/comics")  // This maps HTTP requests to /api/comics
public class ComicController {

    @Autowired
    private ComicService comicService;

    // Get latest comics
    @GetMapping("/latest/{page}")
    public List<Map<String, Object>> getLatestComics(@PathVariable int page) {
        return comicService.getLatestComics(page);
    }

    // Get Marvel comics
    @GetMapping("/marvel/{page}")
    public List<Map<String, Object>> getMarvelComics(@PathVariable int page) {
        return comicService.getMarvelComics(page);
    }

    // Get DC comics
    @GetMapping("/dc/{page}")
    public List<Map<String, Object>> getDCComics(@PathVariable int page) {
        return comicService.getDCComics(page);
    }

    // Get comics through search
    @GetMapping("/search")
    public List<Map<String, Object>> getComicsThroughSearch(@RequestParam String query, @RequestParam(defaultValue = "1") int page) {
        return comicService.getComicsThroughSearch(query, page);
    }
}
