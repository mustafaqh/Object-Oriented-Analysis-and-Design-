package view;

import model.Card;

/**
 * Implements a Swedish console view.
 */
public class SwedishView implements View {

  /**
   * Shows a welcome message.
   */
  public void displayWelcomeMessage() {
    for (int i = 0; i < 3; i++) {
      System.out.print("\n");
    }

    System.out.println("Hej Black Jack Världen");
    System.out.println("----------------------");
    System.out.println("Skriv 'p' för att Spela, 'h' för nytt kort, 's' för att stanna 'q' för att avsluta\n");
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

  /**
   * Displays a card.

   * @param card The card to display.
   */
  public void displayCard(model.Card card) {
    if (card.getColor() == model.Card.Color.Hidden) {
      System.out.println("Dolt Kort");
    } else {
      String[] colors = { "Hjärter", "Spader", "Ruter", "Klöver" };
      String[] values = { "två", "tre", "fyra", "fem", "sex", "sju", "åtta", "nio", "tio",
          "knekt", "dam", "kung", "ess" };
      System.out.println("" + colors[card.getColor().ordinal()] + " " + values[card.getValue().ordinal()]);
    }
  }

  public void displayPlayerHand(Iterable<model.Card> hand, int score) {
    displayHand("Spelare", hand, score);
  }

  public void displayDealerHand(Iterable<model.Card> hand, int score) {
    displayHand("Croupier", hand, score);
  }

  /**
   * Displays the winner of the game.

   * @param dealerIsWinner True if the dealer is the winner.
   */
  public void displayGameOver(boolean dealerIsWinner) {
    System.out.println("Slut: ");
    if (dealerIsWinner) {
      System.out.println("Croupiern Vann!");
    } else {
      System.out.println("Du vann!");
    }
  }

  private void displayHand(String name, Iterable<model.Card> hand, int score) {
    System.out.println(name + " Har: " + score);
    for (model.Card c : hand) {
      displayCard(c);
    }
    System.out.println("Poäng: " + score);
    System.out.println("");
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
  @Override
  public void pause() {
    try {
      System.out.println("*pause*");
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * a method to count card number.
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

    System.out.println("" + name + "(får kort nummer " + cardCounter(hand) + ")");


  }

  /**
   * display player cards.
   */
  public void playerdisplayNumber(Iterable<model.Card> hand) {
    displayCardNumber("Spelare: ", hand);
    displayCard(getLastCard(hand));
    pause();
  }

  /**
   * display dealer cards.
   */
  public void dealerdisplayNumber(Iterable<model.Card> hand) {
    displayCardNumber("Croupier: ", hand);
    displayCard(getLastCard(hand));
    pause();
  }

  /**
   * display player notification.
   */
  @Override
  public void displayNotificationPlayer(Iterable<Card> playerHand) {
    playerdisplayNumber(playerHand);

  }

  /**
   * display dealer notification.
   */
  @Override
  public void displayNotificationDealer(Iterable<Card> dealarHand) {
    dealerdisplayNumber(dealarHand);

  }

}
