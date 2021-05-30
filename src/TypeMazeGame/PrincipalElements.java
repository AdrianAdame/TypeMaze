package TypeMazeGame;

import TypeMazeGame.Entites.Player;
import TypeMazeGame.Maps.Map;
import TypeMazeGame.QuestionsEvent.Questions;
import TypeMazeGame.Tools.LoadResources;
import TypeMazeGame.Tools.Stopwatch;

import javax.sound.sampled.Clip;

public class PrincipalElements {

    public static Map map = new Map(Constants.MAP_ROUTE);
    public static Player player = new Player();
    public static Questions questions = new Questions(Constants.QUESTIONS_MAP1);
    public static Clip mainThemeSong = LoadResources.loadSound(Constants.MAIN_THEME_SONG);
    public static Stopwatch watch;
}
