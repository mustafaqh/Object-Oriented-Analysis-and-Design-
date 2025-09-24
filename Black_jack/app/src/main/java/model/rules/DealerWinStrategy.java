package model.rules;

import model.Dealer;
import model.Player;

/**
 * dealer is winner strategy that implement WinStrategy.
 */
public class DealerWinStrategy implements WinStrategy {

  @Override
  public boolean whoWin(Player player, Dealer dealer) {
    if (player.calcScore() > player.getMaxScore()) {
      return true;

    } else if (dealer.calcScore() > player.getMaxScore()) {
      return false;
    }

    return dealer.calcScore() >= player.calcScore();

  }

}
