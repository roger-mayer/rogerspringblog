package com.codeup.rogerspringblog.controllers;

import com.codeup.rogerspringblog.models.Ad;
import com.codeup.rogerspringblog.repositories.AdRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AdsController {
    //dependency injection
    private AdRepository adsDao;
    public AdsController(AdRepository adsDao){
        this.adsDao = adsDao;
    }

    @GetMapping("/ads")
    public String allAds(){
        return null;

    }
    @GetMapping("/ads/jpa")
    @ResponseBody
    public List<Ad> returnAds(){
        return adsDao.findAll();

    }
    @GetMapping("/ads/jpa/create")
    @ResponseBody
    public void createAd(){
        Ad ad = new Ad();
        ad.setTitle("A new ad");
        ad.setDescription("This is a new decription");
        adsDao.save(ad);

    }




}

