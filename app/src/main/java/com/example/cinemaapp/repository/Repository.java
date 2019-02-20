package com.example.cinemaapp.repository;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.cinemaapp.R;
import com.example.cinemaapp.model.Film;
import com.example.cinemaapp.model.Reservation;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Repository {
    static List<Film> filmList = new ArrayList<>();
    static List<Reservation> reservationList = new ArrayList<>();
    public static List<Film> favoriteList = new ArrayList<>();
    private static HashMap<String, HashMap<Time, List<Boolean>>> program;
    static List<Boolean> cinemaPlaces;

    public static List<Film> getHardcodedList() {
        List<Film> filmList = Arrays.asList(
                new Film("Despicable Me", "Animation", "When a criminal mastermind uses a trio of orphan girls as pawns for a grand scheme, he finds their love is profoundly changing him for the better. ", 7.7, R.drawable.despicable),
                new Film("La La Land", "Drama", "While navigating their careers in Los Angeles, a pianist and an actress fall in love while attempting to reconcile their aspirations for the future. ", 8.0, R.drawable.lalaland),
                new Film("The Martian", "Adventure", "An astronaut becomes stranded on Mars after his team assume him dead, and must rely on his ingenuity to find a way to signal to Earth that he is alive. ", 8.0, R.drawable.martian),
                new Film("The Great Gatsby", "Drama", "A writer and wall street trader, Nick, finds himself drawn to the past and lifestyle of his millionaire neighbor, Jay Gatsby. ", 7.3, R.drawable.gatsby),
                new Film("Ted", "Comedy", "John Bennett, a man whose childhood wish of bringing his teddy bear to life came true, now must decide between keeping the relationship with the bear or his girlfriend, Lori. ", 6.9, R.drawable.ted),
                new Film("Ralph Breaks The Internet", "Animation", "Six years after the events of \"Wreck-It Ralph,\" Ralph and Vanellope, now friends, discover a wi-fi router in their arcade, leading them into a new adventure. ", 7.2, R.drawable.ralph),
                new Film("Happy Death Day 2U", "Horror", "Tree Gelbman discovers that dying over and over was surprisingly easier than the dangers that lie ahead. ", 6.8, R.drawable.happy),
                new Film("Lego Movie 2", "Animation", "It's been five years since everything was awesome and the citizens are facing a huge new threat: Lego Duplo invaders from outer space, wrecking everything faster than they can rebuild. ", 7.2, R.drawable.lego),
                new Film("Papillon", "Drama", "Wrongfully convicted for murder, Henri Charriere forms an unlikely relationship with fellow inmate and quirky convicted counterfeiter Louis Dega, in an attempt to escape from the notorious penal colony on Devil's Island. ", 7.1, R.drawable.papillon),
                new Film("Alita", "Adventure", "A deactivated female cyborg is revived, but cannot remember anything of her past life and goes on a quest to find out who she is. ", 7.6, R.drawable.alita),
                new Film("Girl", "Drama", "Lara is a 15-year-old girl, born in the body of a boy, who dreams of becoming a ballerina. ", 7.2, R.drawable.girl),
                new Film("A Star Is Born", "Romance", "A musician helps a young singer find fame, even as age and alcoholism send his own career into a downward spiral. ", 7.9, R.drawable.star),
                new Film("Glass", "Adventure", "Security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. ", 7.0, R.drawable.glass),
                new Film("Bumblebee", "Adventure", "On the run in the year of 1987, Bumblebee finds refuge in a junkyard in a small Californian beach town. Charlie, on the cusp of turning 18 and trying to find her place in the world, discovers Bumblebee, battle-scarred and broken. ", 7.1, R.drawable.bumblebee),
                new Film("Bohemian Rapsody", "Drama", "The story of the legendary rock band Queen and lead singer Freddie Mercury, leading up to their famous performance at Live Aid (1985). ", 8.2, R.drawable.bohemian),
                new Film("Aquaman", "Adventure", "Arthur Curry, the human-born heir to the underwater kingdom of Atlantis, goes on a quest to prevent a war between the worlds of ocean and land. ", 7.4, R.drawable.aquaman),
                new Film("Untamed Romania", "Documentary", "This film is a unique production that reveals the beauty of Romania as it is, raw, magical but fragile at the same time. In the heart of Europe, there is a fabulous wildlife, rich in biodiversity, home to numerous wild animals. The endless mountain peaks and river streams, ancient forests, all provide home to various creatures. ", 8.9, R.drawable.romania),
                new Film("Mary Queen Of Scots", "Drama", "Mary Stuart's attempt to overthrow her cousin Elizabeth I, Queen of England, finds her condemned to years of imprisonment before facing execution. ", 6.4, R.drawable.queen)
        );
        return filmList;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void addReservation(Reservation reservation) {

        if (!searchReservation(reservation)) {

            reservationList.add(reservation);
            markReservedPlaces(reservation.getPlaces());
        }
    }

    private static void markReservedPlaces(List<Integer> places) {
        for (int i : places) {
            cinemaPlaces.set(i, false);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private static boolean searchReservation(Reservation reservation) {

        for (Reservation r : reservationList) {

            if (r.equals(reservation))
                return true;
        }
        return false;
    }

    public static void addToFavorites(Film film) {

        if (!searchInFavorites(film)) {

            favoriteList.add(film);
        }

    }

    public static boolean searchInFavorites(Film film) {

        for (Film f : favoriteList) {

            if (f.equals(film))
                return true;
        }
        return false;
    }

    public static void deleteFromFavorites(Film film) {

        for (Film f : favoriteList) {

            if (f.equals(film)) {
                favoriteList.remove(f);
                return;
            }
        }
    }


//    public static HashMap<String, HashMap<Time, List<Boolean>>> getHardcodedProgram() {
//        if (program == null) {
//            program = new HashMap<>();
//            List<Film> films = getHardcodedList();
//            List<List<Time>> posibilities = Arrays.asList(
//                    Arrays.asList(new Time(10, 0, 0), new Time(13, 30, 0), new Time(17, 0, 0)),
//                    Arrays.asList(new Time(11, 0, 0), new Time(14, 30, 0), new Time(18, 0, 0)),
//                    Arrays.asList(new Time(12, 30, 0), new Time(16, 0, 0), new Time(19, 30, 0)),
//                    Arrays.asList(new Time(12, 0, 0), new Time(17, 0, 0)),
//                    Arrays.asList(new Time(10, 0, 0), new Time(13, 30, 0), new Time(17, 0, 0)),
//                    Arrays.asList(new Time(15, 0, 0), new Time(20, 0, 0))
//            );
//            Random r = new Random();
//            for (Film f : films) {
//                List<Time> thisFilmTimes = posibilities.get(r.nextInt(5));
//                HashMap<Time, List<Boolean>> thisFilmProgram = new HashMap<>();
//                for (Time t : thisFilmTimes) {
//                    thisFilmProgram.put(t, Arrays.asList(true, true, true, true, true, true, true, true, true));
//                }
//                program.put(f.getTitle(), thisFilmProgram);
//            }
//        }
//        return program;
//    }

    public static HashMap<String, HashMap<Time, List<Boolean>>> getHardcodedProgram() {
        if (program == null) {
            program = new HashMap<>();
            List<Film> films = getHardcodedList();
            List<List<Time>> posibilities = Arrays.asList(
                    Arrays.asList(new Time(12, 0, 0), new Time(17, 0, 0), new Time(20, 30, 0)),
                    Arrays.asList(new Time(10, 0, 0), new Time(13, 30, 0), new Time(17, 0, 0)),
                    Arrays.asList(new Time(11, 0, 0), new Time(14, 30, 0), new Time(18, 0, 0)),
                    Arrays.asList(new Time(12, 30, 0), new Time(16, 0, 0), new Time(19, 30, 0)),
                    Arrays.asList(new Time(10, 0, 0), new Time(13, 30, 0), new Time(17, 0, 0))

            );
            int i = 0;
            for (Film f : films) {
                List<Time> thisFilmTimes = posibilities.get(i % 5);
                i++;
                HashMap<Time, List<Boolean>> thisFilmProgram = new HashMap<>();
                for (Time t : thisFilmTimes) {
                    thisFilmProgram.put(t, Arrays.asList(true, true, true, true, true, true, true, true, true));
                }
                program.put(f.getTitle(), thisFilmProgram);
            }
        }
        return program;
    }

    /**
     * Get cinemaPlaces for selected film at selected startTime
     * @param filmTitle
     * @param time
     * @return
     */
    public static List<Boolean> getCinemaPlaces(String filmTitle, String time) {
        //transform time from string to Time obj
        Time timeObj = null;
        DateFormat formatter = new SimpleDateFormat("kk:mm");
        try {
            timeObj = new Time(formatter.parse(time).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

        cinemaPlaces = getHardcodedProgram().get(filmTitle).get(timeObj);
        return cinemaPlaces;
    }

    public static List<Reservation> getReservationList() {
        return reservationList;
    }
}

