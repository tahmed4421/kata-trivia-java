package trivia;

import java.util.Optional;

public class Player {

    public static final int NUMBER_OF_COINS_TO_WIN = 6;
    private final String playerName;

    private int currentPosition;

    private int numberOfCoins;

    private boolean isInPenaltyBox;

    public Player(String playerName) {
        this.playerName = playerName;
        this.currentPosition = 0;
        this.numberOfCoins = 0;
        this.isInPenaltyBox = false;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public int getNumberOfCoins() {
        return numberOfCoins;
    }

    public boolean isInPenaltyBox() {
        return isInPenaltyBox;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
        System.out.println(getPlayerName() + "'s new location is " + getCurrentPosition());
    }

    public void setNumberOfCoins(int numberOfCoins) {
        this.numberOfCoins = numberOfCoins;
        System.out.println(getPlayerName()
                + " now has "
                + getNumberOfCoins()
                + " Gold Coins.");
    }

    public void setInPenaltyBox(boolean inPenaltyBox) {
        isInPenaltyBox = inPenaltyBox;
    }

    public void addCoin() {
        setNumberOfCoins(getNumberOfCoins() + 1);
    }

    protected Optional<QuestionType> rollAndGetCurentQuestionCategory(int roll, int boardSize) {
        System.out.println(this.getPlayerName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (this.isInPenaltyBox()) {
            if (roll % 2 != 0) {
                this.setInPenaltyBox(false);
                System.out.println(this.getPlayerName() + " is getting out of the penalty box");
            } else {
                System.out.println(this.getPlayerName() + " is not getting out of the penalty box");
            }
        }

        if (!this.isInPenaltyBox()) {
            int targetPosition = (this.getCurrentPosition() + roll) % boardSize;
            this.setCurrentPosition(targetPosition);
            QuestionType currentQuestionCategory = currentCategory(targetPosition);
            System.out.println("The category is " + currentQuestionCategory);
            return Optional.of(currentQuestionCategory);
        }
        return Optional.empty();
    }

    private QuestionType currentCategory(int playerPosition) {
        QuestionType[] types = QuestionType.values();
        QuestionType category = types[playerPosition % types.length];
        return category;
    }

    protected void correctAnswer() {
        System.out.println("Answer was correct!!!!");
        this.addCoin();
    }

    protected boolean didPlayerWin() {
        return this.getNumberOfCoins() == NUMBER_OF_COINS_TO_WIN;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
