package TypeMazeGame.StateMachine.Inventory;

import TypeMazeGame.Constants;
import TypeMazeGame.Graphics.DrawingSurface;
import TypeMazeGame.StateMachine.GameState;

import java.awt.*;

public class MenuItemManager implements GameState {

    private final DrawingSurface drawingSurface;

    private final MenuStructure menuStructure;

    private final InventorySection[] sections;

    private InventorySection actualSection;

    public MenuItemManager(final DrawingSurface drawingSurface){

        this.drawingSurface = drawingSurface;

        menuStructure = new MenuStructure();
        sections = new InventorySection[2];
        final Rectangle inventoryTAG = new Rectangle(menuStructure.RIGHT_BANNER.x + menuStructure.HORIZONTAL_TAG_MARGIN, menuStructure.RIGHT_BANNER.y + menuStructure.VERTICAL_TAG_MARGIN, menuStructure.TAGS_WIDTH, menuStructure.TAGS_HEIGHT);

        sections[0] = new ItemsInventory("Inventario", inventoryTAG);

        final Rectangle storeTAG = new Rectangle(menuStructure.RIGHT_BANNER.x + menuStructure.HORIZONTAL_TAG_MARGIN, inventoryTAG.y + inventoryTAG.height + menuStructure.VERTICAL_TAG_MARGIN, menuStructure.TAGS_WIDTH, menuStructure.TAGS_HEIGHT);

        sections[1] = new ItemsInventory("Tienda", storeTAG);

        actualSection = sections[0];

    }

    @Override
    public void update() {
        for(int i = 0; i < sections.length; i++){
            if(drawingSurface.getMouse().getClick() && drawingSurface.getMouse().getPositionRectangle().intersects(sections[i].getScaledMenuTag())){
                actualSection = sections[i];
            }
        }

        drawingSurface.getMouse().restartClick();
    }

    @Override
    public void draw(final Graphics graphics) {
        graphics.setFont(Constants.MAIN_FONT_BOLD.deriveFont(12f));
        menuStructure.draw(graphics);
        for(int i = 0; i < sections.length; i++){

            if(actualSection == sections[i]) {
                if (drawingSurface.getMouse().getPositionRectangle().intersects(sections[i].getScaledMenuTag())) {
                    sections[i].drawActiveTAGandRemarked(graphics);
                } else {
                    sections[i].drawActiveTAG(graphics);
                }
            }
            else{
                if (drawingSurface.getMouse().getPositionRectangle().intersects(sections[i].getScaledMenuTag())) {
                    sections[i].drawInactiveTAGandRemarked(graphics);
                } else {
                    sections[i].drawInactiveTAG(graphics);
                }
            }
        }

    }
}
