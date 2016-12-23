package Modeles;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Benja on 22/12/2016.
 */
public class FileManager {

    public ArrayList<String> getListMusic(String path){
        File directory = new File(path);

        ArrayList listFiles = new ArrayList<String>();

        for (File file:directory.listFiles()) {
            if(file.getName().split("\\.")[file.getName().split("\\.").length-1].equals("mp3")){
                listFiles.add(file.getAbsolutePath().toString());
            }
        }
        return listFiles;
    }

}
