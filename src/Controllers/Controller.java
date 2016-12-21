package Controllers;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private ToggleButton playPauseButon;
    @FXML
    private MediaView mediaView;
    @FXML
    private Slider volumeSlider;
    @FXML
    private Slider speedSlider;
    @FXML
    private Slider SliderTimeSlace;

    private MediaPlayer mp;
    private Media me;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String path = new File("E:\\Users\\Benja\\Music\\11 - Jinx.mp3").getAbsolutePath();
        me = new Media(new File(path).toURI().toString());
        mp = new MediaPlayer(me);
                mediaView.setMediaPlayer(mp);
        mp.setAutoPlay(true);

        DoubleProperty width = mediaView.fitWidthProperty();
        DoubleProperty height = mediaView.fitHeightProperty();
        width.bind(Bindings.selectDouble(mediaView.sceneProperty(),"width"));
        height.bind(Bindings.selectDouble(mediaView.sceneProperty(),"height"));

    }
    public void addDirectorySelected(){
        
    }
    public void playPauseButtonSelected(){
        if(mp.getStatus()== MediaPlayer.Status.PLAYING){
            mp.pause();
        }
        else{
            mp.play();
        }
    }
    public void replaySelected(){

    }
    public void randomSelected(){

    }
    public void VolumeChangeValue(){
        mp.setVolume(volumeSlider.getValue()/100);
    }
    public void SpeedChangeValue(){
        mp.setRate(speedSlider.getValue()/3);
    }
    public void TimeChangeValue(){
        //mp.seek(SliderTimeSlace.getValue());
    }

}
