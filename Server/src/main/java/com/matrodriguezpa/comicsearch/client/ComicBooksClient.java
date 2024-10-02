package com.matrodriguezpa.comicsearch.client;

import com.matrodriguezpa.comicsearch.model.Comic;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// Define Feign Client for external comic book API
@FeignClient(name = "comicbooksClient", url = "https://getcomics.info")
public interface ComicBooksClient {

    // Get the latest comics from the API
    @GetMapping("/page/{pageNumber}")
    List<Comic> getLatestComics(@PathVariable int pageNumber);

    // Search for comics based on a query and page number
    @GetMapping("/page/{pageNumber}") // This may need to change based on the API endpoint
    List<Comic> getComicsThroughSearch(@RequestParam("s") String query, @PathVariable int pageNumber);
    
    // Additional methods for specific publishers can be added here
}