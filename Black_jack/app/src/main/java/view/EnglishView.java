package view;

/**
 * Implements an english console view.
 */
public class EnglishView implements View {

  /**
   * Shows a welcome message.
   */
  public void displayWelcomeMessage() {
    for (int i = 0; i < 3; i++) {
      System.out.print("\n");
    }
    System.out.println("Hello Black Jack World");
    System.out.println("----------------------");
    System.out.println("Type 'p' to Play, 'h' to Hit, 's' to Stand or 'q' to Quit\n");
  }

  /**
   * Returns pressed characters from the keyboard.

   * @return the pressed character.
   */
  public int getInput() {
    try {
      int c = System.in.read();
      while (c == '\r' || c == '\n') {
        c = System.in.read();
      }
      return c;
    } catch (java.io.IOException e) {
      System.out.println("" + e);
      return 0;
    }
  }

  public void displayCard(model.Card card) {
    System.out.println("" + card.getValue() + " of " + card.getColor());
  }

  public void displayPlayerHand(Iterable<model.Card> hand, int score) {
    displayHand("Player: ", hand, score);
  }

  public void displayDealerHand(Iterable<model.Card> hand, int score) {
    displayHand("Dealer: ", hand, score);
  }

  private void displayHand(String name, Iterable<model.Card> hand, int score) {
    System.out.println(name);
    for (model.Card c : hand) {
      displayCard(c);
    }
    System.out.println("Score: " + score);
    System.out.println("");
  }

  /**
   * Displays the winner of the game.

   * @param dealerIsWinner True if the dealer is the winner.
   */
  public void displayGameOver(boolean dealerIsWinner) {
    System.out.println("GameOver: ");
    if (dealerIsWinner) {
      System.out.println("Dealer Won!");
    } else {
      System.out.println("You Won!");
    }

  }

  /**
   * choices.
   */
  public Choice choices() {
    int input = getInput();
    if (input == 'p') {
      return Choice.Play;
    }
    if (input == 'h') {
      return Choice.Hit;
    }
    if (input == 's') {
      return Choice.Stand;
    }
    if (input == 'q') {
      return Choice.Quit;
    }
    return null;

  }

  /**
   * a method to pause for 2 seconds.
   */
  public void pause() {
    try {
      System.out.println("*pause*");
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * a method to count the card number.
   */
  public int cardCounter(Iterable<model.Card> hand) {
    int x = 0;
    for (model.Card c : hand) {
      x++;
    }
    return x;
  }

  /**
   * getting the last card.
   */
  public model.Card getLastCard(Iterable<model.Card> hand) {
    model.Card c = null;
    for (model.Card card : hand) {
      c = card;
    }
    return c;
  }

  /**
   * display card number.
   */
  public void displayCardNumber(String name, Iterable<model.Card> hand) {
  
    System.out.println("" + name + "(gets the card number " + cardCounter(hand) + ")");
    
  }

  /**
   * display player cards.
   */
  public void playerdisplayNumber(Iterable<model.Card> hand) {
    displayCardNumber("Player: ", hand);
    displayCard(getLastCard(hand));
    pause();
  }

  /**
   * display dealer cards.
   */
  public void dealerdisplayNumber(Iterable<model.Card> hand) {
    displayCardNumber("Dealer: ", hand);
    displayCard(getLastCard(hand));
    pause();

  }

  
  /**
   * display player notification.
   */
  public void displayNotificationPlayer(Iterable<model.Card> playerHand) {
    playerdisplayNumber(playerHand);

  }

  /**
   * display dealer notification.
   */
  public void displayNotificationDealer(Iterable<model.Card> dealarHand) {
    dealerdisplayNumber(dealarHand);
  }



}
