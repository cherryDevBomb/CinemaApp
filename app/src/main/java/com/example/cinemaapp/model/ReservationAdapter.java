package com.example.cinemaapp.model;

import android.app.Dialog;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cinemaapp.R;
import com.example.cinemaapp.repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ReservationHolder> {

    private List<Reservation> reservationList = new ArrayList<>();
    private Fragment context;
    private Dialog popup;

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

        Reservation reservationObject;

        TextView textTitle;
        TextView textStartingTime;
        TextView reservedSeats;
        ImageView qrCode;

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        ReservationHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.film_title_text);
            textStartingTime = itemView.findViewById(R.id.starting_time_text);
            reservedSeats = itemView.findViewById(R.id.reserved_seats);
            qrCode = itemView.findViewById(R.id.qr_code);

            popup = new Dialog(Objects.requireNonNull(context.getActivity()));

            Button popupButton = itemView.findViewById(R.id.detail_button);
            popupButton.setOnClickListener(new View.OnClickListener(){

                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View v) {

                    //sendInformation(reservationObject);
                    openPopup();
                    reservedSeats.setText(reservationObject.getPlaces().toString());
                    qrCode.setImageBitmap(reservationObject.getCodeQR());
                }
            });
        }
    }

//    private void sendInformation(Reservation currentReservation){
//
//        Intent intent = new Intent(context.getActivity(), ReservationHolder.class);
//        intent.putExtra("currentReservation", currentReservation);
//        context.startActivity(intent);
//    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void openPopup(){

        popup.setContentView(R.layout.popup_activity);
        popup.show();

        ImageView close = popup.findViewById(R.id.close_popup);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popup.dismiss();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
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

        //check if reservationList is empty and then show corresponding layout
        if (Repository.getReservationList().isEmpty()) {
            final TextView header = context.getView().findViewById(R.id.no_reservations_message_header);
            header.setVisibility(View.VISIBLE);
            final TextView body = context.getView().findViewById(R.id.no_reservations_message_body);
            body.setVisibility(View.VISIBLE);
        }
    }


}
