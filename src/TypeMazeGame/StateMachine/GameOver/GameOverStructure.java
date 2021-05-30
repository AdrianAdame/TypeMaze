package TypeMazeGame.StateMachine.GameOver;

import TypeMazeGame.Constants;
import TypeMazeGame.PrincipalElements;
import TypeMazeGame.Tools.DebugDrawing;
import TypeMazeGame.Tools.LoadResources;

import javax.sound.sampled.Clip;
import java.awt.*;

public class GameOverStructure {
    public final Color BACKGROUND_COLOR;

    public final Rectangle BACKGROUND, INNER_BACKGROUND;

    public boolean timerReaded = false;
    private int hours ,minutes, seconds;

    public GameOverStructure(){
        BACKGROUND_COLOR = new Color(0xB59685);

        BACKGROUND = new Rectangle(0,0, Constants.WIDTH_GAME, Constants.HEIGHT_GAME);
        INNER_BACKGROUND = new Rectangle(100, 100, Constants.WIDTH_GAME - 20 * Constants.WIDTH_GAME/100, Constants.HEIGHT_GAME - 20 * Constants.HEIGHT_GAME/100);
    }

    public void draw(final Graphics graphics){
        DebugDrawing.drawRectangleFilled(graphics, BACKGROUND, BACKGROUND_COLOR);
        DebugDrawing.drawRectangleFilled(graphics, INNER_BACKGROUND, Color.WHITE);

        graphics.setFont(Constants.MAIN_FONT_EXTRABOLD.deriveFont(48f));
        DebugDrawing.drawString(graphics, "FIN DEL JUEGO", Constants.WIDTH_GAME/2-180, Constants.HEIGHT_GAME/2-300, Color.BLACK);
        graphics.setFont(Constants.MAIN_FONT_BOLD.deriveFont(28f));

        if(!timerReaded){
            int[] curTime = PrincipalElements.watch.getTime();

            minutes = curTime[1];
            seconds = curTime[2];
            hours = curTime[0];

            timerReaded = true;

            final Clip gameOver = LoadResources.loadSound(Constants.GAMEOVER_SOUND);
            gameOver.start();
        }

        PrincipalElements.watch = null;

        StringBuilder time = new StringBuilder();

        if(hours < 10){
            time.append("0").append(hours);
        }else{
            time.append(hours);
        }
        time.append(":");
        if(minutes < 10){
            time.append("0").append(minutes);
        }else{
            time.append(minutes);
        }
        time.append(":");
        if(seconds < 10){
            time.append("0").append(seconds);
        }else{
            time.append(seconds);
        }

        DebugDrawing.drawString(graphics, "Tiempo total:", INNER_BACKGROUND.x+20, INNER_BACKGROUND.y+50, Color.BLACK);
        DebugDrawing.drawString(graphics, "" + time, INNER_BACKGROUND.width-80, INNER_BACKGROUND.y+50, Color.BLACK);

        DebugDrawing.drawString(graphics, "Fases completadas:", INNER_BACKGROUND.x+20, INNER_BACKGROUND.y+150, Color.BLACK);
        DebugDrawing.drawString(graphics, "1", INNER_BACKGROUND.width-80, INNER_BACKGROUND.y+150, Color.BLACK);

        DebugDrawing.drawString(graphics, "Vida restante:", INNER_BACKGROUND.x+20, INNER_BACKGROUND.y+250, Color.BLACK);
        DebugDrawing.drawString(graphics, "0", INNER_BACKGROUND.width-80, INNER_BACKGROUND.y+250, Color.BLACK);

        DebugDrawing.drawString(graphics, "Monedas acumuladas", INNER_BACKGROUND.x+20, INNER_BACKGROUND.y+350, Color.BLACK);
        DebugDrawing.drawString(graphics, ""+PrincipalElements.player.getTotalCoins(), INNER_BACKGROUND.width-80, INNER_BACKGROUND.y+350, Color.BLACK);

        DebugDrawing.drawString(graphics, "Puntaje obtenido", INNER_BACKGROUND.x+20, INNER_BACKGROUND.y+450, Color.BLACK);
        DebugDrawing.drawString(graphics, "" + PrincipalElements.player.getTotalPoints(), INNER_BACKGROUND.width-80, INNER_BACKGROUND.y+450, Color.BLACK);

        graphics.setFont(Constants.MAIN_FONT_BOLD.deriveFont(36f));

    }
}
