package TypeMazeGame.StateMachine;

import java.awt.*;

public interface GameState {
    void update();

    void draw(final Graphics graphics);

}
