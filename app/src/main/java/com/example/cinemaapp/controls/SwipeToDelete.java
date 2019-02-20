package com.example.cinemaapp.controls;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.example.cinemaapp.R;
import com.example.cinemaapp.model.Reservation;
import com.example.cinemaapp.adapters.ReservationAdapter;
import com.example.cinemaapp.repository.Repository;

import java.util.List;
import java.util.Objects;

public class SwipeToDelete extends ItemTouchHelper.SimpleCallback {

    private ReservationAdapter rAdapter;
    private ColorDrawable background;
    private Drawable icon;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public SwipeToDelete(ReservationAdapter adapter){

        super(0, ItemTouchHelper.LEFT);

        this.rAdapter = adapter;
        rAdapter.setReservationList(Repository.getReservationList());

        icon = ContextCompat.getDrawable(Objects.requireNonNull(rAdapter.getContext().getActivity()), R.drawable.ic_delete_sweep_black_24dp);

        background = new ColorDrawable(Color.rgb(255, 99, 71));
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

        int iconMargin = (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
        int iconTop = itemView.getTop() + (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
        int iconBottom = iconTop + icon.getIntrinsicHeight();


        if (dX < 0){

            int iconLeft = itemView.getRight() - iconMargin - icon.getIntrinsicWidth();
            int iconRight = itemView.getRight() - iconMargin;
            icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);

            background.setBounds(itemView.getRight() + ((int) dX) - backgroundCornerOffset,
                    itemView.getTop(), itemView.getRight(), itemView.getBottom());
        }
        else {

            background.setBounds(0,0,0,0);
        }

        background.draw(c);
        icon.draw(c);
    }

}
