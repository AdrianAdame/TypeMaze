package TypeMazeGame.Sprites;

import java.awt.image.BufferedImage;

public final class Sprite {
    private final BufferedImage image;

    private final int width;

    public Sprite(final BufferedImage image){
        this.image = image;
        width = image.getWidth();
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getWidth() {
        return width;
    }

}
