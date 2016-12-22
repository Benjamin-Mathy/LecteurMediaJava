package sample;

//import com.aquafx_project.AquaFx;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //AquaFx.style();
        
        Parent root = FXMLLoader.load(getClass().getResource("../Views/sample.fxml"));
        primaryStage.setTitle("MediaManager Player");
        primaryStage.setScene(new Scene(root, 1200, 800));

        primaryStage.show();


    }



    public static void main(String[] args) {
        launch(args);
    }
}
