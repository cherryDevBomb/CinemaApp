package com.example.cinemaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MakeReservationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_reservation);

        getSupportActionBar().setTitle("Reserve");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
