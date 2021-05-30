package TypeMazeGame.Tools;

import TypeMazeGame.PrincipalElements;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

public class SaveGameData {

    public static void saveDataOnTxt(){
        String name;
        LocalDateTime currentTime = LocalDateTime.now();

        name = JOptionPane.showInputDialog("Ingresa tu nombre:");

        currentTime = LocalDateTime.now();

        FileOutputStream outputFile = null;

        try{
            outputFile = new FileOutputStream("SaveGameData/" + "TypeMaze-" + name + ".txt");
            outputFile.write(PrincipalElements.player.savePlayer(name, currentTime));
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(outputFile != null){
                try {
                    outputFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
