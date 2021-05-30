package TypeMazeGame.QuestionsEvent;

import TypeMazeGame.Tools.LoadResources;

import java.util.ArrayList;

public class Questions {

    private final ArrayList<String> questions;
    private final ArrayList<String> answers;

    public Questions(final String route){
        String content = LoadResources.readTextFile(route);

        String[] questionsAnswers = content.split(",");

        questions = new ArrayList<>();
        answers = new ArrayList<>();

        for(int i = 0; i < questionsAnswers.length; i++){
            questions.add(questionsAnswers[i].split(":")[0]);
            answers.add(questionsAnswers[i].split(":")[1]);
        }
    }
    public String getAnswer(final int index){
        return answers.get(index);
    }

    public String getQuestion(final int index){
        return questions.get(index);
    }

    public ArrayList<String> getQuestions() {
        return questions;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }
}
