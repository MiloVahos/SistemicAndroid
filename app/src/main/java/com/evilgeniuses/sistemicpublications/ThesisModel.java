package com.evilgeniuses.sistemicpublications;

public class ThesisModel {

    private String author;
    private String student;
    private String title;
    private String type;
    private String university;
    private String year;

    public ThesisModel() {}

    public ThesisModel(String author, String student, String title, String type, String university, String year) {
        this.author = author;
        this.student = student;
        this.title = title;
        this.type = type;
        this.university = university;
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
