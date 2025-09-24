package view;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Scanner;

/**
 * Data counter class.
 */
public class DateCounterConsole {
  Scanner scan;

  /**
   * Data counter contructure.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We want this.")
  public DateCounterConsole(Scanner scan) {
    this.scan = scan;
  }

  /**
   * Increment date.
   */
  public int incrementDate() {
    System.out.println("Enter number of days you want to advance: ");
    int days = scan.nextInt();
    scan.nextLine();

    while (days <= 1) {
      System.out.println("Enter number of days you want to advance: ");
      days = scan.nextInt();
      scan.nextLine();
    }
    return days;

  }

  /**
   * Getting current date.
   */
  public void getCurrentDate(int timeCounter) {
    System.out.println("The current time counter is: " + timeCounter);
  }

  /**
   * Chcking the date counter.
   */
  public int checkDateCounter(int timeCounter) {
    getCurrentDate(timeCounter);
    System.out.println("1- If you want to advance the counter enter");
    String input = scan.nextLine();

    if (input.equals("1")) {
      return incrementDate();
    }
    return 0;
  }



}
