package trivia;

import java.util.*;

public class GameBoard {

    private int boardSize;
    private int numberOfQuestionsPerType;

    private final ArrayList<LinkedList<Question>> questionQueues = new ArrayList<>();

    public GameBoard(int boardSize, int numberOfQuestionsPerType) {
        this.boardSize = boardSize;
        this.numberOfQuestionsPerType = numberOfQuestionsPerType;

        for (QuestionType questionType : QuestionType.values()) {

            LinkedList<Question> questionList = new LinkedList<>();
            questionQueues.add(questionList);

            for (int questionNumber = 0; questionNumber < this.numberOfQuestionsPerType; questionNumber++) {
                questionList.addLast(new Question(questionType, questionNumber));
            }
        }

    }

    public int getBoardSize() {
        return this.boardSize;
    }

    public void askQuestion(QuestionType questionType) {
        System.out.println(questionQueues.get(questionType.ordinal()).removeFirst().generatePrefix());
    }


}