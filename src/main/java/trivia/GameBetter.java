package trivia;

import java.util.Optional;

public class GameBetter implements Game {

    GameBoard gameBoard;

    public GameBetter(int numberOfQuestionsPerType) {
        this.gameBoard = new GameBoard(12, numberOfQuestionsPerType);
    }

    public boolean isPlayable() {
        return (gameBoard.getPlayerSize() >= 2);
    }

    public boolean add(String playerName) {
        return gameBoard.addPlayer(playerName);
    }

    public void roll(int roll) {
        Optional<QuestionType> currentQuestionCategory = gameBoard.getCurrentPlayer().rollAndGetCurentQuestionCategory(roll, gameBoard.getBoardSize());
        currentQuestionCategory.ifPresent(questionType -> gameBoard.askQuestion(questionType));
    }


    public boolean wasCorrectlyAnswered() {
        if (gameBoard.getCurrentPlayer().isInPenaltyBox()) {
            gameBoard.switchToNextPlayer();
            return true;
        } else {
            gameBoard.getCurrentPlayer().correctAnswer();
            boolean winner = gameBoard.getCurrentPlayer().didPlayerWin();
            gameBoard.switchToNextPlayer();
            return !winner;
        }
    }


    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(gameBoard.getCurrentPlayer().getPlayerName() + " was sent to the penalty box");

        gameBoard.getCurrentPlayer().setInPenaltyBox(true);
        gameBoard.switchToNextPlayer();
        return true;
    }
}
