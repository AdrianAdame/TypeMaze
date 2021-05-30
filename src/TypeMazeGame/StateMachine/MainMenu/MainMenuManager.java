package TypeMazeGame.StateMachine.MainMenu;

import TypeMazeGame.Constants;
import TypeMazeGame.Control.ControlManager;
import TypeMazeGame.Graphics.DrawingSurface;
import TypeMazeGame.StateMachine.GameState;

import java.awt.*;

public class MainMenuManager implements GameState {

    private final DrawingSurface drawingSurface;

    private final MainMenuStructure mainMenuStructure;

    private final MainMenuSection[] sections;

    private MainMenuSection actualSection;

    public MainMenuManager(final DrawingSurface drawingSurface){
        this.drawingSurface = drawingSurface;
        mainMenuStructure = new MainMenuStructure();
        sections = new MainMenuSection[3];
        final Rectangle startGame = new Rectangle(Constants.WIDTH_GAME/2-150, Constants.HEIGHT_GAME-500, 300, 50);
        final Rectangle howToPlay = new Rectangle(Constants.WIDTH_GAME/2 - 150, Constants.HEIGHT_GAME - 400, 300, 50);
        final Rectangle settings = new Rectangle(Constants.WIDTH_GAME/2 - 150, Constants.HEIGHT_GAME - 300, 300, 50);

        sections[0] = new MenuItem("INICIAR JUEGO", startGame,1);
        sections[1] = new MenuItem("COMO JUGAR", howToPlay,2);
        sections[2] = new MenuItem("AJUSTES", settings,3);
        actualSection = sections[0];

    }

    @Override
    public void update() {
        for(int i = 0; i < sections.length; i++){
            if(drawingSurface.getMouse().getClick() && drawingSurface.getMouse().getPositionRectangle().intersects(sections[i].getScaledMenuTag())){
                actualSection = sections[i];
            }
        }

        drawingSurface.getMouse().restartClick();
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setFont(Constants.MAIN_FONT_BOLD.deriveFont(36f));

        mainMenuStructure.draw(graphics);
        for(int i = 0; i < sections.length; i++){

            if(actualSection == sections[i]) {
                if (drawingSurface.getMouse().getPositionRectangle().intersects(sections[i].getScaledMenuTag())) {
                    sections[i].drawActiveTAGandRemarked(graphics);
                } else {
                    sections[i].drawActiveTAG(graphics);
                }
            }
            else{
                if (drawingSurface.getMouse().getPositionRectangle().intersects(sections[i].getScaledMenuTag())) {
                    sections[i].drawInactiveTAGandRemarked(graphics);
                } else {
                    sections[i].drawInactiveTAG(graphics);
                }
            }

            if(drawingSurface.getMouse().getPositionRectangle().intersects(sections[i].getScaledMenuTag()) && drawingSurface.getMouse().getClick()){
                if(sections[i].getMenuItemId() == 1){
                    ControlManager.keyboard.mainMenu = false;
                }
            }
        }
    }
}
