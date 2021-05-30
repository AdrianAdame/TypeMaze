package TypeMazeGame.Tools;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DebugDrawing {

    public static int drawingObjects = 0;

    public static void drawImage(final Graphics graphics, final BufferedImage image, final int x, final int y){
        drawingObjects++;
        graphics.drawImage(image, x, y, null);
    }

    public static void drawImage(final Graphics graphics, final BufferedImage image,final Point point){
        drawingObjects++;
        graphics.drawImage(image, point.x, point.y, null);
    }

    public static void drawString(final Graphics graphics, final String s, final int x, final int y){
        drawingObjects++;
        graphics.drawString(s, x, y);
    }

    public static void drawString(final Graphics graphics, final String s, final Point point){
        drawingObjects++;
        graphics.drawString(s, point.x, point.y);
    }

    public static void drawString(final Graphics graphics, final String s, final int x, final int y, final Color color){
        drawingObjects++;
        graphics.setColor(color);
        graphics.drawString(s, x, y);
    }

    public static void drawString(final Graphics graphics, final String s, final Point point, final Color color){
        drawingObjects++;
        graphics.setColor(color);
        graphics.drawString(s, point.x, point.y);
    }

    public static void drawRectangleFilled(final Graphics graphics, final int x, final int y, final int width, final int height){
        drawingObjects++;
        graphics.fillRect(x, y, width, height);
    }

    public static void drawRectangleFilled(final Graphics graphics, final Rectangle rectangle){
        drawingObjects++;
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public static void drawRectangleFilled(final Graphics graphics, final int x, final int y, final int width, final int height, final Color color){
        drawingObjects++;
        graphics.setColor(color);
        graphics.fillRect(x, y, width, height);
    }

    public static void drawRectangleFilled(final Graphics graphics, final Rectangle rectangle, final Color color){
        drawingObjects++;
        graphics.setColor(color);
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public static void drawRectangleBordered(final Graphics graphics, final int x, final int y, final int width, final int height){
        drawingObjects++;
        graphics.drawRect(x, y, width, height);
    }

    public static void drawRectangleBordered(final Graphics graphics, final Rectangle rectangle){
        drawingObjects++;
        graphics.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public static void drawRectangleBordered(final Graphics graphics, final int x, final int y, final int width, final int height, final Color color){
        drawingObjects++;
        graphics.setColor(color);
        graphics.drawRect(x, y, width, height);
    }

    public static void drawRectangleBordered(final Graphics graphics, final Rectangle rectangle, final Color color){
        drawingObjects++;
        graphics.setColor(color);
        graphics.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public static int getDrawingObjects() {
        return drawingObjects;
    }

    public static void restartDrawingObjectCounter(){
        drawingObjects = 0;
    }
}
