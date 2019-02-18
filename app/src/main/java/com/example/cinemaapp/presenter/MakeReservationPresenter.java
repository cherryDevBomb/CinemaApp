package com.example.cinemaapp.presenter;


import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.cinemaapp.model.Film;
import com.example.cinemaapp.model.Reservation;
import com.example.cinemaapp.repository.Repository;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class MakeReservationPresenter {
    private MainView view;
    private String time;
    private Film film;
    private List<Integer> listPlaces;

    public MakeReservationPresenter(MainView view, Film film, String time) {
        this.view = view;
        this.film = film;
        this.time = time;
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

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void createReservation() {
        //create object time from string
        Time timeObj = null;
        DateFormat formatter= new SimpleDateFormat("hh:mm");
        try {
             timeObj = new Time(formatter.parse(time).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Create reservation & save to Repository
        Reservation reservation = new Reservation(film, timeObj, listPlaces, "qr");
        Repository.addReservation(reservation);
    }

    public List<Integer> getListPlaces() {
        return listPlaces;
    }

    public void setListPlaces(List<Integer> listPlaces) {
        this.listPlaces = listPlaces;
    }

    public interface MainView {
        void setPoster(int moviePosterID);
        void setMovieTitle(String title);
        void setStartTime(String time);
        void saveReservation();
    }
}
