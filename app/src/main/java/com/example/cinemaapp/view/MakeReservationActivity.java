package com.example.cinemaapp.view;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.example.cinemaapp.R;
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
        final GridViewAdapter adapter = new GridViewAdapter(presenter.getListPlaces(), this);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int selectedIndex = adapter.getSelectedPositions().indexOf(position);
                if (selectedIndex > -1) {
                    adapter.getSelectedPositions().remove(selectedIndex);
                    ((Button)view).setBackgroundColor(Color.LTGRAY);
                } else {
                    adapter.getSelectedPositions().add(position);
                    ((Button)view).setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.titleColor));
                }
            }
        });

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
