package com.example.cinemaapp.adapters;

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

import com.example.cinemaapp.model.Film;
import com.example.cinemaapp.repository.Repository;
import com.example.cinemaapp.view.MakeReservationActivity;
import com.example.cinemaapp.R;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FavoriteFilmAdapter extends RecyclerView.Adapter<FavoriteFilmAdapter.FavoriteFilmHolder> {
    private List<Film> filmlist = Repository.favoriteList;
    private Fragment contextGetter;
    private int mExpandedPosition = -1;

    public FavoriteFilmAdapter(Fragment contextGetter) {
        this.contextGetter = contextGetter;
    }

    @NonNull
    @Override
    public FavoriteFilmHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.film_card, parent, false);
        return new FavoriteFilmHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final FavoriteFilmHolder holder, final int position) {
        Film currentFilm = filmlist.get(position);
        holder.filmObject = currentFilm;
        holder.position = position;
        holder.poster.setImageResource(currentFilm.getImagePath());
        holder.textViewTitle.setText(currentFilm.getTitle());
        holder.textViewGenre.setText(currentFilm.getGenre());
        holder.textViewRating.setText("  " + String.valueOf(currentFilm.getRating()));
        holder.textViewDescription.setText(currentFilm.getDescription());

        HashMap<Time, List<Boolean>> thisFilmProgram = Repository.getHardcodedProgram().get(currentFilm.getTitle());
        List<Time> times = new ArrayList<Time>(thisFilmProgram.keySet());
        for (int i = 0; i < times.size(); i++) {
            Date date=new Date(times.get(i).getTime());
            DateFormat df= new SimpleDateFormat("HH:mm");
            holder.buttons.get(i).setText(df.format(date));
            holder.buttons.get(i).setVisibility(View.VISIBLE);
        }

        boolean isFavorite = Repository.searchInFavorites(currentFilm);
        if (isFavorite) {
            holder.favorite.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_favorite_red_35dp, 0);
        }
        else {
            holder.favorite.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_favorite_border_black_35dp, 0);
        }


        //expand card
        final boolean isExpanded = position==mExpandedPosition;
        holder.detailsOnExpand.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        holder.itemView.setActivated(isExpanded);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                if (!isExpanded) {
                    holder.expandIcon.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_up_black_24dp, 0);
                }
                else {
                    holder.expandIcon.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_down_black_24dp, 0);
                }
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

    class FavoriteFilmHolder extends RecyclerView.ViewHolder {
        private Film filmObject;
        private int position;

        private ImageView poster;
        private TextView textViewTitle;
        private TextView textViewGenre;
        private TextView textViewRating;
        private TextView expandIcon;
        private TextView textViewDescription;
        private RelativeLayout detailsOnExpand;
        private RadioGroup radioGroup;
        List<RadioButton> buttons = new ArrayList<>();
        private Button reserveButton;
        private Button favorite;


        public FavoriteFilmHolder(final View itemView) {
            super(itemView);

            poster = itemView.findViewById(R.id.film_poster);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewGenre = itemView.findViewById(R.id.text_view_genre);
            textViewRating = itemView.findViewById(R.id.text_view_rating);
            expandIcon = itemView.findViewById(R.id.expand_icon);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            detailsOnExpand = itemView.findViewById(R.id.details_on_expand);

            List<Integer> ids = Arrays.asList(R.id.radio1, R.id.radio2, R.id.radio3, R.id.radio4);
            for (int i = 0; i < 4; i++) {
                final RadioButton button = itemView.findViewById(ids.get(i));
                buttons.add(button);
            }

            radioGroup = itemView.findViewById(R.id.hour_choices);

            reserveButton = itemView.findViewById(R.id.openPage);
            reserveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int selectedId = radioGroup.getCheckedRadioButtonId();
                    RadioButton selectedButton = (RadioButton) itemView.findViewById(selectedId);
                    if (selectedButton != null) {
                        String selectedTime = (String) selectedButton.getText();
                        openPage(filmObject, selectedTime);
                    }
                }
            });


            favorite = itemView.findViewById(R.id.heart);
            favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isFavorite = Repository.searchInFavorites(filmObject);
                    if (!isFavorite) {
                        favorite.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_favorite_red_35dp, 0);
                        filmObject.setFavorite(true);
                        Repository.addToFavorites(filmObject);

                    }
                    else {
                        favorite.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_favorite_border_black_35dp, 0);
                        filmObject.setFavorite(false);
                        filmlist.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, filmlist.size());

                        if (Repository.favoriteList.isEmpty()) {
                            final TextView header = contextGetter.getView().findViewById(R.id.no_favorites_message_header);
                            header.setVisibility(View.VISIBLE);
                            final TextView body = contextGetter.getView().findViewById(R.id.no_favorites_message_body);
                            body.setVisibility(View.VISIBLE);
                        }
                    }
                }
            });
        }

        private void openPage(Film film, String time) {
            Intent intent = new Intent(contextGetter.getActivity(), MakeReservationActivity.class);
            intent.putExtra("film", film);
            intent.putExtra("time", time);
            contextGetter.startActivity(intent);
        }
    }
}
