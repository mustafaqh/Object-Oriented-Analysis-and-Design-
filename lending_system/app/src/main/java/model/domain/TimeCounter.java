package model.domain;

/**
 * TimeCounter class.
 */
public class TimeCounter {
  private int counter;

  public int setStartCounter() {
    counter = 0;
    return counter;
  }

  public int getCurrentDate() {
    return counter;
  }

  public void incrementCounter(int days) {
    counter += days;
  }

}
