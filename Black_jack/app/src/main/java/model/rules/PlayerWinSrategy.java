package model.rules;

import model.Dealer;
import model.Player;

/**
 * player is winner strategy that implements WinStrategy.
 */
public class PlayerWinSrategy implements WinStrategy {

  @Override
  public boolean whoWin(Player player, Dealer dealer) {
    if (dealer.calcScore() > player.getMaxScore()) {
      return false;
    } else if (player.calcScore() > player.getMaxScore()) {
      return true;
    } else if (player.calcScore() == dealer.calcScore()) {
      return false;
    }
    return true;

  }

}
