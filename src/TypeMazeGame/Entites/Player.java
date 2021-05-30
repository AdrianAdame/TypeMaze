package TypeMazeGame.Entites;

import TypeMazeGame.Constants;
import TypeMazeGame.Control.ControlManager;
import TypeMazeGame.PrincipalElements;
import TypeMazeGame.Sprites.SpritesSheet;
import TypeMazeGame.Tools.DebugDrawing;
import TypeMazeGame.Tools.LoadResources;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Player {

    private double positionX, positionY, velocity = 2;
    private int direction, animation, state;
    private boolean inMovement;

    private SpritesSheet spritesSheet;
    private BufferedImage actualImage;

    private final int PLAYER_WIDTH = 64;
    private final int PLAYER_HEIGHT = 64;

    private final Rectangle UPPER_LIMIT = new Rectangle(Constants.WINDOW_CENTER_X - PLAYER_WIDTH / 2+64+16, Constants.WINDOW_CENTER_Y+64, 32, 1);
    private final Rectangle DOWN_LIMIT = new Rectangle(Constants.WINDOW_CENTER_X - PLAYER_WIDTH / 2+64+16, Constants.WINDOW_CENTER_Y + PLAYER_HEIGHT+32, 32, 1);
    private final Rectangle RIGHT_LIMIT = new Rectangle(Constants.WINDOW_CENTER_X + 20 + 64, Constants.WINDOW_CENTER_Y + 64, 1, 32);
    private final Rectangle LEFT_LIMIT = new Rectangle(Constants.WINDOW_CENTER_X - 20 + 64, Constants.WINDOW_CENTER_Y + 64, 1, 32);

    private int resistance = 600;
    private int recovery = 0;
    private int MAX_RECOVERY = 200;
    private boolean recovered = true;
    private int health = 1000;
    private int totalPoints = 0;
    private int totalCoins = 0;
    private int phasesCompleted = 0;

    public Player(){
        positionX = PrincipalElements.map.getInitialPoint().getX();
        positionY = PrincipalElements.map.getInitialPoint().getY();

        direction = 0;
        inMovement = false;

        spritesSheet = new SpritesSheet(Constants.PLAYER_ROUTE, Constants.SPRITE_SIDE, false);
        actualImage = spritesSheet.getSprite(0).getImage();

        animation = 0;
        state = 0;
    }

    public void draw(Graphics graphics){
        final int centerX = Constants.WIDTH_GAME / 2 - Constants.SPRITE_SIDE / 2 + 64;
        final int centerY = Constants.HEIGHT_GAME / 2 - Constants.SPRITE_SIDE / 2 + 64;

        DebugDrawing.drawImage(graphics, actualImage, centerX, centerY);
    }

    private boolean outMap(final int velocityX, final int velocityY){
        int positionFutureX = (int) positionX + velocityX * (int) velocity;
        int positionFutureY = (int) positionY + velocityY * (int) velocity;

        final Rectangle mapBorders = PrincipalElements.map.getBorders(positionFutureX, positionFutureY);

        final boolean out;

        if(UPPER_LIMIT.intersects(mapBorders) || DOWN_LIMIT.intersects(mapBorders) || LEFT_LIMIT.intersects(mapBorders) || RIGHT_LIMIT.intersects(mapBorders)){
            out = false;
        }else{
            out = true;
        }

        return out;
    }

    private boolean inUpperCollision(final int velocityY){
        for(int r = 0; r < PrincipalElements.map.collisionAreas.size(); r++){
            final Rectangle area = PrincipalElements.map.collisionAreas.get(r);

            int originX = area.x;
            int originY = area.y + velocityY * (int) velocity + 3 * (int) velocity;

            final Rectangle futureArea = new Rectangle(originX, originY, Constants.SPRITE_SIDE, Constants.SPRITE_SIDE);

            if(UPPER_LIMIT.intersects(futureArea)){
                return true;
            }
        }

        return false;
    }

    private boolean inDownCollision(final int velocityY){
        for(int r = 0; r < PrincipalElements.map.collisionAreas.size(); r++){
            final Rectangle area = PrincipalElements.map.collisionAreas.get(r);

            int originX = area.x;
            int originY = area.y + velocityY * (int) velocity - 3 * (int) velocity;

            final Rectangle futureArea = new Rectangle(originX, originY, Constants.SPRITE_SIDE, Constants.SPRITE_SIDE);

            if(DOWN_LIMIT.intersects(futureArea)){
                return true;
            }
        }

        return false;
    }

    private boolean inRightCollision(final int velocityX){
        for(int r = 0; r < PrincipalElements.map.collisionAreas.size(); r++){
            final Rectangle area = PrincipalElements.map.collisionAreas.get(r);

            int originX = area.x + velocityX * (int) velocity - 3 * (int) velocity;
            int originY = area.y;

            final Rectangle futureArea = new Rectangle(originX, originY, Constants.SPRITE_SIDE, Constants.SPRITE_SIDE);

            if(RIGHT_LIMIT.intersects(futureArea)){
                return true;
            }
        }

        return false;
    }

    private boolean inLeftCollision(final int velocityX){
        for(int r = 0; r < PrincipalElements.map.collisionAreas.size(); r++){
            final Rectangle area = PrincipalElements.map.collisionAreas.get(r);

            int originX = area.x + velocityX * (int) velocity + 3 * (int) velocity;
            int originY = area.y;

            final Rectangle futureArea = new Rectangle(originX, originY, Constants.SPRITE_SIDE, Constants.SPRITE_SIDE);

            if(LEFT_LIMIT.intersects(futureArea)){
                return true;
            }
        }

        return false;
    }

    public void update(){
        manageVelocityResistance();
        changeAnimationState();
        inMovement = false;
        determineDirection();
        animate();
    }

    private void manageVelocityResistance(){
        if(ControlManager.keyboard.running && resistance > 0){
            velocity = 4;
            recovered = false;
            recovery = 0;
        }else{
            velocity = 2;
            if(!recovered && recovery < MAX_RECOVERY){
                recovery++;
            }
            if(recovery == MAX_RECOVERY && resistance < 600){
                resistance++;
            }
        }
    }

    private void changeAnimationState(){
        if(animation < 30){
            animation++;
        }else{
            animation = 0;
        }

        if(animation < 15){
            state = 1;
        }else{
            state = 2;
        }
    }

    private void determineDirection(){
        final int velocityX = evaluateXVelocity(), velocityY = evaluateYVelocity();

        if(velocityX == 0 && velocityY == 0){
            return;
        }

        if((velocityX != 0 && velocityY == 0) || (velocityX == 0 && velocityY != 0)){
            move(velocityX, velocityY);
        }else{
            //Left and up
            if(velocityX == -1 && velocityY == -1){
                if(ControlManager.keyboard.left.getLastPressed() > ControlManager.keyboard.up.getLastPressed()){
                    move(velocityX, 0);
                }else{
                    move(0, velocityY);
                }
            }
            //Left and down
            if(velocityX == -1 && velocityY == 1){
                if(ControlManager.keyboard.left.getLastPressed() > ControlManager.keyboard.down.getLastPressed()){
                    move(velocityX, 0);
                }else{
                    move(0, velocityY);
                }
            }

            //Right and up
            if(velocityX == 1 && velocityY == -1){
                if(ControlManager.keyboard.right.getLastPressed() > ControlManager.keyboard.up.getLastPressed()){
                    move(velocityX, 0);
                }else{
                    move(0, velocityY);
                }
            }

            //Right and down
            if(velocityX == 1 && velocityY == 1){
                if(ControlManager.keyboard.right.getLastPressed() > ControlManager.keyboard.down.getLastPressed()){
                    move(velocityX, 0);
                }else{
                    move(0, velocityY);
                }
            }
        }

    }

    private int evaluateXVelocity(){
        int velocityX = 0;

        if(ControlManager.keyboard.left.isPressed() && !ControlManager.keyboard.right.isPressed()){
            velocityX = -1;
        }else if(ControlManager.keyboard.right.isPressed() && !ControlManager.keyboard.left.isPressed()){
            velocityX = 1;
        }

        return velocityX;
    }

    private int evaluateYVelocity(){
        int velocityY = 0;

        if(ControlManager.keyboard.up.isPressed() && !ControlManager.keyboard.down.isPressed()){
            velocityY = -1;
        }else if(ControlManager.keyboard.down.isPressed() && !ControlManager.keyboard.up.isPressed()){
            velocityY = 1;
        }

        return velocityY;

    }

    private void animate(){
        if(!inMovement){
            state = 0;
            animation = 0;
        }

        actualImage = spritesSheet.getSprite(state, direction).getImage();
    }

    private void move(final int velocityX, final int velocityY){
        inMovement = true;

        changeDirection(velocityX, velocityY);

        if(!outMap(velocityX, velocityY)){
            if(velocityX == -1 && !inLeftCollision(velocityX)){
                positionX += velocityX * velocity;
                decreaseResistance();
            }
            if(velocityX == 1 && !inRightCollision(velocityX)){
                positionX += velocityX * velocity;
                decreaseResistance();
            }
            if(velocityY == -1 && !inUpperCollision(velocityY)){
                positionY += velocityY * velocity;
                decreaseResistance();
            }
            if(velocityY == 1 && !inDownCollision(velocityY)){
                positionY += velocityY * velocity;
                decreaseResistance();
            }
        }

        final Clip footstep = LoadResources.loadSound(Constants.FOOTSTEP_SOUND);
        footstep.start();
    }

    private void decreaseResistance(){
        if(ControlManager.keyboard.running && resistance > 0)
            resistance--;
    }

    public void decreaseHealth(){
        health -= 250;
    }

    private void changeDirection(final int velocityX, final int velocityY){
        if(velocityX == -1){
            direction = 2;
        }else if(velocityX == 1){
            direction = 3;
        }

        if(velocityY == -1){
            direction = 1;
        }else if(velocityY == 1){
            direction = 0;
        }
    }

    public void setPositionX(final double positionX){
        this.positionX = positionX;
    }

    public void setPositionY(final double positionY){
        this.positionY = positionY;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public int getPositionXInt() {
        return (int) positionX;
    }

    public int getPositionYInt() {
        return (int) positionY;
    }

    public int getPLAYER_WIDTH() {
        return PLAYER_WIDTH;
    }

    public int getPLAYER_HEIGHT() {
        return PLAYER_HEIGHT;
    }

    public int getResistance() {
        return resistance;
    }

    public int getTotalPoints(){
        return totalPoints;
    }

    public int getTotalCoins(){
        return totalCoins;
    }

    public Rectangle getUPPER_LIMIT() {
        return UPPER_LIMIT;
    }

    public Rectangle getDOWN_LIMIT() {
        return DOWN_LIMIT;
    }

    public Rectangle getLEFT_LIMIT() {
        return LEFT_LIMIT;
    }

    public Rectangle getRIGHT_LIMIT() {
        return RIGHT_LIMIT;
    }

    public void updateTotalPoints(int points) {
        totalPoints += points;
    }

    public void updateTotalCoins(int coins){
        totalCoins += coins;
    }

    public void updatePhasesCompleted(){
        phasesCompleted ++;
    }

    public int getPhasesCompleted() {
        return phasesCompleted;
    }

    public void pauseMovement(){
        inMovement = false;
    }

    public int getHealth() {
        return health;
    }

    public byte[] savePlayer(final String name, final LocalDateTime dateTime){
        StringBuilder dataPlayer = new StringBuilder();

        dataPlayer.append("Name:").append(name);
        dataPlayer.append("\n");
        dataPlayer.append("Total Points Obtained:").append(totalPoints);
        dataPlayer.append("\n");
        dataPlayer.append("Total Coins Obtained:").append(totalCoins);
        dataPlayer.append("\n");
        dataPlayer.append("Total Phases Completed:").append(phasesCompleted);
        dataPlayer.append("\n");
        dataPlayer.append("Saved on:").append(DateTimeFormatter.ofPattern("yyy/MM/dd HH:mm:ss").format(dateTime));

        return dataPlayer.toString().getBytes(StandardCharsets.UTF_8);
    }
}
