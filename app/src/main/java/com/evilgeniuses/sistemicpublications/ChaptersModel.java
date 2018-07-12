package com.evilgeniuses.sistemicpublications;

public class ChaptersModel {

    private String author;
    private String bookTitle;
    private String editorial;
    private String pages;
    private String title;
    private String year;

    public ChaptersModel() {}

    public ChaptersModel(String author, String bookTitle, String editorial, String pages, String title, String year) {
        this.author = author;
        this.bookTitle = bookTitle;
        this.editorial = editorial;
        this.pages = pages;
        this.title = title;
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
