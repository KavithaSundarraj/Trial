package com.example.shashidhar.trial;

/**
 * Created by Shashidhar on 28-10-2017.
 */

// Model

public class RssFeedModel {

    public String title;
    public String link;
    public String description;
    public String imgurl;

    /**
     * Creates a new RssFeedModel
     * @param title
     * @param link
     * @param description
     * @param imgurl
     */

    public RssFeedModel(String title, String link, String description,String imgurl) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.imgurl = imgurl;
    }

}
