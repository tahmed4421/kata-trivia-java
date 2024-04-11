package trivia;

import java.util.*;

public class GameBoard {

    private int boardSize;

    private int numberOfQuestionsPerType;

    private final ArrayList<LinkedList<Question>> questionQueues = new ArrayList<>();

    private final LinkedList<Player> players = new LinkedList<>();

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

    public LinkedList<Player> getPlayers() {
        return players;
    }

    public int getPlayerSize() {
        return players.size();
    }

    public Player getCurrentPlayer() {
        return players.getFirst();
    }

    public void addPlayer(String playerName){
        this.players.add(new Player(playerName));
        System.out.println(playerName + " was added");
        System.out.println("They are player number " + getPlayerSize());
    }

    public void switchToNextPlayer() {
        this.players.addLast(this.players.pollFirst());
    }
}