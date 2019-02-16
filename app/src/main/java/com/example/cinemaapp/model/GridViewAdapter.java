package com.example.cinemaapp.model;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import java.util.List;

public class GridViewAdapter extends BaseAdapter {
    List<Integer> listOfPlaces;
    Context mContext;

    public GridViewAdapter(List<Integer> listOfPlaces, Context mContext) {
        this.listOfPlaces = listOfPlaces;
        this.mContext = mContext;
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
            button.setLayoutParams(new GridView.LayoutParams(85, 85));
            button.setPadding(8,8,8,8);
            button.setText(Integer.toString(listOfPlaces.get(position)));
            button.setBackgroundColor(Color.DKGRAY);
            button.setTextColor(Color.BLACK);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("yay");
                }
            });
        } else {
          button = (Button)convertView;
        }

        return button;
    }
}
