package com.example.cinemaapp.view;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cinemaapp.R;
import com.example.cinemaapp.controls.SwipeButton;
import com.example.cinemaapp.model.Film;
import com.example.cinemaapp.model.GridViewAdapter;
import com.example.cinemaapp.presenter.MakeReservationPresenter;

public class MakeReservationActivity extends AppCompatActivity implements MakeReservationPresenter.MainView {
    private MakeReservationPresenter presenter;

    private SwipeButton swipeButton;
    private SwipeButton.OnSwipeButtonExpandedListener swipeButtonExpandedListener = new SwipeButton.OnSwipeButtonExpandedListener() {
        @Override
        public void onSwipeButtonExpanded(View v) {
            saveReservation();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_reservation);

        getSupportActionBar().setTitle("Reserve");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //retrieve values from Intent
        Intent creatorIntent = getIntent();
        Film film = (Film)creatorIntent.getSerializableExtra("film");
        String time = creatorIntent.getStringExtra("time");

        //create the presenter and update the with the new film information on View
        presenter = new MakeReservationPresenter(this, film, time);
        presenter.updatePoster();
        presenter.updateTitle();
        presenter.updateStartTime();

        GridView gridView = (GridView)findViewById(R.id.gridPlaces);
        final GridViewAdapter adapter = new GridViewAdapter(this);
        gridView.setAdapter(adapter);

        //Attach swipe listener to SwipeButton
        swipeButton = findViewById(R.id.swipe_btn);
        swipeButton.setSwipeListener(swipeButtonExpandedListener);
    }


    @Override
    public void setPoster(int moviePosterID) {
        ImageView posterImageView = (ImageView)findViewById(R.id.moviePosterImageView);
        posterImageView.setImageResource(moviePosterID);
    }

    @Override
    public void setMovieTitle(String title) {
        TextView titleTextView = (TextView)findViewById(R.id.movieTitleTextView);
        titleTextView.setText(title);
    }

    @Override
    public void setStartTime(String time) {
        TextView timeTextView = (TextView)findViewById(R.id.movieHourTextView);
        timeTextView.setText(time);
    }

    @Override
    public void saveReservation() {
        presenter.createReservation();

        //return to Parent
        Intent parentIntent = NavUtils.getParentActivityIntent(this);
        startActivity(parentIntent);
    }
}
