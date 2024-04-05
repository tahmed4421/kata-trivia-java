package trivia;

public class Player {

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

    @Override
    public String toString() {
        return super.toString();
    }
}
