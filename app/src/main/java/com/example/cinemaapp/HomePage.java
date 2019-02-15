package com.example.cinemaapp;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomePage extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.activity_home_page, container, false);
        return rootView;
        //setContentView(R.layout.activity_home_page);
    }
}
