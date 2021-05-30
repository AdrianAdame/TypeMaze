package TypeMazeGame.Entites;

import TypeMazeGame.Constants;
import TypeMazeGame.Sprites.SpritesSheet;
import TypeMazeGame.Tools.DebugDrawing;

import java.awt.*;

public class Flea extends Enemy {

    private static SpritesSheet enemySheet;

    public Flea(int enemyId, String name, int maxHealth) {
        super(enemyId, name, maxHealth);

        if(enemySheet == null){
            enemySheet = new SpritesSheet(Constants.ENEMY_ROUTE + enemyId + ".png", 64, false);
        }
    }

    public void draw(final Graphics graphics, final int positionX, final int positionY){
        DebugDrawing.drawImage(graphics, enemySheet.getSprite(0).getImage(), positionX, positionY);
        super.draw(graphics, positionX, positionY);
    }

}
