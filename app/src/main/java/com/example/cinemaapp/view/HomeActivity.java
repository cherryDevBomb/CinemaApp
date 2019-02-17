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
import com.example.cinemaapp.presenter.Repository;

import java.util.Arrays;
import java.util.List;

import android.widget.Button;

public class HomeActivity extends Fragment {

    private Button button;
    private Repository repository = new Repository();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home_activity, container, false);


                                ///try to set desired height
        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {

            @Override
            public boolean onPreDraw() {
                WindowManager windowmanager = (WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE);
                DisplayMetrics dimension = new DisplayMetrics();
                windowmanager.getDefaultDisplay().getMetrics(dimension);
                final int screenHeight = dimension.heightPixels;

                view.getViewTreeObserver().removeOnPreDrawListener(this);

                int maxHeight = view.getHeight();
                int h = ((BottomNavigationView)getActivity().findViewById(R.id.navigation)).getLayoutParams().height;

                //BottomNavigationView bottomBar = .findViewById(R.id.navigation);
                //int bottomBarHeight = getActivity().findViewById(R.id.navigation);

                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = maxHeight - h;
                view.setLayoutParams(layoutParams);

                return true;
            }
        });



        //Add recyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(true);

        FilmAdapter adapter = new FilmAdapter(this);
        recyclerView.setAdapter(adapter);

        adapter.setFilmlist(repository.getHardcodedList());

        //Add spinner
        Spinner spinner = (Spinner) view.findViewById(R.id.filter_spinner);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.genres_array, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

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
}
