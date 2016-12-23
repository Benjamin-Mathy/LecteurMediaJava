package Modeles;

import Controllers.Controller;
import Views.MainWindowController;
import javafx.collections.MapChangeListener;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.swing.event.EventListenerList;
import java.io.File;
import java.util.*;

/**
 * Created by Benja on 20-12-16.
 */
public class MediaManager {
    private String path;
    private MediaPlayer mp;
    private javafx.scene.media.Media me;
 //   private MediaInfo mediaInfo;
    private EventListenerList listeners = new EventListenerList();
    private Controller controller;

    private Hashtable<Integer, String> listMusic;
    private SortedMap<String, ArrayList<Integer>> listArtist;
    private SortedMap<String, ArrayList<Integer>> listAlbum;
    private SortedMap<String, ArrayList<Integer>> listYear;
    private SortedMap<String, ArrayList<Integer>> listFolder;

    public MediaManager(Controller controller){
        this.controller= controller;

        listMusic = new Hashtable<Integer, String>();
        listArtist = new TreeMap<String, ArrayList<Integer>>();
        listAlbum = new TreeMap<String, ArrayList<Integer>>();
        listYear = new TreeMap<String, ArrayList<Integer>>();
        listFolder = new TreeMap<String, ArrayList<Integer>>();



    }
    public void PlayMusic(String path){
        if(mp!=null) {
            mp.stop();
            mp.dispose();
        }
        me = new Media(new File(path).toURI().toString());
        mp = new MediaPlayer(me);
        mp.setAutoPlay(true);
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

    public void loadListMusic(ArrayList<String> listMusic) throws InterruptedException {
        if(!listMusic.isEmpty()) {
            listFolder.put(listMusic.get(0).substring(0,listMusic.get(0).lastIndexOf("\\")),new ArrayList<>());
        }
        for (String musicPath: listMusic) {
            addMusic(musicPath);
        }
    }

    private void addMusic(String path) {
        me = new Media(new File(path).toURI().toString());
        //String artist = mediaInfo.get(MediaInfo.StreamKind.Audio,1,"artist", MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
        /*for(ListListener listener : listeners.getListeners(ListListener.class)) {
            listener.musicAdded(path);
        }*/
        if(!listMusic.contains(path)) {
            listMusic.put(listMusic.size()+1,path);
            listFolder.get(path.substring(0,path.lastIndexOf("\\"))).add(listMusic.size());

            me.getMetadata().addListener(new MapChangeListener<String, Object>() {
                @Override
                public void onChanged(Change<? extends String, ? extends Object> ch) {
                    if (ch.wasAdded()) {
                        addMetadata(ch.getKey(), ch.getValueAdded(), listMusic.size());
                    }
                }
            });
        }
    }
    private void addMetadata(String key, Object value, int id){

        if (key.equals("album")) {
            if(listAlbum.containsKey(value.toString())){
                listAlbum.get(value.toString()).add(id);
            }
            else{
                ArrayList<Integer> list = new ArrayList<Integer>();
                list.add(id);
                listAlbum.put(value.toString(),list);
                controller.albumAdded(value.toString());
            }
        } if (key.equals("artist")) {
            if(listArtist.containsKey(value.toString())){
                listArtist.get(value.toString()).add(id);

            }
            else{
                ArrayList<Integer> list = new ArrayList<Integer>();
                list.add(id);
                listArtist.put(value.toString(),list);
                controller.artistAdded(value.toString());
            }
        } if (key.equals("year")) {
            if(listYear.containsKey(value.toString())){
                listYear.get(value.toString()).add(id);
            }
            else{
                ArrayList<Integer> list = new ArrayList<Integer>();
                list.add(id);
                listYear.put(value.toString(),list);
                controller.yearAdded(value.toString());
            }
        }
    }
    public Hashtable<String,String> getListMusic(String typeFilter, String filter){
        Hashtable<String,String> results = new Hashtable<>();
        int i = 0;
        if (typeFilter.equals("album")) {
            for (int id: listAlbum.get(filter)) {
                i++;
                results.put(i+":\t"+listMusic.get(id).split("\\\\")[listMusic.get(id).split("\\\\").length-1],listMusic.get(id));
            }
        }
        if (typeFilter.equals("artist")) {
            for (int id: listArtist.get(filter)) {
                i++;
                results.put(i+":\t"+listMusic.get(id).split("\\\\")[listMusic.get(id).split("\\\\").length-1],listMusic.get(id));
            }
        }
        if (typeFilter.equals("year")) {
            for (int id: listYear.get(filter)) {
                i++;
                results.put(i+":\t"+listMusic.get(id).split("\\\\")[listMusic.get(id).split("\\\\").length-1],listMusic.get(id));
            }
        }
        if (typeFilter.equals("folder")) {
            for (int id: listFolder.get(filter)) {
                i++;
                results.put(i+":\t"+listMusic.get(id).split("\\\\")[listMusic.get(id).split("\\\\").length-1],listMusic.get(id));
            }
        }
        return results;
    }
}
