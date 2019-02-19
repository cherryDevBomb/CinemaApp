package com.example.cinemaapp.presenter;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.cinemaapp.model.Film;
import com.example.cinemaapp.model.Reservation;
import com.example.cinemaapp.repository.Repository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class MakeReservationPresenter {
    private MainView view;
    private String time;
    private Film film;
    private List<Integer> listPlaces;

    public MakeReservationPresenter(MainView view, Film film, String time) {
        this.view = view;
        this.film = film;
        this.time = time;
    }

    public void setListPlaces(List<Integer> listPlaces) {
        this.listPlaces = listPlaces;
    }

    public void updatePoster() {
        view.setPoster(film.getImagePath());
    }

    public void updateTitle() {
        view.setMovieTitle(film.getTitle());
    }

    public void updateStartTime() {
        view.setStartTime(time);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void createReservation() {
        //create object time from string
        Time timeObj = null;
        DateFormat formatter= new SimpleDateFormat("kk:mm");
        try {
             timeObj = new Time(formatter.parse(time).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Bitmap generatedQR = generateQR();
        //Create reservation & save to Repository
        Reservation reservation = new Reservation(film, timeObj, listPlaces, generatedQR);
        Repository.addReservation(reservation);
    }

    /**
     * Use Zxing to generate QR Code for Reservation
     * @return
     */
    private Bitmap generateQR() {
        //save information of reservation into string
        StringBuilder infoB = new StringBuilder(film.getTitle() + " " + time + "\n Places: ");
        for (int i : listPlaces) {
            infoB = infoB.append(i + 1);
            infoB = infoB.append(", ");
        }
        //delete last comma
        infoB.deleteCharAt(infoB.length() - 2);
        String info = infoB.toString();
        System.out.println(info);

        //generate bitmap with QR generated
        Bitmap bitmap = null;
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(info, BarcodeFormat.QR_CODE, 200, 200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            bitmap = barcodeEncoder.createBitmap(bitMatrix);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    public interface MainView {
        void setPoster(int moviePosterID);
        void setMovieTitle(String title);
        void setStartTime(String time);
        void saveReservation();
    }
}
