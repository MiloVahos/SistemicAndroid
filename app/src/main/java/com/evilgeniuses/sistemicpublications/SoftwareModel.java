package com.evilgeniuses.sistemicpublications;

public class SoftwareModel {

    private String author;
    private String availability;
    private String institution;
    private String name;
    private String title;
    private String year;

    public SoftwareModel() {}

    public SoftwareModel(String author, String availability, String institution, String name, String title, String year) {
        this.author = author;
        this.availability = availability;
        this.institution = institution;
        this.name = name;
        this.title = title;
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
