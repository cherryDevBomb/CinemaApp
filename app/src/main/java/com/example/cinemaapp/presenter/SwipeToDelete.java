package com.example.cinemaapp.presenter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.example.cinemaapp.R;
import com.example.cinemaapp.model.Film;
import com.example.cinemaapp.model.Reservation;
import com.example.cinemaapp.model.ReservationAdapter;
import com.example.cinemaapp.repository.Repository;

import java.sql.Time;
import java.util.Arrays;
import java.util.List;

public class SwipeToDelete extends ItemTouchHelper.SimpleCallback {

    private ReservationAdapter rAdapter;
    private final ColorDrawable background;

    Drawable icon;

    public SwipeToDelete(ReservationAdapter adapter){

        super(0, ItemTouchHelper.LEFT);

        this.rAdapter = adapter;
        rAdapter.setReservationList(testListReservation());

        //icon = ContextCompat.getDrawable(rAdapter.getContext(), R.drawable.ic_delete_black_24dp);

        background = new ColorDrawable(Color.RED);
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0, ItemTouchHelper.LEFT);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        int position = viewHolder.getAdapterPosition();
        rAdapter.removeReservation(position);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive){

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        View itemView = viewHolder.itemView;

        //push background behind the edge of the parent
        int backgroundCornerOffset = 20;

        if (dX < 0){

            background.setBounds(itemView.getRight() + ((int) dX) - backgroundCornerOffset,
                    itemView.getTop(), itemView.getRight(), itemView.getBottom());
        }
        else {

            background.setBounds(0,0,0,0);
        }

        background.draw(c);
    }


    private List<Reservation> testListReservation() {


//        Film film = new Film("The Martian", "Adventure", "An astronaut becomes stranded on Mars after his team assume him dead, and must rely on his ingenuity to find a way to signal to Earth that he is alive. ", 8.0, R.drawable.martian);
//        Time time = new Time(12, 0, 0);
//        List<Reservation> reservationList = Arrays.asList(new Reservation(film, time, null,  "qrCode"), new Reservation(film, time, null, "qrCode"));
//
//        return  reservationList;
        return Repository.getReservationList();
    }

}
