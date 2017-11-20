package com.example.shashidhar.trial;

/**
 * Created by Shashidhar on 28-10-2017.
 */

// Model

public class RssFeedModel {

    private String title;
    private String link;
    private String description;
    private String imgurl;

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

    /**
     * To get title
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     * To get Link
     * @return String
     */

    public String getLink() {
        return link;
    }

    /**
     * To get description
     * @return String
     */

    public String getDescription() {
        return description;
    }

    /**
     * To get imageurl
     * @return String
     */

    public String getImgurl() {
        return imgurl;
    }

}
