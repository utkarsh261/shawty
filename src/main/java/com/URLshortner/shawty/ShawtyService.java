package com.URLshortner.shawty;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import org.springframework.cache.annotation.Cacheable;

import com.URLshortner.shawty.Hash;
import com.URLshortner.shawty.Link;
import com.URLshortner.shawty.LinkRepository;

@Service
public class ShawtyService {
    private LinkRepository linkRepo;
  
    @Autowired
    public ShawtyService(LinkRepository urlRepository) {
        this.linkRepo = urlRepository;
    }

    public Link shortenUrl(String url) {
        Link l = new Link();
        l.setUrl(url);
        String hash = Hash.getKey(url);
        l.setKey(hash);
        // No need to create new if exists
        if (linkRepo.existsByK(hash) == false) {
            linkRepo.save(l);
        }
        return l;
    }

    @Cacheable("short-url")
    public String getOriginalURL(String id) {
        return linkRepo.findByK(id).getUrl();
    }

    public Iterable<Link> getAll() {
        return linkRepo.findAll();
    }
}
