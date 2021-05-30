package TypeMazeGame.StateMachine.GameOver;

import TypeMazeGame.Constants;
import TypeMazeGame.Tools.DebugDrawing;

import java.awt.*;

public abstract class GameOverSection {
    protected final String gameOverItemName;
    protected final Rectangle menuRectangle;
    protected final int menuItemId;

    public GameOverSection(final String gameOverItemName, final Rectangle menuRectangle, final int id){
        this.gameOverItemName = gameOverItemName;
        this.menuRectangle = menuRectangle;
        menuItemId = id;
    }

    public abstract void update();

    public abstract void draw(Graphics graphics);

    public void drawInactiveTAG(final Graphics graphics){
        DebugDrawing.drawRectangleFilled(graphics, menuRectangle, Color.BLACK);
        DebugDrawing.drawString(graphics, gameOverItemName, menuRectangle.x + 15, menuRectangle.y+35, Color.WHITE);
    }

    public void drawActiveTAG(final Graphics graphics){
        drawActiveTAGrectangle(graphics);
    }

    public void drawInactiveTAGandRemarked(final Graphics graphics){
        DebugDrawing.drawRectangleFilled(graphics, menuRectangle, Color.WHITE);
        DebugDrawing.drawString(graphics, gameOverItemName, menuRectangle.x + 15, menuRectangle.y+35, Color.BLACK);
    }

    public void drawActiveTAGandRemarked(final Graphics graphics){
        drawActiveTAGrectangle(graphics);

        DebugDrawing.drawRectangleFilled(graphics,menuRectangle,Color.WHITE);
        DebugDrawing.drawString(graphics, gameOverItemName, menuRectangle.x + 15, menuRectangle.y+35, Color.BLACK);
    }

    private void drawActiveTAGrectangle(final Graphics graphics) {
        DebugDrawing.drawRectangleFilled(graphics, menuRectangle, Color.BLACK);
        DebugDrawing.drawString(graphics, gameOverItemName, menuRectangle.x + 15, menuRectangle.y+35, Color.WHITE);
    }

    public Rectangle getScaledMenuTag(){
        final Rectangle scaledTag = new Rectangle((int) (menuRectangle.x * Constants.SCALED_FACTOR_X), (int) (menuRectangle.y * Constants.SCALED_FACTOR_Y), (int) (menuRectangle.width * Constants.SCALED_FACTOR_X),  (int) (menuRectangle.height * Constants.SCALED_FACTOR_Y));
        return scaledTag;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

}
