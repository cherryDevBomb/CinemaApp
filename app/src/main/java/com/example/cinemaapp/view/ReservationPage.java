package com.example.cinemaapp.view;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cinemaapp.R;
import com.example.cinemaapp.model.Film;
import com.example.cinemaapp.model.Reservation;
import com.example.cinemaapp.model.ReservationAdapter;
import com.example.cinemaapp.presenter.SwipeController;

import java.sql.Time;
import java.util.Arrays;
import java.util.List;

public class ReservationPage extends Fragment {

    SwipeController swipeController = new SwipeController();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_reservation_page, container, false);

        //adding recycler view to fragment
        RecyclerView recyclerView = view.findViewById(R.id.reservation_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(true);

        ReservationAdapter rAdapter = new ReservationAdapter(this);
        rAdapter.setReservationList(testListReservation());

        recyclerView.setAdapter(rAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeController);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        return view;
    }

    private List<Reservation> testListReservation() {


        Film film = new Film("The Martian", "Adventure", "An astronaut becomes stranded on Mars after his team assume him dead, and must rely on his ingenuity to find a way to signal to Earth that he is alive. ", 8.0, "martian");
        Time time = new Time(12, 0, 0);
        List<Reservation> reservationList = Arrays.asList(new Reservation(film, time, "qrCode"), new Reservation(film, time, "qrCode"));

        return reservationList;
    }
}


