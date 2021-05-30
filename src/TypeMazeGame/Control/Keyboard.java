package TypeMazeGame.Control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class Keyboard implements KeyListener {

    public Key up = new Key();
    public Key down = new Key();
    public Key left = new Key();
    public Key right = new Key();

    public boolean running = false;
    public boolean debug = false;
    public boolean inventoryActive = false;
    public boolean mainMenu = true;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP -> up.pressedKey();
            case KeyEvent.VK_DOWN -> down.pressedKey();
            case KeyEvent.VK_LEFT -> left.pressedKey();
            case KeyEvent.VK_RIGHT -> right.pressedKey();
            case KeyEvent.VK_SHIFT -> running = true;
            case KeyEvent.VK_ESCAPE -> System.exit(0);
            case KeyEvent.VK_F1 -> debug = !debug;
            case KeyEvent.VK_I -> inventoryActive = !inventoryActive;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP -> up.releasedKey();
            case KeyEvent.VK_DOWN -> down.releasedKey();
            case KeyEvent.VK_LEFT -> left.releasedKey();
            case KeyEvent.VK_RIGHT -> right.releasedKey();
            case KeyEvent.VK_SHIFT -> running = false;
        }
    }
}
