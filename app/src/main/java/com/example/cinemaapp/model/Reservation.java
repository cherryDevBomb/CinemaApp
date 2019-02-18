package com.example.cinemaapp.model;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.Serializable;
import java.sql.Time;
import java.util.List;
import java.util.Objects;

public class Reservation implements Serializable {
    private Film film;
    private Time startTime;
    private List<Integer> places;
    private String codeQR;

    public Reservation(){}

    public Reservation(Film film, Time startTime, List<Integer> places, String codeQR) {
        this.film = film;
        this.startTime = startTime;
        this.places = places;
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

    public List<Integer> getPlaces() { return places; }

    @Override
    public String toString() {
        return "Reservation{" +
                "film=" + film +
                ", startTime=" + startTime +
                ", codeQR='" + codeQR + '\'' +
                '}';
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(film, that.film) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(places, that.places);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(film, startTime, places);
    }
}
