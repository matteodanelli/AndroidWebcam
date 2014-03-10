package com.blogspot.cyberteo.android.webcam;

/**
 * Created by mattd on 3/10/14.
 */
public class WebcamPreviewData {

    private String title;
    private String thumb;
    private String link;

    public WebcamPreviewData(String title, String thumb, String link) {
        this.title = title;
        this.thumb = thumb;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public String getThumb() {
        return thumb;
    }

    public String getLink() {
        return link;
    }

}

