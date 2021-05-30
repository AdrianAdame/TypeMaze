package TypeMazeGame.Entites;

import java.awt.*;

public class Item extends MapEntities{

    public Item(final int itemId, final String name, final int totalCoins){

        super(itemId, name, totalCoins);
    }

    @Override
    public void update(){

    }

    @Override
    public void draw(final Graphics graphics, final int positionX, final int positionY){
        if(getValue() <= 0){
            return;
        }
    }

    public int retrieveCoins(){
        return getValue();
    }

}
