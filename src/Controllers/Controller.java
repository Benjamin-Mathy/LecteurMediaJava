package Controllers;

import Modeles.FileManager;
import Modeles.MediaManager;
import Views.MainWindowController;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.util.Hashtable;

/**
 * Created by Benja on 23/12/2016.
 */
public class Controller {
    private MediaManager mediaM;
    private FileManager fileM;
    private MainWindowController mainWindow;

    private Hashtable<String,String> ListMusicsSearch;

    public Controller(MainWindowController mainWindow){
        mediaM = new MediaManager(this);
        fileM = new FileManager();
        this.mainWindow=mainWindow;

        ListMusicsSearch= new Hashtable<>();
    }

    public void playPauseButtonSelected(){
        mediaM.PlayPauseButtonPressed();
    }
    public void VolumeChangeValue(double value){
        mediaM.ChangeVolume(value);
    }
    public void SpeedChangeValue(double value){
        mediaM.ChangeSpeed(value);
    }
    public void addDirectorySelected() {
        DirectoryChooser directoryChooser = new DirectoryChooser();

        File file = directoryChooser.showDialog(null);

        if(file!=null){
            try {
                mediaM.loadListMusic(fileM.getListMusic(file.getPath()));
                directoryAdded(file.getPath());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void artistAdded(String artist) {
        mainWindow.artistAdded(artist);
    }


    public void yearAdded(String year) {
        mainWindow.yearAdded(year);
    }


    public void albumAdded(String album) {
        mainWindow.albumAdded(album);
    }

    public void genreAdded(String genre) { mainWindow.genreAdded(genre); }

    public void directoryAdded(String path){
        mainWindow.directoryAdded(path);
    }
    public void filterSelected(String typeFilter,String filter){

        ListMusicsSearch=mediaM.getListMusic(typeFilter,filter);

        mainWindow.filterSelected(ListMusicsSearch);
    }
    public void musicSelected(String music){
        if(music!=null) {
            mediaM.PlayMusic(ListMusicsSearch.get(music));
        }
    }
}
