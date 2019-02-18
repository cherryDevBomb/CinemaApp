package com.example.cinemaapp.model;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cinemaapp.R;

import java.util.ArrayList;
import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ReservationHolder> {

    private List<Reservation> reservationList = new ArrayList<>();
    private Fragment context;

    public ReservationAdapter(Fragment context) {

        this.context = context;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public Fragment getContext() {
        return context;
    }

    class ReservationHolder extends RecyclerView.ViewHolder {

        TextView textTitle;
        TextView textStartingTime;

        ReservationHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.film_title_text);
            textStartingTime = itemView.findViewById(R.id.starting_time_text);
        }
    }

    @NonNull
    @Override
    public ReservationAdapter.ReservationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //create new View
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reservation_card, parent, false);

        return new ReservationHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationAdapter.ReservationHolder reservationHolder, int position) {

        Reservation reservation = reservationList.get(position);

        reservationHolder.textTitle.setText(reservation.getFilm().getTitle());
        reservationHolder.textStartingTime.setText(reservation.getStartTime().toString());
    }

    @Override
    public int getItemCount() {
        return reservationList.size();
    }

    public void removeReservation(int position) {

        reservationList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, reservationList.size());
    }
}
