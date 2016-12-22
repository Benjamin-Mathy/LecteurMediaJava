package Controllers;

import Modeles.MediaManager;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private MediaManager mediaM;

    @FXML
    private ToggleButton playPauseButton;
    @FXML
    private Slider volumeSlider;
    @FXML
    private Slider speedSlider;
    @FXML
    private Slider SliderTimeSlace;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mediaM = new MediaManager();

    }
    public void addDirectorySelected(){
        
    }
    public void playPauseButtonSelected(){
        mediaM.PlayPauseButtonPressed();
    }
    public void replaySelected(){

    }
    public void randomSelected(){

    }
    public void VolumeChangeValue(){
        mediaM.ChangeVolume(volumeSlider.getValue());
    }
    public void SpeedChangeValue(){
        mediaM.ChangeSpeed(speedSlider.getValue());
    }
    public void TimeChangeValue(){
    }

}
