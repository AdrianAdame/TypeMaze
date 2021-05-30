package TypeMazeGame.QuestionsEvent;

import TypeMazeGame.Constants;
import TypeMazeGame.PrincipalElements;

import javax.swing.*;
import java.awt.*;

public class QuestionsEvent{

    private String question, answer;
    private int points;
    private JTextArea text;



    public QuestionsEvent(final String question, final String answer){
        this.question = question;
        this.answer = answer;
        points = 0;
    }

    public void displayGUI(){
        int selection = JOptionPane.showConfirmDialog(null, getPanel());

        if(selection == JOptionPane.OK_OPTION){
            if(text.getText().equals(answer)){
                points = 100;
                PrincipalElements.player.updateTotalPoints(points);
            }else{
                PrincipalElements.player.decreaseHealth();
            }
        }else{
            points = 0;
            PrincipalElements.player.decreaseHealth();
        }
    }

    private JPanel getPanel(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        StringBuilder questionBuilder = new StringBuilder();

        int counter = 0;
        for(int i = 0; i < question.length(); i++){
            counter++;
            if(counter > 40){
                questionBuilder.append(question.charAt(i)).append("-");
                questionBuilder.append("\n");
                counter = 0;
            }else{
                questionBuilder.append(question.charAt(i));
            }
        }

        JTextArea label = new JTextArea("Escribe la siguiente frase:\n"+ questionBuilder, 6, 20);
        label.setFont(Constants.MAIN_FONT_EXTRABOLD.deriveFont(34f));
        label.setOpaque(false);

        text = new JTextArea(15,30);
        text.setFont(Constants.MAIN_FONT_BOLD.deriveFont(16f));
        jPanel.add(label, c);

        c.gridx = 0;
        c.gridy = 1;
        jPanel.add(text,c);

        return jPanel;
    }

    public int getPoints(){
        return points;
    }

    public void StartEvent(){
        SwingUtilities.invokeLater(this::displayGUI);
    }
}
