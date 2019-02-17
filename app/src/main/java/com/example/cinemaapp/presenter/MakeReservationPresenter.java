package com.example.cinemaapp.presenter;


import com.example.cinemaapp.model.Film;

import java.util.Arrays;
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
        this.listPlaces = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
    }

    public List<Integer> getListPlaces() {
        return listPlaces;
    }

    public void updatePoster() {
        //view.setPoster(film.getImagePath());
    }

    public void updateTitle() {
        view.setMovieTitle(film.getTitle());
    }

    public void updateStartTime() {
        view.setStartTime(time);
    }

    public interface MainView {
        void setPoster(int moviePosterID);
        void setMovieTitle(String title);
        void setStartTime(String time);
    }
}
