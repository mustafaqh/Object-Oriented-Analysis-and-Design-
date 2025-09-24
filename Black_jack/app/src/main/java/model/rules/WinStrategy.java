package model.rules;

import model.Dealer;
import model.Player;

/**
 * WinStrategy interface.
 */
public interface WinStrategy {
  public boolean whoWin(Player player, Dealer dealer);
}
