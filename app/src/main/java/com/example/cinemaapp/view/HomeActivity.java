package com.example.cinemaapp.view;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.example.cinemaapp.R;
import com.example.cinemaapp.model.Film;
import com.example.cinemaapp.model.FilmAdapter;

import java.util.Arrays;
import java.util.List;

import android.widget.Button;

public class HomeActivity extends Fragment {

    private Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home_activity, container, false);

        //Add recyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(true);

        FilmAdapter adapter = new FilmAdapter(this);
        recyclerView.setAdapter(adapter);

        adapter.setFilmlist(getHardcodedList());

        //Add spinner
        Spinner spinner = (Spinner) view.findViewById(R.id.filter_spinner);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.genres_array, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        return view;

    }

    private List<Film> getHardcodedList() {
        List<Film> filmList = Arrays.asList(new Film("The martian", "Adventure", "An astronaut becomes stranded on Mars after his team assume him dead, and must rely on his ingenuity to find a way to signal to Earth that he is alive. ", 8.0, R.drawable.martian),
                                            new Film("The martian", "Adventure", "An astronaut becomes stranded on Mars after his team assume him dead, and must rely on his ingenuity to find a way to signal to Earth that he is alive. ", 8.0, R.drawable.martian),
                                            new Film("The martian", "Adventure", "An astronaut becomes stranded on Mars after his team assume him dead, and must rely on his ingenuity to find a way to signal to Earth that he is alive. ", 8.0, R.drawable.martian),
                                            new Film("The martian", "Adventure", "An astronaut becomes stranded on Mars after his team assume him dead, and must rely on his ingenuity to find a way to signal to Earth that he is alive. ", 8.0, R.drawable.martian),
                                            new Film("The martian", "Adventure", "An astronaut becomes stranded on Mars after his team assume him dead, and must rely on his ingenuity to find a way to signal to Earth that he is alive. ", 8.0, R.drawable.martian),
                                            new Film("The martian", "Adventure", "An astronaut becomes stranded on Mars after his team assume him dead, and must rely on his ingenuity to find a way to signal to Earth that he is alive. ", 8.0, R.drawable.martian)

        );
        return filmList;
    }




}
