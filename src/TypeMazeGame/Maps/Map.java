package TypeMazeGame.Maps;

import TypeMazeGame.Constants;
import TypeMazeGame.Control.ControlManager;
import TypeMazeGame.Entites.*;
import TypeMazeGame.PrincipalElements;
import TypeMazeGame.QuestionsEvent.QuestionsEvent;
import TypeMazeGame.Sprites.Sprite;
import TypeMazeGame.Sprites.SpritesSheet;
import TypeMazeGame.Tools.DebugDrawing;
import TypeMazeGame.Tools.LoadResources;
import TypeMazeGame.Tools.Stopwatch;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Map {

    private final int width, height;

    private final Point initialPoint, exitPoint;

    private String nextMap;

    private Rectangle exitZone;

    private final String[] partsMap;

    private final Sprite[] palette;

    private final boolean[] collisions;

    public ArrayList<Rectangle> collisionAreas = new ArrayList<Rectangle>();

    public final ArrayList<Enemy> enemies;
    public final ArrayList<Rectangle> enemiesRectangle;

    public final ArrayList<Item> items;
    public final ArrayList<Rectangle> itemsRectangle;

    private final int[] sprites;

    private final int MARGIN_X = Constants.WIDTH_GAME / 2 - Constants.SPRITE_SIDE / 2;
    private final int MARGIN_Y = Constants.HEIGHT_GAME / 2 - Constants.SPRITE_SIDE / 2;

    public Map(final String route){
        String content = LoadResources.readTextFile(route);

        partsMap = content.split("\\*");

        width = Integer.parseInt(partsMap[0]);
        height = Integer.parseInt(partsMap[1]);

        String usedSheets = partsMap[2];
        String[] separatedSheets = usedSheets.split(",");

        //Palette sprites lecture
        String entirePalette = partsMap[3];
        String[] paletteParts = entirePalette.split("#");

        palette = assignSprites(paletteParts, separatedSheets);

        String allCollisions = partsMap[4];

        collisions = extractCollisions(allCollisions);

        String spritesEntire = partsMap[5];
        String[] spritesString = spritesEntire.split(" ");

        sprites = extractSprites(spritesString);

        String position = partsMap[6];
        String[] positionsString = position.split("-");

        initialPoint = new Point();
        initialPoint.x = Integer.parseInt(positionsString[0]) * Constants.SPRITE_SIDE;
        initialPoint.y = Integer.parseInt(positionsString[1]) * Constants.SPRITE_SIDE;

        String exit = partsMap[7];
        String[] exitData = exit.split("-");

        exitPoint = new Point();
        exitPoint.x = Integer.parseInt(exitData[0]);
        exitPoint.y = Integer.parseInt(exitData[1]);

        nextMap = exitData[2];

        exitZone = new Rectangle();

        String enemiesInformation = partsMap[8];
        enemies = assignEnemies(enemiesInformation);
        enemiesRectangle = new ArrayList<>();

        String itemsInformation = partsMap[9];
        items = assignItems(itemsInformation);
        itemsRectangle = new ArrayList<>();
    }

    private ArrayList<Enemy> assignEnemies(final String enemiesInformation) {
        ArrayList<Enemy> enemiesTemp = new ArrayList<>();

        String[] enemiesSeparatedInformation = enemiesInformation.split("#");

        for(int i = 0; i < enemiesSeparatedInformation.length; i++){
            String[] actualEnemy = enemiesSeparatedInformation[i].split(":");
            String[] coordinates = actualEnemy[0].split(",");
            String enemyId = actualEnemy[1];

            Enemy enemy = EnemyRegistry.getEnemy(Integer.parseInt(enemyId));
            enemy.setPosition(Double.parseDouble(coordinates[0]), Double.parseDouble(coordinates[1]));

            enemiesTemp.add(enemy);
        }

        return enemiesTemp;
    }

    private ArrayList<Item> assignItems(final String itemsInformation){
        ArrayList<Item> itemsTemp = new ArrayList<>();

        String[] itemsSeparatedInformation = itemsInformation.split("#");

        for(int i = 0; i < itemsSeparatedInformation.length; i++){
            String[] actualItem = itemsSeparatedInformation[i].split(":");
            String[] coordinates = actualItem[0].split(",");
            String itemId = actualItem[1];

            Item item = ItemRegistry.getItem(Integer.parseInt(itemId));
            item.setPosition(Double.parseDouble(coordinates[0]), Double.parseDouble(coordinates[1]));

            itemsTemp.add(item);
        }

        return itemsTemp;
    }

    private Sprite[] assignSprites(final String[] paletteSprites, final String[] separatedSheets){
        Sprite[] paletteTemp = new Sprite[paletteSprites.length];

        SpritesSheet spritesSheet = new SpritesSheet("../resources/textures/" + separatedSheets[0] + ".png", 64, false);

        for(int i = 0; i <paletteSprites.length; i++){
            String tempSprite = paletteSprites[i];

            String[] spriteParts = tempSprite.split("-");

            int indexPalette = Integer.parseInt(spriteParts[0]);
            int indexSprite = Integer.parseInt(spriteParts[2]);

            paletteTemp[indexPalette] = spritesSheet.getSprite(indexSprite);

        }

        return paletteTemp;
    }

    private boolean[] extractCollisions(final String collisionsString){
        boolean[] collisions = new boolean[collisionsString.length()];

        for(int i = 0; i < collisionsString.length(); i++){
            if(collisionsString.charAt(i) == '0'){
                collisions[i] = false;
            }else{
                collisions[i] = true;
            }
        }

        return collisions;
    }

    private int[] extractSprites(final String[] spritesString){
        ArrayList<Integer> sprites = new ArrayList<Integer>();

        for(int i =0; i < spritesString.length; i++){
            if(spritesString[i].length() == 2){
                sprites.add(Integer.parseInt(spritesString[i]));
            }else{
                String two;
                String one = two = "";

                String error = spritesString[i];

                one += error.charAt(0);
                one += error.charAt(1);

                two += error.charAt(2);
                two += error.charAt(3);

                sprites.add(Integer.parseInt(one));
                sprites.add(Integer.parseInt(two));
                
            }
        }

        int[] spritesTemp = new int[sprites.size()];
        for(int i = 0; i < spritesTemp.length; i++){
            spritesTemp[i] = sprites.get(i);
        }

        return spritesTemp;
    }

    public void draw(Graphics graphics){

        int drawingIntents = 0;
        for(int y = 0; y < this.height; y++){
            for(int x = 0; x < this.width; x++){
                BufferedImage image = palette[sprites[x + y * width]].getImage();

                int pointX = x * Constants.SPRITE_SIDE - PrincipalElements.player.getPositionXInt() + MARGIN_X;
                int pointY = y * Constants.SPRITE_SIDE - PrincipalElements.player.getPositionYInt() + MARGIN_Y;

                if(pointX < 0 - Constants.SPRITE_SIDE || pointX > Constants.WIDTH_GAME || pointY < 0 - Constants.SPRITE_SIDE || pointY > Constants.HEIGHT_GAME - 50)
                    continue;

                drawingIntents++;
                DebugDrawing.drawImage(graphics, image, pointX, pointY);
            }
        }

        if(!enemies.isEmpty()){
            for(Enemy enemy : enemies){
                final int pointX = (int) enemy.getPositionX() * Constants.SPRITE_SIDE - PrincipalElements.player.getPositionXInt() + MARGIN_X;
                final int pointY = (int) enemy.getPositionY() * Constants.SPRITE_SIDE - PrincipalElements.player.getPositionYInt() + MARGIN_Y;

                enemy.draw(graphics, pointX, pointY);

                enemiesRectangle.add(enemy.getArea());
            }

        }

        if(!items.isEmpty()){
            for(Item item : items){
                final int pointX = (int) item.getPositionX() * Constants.SPRITE_SIDE - PrincipalElements.player.getPositionXInt() + MARGIN_X;
                final int pointY = (int) item.getPositionY() * Constants.SPRITE_SIDE - PrincipalElements.player.getPositionYInt() + MARGIN_Y;

                item.draw(graphics, pointX, pointY);

                itemsRectangle.add(item.getArea());
            }
        }

        System.out.println(drawingIntents);
    }

    public void update(){
        updateCollisionAreas();
        updateExitZone();
        updateEnemies();
        updateCollisionWithEnemies();
        updateItems();
        updateCollisionWithItems();

        for(Iterator<Enemy> iterator = enemies.iterator(); iterator.hasNext();){
            Enemy enemy = iterator.next();

            if(PrincipalElements.player.getLEFT_LIMIT().intersects(enemy.getArea()) || PrincipalElements.player.getRIGHT_LIMIT().intersects(enemy.getArea())||
                    PrincipalElements.player.getUPPER_LIMIT().intersects(enemy.getArea()) || PrincipalElements.player.getDOWN_LIMIT().intersects(enemy.getArea())){

                int index = new Random().nextInt(PrincipalElements.questions.getQuestions().size());
                QuestionsEvent event = new QuestionsEvent(PrincipalElements.questions.getQuestion(index),PrincipalElements.questions.getAnswer(index));

                event.StartEvent();
                PrincipalElements.player.pauseMovement();
                ControlManager.keyboard.up.releasedKey();
                ControlManager.keyboard.down.releasedKey();
                ControlManager.keyboard.right.releasedKey();
                ControlManager.keyboard.left.releasedKey();


                if(event.getPoints() != 0){
                    enemy.loseHealth();
                }
                iterator.remove();
                final Clip dead = LoadResources.loadSound(Constants.ZOMBIE_DEATH_SOUND);

                dead.start();
            }
        }

        for(Iterator<Item> iterator = items.iterator(); iterator.hasNext();){
            Item item = iterator.next();

            if(PrincipalElements.player.getLEFT_LIMIT().intersects(item.getArea()) || PrincipalElements.player.getRIGHT_LIMIT().intersects(item.getArea())||
                    PrincipalElements.player.getUPPER_LIMIT().intersects(item.getArea()) || PrincipalElements.player.getDOWN_LIMIT().intersects(item.getArea())){
                if(item instanceof CoinsBag)
                    PrincipalElements.player.updateTotalCoins(item.getValue());
                if(item instanceof CoinsChest)
                    PrincipalElements.player.updateTotalCoins(item.getValue());
                item.retrieveCoins();

                iterator.remove();
                PrincipalElements.mainThemeSong.stop();

                final Clip takeCoins = LoadResources.loadSound(Constants.TAKE_COINS_SOUND);
                takeCoins.start();
            }
        }

    }

    private void updateEnemies(){
        if(!enemies.isEmpty()){
            for(Enemy enemy : enemies){
                enemy.update();
            }
        }
    }

    private void updateCollisionWithEnemies(){
        for(Rectangle rectangleEnemy: enemiesRectangle){
            if(PrincipalElements.player.getLEFT_LIMIT().intersects(rectangleEnemy) || PrincipalElements.player.getRIGHT_LIMIT().intersects(rectangleEnemy)){
                System.out.println("Choca");
            }else{
                return;
            }
        }
    }

    private void updateItems(){
        if(!items.isEmpty()){
            for(Item item : items){
                item.update();
            }
        }
    }

    private void updateCollisionWithItems(){
        for(Rectangle rectangleItem : itemsRectangle){
            if(PrincipalElements.player.getLEFT_LIMIT().intersects(rectangleItem) || PrincipalElements.player.getRIGHT_LIMIT().intersects(rectangleItem)){
                System.out.println("Choca");
            }else{
                return;
            }
        }
    }

    private void updateCollisionAreas(){

        if(!collisionAreas.isEmpty())
            collisionAreas.clear();

        for(int y = 0; y < this.height; y++){
            for(int x = 0; x < this.width; x++){
                int pointX = x * Constants.SPRITE_SIDE - PrincipalElements.player.getPositionXInt() + MARGIN_X;
                int pointY = y * Constants.SPRITE_SIDE - PrincipalElements.player.getPositionYInt() + MARGIN_Y;

                if(collisions[x + y * this.width]){
                    final Rectangle rectangle = new Rectangle(pointX, pointY, Constants.SPRITE_SIDE, Constants.SPRITE_SIDE);

                    collisionAreas.add(rectangle);
                }
            }
        }
    }

    private void updateExitZone(){
        int pointX = ((int) exitPoint.getX()) * Constants.SPRITE_SIDE - PrincipalElements.player.getPositionXInt() + MARGIN_X;
        int pointY = ((int) exitPoint.getY()) * Constants.SPRITE_SIDE - PrincipalElements.player.getPositionYInt() + MARGIN_Y;

        exitZone = new Rectangle(pointX, pointY, Constants.SPRITE_SIDE, Constants.SPRITE_SIDE);
    }

    public Rectangle getBorders(final int positionX, final int positionY){
        int x = MARGIN_X - positionX + PrincipalElements.player.getPLAYER_WIDTH();
        int y = MARGIN_Y - positionY + PrincipalElements.player.getPLAYER_HEIGHT();

        int widthRectangle = this.width * Constants.SPRITE_SIDE - PrincipalElements.player.getPLAYER_WIDTH() * 2;
        int heightRectangle = this.height * Constants.SPRITE_SIDE - PrincipalElements.player.getPLAYER_HEIGHT() * 2;

        return new Rectangle(x, y, widthRectangle, heightRectangle);
    }

    public Point getInitialPoint() {
        return initialPoint;
    }

    public String getNextMap() {
        return nextMap;
    }

    public Rectangle getExitZone() {
        return exitZone;
    }
}
