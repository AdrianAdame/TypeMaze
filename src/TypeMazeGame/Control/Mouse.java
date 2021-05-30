package TypeMazeGame.Control;

import TypeMazeGame.Constants;
import TypeMazeGame.Graphics.DrawingSurface;
import TypeMazeGame.Tools.DebugData;
import TypeMazeGame.Tools.LoadResources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Mouse extends MouseAdapter {

    private final Cursor cursor;
    private Point position;
    private boolean click;

    public Mouse(final DrawingSurface surface){
        Toolkit configuration = Toolkit.getDefaultToolkit();

        BufferedImage icon = LoadResources.loadCompatibleImageTranslucent(Constants.MOUSE_ICON_ROUTE);

        Point peak = new Point(0,0);

        position = new Point();
        updatePosition(surface);
        click = false;

        this.cursor = configuration.createCustomCursor(icon, peak, "Default Cursor");

    }

    public Cursor getCursor() {
        return cursor;
    }

    private void updatePosition(final DrawingSurface surface){
        final Point mouseInitialPoint = MouseInfo.getPointerInfo().getLocation();

        SwingUtilities.convertPointFromScreen(mouseInitialPoint, surface);

        position.setLocation(mouseInitialPoint.getX(), mouseInitialPoint.getY());

    }

    public void update(DrawingSurface surface){
        updatePosition(surface);
    }

    public void draw(){
        DebugData.addData("RX: " + position.getX());
        DebugData.addData("RY: " + position.getY());
    }

    public Rectangle getPositionRectangle(){
        return new Rectangle(position.x, position.y, 1, 1);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(!click)
            click = true;
    }

    public boolean getClick() {
        return click;
    }

    public void restartClick(){
        if(click)
            click = false;
    }
}
