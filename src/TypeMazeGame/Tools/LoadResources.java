package TypeMazeGame.Tools;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class LoadResources {

    public static BufferedImage loadCompatibleImageOpaque(final String route){
        Image image = null;

        try {
            image = ImageIO.read(LoadResources.class.getResource(route));
        } catch (IOException e) {
            e.printStackTrace();
        }

        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

        BufferedImage acceleratedImage = gc.createCompatibleImage(image.getWidth(null), image.getHeight(null), Transparency.OPAQUE);

        Graphics g = acceleratedImage.getGraphics();

        g.drawImage(image, 0, 0, null);
        g.dispose();

        return acceleratedImage;
    }

    public static BufferedImage loadCompatibleImageTranslucent(final String route){
        Image image = null;

        try {
            image = ImageIO.read(LoadResources.class.getResource(route));
        } catch (IOException e) {
            e.printStackTrace();
        }

        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

        BufferedImage acceleratedImage = gc.createCompatibleImage(image.getWidth(null), image.getHeight(null), Transparency.TRANSLUCENT);

        Graphics g = acceleratedImage.getGraphics();

        g.drawImage(image, 0, 0, null);
        g.dispose();

        return acceleratedImage;
    }

    public static String readTextFile(final String route){
        StringBuilder content = new StringBuilder();

        InputStream bytesEntrance = LoadResources.class.getResourceAsStream(route);

        BufferedReader reader = new BufferedReader(new InputStreamReader(bytesEntrance));

        String line;

        try{
            while((line = reader.readLine()) != null){
                content.append(line);
            }
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR");
        }finally {
            try{
                if(bytesEntrance != null){
                    bytesEntrance.close();
                }
                if(reader != null){
                    reader.close();
                }
            }catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return content.toString();
    }

    public static Font loadFont(final String route, final float size){
        Font font = null;

        InputStream byteEntrance = LoadResources.class.getResourceAsStream(route);

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, byteEntrance);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        font = font.deriveFont(size);

        return font;
    }

    public static Clip loadSound(final String route){
        Clip clip = null;

        try{
            InputStream is = LoadResources.class.getResourceAsStream(route);
            AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
            DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat());
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(ais);

        }catch (Exception e){
            e.printStackTrace();
        }

        return clip;
    }

}
