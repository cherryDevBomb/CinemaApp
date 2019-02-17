package com.example.cinemaapp.presenter;

import com.example.cinemaapp.model.Film;
import com.example.cinemaapp.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class Presenter {

    private Repository repository = new Repository();

    List<Film> filmList = repository.getHardcodedList();


    public List<Film> filterByGenre(String genre){

        List<Film> filteredFilmList = new ArrayList<>();

        for (Film f : filmList){

            if(f.getGenre().equals(genre)){

                filteredFilmList.add(f);
            }
        }

        return filteredFilmList;
    }
 }
