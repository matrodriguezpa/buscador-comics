package com.matrodriguezpa.comicsearch.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matrodriguezpa.comicsearch.model.ComicIssue;
import com.matrodriguezpa.comicsearch.service.ComicService;

@RestController
@RequestMapping("/api/comics")
public class ComicController {

    @Autowired
    private ComicService comicService;

    @GetMapping("/new-releases")
    public ResponseEntity<List<ComicIssue>> getNewComics(@RequestParam(required = false) String date, 
                                                          @RequestParam(required = false) String publisher) {
        if (publisher != null && !publisher.isEmpty()) {
            List<ComicIssue> comics = comicService.getComicsByPublisher(publisher);
            return ResponseEntity.ok(comics);
        }
        
        if (date == null || date.isEmpty()) {
            date = LocalDate.now().toString(); // Default to today's date
        }
        
        List<ComicIssue> comics = comicService.getComicsByDate(date);
        return ResponseEntity.ok(comics);
    }
}
