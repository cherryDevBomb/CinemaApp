package com.example.cinemaapp.model;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cinemaapp.view.MakeReservationActivity;
import com.example.cinemaapp.R;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmHolder> {
    private List<Film> filmlist = new ArrayList<>();
    private Fragment contextGetter;
    private int mExpandedPosition = -1;

    public FilmAdapter(Fragment contextGetter) {
        this.contextGetter = contextGetter;
    }

    @NonNull
    @Override
    public FilmHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.film_card, parent, false);
        return new FilmHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final FilmHolder holder, final int position) {
        Film currentFilm = filmlist.get(position);
        holder.textViewTitle.setText(currentFilm.getTitle());
        holder.textViewGenre.setText(currentFilm.getGenre());
        holder.textViewRating.setText("  " + String.valueOf(currentFilm.getRating()));
        holder.textViewDescription.setText(currentFilm.getDescription());


        //expand card
        final boolean isExpanded = position==mExpandedPosition;
        holder.detailsOnExpand.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        holder.itemView.setActivated(isExpanded);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                mExpandedPosition = isExpanded ? -1:position;
                TransitionManager.beginDelayedTransition(holder.detailsOnExpand);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return filmlist.size();
    }

    public void setFilmlist(List<Film> filmlist) {
        this.filmlist = filmlist;
    }

    class FilmHolder extends RecyclerView.ViewHolder {
        private ImageView poster;
        private TextView textViewTitle;
        private TextView textViewGenre;
        private TextView textViewRating;
        private TextView textViewDescription;

        private RelativeLayout detailsOnExpand;

        private RadioGroup radioGroup;
        private List<RadioButton> radioButtons;
        private Button reserveButton;

        public FilmHolder(View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.film_poster);
            poster.setImageResource(R.drawable.martian);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewGenre = itemView.findViewById(R.id.text_view_genre);
            textViewRating = itemView.findViewById(R.id.text_view_rating);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            detailsOnExpand = itemView.findViewById(R.id.details_on_expand);

            //hardcoded
            List<Time> times = Arrays.asList(new Time(12, 0, 0), new Time(16, 30, 0));
            //

            List<Integer> ids = Arrays.asList(R.id.radio1, R.id.radio2, R.id.radio3, R.id.radio4);
            radioButtons = new ArrayList<>();

            for (int i = 0; i < times.size(); i++) {
                final RadioButton button = itemView.findViewById(ids.get(i));
                Date date=new Date(times.get(i).getTime());
                DateFormat df= new SimpleDateFormat("HH:mm");
                button.setText(df.format(date));
                button.setVisibility(View.VISIBLE);
                radioButtons.add(button);           //may be optional?
            }

            radioGroup = itemView.findViewById(R.id.hour_choices);

            reserveButton = itemView.findViewById(R.id.openPage);
            reserveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openPage();
                }
            });
        }

        private void openPage() {
            Intent intent = new Intent(contextGetter.getActivity(), MakeReservationActivity.class);
            contextGetter.startActivity(intent);
        }
    }
}
