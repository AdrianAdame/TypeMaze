package TypeMazeGame;

import TypeMazeGame.Control.ControlManager;
import TypeMazeGame.Graphics.DrawingSurface;
import TypeMazeGame.Graphics.Window;
import TypeMazeGame.StateMachine.StatesManager;
import TypeMazeGame.Tools.LoadResources;
import TypeMazeGame.Tools.Stopwatch;

import javax.sound.sampled.Clip;

public class MainGestor {

    boolean running = false;
    private String title;
    private int windowWidth, windowHeight;

    private DrawingSurface drawingSurface;
    private Window window;

    private StatesManager statesManager;

    private static int aps = 0, fps = 0;

    private boolean stopWatchInitializated = false;

    private MainGestor (final String title, final int windowWidth, final int windowHeight){
        this.title = title;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

    public static void main(String [] args){
        MainGestor mainGestor = new MainGestor("Type Maze", Constants.WIDTH_FULL_WINDOW, Constants.HEIGHT_FULL_WINDOW);

        mainGestor.initializeGame();
        mainGestor.initializeMainLoop();
    }

    private void initializeGame(){
        running = true;
        initialize();
    }

    private void initialize(){
        drawingSurface = new DrawingSurface(windowWidth, windowHeight);
        window = new Window(title, drawingSurface);
        statesManager = new StatesManager(drawingSurface);
    }

    private void initializeMainLoop(){
        int accumulatedUpdates = 0, accumulatedFrames = 0;
        final int NS_PER_SECOND = 1000000000;
        final byte TARGET_UPS = 60;
        final double NS_PER_UPDATE = NS_PER_SECOND / TARGET_UPS;

        long updateReference = System.nanoTime();
        long counterReference = System.nanoTime();

        double timeElapsed;
        double delta = 0;

        while(running){
            //System.out.println("APS: " + accumulatedUpdates + " || FPS: " + accumulatedFrames);
            final long initCycle = System.nanoTime();
            timeElapsed = initCycle - updateReference;
            updateReference = initCycle;
            delta += timeElapsed / NS_PER_UPDATE;

            while (delta >= 1){
                updateWindow();
                accumulatedUpdates++;
                delta--;
            }

            drawWindow();
            accumulatedFrames++;

            if((System.nanoTime() - counterReference) > NS_PER_SECOND){
                fps = accumulatedFrames;
                aps = accumulatedUpdates;

                accumulatedUpdates = 0;
                accumulatedFrames = 0;
                counterReference = System.nanoTime();
            }

        }
    }

    private void updateWindow(){

        if(ControlManager.keyboard.inventoryActive){
            statesManager.changeActualState(2);
        }else{
            if(ControlManager.keyboard.mainMenu){
                statesManager.changeActualState(0);
            }else{

                if(!stopWatchInitializated){
                    PrincipalElements.watch = new Stopwatch();
                    PrincipalElements.watch.startThread();
                    stopWatchInitializated = true;
                }

                PrincipalElements.mainThemeSong.loop(Clip.LOOP_CONTINUOUSLY);

                PrincipalElements.mainThemeSong.start();
                statesManager.changeActualState(1);

            }
        }

        if(PrincipalElements.player.getHealth() <= 0){
            PrincipalElements.mainThemeSong.stop();
            statesManager.changeActualState(3);
        }

        drawingSurface.update();
        statesManager.update();

    }

    private void drawWindow(){
        drawingSurface.draw(statesManager);

    }

    public static int getFps() {
        return fps;
    }

    public static int getAps() {
        return aps;
    }
}
