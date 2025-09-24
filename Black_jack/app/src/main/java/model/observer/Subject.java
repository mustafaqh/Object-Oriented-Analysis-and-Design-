package model.observer;

import java.util.ArrayList;

/**
 * abstract class.
 */
public abstract class Subject {
  private ArrayList<Observer> observer = new ArrayList<>();

  public void addObserver(Observer o) {
    observer.add(o);
  }

  /**
   * player notify observer.
   */
  public void notifyObserversPlayer() {
    for (Observer o : observer) {
      o.notifyPlayerNewCard();
    }
  }

  /**
   * dealer notify observer.
   */
  public void notifyObserversDelaer() {
    for (Observer o : observer) {
      o.notifyDealerNewCard();
    }
  }

}
