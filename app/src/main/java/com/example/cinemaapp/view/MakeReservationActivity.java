package com.example.cinemaapp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.example.cinemaapp.R;
import com.example.cinemaapp.model.Film;
import com.example.cinemaapp.model.GridViewAdapter;
import com.example.cinemaapp.presenter.MakeReservationPresenter;

public class MakeReservationActivity extends AppCompatActivity implements MakeReservationPresenter.MainView {
    private MakeReservationPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_reservation);

        presenter = new MakeReservationPresenter(this);
        GridView gridView = (GridView)findViewById(R.id.gridPlaces);
        GridViewAdapter adapter = new GridViewAdapter(presenter.getListPlaces(), this);
        gridView.setAdapter(adapter);

        /////////////////////////////////////////////////////////
        //retrieve values from Intent
//        Intent creatorIntent = getIntent();
//        Film filmObject = (Film)creatorIntent.getSerializableExtra("film");
//        String time = creatorIntent.getStringExtra("time");
        /////////////////////////////////////////////////////////

        getSupportActionBar().setTitle("Reserve");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void showPlaces(int[][] places) {

    }

    @Override
    public void setPoster(String moviePoster) {

    }

    @Override
    public void setMovieTitle(String title) {

    }

    @Override
    public void setStartTime(String time) {

    }
}
