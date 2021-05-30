package TypeMazeGame.Entites;

import TypeMazeGame.Constants;
import TypeMazeGame.PrincipalElements;

import java.awt.*;

public abstract class MapEntities {

    private int Id;
    private double positionX, positionY;

    private String name;
    private int value;

    public MapEntities(final int Id, final String name, final int value){
        this.Id = Id;
        this.name = name;
        this.value = value;

        this.positionX = 0;
        this.positionY = 0;
    }

    public abstract void update();

    public abstract void draw(final Graphics graphics, final int positionX, final int positionY);

    public void setPosition(final double positionX, final double positionY){
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public Rectangle getArea(){
        final int pointX = (int) positionX * Constants.SPRITE_SIDE  - PrincipalElements.player.getPositionXInt() + Constants.MARGIN_X;
        final int pointY = (int) positionY * Constants.SPRITE_SIDE  - PrincipalElements.player.getPositionYInt() + Constants.MARGIN_Y;

        return new Rectangle(pointX, pointY, Constants.SPRITE_SIDE, Constants.SPRITE_SIDE);
    }

    public int getValue() {
        return value;
    }

}
