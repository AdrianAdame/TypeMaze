package TypeMazeGame.Tools;

import java.awt.*;
import java.util.ArrayList;

public class DebugData {
    private static ArrayList<String> data = new ArrayList<>();

    public static void addData(final String newData){
        data.add(newData);
    }

    public static void clearData(){
        data.clear();
    }

    public static void drawData(Graphics graphics){
        graphics.setColor(Color.PINK);

        for(int i = 0; i < data.size(); i++){
            DebugDrawing.drawString(graphics, data.get(i), 20, 80 + i * 10);
        }
        clearData();
    }
}
