package model.rules;

import model.Player;

/**
 * creat a new rule that implements HitStrategy.
 */
public class SoftHitStrategy implements HitStrategy {
  private static final int hitLimit = 17;

  /**
   * getting the total score of the cards, all ace value is 11.
   */
  public int totalScore(Player p) {
    int[] cardScores = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11 };
    assert (cardScores.length == model.Card.Value.Count.ordinal())
        : "Card Scores array size does not match number of card values";
    int score = 0;

    for (model.Card c : p.getHand()) {
      if (c.getValue() != model.Card.Value.Hidden) {
        score += cardScores[c.getValue().ordinal()];
      }
    }
    return score;
  }

  /**
   * checking if the dealer can take more cards if one ace value is 11.
   */
  public boolean doHit(Player dealer) {
    int currentScore = dealer.calcScore();
    var cards = dealer.getHand();
    int aceCounter = 0;
    if (currentScore == hitLimit) {
      for (model.Card c : cards) {
        if (c.getValue() == model.Card.Value.Ace) {
          aceCounter++;
        }
      }

      return aceCounter == 0
          ? false
          : totalScore(dealer) - (10 * (aceCounter - 1)) == currentScore;
    }

    return currentScore < hitLimit;
  }

}
