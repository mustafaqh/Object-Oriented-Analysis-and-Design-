package model.rules;


import model.Dealer;
import model.Player;


class InternationalNewGameStrategy implements NewGameStrategy {

  public boolean newGame(Dealer dealer, Player player) {
    // Card.Mutable c;

    // c = deck.getCard();
    // c.show(true);
    // player.dealCard(c);

    // c = deck.getCard();
    // c.show(true);
    // dealer.dealCard(c);

    // c = deck.getCard();
    // c.show(true);
    // player.dealCard(c);

    dealer.getNewCard(player, true);
    dealer.getNewCard(dealer, true);
    dealer.getNewCard(player, true);
    
    return true;

  }
}