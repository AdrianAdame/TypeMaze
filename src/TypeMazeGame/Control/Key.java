package TypeMazeGame.Control;

public class Key {
    private boolean pressed;
    private long lastPressed = System.nanoTime();

    public void pressedKey(){
        pressed = true;
        lastPressed = System.nanoTime();
    }

    public void releasedKey(){
        pressed = false;
    }

    public boolean isPressed() {
        return pressed;
    }

    public long getLastPressed() {
        return lastPressed;
    }
}
