package com.example.cinemaapp.view;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cinemaapp.R;
import com.example.cinemaapp.model.FilmAdapter;
import com.example.cinemaapp.repository.Repository;


public class FavoritesActivity extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        final Fragment pointerSaver = this;

        //Add recyclerView
        final RecyclerView recyclerView = view.findViewById(R.id.recycler_view_favorites);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(true);

        final FilmAdapter adapter = new FilmAdapter(this);
        recyclerView.setAdapter(adapter);
        adapter.setFilmlist(Repository.favoriteList);

        Repository.setFavoriteListChangedListener(new Repository.FavoriteListChangedListener() {
            @Override
            public void onFavoriteListChanged() {
                final FilmAdapter adapter = new FilmAdapter(pointerSaver);
                recyclerView.setAdapter(adapter);
                adapter.setFilmlist(Repository.favoriteList);
            }
        });

        return view;
    }
}
