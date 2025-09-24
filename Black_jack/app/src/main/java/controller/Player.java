package controller;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import model.Game;
import model.observer.Observer;
import view.Choice;
import view.View;



/**
 * Scenario controller for playing the game.
 */
public class Player implements Observer {

  private Game game;
  private View view;

  /**
   * player constructor.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We want this.")
  public Player(Game game, View view) {
    this.game = game;
    this.view = view;
    this.game.addObserver(this);
  }


  /**
   * Runs the play use case.

   * @return True as long as the game should continue.
   */
  public boolean play() {
    view.displayWelcomeMessage();
    view.displayDealerHand(game.getDealerHand(), game.getDealerScore());
    view.displayPlayerHand(game.getPlayerHand(), game.getPlayerScore());

  
    if (game.isGameOver()) {
      view.displayGameOver(game.isDealerWinner());
    }

    Choice c = view.choices();
    if (c == Choice.Play) {
      game.newGame();
    }
    if (c == Choice.Hit) {
      game.hit();
    }
    if (c == Choice.Stand) {
      game.stand();
    }
    return c != Choice.Quit;
  }

  @Override
  public void notifyPlayerNewCard() {
    view.displayNotificationPlayer(game.getPlayerHand());
  }


  @Override
  public void notifyDealerNewCard() {
    view.displayNotificationDealer(game.getDealerHand());
    
  }

}