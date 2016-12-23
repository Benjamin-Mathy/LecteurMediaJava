package Views;

import Controllers.Controller;
import Modeles.FileManager;
import Modeles.MediaManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.net.URL;
import java.util.Hashtable;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable{
    Controller controller;




    @FXML
    private ToggleButton playPauseButton;
    @FXML
    private Slider volumeSlider;
    @FXML
    private Slider speedSlider;
    @FXML
    private Slider SliderTimeSlace;
    @FXML
    private ListView<String> ListArtist;
    @FXML
    private ListView<String> ListAlbum;
    @FXML
    private ListView<String> ListYear;
    @FXML
    private ListView<String> ListFolder;
    @FXML
    private ListView<String> ListViewSearch;
    @FXML
    private Label LabelSearch;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = new Controller(this);

        ListArtist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                LabelSearch.setText(newValue);
                controller.filterSelected("artist", newValue);
            }
        });
        ListAlbum.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                LabelSearch.setText(newValue);
                controller.filterSelected("album", newValue);
            }
        });
        ListYear.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                LabelSearch.setText(newValue);
                controller.filterSelected("year", newValue);
            }
        });
        ListFolder.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                LabelSearch.setText(newValue);
                controller.filterSelected("folder", newValue);
            }
        });
        ListViewSearch.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                musicSelected(newValue);
            }
        });



    }
    public void addDirectorySelected() {
        controller.addDirectorySelected();
    }
    public void playPauseButtonSelected(){
        controller.playPauseButtonSelected();
    }
    public void replaySelected(){
        // TODO
    }
    public void randomSelected(){
        // TODO
    }
    public void VolumeChangeValue(){controller.VolumeChangeValue(volumeSlider.getValue());
    }
    public void SpeedChangeValue(){
        controller.SpeedChangeValue(speedSlider.getValue());
    }
    public void TimeChangeValue(){
    }
        // TODO
    public void artistAdded(String artist) {
        ListArtist.getItems().add(artist);
    }


    public void yearAdded(String year) {
        ListYear.getItems().add(year);
    }


    public void albumAdded(String album) {
        ListAlbum.getItems().add(album);
    }

    public void directoryAdded(String path){
        ListFolder.getItems().add(path);
    }
    public void filterSelected(Hashtable<String,String> ListMusicsSearch){
        ListViewSearch.getItems().clear();
        for (String music:ListMusicsSearch.keySet()) {
            ListViewSearch.getItems().add(music);
        }
    }
    public void musicSelected(String music){
        controller.musicSelected(music);
    }
}
