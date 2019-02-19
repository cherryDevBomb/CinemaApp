package com.example.cinemaapp.view;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cinemaapp.R;
import com.example.cinemaapp.model.Reservation;
import com.example.cinemaapp.model.ReservationAdapter;
import com.example.cinemaapp.presenter.SwipeToDelete;
import com.example.cinemaapp.repository.Repository;

import java.io.Serializable;
import java.util.List;

public class ReservationPage extends Fragment implements Serializable {



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
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

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDelete(rAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);

        if (Repository.getReservationList().isEmpty()) {
            final TextView header = view.findViewById(R.id.no_reservations_message_header);
            header.setVisibility(View.VISIBLE);
            final TextView body = view.findViewById(R.id.no_reservations_message_body);
            body.setVisibility(View.VISIBLE);
        }

        return view;
    }

    private List<Reservation> testListReservation() {

        return Repository.getReservationList();
    }

}


