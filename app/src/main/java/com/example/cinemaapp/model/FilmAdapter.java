package com.example.cinemaapp.model;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabWidget;
import android.widget.TextView;

import com.example.cinemaapp.R;

import java.util.ArrayList;
import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmHolder> {
    private List<Film> filmlist = new ArrayList<>();

    private int mExpandedPosition = -1;

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
        holder.textViewRating.setText(String.valueOf(currentFilm.getRating()));
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

        public FilmHolder(View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.film_poster);
            poster.setImageResource(R.drawable.martian);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewGenre = itemView.findViewById(R.id.text_view_genre);
            textViewRating = itemView.findViewById(R.id.text_view_rating);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            detailsOnExpand = itemView.findViewById(R.id.details_on_expand);
        }
    }
}
