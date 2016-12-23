package Modeles;

import Controllers.Controller;
import Views.MainWindowController;
import com.sun.media.jfxmediaimpl.MediaUtils;
import javafx.collections.MapChangeListener;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.farng.mp3.id3.ID3v1;
import org.farng.mp3.id3.ID3v1_1;
import sun.misc.IOUtils;

import javax.swing.event.EventListenerList;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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

    private List<String> listMusic;
    private SortedMap<String, ArrayList<Integer>> listArtist;
    private SortedMap<String, ArrayList<Integer>> listAlbum;
    private SortedMap<String, ArrayList<Integer>> listYear;
    private SortedMap<String, ArrayList<Integer>> listFolder;
    private SortedMap<String, ArrayList<Integer>> listGenre;

    public MediaManager(Controller controller){
        this.controller= controller;

        listMusic = new ArrayList<String>();
        listArtist = new TreeMap<String, ArrayList<Integer>>();
        listAlbum = new TreeMap<String, ArrayList<Integer>>();
        listYear = new TreeMap<String, ArrayList<Integer>>();
        listFolder = new TreeMap<String, ArrayList<Integer>>();
        listGenre = new TreeMap<String, ArrayList<Integer>>();



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
        if(!listMusic.contains(path)) {
            listMusic.add(path);
            listFolder.get(path.substring(0,path.lastIndexOf("\\"))).add(listMusic.size()-1);

            try {
                MP3File file = new MP3File(new File(path));
                ID3v1 tag = file.getID3v1Tag();

                if(tag.getAlbum()!=null) {
                    if (listAlbum.containsKey(tag.getAlbum())) {
                        listAlbum.get(tag.getAlbum()).add(listMusic.size()-1);
                    } else {
                        ArrayList<Integer> list = new ArrayList<Integer>();
                        list.add(listMusic.size()-1);
                        listAlbum.put(tag.getAlbum(), list);
                        controller.albumAdded(tag.getAlbum());
                    }
                }
                if(tag.getArtist()!=null) {
                    if (listArtist.containsKey(tag.getArtist())) {
                        listArtist.get(tag.getArtist()).add(listMusic.size()-1);

                    } else {
                        ArrayList<Integer> list = new ArrayList<Integer>();
                        list.add(listMusic.size()-1);
                        listArtist.put(tag.getArtist(), list);
                        controller.artistAdded(tag.getArtist());
                    }
                }
                if(tag.getYear()!=null) {
                    if (listYear.containsKey(tag.getYear())) {
                        listYear.get(tag.getYear()).add(listMusic.size()-1);
                    } else {
                        ArrayList<Integer> list = new ArrayList<Integer>();
                        list.add(listMusic.size()-1);
                        listYear.put(tag.getYear(), list);
                        controller.yearAdded(tag.getYear());
                    }
                }
                if(tag.getGenre()!=0) {
                    if (listGenre.containsKey("" + tag.getGenre())) {
                        listGenre.get("" + tag.getGenre()).add(listMusic.size()-1);
                    } else {
                        ArrayList<Integer> list = new ArrayList<Integer>();
                        list.add(listMusic.size()-1);
                        listGenre.put("" + tag.getGenre(), list);
                        controller.genreAdded("" + tag.getGenre());
                    }
                }

            } catch (TagException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
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
        if (typeFilter.equals("genre")) {
            for (int id: listGenre.get(""+filter)) {
                i++;
                results.put(i+":\t"+listMusic.get(id).split("\\\\")[listMusic.get(id).split("\\\\").length-1],listMusic.get(id));
            }
        }
        return results;
    }
}
