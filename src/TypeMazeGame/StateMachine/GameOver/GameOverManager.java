package TypeMazeGame.StateMachine.GameOver;

import TypeMazeGame.Constants;
import TypeMazeGame.Control.ControlManager;
import TypeMazeGame.Entites.Player;
import TypeMazeGame.Graphics.DrawingSurface;
import TypeMazeGame.Maps.Map;
import TypeMazeGame.PrincipalElements;
import TypeMazeGame.StateMachine.GameState;
import TypeMazeGame.Tools.LoadResources;
import TypeMazeGame.Tools.SaveGameData;

import java.awt.*;

public class GameOverManager implements GameState {

    private final DrawingSurface drawingSurface;
    private final GameOverStructure gameOverStructure;

    private final GameOverSection[] section;

    public GameOverManager(final DrawingSurface drawingSurface){
        this.drawingSurface = drawingSurface;
        gameOverStructure = new GameOverStructure();

        section = new GameOverSection[1];

        final Rectangle returnMainMenu = new Rectangle(Constants.WIDTH_GAME/2-350, Constants.HEIGHT_GAME-80, 650, 50);

        section[0] = new GameOverItem("REGRESAR AL MENU PRINCIPAL", returnMainMenu, 1);

    }

    @Override
    public void update() {
        drawingSurface.getMouse().restartClick();
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setFont(Constants.MAIN_FONT_BOLD.deriveFont(36f));

        gameOverStructure.draw(graphics);

        if (drawingSurface.getMouse().getPositionRectangle().intersects(section[0].getScaledMenuTag())) {
            section[0].drawActiveTAGandRemarked(graphics);
        } else {
            section[0].drawActiveTAG(graphics);
        }

        if (drawingSurface.getMouse().getPositionRectangle().intersects(section[0].getScaledMenuTag())) {
            section[0].drawInactiveTAGandRemarked(graphics);
        } else {
            section[0].drawInactiveTAG(graphics);
        }

        if(drawingSurface.getMouse().getPositionRectangle().intersects(section[0].getScaledMenuTag()) && drawingSurface.getMouse().getClick()){
            if(section[0].getMenuItemId() == 1){
                SaveGameData.saveDataOnTxt();

                ControlManager.keyboard.mainMenu = true;
                PrincipalElements.mainThemeSong.stop();
                PrincipalElements.player = new Player();
                PrincipalElements.map = new Map(Constants.MAP_ROUTE);
                PrincipalElements.mainThemeSong = LoadResources.loadSound(Constants.MAIN_THEME_SONG);
            }
        }
    }
}
