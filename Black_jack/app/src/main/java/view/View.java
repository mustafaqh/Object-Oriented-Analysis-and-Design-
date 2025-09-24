package view;



/**
 * Encapsulates the generic view functionality.
 */
public interface View {

  /**
   * Shows a welcome message.
   */
  void displayWelcomeMessage();

  /**
   * Returns pressed characters from the keyboard.

   * @return the pressed character.
   */
  int getInput();

  /**
   * enum with different choices.
   */
  Choice choices();

  /**
   * Displays a card.

   * @param card The card to display.
   */
  void displayCard(model.Card card);

  /**
   * Displays the cards and score of the player.

   * @param hand  the player's hand.
   * @param score the player's score.
   */
  void displayPlayerHand(Iterable<model.Card> hand, int score);

  /**
   * Displays the cards and score of the dealer.

   * @param hand  the dealer's score.
   * @param score the players's score.
   */
  void displayDealerHand(Iterable<model.Card> hand, int score);

  /**
   * Displays the winner of the game.

   * @param dealerIsWinner True if the dealer is the winner.
   */
  void displayGameOver(boolean dealerIsWinner);

  /**
   * Returns last card.

   * @return player/ dealer last card.
   */
  model.Card getLastCard(Iterable<model.Card> hand);


  /**
   * display card number.
   */
  void displayCardNumber(String name, Iterable<model.Card> hand);


  /**
   * Displays the player notification.
   */
  void displayNotificationPlayer(Iterable<model.Card> playerHand);

  /**
   * Displays the dealer notification.
   */
  void displayNotificationDealer(Iterable<model.Card> dealarHand);

  /**
   * pause for 2 seconds.
   */
  void pause();

}