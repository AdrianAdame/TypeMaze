package TypeMazeGame.Graphics;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private String title;

    public Window(final String title, final DrawingSurface drawingSurface){
        this.title = title;

        configureWindow(drawingSurface);
    }

    private void configureWindow(final DrawingSurface drawingSurface){
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setLayout(new BorderLayout());
        add(drawingSurface, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
