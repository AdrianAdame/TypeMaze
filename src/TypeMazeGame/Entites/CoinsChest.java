package TypeMazeGame.Entites;

import TypeMazeGame.Constants;
import TypeMazeGame.Sprites.SpritesSheet;
import TypeMazeGame.Tools.DebugDrawing;

import java.awt.*;

public class CoinsChest extends Item {

    private static SpritesSheet itemSheet;

    public CoinsChest(int itemId, String name, int value) {
        super(itemId, name, value);

        if(itemSheet == null){
            itemSheet = new SpritesSheet(Constants.ITEM_ROUTE + itemId + ".png", 64, false);
        }
    }

    public void draw(final Graphics graphics, final int positionX, final int positionY){
        DebugDrawing.drawImage(graphics, itemSheet.getSprite(0).getImage(), positionX, positionY);
        super.draw(graphics, positionX, positionY);
    }

}
