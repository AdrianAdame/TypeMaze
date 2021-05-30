package TypeMazeGame.Entites;

import java.awt.*;

public class Enemy extends MapEntities{
    private float actualHealth;

    public Enemy(final int enemyId, final String name, final int maxHealth){

        super(enemyId, name, maxHealth);
        this.actualHealth = maxHealth;


    }

    public void update(){

    }


    public void draw(final Graphics graphics, final int positionX, final int positionY){
        if(actualHealth <= 0){
            return;
        }
    }

    public void loseHealth(){
        actualHealth = 0;
    }
}
