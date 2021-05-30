package TypeMazeGame;

import TypeMazeGame.Tools.LoadResources;

import java.awt.*;

public class Constants {
    public static final int SPRITE_SIDE = 64;

    public static int WIDTH_GAME = 1280; //1280
    public static int HEIGHT_GAME = 720; //720

    public static int WIDTH_FULL_WINDOW = 1920; //2560
    public static int HEIGHT_FULL_WINDOW = 1080; //1440

    public static int MARGIN_X = WIDTH_GAME / 2 - SPRITE_SIDE / 2;
    public static int MARGIN_Y = HEIGHT_GAME / 2 - SPRITE_SIDE / 2;


    public static double SCALED_FACTOR_X = (double) WIDTH_FULL_WINDOW / (double) WIDTH_GAME;
    public static double SCALED_FACTOR_Y = (double) HEIGHT_FULL_WINDOW /(double) HEIGHT_GAME;

    public static int WINDOW_CENTER_X = WIDTH_GAME / 2;
    public static int WINDOW_CENTER_Y = HEIGHT_GAME / 2;

    public static String MAP_ROUTE = "../resources/maps/tempMap.txt";
    public static String MOUSE_ICON_ROUTE = "../resources/images/icons/cursor.png";
    public static String PLAYER_ROUTE = "../resources/charactersImages/1.png";
    public static String ENEMY_ROUTE = "../resources/enemiesImages/";
    public static String ITEM_ROUTE = "../resources/itemsImages/";

    public static String MAIN_THEME_SONG = "../resources/songs/themeSong.wav";
    public static String ZOMBIE_DEATH_SOUND = "../resources/audioEffects/defeatenemy.wav";
    public static String FOOTSTEP_SOUND = "../resources/audioEffects/footstep.wav";
    public static String GAMEOVER_SOUND = "../resources/audioEffects/gameover.wav";
    public static String TAKE_COINS_SOUND = "../resources/audioEffects/takecoins.wav";
    public static String ZONE_CHANGED_SOUND = "../resources/audioEffects/zonechange.wav";

    public static String QUESTIONS_MAP1 = "../resources/questions/1.txt";

    public static Font MAIN_FONT = LoadResources.loadFont("../resources/fonts/Solway-Regular.ttf", 14f);
    public static Font MAIN_FONT_BOLD = LoadResources.loadFont("../resources/fonts/Solway-Bold.ttf", 24f);
    public static Font MAIN_FONT_EXTRABOLD = LoadResources.loadFont("../resources/fonts/Solway-ExtraBold.ttf", 14f);
    public static Font MAIN_FONT_LIGHT = LoadResources.loadFont("../resources/fonts/Solway-Light.ttf", 14f);

}
