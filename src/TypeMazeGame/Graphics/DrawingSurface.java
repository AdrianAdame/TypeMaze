package TypeMazeGame.Graphics;

import TypeMazeGame.Constants;
import TypeMazeGame.Control.ControlManager;
import TypeMazeGame.Control.Mouse;
import TypeMazeGame.MainGestor;
import TypeMazeGame.StateMachine.StatesManager;
import TypeMazeGame.Tools.DebugData;
import TypeMazeGame.Tools.DebugDrawing;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class DrawingSurface extends Canvas{

    private int width, height;

    private Mouse mouse;

    public DrawingSurface(final int width, final int height){
        this.width = width;
        this.height = height;

        this.mouse = new Mouse(this);

        setCursor(this.mouse.getCursor());

        setIgnoreRepaint(true);
        setPreferredSize(new Dimension(width, height));
        addKeyListener(ControlManager.keyboard);
        addMouseListener(mouse);
        setFocusable(true);
        requestFocus();
    }

    public void update(){
        mouse.update(this);
    }

    public void draw(final StatesManager statesManager){
        BufferStrategy bufferStrategy = getBufferStrategy();

        if(bufferStrategy == null){
            try {
                createBufferStrategy(4);
                return;
            }catch (IllegalStateException e){
                System.out.println("Componentes aun no inicializados");
            }
        }

        try{
            Graphics2D graphics = (Graphics2D) bufferStrategy.getDrawGraphics();

            DebugDrawing.restartDrawingObjectCounter();

            graphics.setFont(Constants.MAIN_FONT);

            DebugDrawing.drawRectangleFilled(graphics, 0, 0, Constants.WIDTH_FULL_WINDOW, Constants.HEIGHT_FULL_WINDOW, Color.BLACK);


            if(Constants.SCALED_FACTOR_X != 1.0 || Constants.SCALED_FACTOR_Y != 1.0)
                graphics.scale(Constants.SCALED_FACTOR_X, Constants.SCALED_FACTOR_Y);


            statesManager.draw(graphics);
            mouse.draw();

            DebugData.addData("FPS: " + MainGestor.getFps());
            DebugData.addData("APS: " + MainGestor.getAps());
            DebugData.addData("OPF: " + DebugDrawing.getDrawingObjects());


            if(ControlManager.keyboard.debug){
                DebugData.drawData(graphics);
            }else{
                DebugData.clearData();
            }

            Toolkit.getDefaultToolkit().sync();

            graphics.dispose();

            bufferStrategy.show();

        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public Mouse getMouse() {
        return mouse;
    }
}
