package com.evilgeniuses.sistemicpublications;

public class BooksModel {

    private String author;
    private String editorial;
    private String title;
    private String year;

    public BooksModel() {}

    public BooksModel(String author, String editorial, String title, String year) {
        this.author = author;
        this.editorial = editorial;
        this.title = title;
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
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
