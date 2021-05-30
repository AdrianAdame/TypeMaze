package TypeMazeGame.StateMachine;

import TypeMazeGame.Graphics.DrawingSurface;
import TypeMazeGame.StateMachine.Game.GameManager;
import TypeMazeGame.StateMachine.GameOver.GameOverManager;
import TypeMazeGame.StateMachine.Inventory.MenuItemManager;
import TypeMazeGame.StateMachine.MainMenu.MainMenuManager;

import java.awt.*;

public class StatesManager {
    private GameState[] states;
    private GameState actualState;

    public StatesManager(final DrawingSurface drawingSurface){
        initializeStates(drawingSurface);
        initializeActualState();
    }

    private void initializeStates(DrawingSurface drawingSurface){
        states = new GameState[4];

        states[0] = new MainMenuManager(drawingSurface);
        states[1] = new GameManager();
        states[2] = new MenuItemManager(drawingSurface);
        states[3] = new GameOverManager(drawingSurface);
    }

    private void initializeActualState(){
        actualState = states[0];
    }

    public void update(){
        actualState.update();
    }

    public void draw(final Graphics graphics){
        actualState.draw(graphics);
    }

    public void changeActualState(final int newState){
        actualState = states[newState];
    }

    public GameState getActualState(){
        return actualState;
    }

}
