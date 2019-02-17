package com.example.cinemaapp.presenter;


import com.example.cinemaapp.model.Film;
import com.example.cinemaapp.model.Reservation;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MakeReservationPresenter {
    private MainView view;
    private String time;
    private List<Integer> listPlaces;
    private Film film;

    public MakeReservationPresenter(MainView view, Film film, String time) {
        this.view = view;
        this.film = film;
        this.time = time;
    }

    public List<Integer> getListPlaces() {
        return listPlaces;
    }

    public void updatePoster() {
        view.setPoster(film.getImagePath());
    }

    public void updateTitle() {
        view.setMovieTitle(film.getTitle());
    }

    public void updateStartTime() {
        view.setStartTime(time);
    }

    public Reservation createReservation() {
        //create object time from string
        Time timeObj = null;
        DateFormat formatter= new SimpleDateFormat("hh:mm");
        try {
             timeObj = new Time(formatter.parse(time).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Create reservation
        Reservation reservation = new Reservation(film, timeObj, "qr");
        return reservation;
    }

    public interface MainView {
        void setPoster(int moviePosterID);
        void setMovieTitle(String title);
        void setStartTime(String time);
        void saveReservation();
    }
}
