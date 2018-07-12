package com.evilgeniuses.sistemicpublications;

public class JournalsModel {

    private String author;
    private String colciencias;
    private String journal;
    private String number;
    private String pages;
    private String sjRJcR;
    private String title;
    private String url;
    private String volume;
    private String year;

    public JournalsModel() {}

    public JournalsModel(String author,
                         String colciencias,
                         String journal,
                         String number,
                         String pages,
                         String sjRJcR,
                         String title,
                         String url,
                         String volume,
                         String year) {
        this.author = author;
        this.colciencias = colciencias;
        this.journal = journal;
        this.number = number;
        this.pages = pages;
        this.sjRJcR = sjRJcR;
        this.title = title;
        this.url = url;
        this.volume = volume;
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getColciencias() {
        return colciencias;
    }

    public void setColciencias(String colciencias) {
        this.colciencias = colciencias;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getSjRJcR() {
        return sjRJcR;
    }

    public void setSjRJcR(String sjRJcR) {
        this.sjRJcR = sjRJcR;
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

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
