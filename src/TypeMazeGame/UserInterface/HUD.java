package TypeMazeGame.UserInterface;

import TypeMazeGame.Constants;
import TypeMazeGame.PrincipalElements;
import TypeMazeGame.Tools.DebugDrawing;

import javax.management.timer.Timer;
import java.awt.*;
import java.time.Duration;
import java.time.Instant;

public class HUD {
    private Rectangle inferiorArea;
    private Rectangle inferiorAreaBorder;
    private Rectangle superiorArea;
    private Rectangle superiorAreaBorder;

    private Color midnightBlack, clearRed, darkRed, mustardYellow, clearBlue, darkBlue, purple;

    public HUD(){

        int heightInventory = 50;

        inferiorArea = new Rectangle(0, Constants.HEIGHT_GAME - heightInventory, Constants.WIDTH_GAME, heightInventory);
        inferiorAreaBorder = new Rectangle(inferiorArea.x, inferiorArea.y - 1, inferiorArea.width, 1);

        superiorArea = new Rectangle(0, 0, Constants.WIDTH_GAME, heightInventory);
        superiorAreaBorder = new Rectangle(superiorArea.x, superiorArea.y+heightInventory, superiorArea.width, 1);

        midnightBlack = new Color(23,23,23);
        clearRed = new Color(255, 0, 0);
        darkRed = new Color(150, 0, 0);
        mustardYellow = new Color(227, 208,38);
        clearBlue = new Color(0,200,255);
        darkBlue = new Color(0,132,168);
        purple = new Color(128,0,255);
    }

    public void draw(final Graphics graphics){
        drawInferiorArea(graphics);
        drawSuperiorArea(graphics);
        drawHealthBar(graphics);
        drawResistanceBar(graphics);
        drawTotalCoins(graphics);
        drawTotalTime(graphics);

        drawSuperiorArea(graphics);
        drawTotalPoints(graphics);
        drawPhasesCompleted(graphics);
    }

    private void drawInferiorArea(final Graphics graphics){
        DebugDrawing.drawRectangleFilled(graphics, inferiorArea, midnightBlack);
        DebugDrawing.drawRectangleFilled(graphics, inferiorAreaBorder, Color.WHITE);
    }

    private void drawHealthBar(final Graphics graphics){
        final int verticalHeight = 5;
        final int totalWith = 100;
        final int width = 100 * PrincipalElements.player.getHealth() / 1000;

        DebugDrawing.drawRectangleFilled(graphics, inferiorArea.x+45, inferiorArea.y+verticalHeight, width, verticalHeight, clearRed);
        DebugDrawing.drawRectangleFilled(graphics, inferiorArea.x+45, inferiorArea.y+verticalHeight * 2, width, verticalHeight, darkRed);
        graphics.setColor(Color.WHITE);

        graphics.setFont(Constants.MAIN_FONT);
        DebugDrawing.drawString(graphics, "VIDA", inferiorArea.x +5, inferiorArea.y + verticalHeight * 3);
        DebugDrawing.drawString(graphics, "" + PrincipalElements.player.getHealth(), totalWith +55, inferiorArea.y + verticalHeight * 3);
    }

    private void drawResistanceBar(final Graphics graphics){
        final int verticalHeight = 5;
        final int totalWidth = 100;
        final int width = 100 * PrincipalElements.player.getResistance() / 600;

        DebugDrawing.drawRectangleFilled(graphics, inferiorArea.x+45, inferiorArea.y+verticalHeight * 5, width, verticalHeight, clearBlue);
        DebugDrawing.drawRectangleFilled(graphics, inferiorArea.x+45, inferiorArea.y+verticalHeight * 6, width, verticalHeight, darkBlue);
        graphics.setColor(Color.WHITE);

        graphics.setFont(Constants.MAIN_FONT);
        DebugDrawing.drawString(graphics, "RES", inferiorArea.x +5, inferiorArea.y + verticalHeight * 7);
        DebugDrawing.drawString(graphics, "" + PrincipalElements.player.getResistance(), totalWidth +55, inferiorArea.y + verticalHeight * 7);
    }

    private void drawTotalCoins(final Graphics graphics){
        final int verticalHeight = 10;

        graphics.setFont(Constants.MAIN_FONT_BOLD);
        DebugDrawing.drawString(graphics, "MONEDAS TOTALES: ", inferiorArea.x + Constants.WIDTH_GAME/2-250, inferiorArea.y + verticalHeight * 3);
        DebugDrawing.drawString(graphics, "$ " + PrincipalElements.player.getTotalCoins(), inferiorArea.x + Constants.WIDTH_GAME/2+20, inferiorArea.y + verticalHeight * 3, mustardYellow);
    }

    private void drawTotalTime(final Graphics graphics){
        final int verticalHeight = 10;

        StringBuilder timer = new StringBuilder();

        int[] curTime = PrincipalElements.watch.getTime();

        if(curTime[1] < 10){
            timer.append("0").append(curTime[1]);
        }else{
            timer.append(curTime[1]);
        }
        timer.append(":");
        if(curTime[2] < 10){
            timer.append("0").append(curTime[2]);
        }else{
            timer.append(curTime[2]);
        }

        graphics.setFont(Constants.MAIN_FONT_BOLD);
        DebugDrawing.drawString(graphics, "TIEMPO TOTAL: ", inferiorArea.x + Constants.WIDTH_GAME-350, inferiorArea.y + verticalHeight * 3, Color.WHITE);
        DebugDrawing.drawString(graphics, "" + timer, inferiorArea.x + Constants.WIDTH_GAME-150, inferiorArea.y + verticalHeight * 3, purple);
    }

    private void drawSuperiorArea(final Graphics graphics){
        DebugDrawing.drawRectangleFilled(graphics,superiorArea, midnightBlack);
        DebugDrawing.drawRectangleFilled(graphics, superiorAreaBorder, Color.WHITE);
    }

    private void drawPhasesCompleted(final Graphics graphics){
        final int verticalHeight = 40;
        graphics.setFont(Constants.MAIN_FONT_BOLD);
        DebugDrawing.drawString(graphics, "FASES COMPLETADAS: ", superiorArea.x +5, superiorArea.y + verticalHeight, Color.WHITE);
        DebugDrawing.drawString(graphics, "" + PrincipalElements.player.getPhasesCompleted(), superiorArea.x + 300, superiorArea.y + verticalHeight, clearRed);
    }

    private void drawTotalPoints(final Graphics graphics){
        final int verticalHeight = 40;
        graphics.setFont(Constants.MAIN_FONT_BOLD);
        DebugDrawing.drawString(graphics, "PUNTAJE TOTAL: ", Constants.WIDTH_GAME - 350, superiorArea.y + verticalHeight, Color.WHITE);
        DebugDrawing.drawString(graphics, "" + PrincipalElements.player.getTotalPoints(), Constants.WIDTH_GAME - 90, superiorArea.y + verticalHeight,mustardYellow);
    }
}
