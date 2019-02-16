package com.example.cinemaapp;

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


                                ///try to set desired height
//        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//
//            @Override
//            public boolean onPreDraw() {
//                WindowManager windowmanager = (WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE);
//                DisplayMetrics dimension = new DisplayMetrics();
//                windowmanager.getDefaultDisplay().getMetrics(dimension);
//                final int screenHeight = dimension.heightPixels;
//
//                view.getViewTreeObserver().removeOnPreDrawListener(this);
//                int maxHeight = view.getHeight();
//                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
//                layoutParams.height = maxHeight;
//                view.setLayoutParams(layoutParams);
//
//                return true;
//            }
//        });



        //Add recyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
                //this.getContext instead of this
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(true);

        FilmAdapter adapter = new FilmAdapter();
        recyclerView.setAdapter(adapter);

        adapter.setFilmlist(getHardcodedList());

        //Add spinner
        Spinner spinner = (Spinner) view.findViewById(R.id.filter_spinner);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.genres_array, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        // button example to open MakeReservationActivity
        button = view.findViewById(R.id.openPage);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPage();
            }
        });

        return view;

    }

//    private void setHomeActivityHeight() {
//
//        FrameLayout containerLayout = getActivity().findViewById(R.id.fragment_container);
//        ViewGroup.LayoutParams containerParams = containerLayout.getLayoutParams();
//        ConstraintLayout homeLayout = containerLayout.findViewById(R.id.homepage);
//        ViewGroup.LayoutParams homeParams = homeLayout.getLayoutParams();
//        homeParams.height = containerParams.height;
//        homeLayout.setLayoutParams(homeParams);
//    }

    private List<Film> getHardcodedList() {
        List<Film> filmList = Arrays.asList(new Film("The martian", "Adventure", "An astronaut becomes stranded on Mars after his team assume him dead, and must rely on his ingenuity to find a way to signal to Earth that he is alive. ", 8.0, "martian"),
                                            new Film("The martian", "Adventure", "An astronaut becomes stranded on Mars after his team assume him dead, and must rely on his ingenuity to find a way to signal to Earth that he is alive. ", 8.0, "martian"),
                                            new Film("The martian", "Adventure", "An astronaut becomes stranded on Mars after his team assume him dead, and must rely on his ingenuity to find a way to signal to Earth that he is alive. ", 8.0, "martian"),
                                            new Film("The martian", "Adventure", "An astronaut becomes stranded on Mars after his team assume him dead, and must rely on his ingenuity to find a way to signal to Earth that he is alive. ", 8.0, "martian"),
                                            new Film("The martian", "Adventure", "An astronaut becomes stranded on Mars after his team assume him dead, and must rely on his ingenuity to find a way to signal to Earth that he is alive. ", 8.0, "martian"),
                                            new Film("The martian", "Adventure", "An astronaut becomes stranded on Mars after his team assume him dead, and must rely on his ingenuity to find a way to signal to Earth that he is alive. ", 8.0, "martian"),
                                            new Film("The martian", "Adventure", "An astronaut becomes stranded on Mars after his team assume him dead, and must rely on his ingenuity to find a way to signal to Earth that he is alive. ", 8.0, "martian"),
                                            new Film("The martian", "Adventure", "An astronaut becomes stranded on Mars after his team assume him dead, and must rely on his ingenuity to find a way to signal to Earth that he is alive. ", 8.0, "martian")

        );
        return filmList;
    }



    private void openPage() {
        Intent intent = new Intent(getActivity(), MakeReservationActivity.class);
        startActivity(intent);
    }
}
