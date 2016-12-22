package Modeles;

import com.sun.xml.internal.ws.api.addressing.WSEndpointReference;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.scene.effect.Reflection;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


import java.awt.*;
import java.io.File;
import java.util.SortedMap;

/**
 * Created by Benja on 20-12-16.
 */
public class MediaManager {
    private String path;
    private MediaPlayer mp;
    private javafx.scene.media.Media me;

    private SortedMap<String, List> listArtist;
    private SortedMap<String, List> ListAlbum;

    private Label artist;
    private Label album;
    private Label title;
    private Label year;
    // ImageView albumCover;

    public MediaManager(){
        path = new File("E:\\Users\\Benja\\Music\\11 - Jinx.mp3").getAbsolutePath();
        me = new Media(new File(path).toURI().toString());
        mp = new MediaPlayer(me);
        mp.setAutoPlay(true);

        mp.volumeProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if(true){}
            }
        });

        me.getMetadata().addListener(new MapChangeListener<String, Object>() {
            @Override
            public void onChanged(Change<? extends String, ? extends Object> ch) {
                if (ch.wasAdded()) {
                    handleMetadata(ch.getKey(), ch.getValueAdded());
                }
            }
        });

    }

    public void PlayPauseButtonPressed(){
        if(mp.getStatus()== MediaPlayer.Status.PLAYING){
            mp.pause();
        }
        else{
            mp.play();
        }
    }
    public void ChangeSpeed(double value){
        mp.setRate(value/3);
    }
    public void ChangeVolume(double value){
        mp.setVolume(value/100);
    }

    private void handleMetadata(String key, Object value) {
        if (key.equals("album")) {
            album.setText(value.toString());

        } else if (key.equals("artist")) {
            artist.setText(value.toString());
        } if (key.equals("title")) {
            title.setText(value.toString());
        } if (key.equals("year")) {
            year.setText(value.toString());
        } /*if (key.equals("image")) {
            albumCover.setImage((Image)value);
        }*/
    }





}
