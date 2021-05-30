package TypeMazeGame.Sprites;

import TypeMazeGame.Tools.LoadResources;

import java.awt.image.BufferedImage;

public class SpritesSheet {

    final private int widthSheetPx, heightSheetPx, widthSheetSprites, heightSheetSprites;
    final private int widthSprites, heightSprites;

    final private Sprite[] sprites;

    public SpritesSheet(final String route, final int spriteSize, final boolean opaque){
        final BufferedImage image;

        if(opaque){
            image = LoadResources.loadCompatibleImageOpaque(route);
        }else{
            image = LoadResources.loadCompatibleImageTranslucent(route);
        }

        widthSheetPx = image.getWidth();
        heightSheetPx = image.getHeight();

        widthSheetSprites = widthSheetPx / spriteSize;
        heightSheetSprites = heightSheetPx / spriteSize;

        widthSprites = heightSprites = spriteSize;

        sprites = new Sprite[widthSheetSprites * heightSheetSprites];

        extractSpritesFromImage(image);
    }

    public SpritesSheet(final String route, final int widthSprite, final int heightSprite, boolean opaque){
        final BufferedImage image;

        if(opaque){
            image = LoadResources.loadCompatibleImageOpaque(route);
        }else{
            image = LoadResources.loadCompatibleImageTranslucent(route);
        }

        widthSheetPx = image.getWidth();
        heightSheetPx = image.getHeight();

        widthSheetSprites = widthSheetPx / widthSprite;
        heightSheetSprites = heightSheetPx / heightSprite;

        widthSprites = widthSprite;
        heightSprites = heightSprite;


        sprites = new Sprite[widthSheetSprites * heightSheetSprites];

        extractSpritesFromImage(image);
    }

    private void extractSpritesFromImage(final BufferedImage image){
        for(int y = 0; y < heightSheetSprites; y++){
            for(int x = 0; x < widthSheetSprites; x++){

                final int positionX = x * widthSprites;
                final int positionY = y * heightSprites;

                sprites[x + y * widthSheetSprites] = new Sprite(image.getSubimage(positionX, positionY, widthSprites, heightSprites));
            }
        }
    }

    public Sprite getSprite(final int index){
        return sprites[index];
    }

    public Sprite getSprite(final int x, final int y){
        return sprites[x + y * widthSheetSprites];
    }

}
