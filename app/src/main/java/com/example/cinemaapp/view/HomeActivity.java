package com.example.cinemaapp.view;

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
import com.example.cinemaapp.model.FilmAdapter;
import com.example.cinemaapp.repository.Repository;

import android.widget.Button;

public class HomeActivity extends Fragment {

    private Button button;
    //private Repository repository = new Repository();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home_activity, container, false);

        //Add recyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(true);

        FilmAdapter adapter = new FilmAdapter(this);
        recyclerView.setAdapter(adapter);

        adapter.setFilmlist(Repository.getHardcodedList());

        //Add spinner
        Spinner spinner = (Spinner) view.findViewById(R.id.filter_spinner);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.genres_array, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        return view;

    }
}
