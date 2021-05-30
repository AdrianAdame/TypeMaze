package TypeMazeGame.StateMachine.MainMenu;

import TypeMazeGame.Constants;
import TypeMazeGame.Tools.DebugDrawing;

import java.awt.*;

public class MainMenuStructure {
    public final Color UPPER_BANNER_COLOR;
    public final Color RIGHT_BANNER_COLOR;
    public final Color BACKGROUND_COLOR;

    public final Rectangle BACKGROUND, namesRectangle, namesBorderRectangle;

    public MainMenuStructure(){
        UPPER_BANNER_COLOR = Color.BLACK;
        BACKGROUND_COLOR = new Color(0xB59685);
        RIGHT_BANNER_COLOR = Color.WHITE;

        BACKGROUND = new Rectangle(0,0, Constants.WIDTH_GAME, Constants.HEIGHT_GAME);
        namesRectangle = new Rectangle(50, Constants.HEIGHT_GAME - 150, 400, 120);
        namesBorderRectangle = new Rectangle(50, Constants.HEIGHT_GAME - 150, namesRectangle.width+5, namesRectangle.height + 5);

    }

    public void draw(final Graphics graphics){
        DebugDrawing.drawRectangleFilled(graphics, BACKGROUND, BACKGROUND_COLOR);
        graphics.setFont(Constants.MAIN_FONT_EXTRABOLD.deriveFont(72f));
        DebugDrawing.drawString(graphics, "TYPE MAZE", Constants.WIDTH_GAME/2-220, Constants.HEIGHT_GAME/2-260, Color.BLACK);
        DebugDrawing.drawRectangleFilled(graphics, namesBorderRectangle, Color.BLACK);
        DebugDrawing.drawRectangleFilled(graphics, namesRectangle, Color.WHITE);

        graphics.setFont(Constants.MAIN_FONT_BOLD.deriveFont(18f));

        DebugDrawing.drawString(graphics, "Desarrollado por:", namesRectangle.x + 20, namesRectangle.y + 20, Color.BLACK);

        graphics.setFont(Constants.MAIN_FONT_LIGHT.deriveFont(18f));
        DebugDrawing.drawString(graphics, "Adame Arroyo Adrian Eduardo", namesRectangle.x + 20, namesRectangle.y + 50, Color.BLACK);
        DebugDrawing.drawString(graphics, "Herrera Berrelleza Angela Amairany", namesRectangle.x + 20, namesRectangle.y + 80, Color.BLACK);

        graphics.setFont(Constants.MAIN_FONT_BOLD.deriveFont(36f));
    }
}
