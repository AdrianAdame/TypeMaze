package TypeMazeGame.StateMachine.Game;

import TypeMazeGame.Constants;
import TypeMazeGame.Maps.Map;
import TypeMazeGame.PrincipalElements;
import TypeMazeGame.StateMachine.GameState;
import TypeMazeGame.Tools.DebugData;
import TypeMazeGame.Tools.DebugDrawing;
import TypeMazeGame.Tools.LoadResources;
import TypeMazeGame.UserInterface.HUD;

import javax.sound.sampled.Clip;
import java.awt.*;

public class GameManager implements GameState {

    HUD playerHUD;

    public GameManager() {
        playerHUD = new HUD();
    }

    private void reloadGame(){
        final String route = "../resources/maps/" + PrincipalElements.map.getNextMap();
        PrincipalElements.map = new Map(route);
        PrincipalElements.player.setPositionX(PrincipalElements.map.getInitialPoint().getX());
        PrincipalElements.player.setPositionY(PrincipalElements.map.getInitialPoint().getY());
    }

    @Override
    public void update() {
        if(PrincipalElements.player.getLEFT_LIMIT().intersects(PrincipalElements.map.getExitZone())){
            reloadGame();
            final Clip changeZone = LoadResources.loadSound(Constants.ZONE_CHANGED_SOUND);
            changeZone.start();
            PrincipalElements.player.updatePhasesCompleted();
        }

        //First player and then map
        PrincipalElements.player.update();
        PrincipalElements.map.update();

    }

    @Override
    public void draw(Graphics graphics) {
        PrincipalElements.map.draw(graphics);
        PrincipalElements.player.draw(graphics);

        DebugDrawing.drawImage(graphics, LoadResources.loadCompatibleImageTranslucent("../resources/images/door.png"), (int) PrincipalElements.map.getExitZone().getX(), (int) PrincipalElements.map.getExitZone().getY());

        playerHUD.draw(graphics);

        DebugData.addData("X: " + PrincipalElements.player.getPositionX());
        DebugData.addData("Y: " + PrincipalElements.player.getPositionY());
        DebugData.addData("Next Map: " + PrincipalElements.map.getNextMap());
        DebugData.addData("Exit Coordinates X: " + PrincipalElements.map.getExitZone().getX() + " Y: " + PrincipalElements.map.getExitZone().getY());

    }

}
