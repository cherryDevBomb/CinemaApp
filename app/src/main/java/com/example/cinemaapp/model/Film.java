package com.example.cinemaapp.model;

import java.io.Serializable;

public class Film implements Serializable {

    private String title;
    private String genre;
    private String description;
    private double rating;
    private String imagePath;

    public Film(String title, String genre, String description, double rating, String imagePath) {
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.rating = rating;
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
