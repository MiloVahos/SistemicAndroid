package com.evilgeniuses.sistemicpublications;

public class GeneralModel {

    private String title;
    private String year;
    private String author;

    public GeneralModel(){};

    public GeneralModel(String title, String year, String author){
        this.title  =   title;
        this.year   =   year;
        this.author =   author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
