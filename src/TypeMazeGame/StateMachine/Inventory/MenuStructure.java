package TypeMazeGame.StateMachine.Inventory;

import TypeMazeGame.Constants;
import TypeMazeGame.Tools.DebugDrawing;

import java.awt.*;

public class MenuStructure {

    public final Color UPPER_BANNER_COLOR;
    public final Color RIGHT_BANNER_COLOR;
    public final Color BACKGROUND_COLOR;

    public final Rectangle UPPER_BANNER;
    public final Rectangle RIGHT_BANNER;
    public final Rectangle BACKGROUND;

    public final int HORIZONTAL_TAG_MARGIN;
    public final int VERTICAL_TAG_MARGIN;
    public final int TAGS_WIDTH;
    public final int TAGS_HEIGHT;

    public MenuStructure(){
        UPPER_BANNER_COLOR = Color.BLACK;
        BACKGROUND_COLOR = new Color(0xE5E5E5);
        RIGHT_BANNER_COLOR = Color.WHITE;

        UPPER_BANNER = new Rectangle(0,0, Constants.WIDTH_GAME, 40);
        RIGHT_BANNER = new Rectangle(0, UPPER_BANNER.height, 140, Constants.HEIGHT_GAME- UPPER_BANNER.height);
        BACKGROUND = new Rectangle(RIGHT_BANNER.x + RIGHT_BANNER.width, RIGHT_BANNER.y, Constants.WIDTH_GAME - RIGHT_BANNER.width, Constants.HEIGHT_GAME - UPPER_BANNER.height);

        HORIZONTAL_TAG_MARGIN = 20;
        VERTICAL_TAG_MARGIN = 20;
        TAGS_WIDTH = 100;
        TAGS_HEIGHT = 35;
    }

    public void draw(final Graphics graphics){
        DebugDrawing.drawRectangleFilled(graphics, UPPER_BANNER, UPPER_BANNER_COLOR);
        DebugDrawing.drawRectangleFilled(graphics, RIGHT_BANNER, RIGHT_BANNER_COLOR);
        DebugDrawing.drawRectangleFilled(graphics, BACKGROUND, BACKGROUND_COLOR);
    }

}
