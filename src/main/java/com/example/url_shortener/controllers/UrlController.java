package com.example.url_shortener.controllers;

import com.example.url_shortener.entities.Url;
import com.example.url_shortener.repositories.UrlRepository;
import com.example.url_shortener.utilities.GenerateUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/u")
public class UrlController  {
    private final UrlRepository urlRepository;

    @Autowired
    public UrlController(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @PostMapping("/")
    public ResponseEntity<String> createUrl( @RequestParam String originalUrl) {
        try {
            Url url = new Url();
            url.setOriginalUrl(originalUrl);
            url.setShortenUrl(GenerateUID.Generate());
            url.setStatus("ACT");
            Date current_time = new Date() ;
            /* TODO change this to a custom domain*/
            String new_url = "http://localhost:8080/u/"+url.getShortenUrl();
            url.setCreatedAt(current_time);
            url.setViews(0L);
            Url savedUrl = urlRepository.save(url);
            return new ResponseEntity<>(new_url, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/check/{shortenUrl}")
    public ResponseEntity<Url> getData( @PathVariable String shortenUrl) {
        try {
            Url url = urlRepository.findByShortenUrl(shortenUrl);
            if (url != null) {
                return new ResponseEntity<>(url, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/v/{shortenUrl}")
    public ResponseEntity<String> getViews(@PathVariable String shortenUrl) {
        Url url = urlRepository.findByShortenUrl(shortenUrl);
        if (url != null) {
            return new ResponseEntity<>(url.getViews().toString(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{shortenUrl}")
    public RedirectView getUrl(@PathVariable String shortenUrl) {
        Url url = urlRepository.findByShortenUrl(shortenUrl);
        if (url != null) {
            Long current_view = url.getViews();
            current_view += 1;
            url.setViews(current_view);
            urlRepository.save(url);
            return new RedirectView(url.getOriginalUrl());
        } else {
            return new RedirectView("/errorPage");
        }
    }

}
