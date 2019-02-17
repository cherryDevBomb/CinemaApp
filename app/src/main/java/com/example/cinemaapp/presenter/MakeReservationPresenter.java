package com.example.cinemaapp.presenter;


import com.example.cinemaapp.model.GridViewAdapter;

import java.util.Arrays;
import java.util.List;

public class MakeReservationPresenter {
    private MainView view;
    private List<Integer> listPlaces;

    public MakeReservationPresenter(MainView view) {
        this.view = view;
        this.listPlaces = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
    }

    public List<Integer> getListPlaces() {
        return listPlaces;
    }

    public interface MainView {
        void showPlaces(int[][] places);
        void setPoster(String moviePoster);
        void setMovieTitle(String title);
        void setStartTime(String time);
    }
}
