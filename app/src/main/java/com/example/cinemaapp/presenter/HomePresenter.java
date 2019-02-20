package com.example.cinemaapp.presenter;

import com.example.cinemaapp.model.Film;
import com.example.cinemaapp.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class HomePresenter {

    List<Film> filmList = Repository.getHardcodedList();


    public List<Film> filterByGenre(String genre){
        if (genre.equals("All"))
            return filmList;

        List<Film> filteredFilmList = new ArrayList<>();

        for (Film f : filmList){

            if(f.getGenre().equals(genre)){

                filteredFilmList.add(f);
            }
        }

        return filteredFilmList;
    }
 }
