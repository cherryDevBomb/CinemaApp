package com.example.cinemaapp.model;

import java.io.Serializable;
import java.sql.Time;

public class Reservation implements Serializable {

    private Film film;
    private Time startTime;
    private String codeQR;

    public Reservation(){}

    public Reservation(Film film, Time startTime, String codeQR) {
        this.film = film;
        this.startTime = startTime;
        this.codeQR = codeQR;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public String getCodeQR() {
        return codeQR;
    }

    public void setCodeQR(String codeQR) {
        this.codeQR = codeQR;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "film=" + film +
                ", startTime=" + startTime +
                ", codeQR='" + codeQR + '\'' +
                '}';
    }
}
