package com.evilgeniuses.sistemicpublications;

public class ConferencesModel {

    private String ambit;
    private String author;
    private String conference;
    private String pages;
    private String title;
    private String url;
    private String year;

    public ConferencesModel() {}

    public ConferencesModel(String ambit, String author, String conference, String pages, String title, String url, String year) {
        this.ambit = ambit;
        this.author = author;
        this.conference = conference;
        this.pages = pages;
        this.title = title;
        this.url = url;
        this.year = year;
    }

    public String getAmbit() {
        return ambit;
    }

    public void setAmbit(String ambit) {
        this.ambit = ambit;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
