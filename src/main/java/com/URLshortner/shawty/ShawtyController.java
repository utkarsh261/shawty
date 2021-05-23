package com.URLshortner.shawty;

import java.net.URI;
import java.net.URL;
import java.nio.charset.MalformedInputException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import javax.servlet.http.*;
import javax.xml.crypto.URIReferenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class ShawtyController {
    static final Logger log = 
        LoggerFactory.getLogger(ShawtyController.class);
    
    private final ShawtyService shawtyService;
    
   
    public ShawtyController(ShawtyService urlConverterService) {
        this.shawtyService = urlConverterService;
    }

    private boolean isValid(String url) {
        if (url.startsWith("http") == false) {
            url = "http://" + url;
        }
        try {
            new URL(url).toURI();
        } catch (MalformedURLException | URISyntaxException e) {
            e.printStackTrace();
            return false; 
        }
        return true;
    }
    
    @PostMapping("/set")
    public ResponseEntity<Link> setLink(@RequestParam String url) {
        if (isValid(url) == false) {
            log.info("invalid URL");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Link l = shawtyService.shortenUrl(url);
        return new ResponseEntity(l, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
        public RedirectView redirectUrl(@PathVariable String id, HttpServletRequest request, 
            HttpServletResponse response) throws java.io.IOException, java.net.URISyntaxException, Exception {
            log.info("Received shortened url to redirect: " + id);
            String redirectUrlString = shawtyService.getOriginalURL(id);
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("http://" + redirectUrlString);
            return redirectView;
        }
    
    // For Debugging purposes only //

    @GetMapping("/list")
    public Iterable<Link> getAll() {
        return shawtyService.getAll();
    }
}
