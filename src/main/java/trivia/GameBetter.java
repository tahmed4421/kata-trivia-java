package trivia;

import java.util.LinkedList;

// REFACTOR ME
public class GameBetter implements Game {

   LinkedList<Player> players = new LinkedList<>();
   GameBoard gameBoard;

   public GameBetter(int numberOfQuestionsPerType) {
      this.gameBoard = new GameBoard(12, numberOfQuestionsPerType);

   }

   public boolean isPlayable() {
      return (howManyPlayers() >= 2);
   }

   public boolean add(String playerName) {
      players.add(new Player(playerName));

      System.out.println(playerName + " was added");
      System.out.println("They are player number " + players.size());
      return true;
   }

   public int howManyPlayers() {
      return players.size();
   }

   public Player getCurrentPlayer(){
      return players.getFirst();
   }

   public void roll(int roll) {
      System.out.println(getCurrentPlayer().getPlayerName() + " is the current player");
      System.out.println("They have rolled a " + roll);

      if (getCurrentPlayer().isInPenaltyBox()) {
         if (roll % 2 != 0) {
            getCurrentPlayer().setInPenaltyBox(false);
            System.out.println(getCurrentPlayer().getPlayerName() + " is getting out of the penalty box");
         } else {
            System.out.println(getCurrentPlayer().getPlayerName() + " is not getting out of the penalty box");
         }
      }

      if (!getCurrentPlayer().isInPenaltyBox()) {
         int targetPosition = (getCurrentPlayer().getCurrentPosition() + roll) % gameBoard.getBoardSize();
         getCurrentPlayer().setCurrentPosition(targetPosition);

         QuestionType currentQuestionCategory = currentCategory(targetPosition);
         System.out.println("The category is " + currentQuestionCategory);
         gameBoard.askQuestion(currentQuestionCategory);
      }

   }


   private QuestionType currentCategory(int playerPosition) {
      QuestionType[] types = QuestionType.values();
      QuestionType category = types[playerPosition % types.length];
      return category;
   }

   public boolean wasCorrectlyAnswered() {
      if (getCurrentPlayer().isInPenaltyBox()) {
            updateNextPlayer();
            return true;
      } else {
         return correctAnswer( getCurrentPlayer() );
      }
   }

   private void updateNextPlayer() {
      players.addLast( players.pollFirst() );
   }

   private boolean correctAnswer( Player player ) {
      System.out.println("Answer was correct!!!!");
      player.addCoin();

      boolean winner = didPlayerWin();
      updateNextPlayer();
      return winner;
   }

   public boolean wrongAnswer() {
      System.out.println("Question was incorrectly answered");
      System.out.println(getCurrentPlayer().getPlayerName() + " was sent to the penalty box");
      getCurrentPlayer().setInPenaltyBox( true );

      updateNextPlayer();
      return true;
   }


   private boolean didPlayerWin() {
      return !(getCurrentPlayer().getNumberOfCoins() == 6);
   }
}
