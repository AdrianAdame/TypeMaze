package TypeMazeGame.StateMachine.Inventory;

import TypeMazeGame.Constants;
import TypeMazeGame.Tools.DebugDrawing;

import java.awt.*;

public abstract class InventorySection {
    protected final String sectionName;
    protected final Rectangle inventoryTAG;

    public InventorySection(final String sectionName, final Rectangle inventoryTAG){
        this.sectionName = sectionName;
        this.inventoryTAG = inventoryTAG;
    }

    public abstract void update();

    public abstract void draw(final Graphics graphics);

    public void drawInactiveTAG(final Graphics graphics){
        DebugDrawing.drawRectangleFilled(graphics, inventoryTAG, Color.PINK);
        DebugDrawing.drawString(graphics, sectionName, inventoryTAG.x + 15, inventoryTAG.y + 15, Color.BLACK);
    }

    public void drawActiveTAG(final Graphics graphics){
        drawActiveTAGrectangle(graphics);
    }

    public void drawInactiveTAGandRemarked(final Graphics graphics){
        DebugDrawing.drawRectangleFilled(graphics, inventoryTAG, Color.PINK);
        DebugDrawing.drawRectangleFilled(graphics,new Rectangle(inventoryTAG.x + inventoryTAG.width-10, inventoryTAG.y+5, 5, inventoryTAG.height-10),new Color(0x2a2a2a));
        DebugDrawing.drawString(graphics, sectionName, inventoryTAG.x + 15, inventoryTAG.y + 15, Color.BLACK);
    }

    public void drawActiveTAGandRemarked(final Graphics graphics){
        drawActiveTAGrectangle(graphics);

        DebugDrawing.drawRectangleFilled(graphics,new Rectangle(inventoryTAG.x + inventoryTAG.width-10, inventoryTAG.y+5, 5, inventoryTAG.height-10),new Color(0x2a2a2a));
        DebugDrawing.drawString(graphics, sectionName, inventoryTAG.x + 15, inventoryTAG.y + 15, Color.BLACK);
    }

    private void drawActiveTAGrectangle(final Graphics graphics) {
        DebugDrawing.drawRectangleFilled(graphics, inventoryTAG, Color.PINK);

        final Rectangle activeMark = new Rectangle(inventoryTAG.x, inventoryTAG.y, 5, inventoryTAG.height);
        final Color activeColor = Color.BLACK;

        DebugDrawing.drawRectangleFilled(graphics, activeMark, activeColor);
        DebugDrawing.drawString(graphics, sectionName, inventoryTAG.x + 15, inventoryTAG.y + 15, Color.BLACK);
    }

    public Rectangle getScaledMenuTag(){
        final Rectangle scaledTag = new Rectangle((int) (inventoryTAG.x * Constants.SCALED_FACTOR_X), (int) (inventoryTAG.y * Constants.SCALED_FACTOR_Y), (int) (inventoryTAG.width * Constants.SCALED_FACTOR_X),  (int) (inventoryTAG.height * Constants.SCALED_FACTOR_Y));
        return scaledTag;
    }
}
