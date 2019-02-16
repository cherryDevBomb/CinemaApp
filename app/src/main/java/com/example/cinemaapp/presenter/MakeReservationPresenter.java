package com.example.cinemaapp.presenter;


public class MakeReservationPresenter {
    private MainView view;

    public MakeReservationPresenter(MainView view) {
        this.view = view;
    }

    public interface MainView {
        void showPlaces(int[][] places);
        void setPoster(String moviePoster);
        void setMovieTitle(String title);
        void setStartTime(String time);
    }
}
