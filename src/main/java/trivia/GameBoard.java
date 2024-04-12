package trivia;

import java.util.*;

public class GameBoard {

    public static final int MAX_NUMBER_OF_PLAYERS = 6;
    private int boardSize;

    private int numberOfQuestionsPerType;

    private final ArrayList<LinkedList<Question>> questionQueues = new ArrayList<>();

    private final LinkedList<Player> players = new LinkedList<>();

    public GameBoard(int boardSize, int numberOfQuestionsPerType) {
        this.boardSize = boardSize;
        this.numberOfQuestionsPerType = numberOfQuestionsPerType;
        createQuestions();
    }

    private void createQuestions() {
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

    public int getPlayerSize() {
        return players.size();
    }

    public Player getCurrentPlayer() {
        return players.getFirst();
    }

    public boolean addPlayer(String playerName){
        Player newPlayer = new Player( playerName );
        if ( checkPlayerCanNotBeAdded( newPlayer ) ) {return false;}
        this.players.add(newPlayer);
        System.out.println(playerName + " was added");
        System.out.println("They are player number " + getPlayerSize());
        return true;
    }

    private boolean checkPlayerCanNotBeAdded( Player newPlayer ) {
        return this.players.size() >= MAX_NUMBER_OF_PLAYERS || this.players.contains( newPlayer );
    }

    public void switchToNextPlayer() {
        this.players.addLast(this.players.pollFirst());
    }
}
