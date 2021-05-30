package TypeMazeGame.StateMachine.MainMenu;

import TypeMazeGame.Constants;
import TypeMazeGame.Tools.DebugDrawing;

import java.awt.*;

public abstract class MainMenuSection {
    protected final String menuItemName;
    protected final Rectangle menuRectangle;
    protected final int menuItemId;

    public MainMenuSection(final String menuItemName, final Rectangle menuRectangle, final int id){
        this.menuItemName = menuItemName;
        this.menuRectangle = menuRectangle;
        menuItemId = id;
    }

    public abstract void update();

    public abstract void draw(Graphics graphics);

    public void drawInactiveTAG(final Graphics graphics){
        DebugDrawing.drawRectangleFilled(graphics, menuRectangle, Color.BLACK);
        DebugDrawing.drawString(graphics, menuItemName, menuRectangle.x + 15, menuRectangle.y+35, Color.WHITE);
    }

    public void drawActiveTAG(final Graphics graphics){
        drawActiveTAGrectangle(graphics);
    }

    public void drawInactiveTAGandRemarked(final Graphics graphics){
        DebugDrawing.drawRectangleFilled(graphics, menuRectangle, Color.WHITE);
        DebugDrawing.drawString(graphics, menuItemName, menuRectangle.x + 15, menuRectangle.y+35, Color.BLACK);
    }

    public void drawActiveTAGandRemarked(final Graphics graphics){
        drawActiveTAGrectangle(graphics);

        DebugDrawing.drawRectangleFilled(graphics,menuRectangle,Color.WHITE);
        DebugDrawing.drawString(graphics, menuItemName, menuRectangle.x + 15, menuRectangle.y+35, Color.BLACK);
    }

    private void drawActiveTAGrectangle(final Graphics graphics) {
        DebugDrawing.drawRectangleFilled(graphics, menuRectangle, Color.BLACK);
        DebugDrawing.drawString(graphics, menuItemName, menuRectangle.x + 15, menuRectangle.y+35, Color.WHITE);
    }

    public String getmenuItemName() {
        return menuItemName;
    }

    public Rectangle getmenuRectangle() {
        return menuRectangle;
    }

    public Rectangle getScaledMenuTag(){
        final Rectangle scaledTag = new Rectangle((int) (menuRectangle.x * Constants.SCALED_FACTOR_X), (int) (menuRectangle.y * Constants.SCALED_FACTOR_Y), (int) (menuRectangle.width * Constants.SCALED_FACTOR_X),  (int) (menuRectangle.height * Constants.SCALED_FACTOR_Y));
        return scaledTag;
    }

    public int getMenuItemId() {
        return menuItemId;
    }
}
