package trivia;

import java.util.LinkedList;

// REFACTOR ME
public class GameBetter implements Game {

   public final int BOARD_SIZE = 12;

   LinkedList<Player> players = new LinkedList<>();

   LinkedList<Question> popQuestions = new LinkedList<>();
   LinkedList<Question> scienceQuestions = new LinkedList<>();
   LinkedList<Question> sportsQuestions = new LinkedList<>();
   LinkedList<Question> rockQuestions = new LinkedList<>();

   public GameBetter(int numberOfQuestionsPerType) {
      for (int questionNumber = 0; questionNumber < numberOfQuestionsPerType; questionNumber++) {
         popQuestions.addLast(new Question(QuestionType.POP, questionNumber));
         scienceQuestions.addLast(new Question(QuestionType.SCIENCE, questionNumber));
         sportsQuestions.addLast(new Question(QuestionType.SPORTS, questionNumber));
         rockQuestions.addLast(new Question(QuestionType.ROCK, questionNumber));
      }
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
         int targetPosition = (getCurrentPlayer().getCurrentPosition() + roll) % BOARD_SIZE;
         getCurrentPlayer().setCurrentPosition(targetPosition);

         QuestionType currentQuestionCategory = currentCategory(targetPosition);
         System.out.println("The category is " + currentQuestionCategory);
         askQuestion(currentQuestionCategory);
      }

   }


   private void askQuestion(QuestionType questionType) {
      switch(questionType){
         case POP:
            System.out.println(popQuestions.removeFirst().generatePrefix());
            break;
         case SCIENCE:
            System.out.println(scienceQuestions.removeFirst().generatePrefix());
            break;
         case SPORTS:
            System.out.println(sportsQuestions.removeFirst().generatePrefix());
            break;
         case ROCK:
            System.out.println(rockQuestions.removeFirst().generatePrefix());
            break;
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
