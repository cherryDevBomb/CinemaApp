package com.example.cinemaapp.model;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.example.cinemaapp.R;
import com.example.cinemaapp.presenter.MakeReservationPresenter;
import com.example.cinemaapp.repository.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GridViewAdapter extends BaseAdapter {
    private List<Integer> listOfPlaces;
    private List<Integer> selectedPositions;
    private Context mContext;
    private MakeReservationPresenter presenter;

    public GridViewAdapter(Context mContext, MakeReservationPresenter presenter) {
        this.listOfPlaces = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        this.mContext = mContext;
        this.selectedPositions = new ArrayList<>();
        this.presenter = presenter;
    }

    @Override
    public int getCount() {
        return listOfPlaces.size();
    }

    @Override
    public Object getItem(int position) {
        return listOfPlaces.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button button;
        if (convertView == null) {
            button = new Button(mContext);
            button.setLayoutParams(new GridView.LayoutParams(100, 100));
            button.setPadding(8,8,8,8);
            button.setText(Integer.toString(listOfPlaces.get(position)));
            if (!Repository.getCinemaPlaces().get(position)) {
                button.setBackgroundColor(Color.LTGRAY);
                button.setEnabled(false);
            } else if (Repository.getCinemaPlaces().get(position)){
                button.setBackgroundColor(Color.rgb(53, 172, 72));
            }
            button.setTextColor(Color.BLACK);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = Integer.parseInt(((Button) view).getText().toString());

                    //allow multiple select
                    int selectedIndex = selectedPositions.indexOf(pos);
                    if (Repository.getCinemaPlaces().get(pos) && selectedIndex > -1) {
                        selectedPositions.remove(selectedIndex);
                        presenter.setListPlaces(selectedPositions);
                        ((Button) view).setBackgroundColor(Color.rgb(53, 172, 72));
                    } else if (Repository.getCinemaPlaces().get(pos) && selectedIndex == -1){
                        selectedPositions.add(pos);
                        presenter.setListPlaces(selectedPositions);
                        ((Button) view).setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.titleColor));
                    }
                }
            });
        } else {
          button = (Button)convertView;
        }

        return button;
    }
}
