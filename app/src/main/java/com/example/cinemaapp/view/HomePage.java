package com.example.cinemaapp.view;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.cinemaapp.R;
import com.example.cinemaapp.model.Film;
import com.example.cinemaapp.model.FilmAdapter;

import java.util.Arrays;
import java.util.List;

import android.widget.Button;

public class HomePage extends Fragment {

    private Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);

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

    private List<Film> getHardcodedList() {
        List<Film> filmList = Arrays.asList(new Film("The martian", "Adventure"),
                                            new Film("The martian", "Adventure"),
                                            new Film("The martian", "Adventure"),
                                            new Film("The martian", "Adventure"),
                                            new Film("The martian", "Adventure"),
                                            new Film("The martian", "Adventure"),
                                            new Film("The martian", "Adventure"),
                                            new Film("The martian", "Adventure"),
                                            new Film("The martian", "Adventure"),
                                            new Film("The martian", "Adventure"),
                                            new Film("The martian", "Adventure"),
                                            new Film("The martian", "Adventure"),
                                            new Film("The martian", "Adventure")
        );
        return filmList;
    }

    private void openPage() {
        Intent intent = new Intent(getActivity(), MakeReservationActivity.class);
        startActivity(intent);
    }
}
