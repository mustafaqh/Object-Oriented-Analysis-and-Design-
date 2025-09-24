package model.domain.validation;

import java.util.regex.Pattern;

/**
 * NumberValid class.
 */
public class NumberValid {

  /**
   * Checking if the number is an integer.
   */
  public boolean checkNumber(String number) {
    String matchString = "[+0123456789]*";
    boolean matching = Pattern.matches(matchString, number);
    if (matching) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Checking if the number is already used.
   */
  public boolean numberUsedCheker(model.domain.Registry n, String number) {
    boolean used = !n.checkNum(number);
    if (used) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * check if the number is integer.
   */
  public boolean intChecker(String number) {
    while (true) {
      try {
        Integer.parseInt(number);
        return true;
      } catch (NumberFormatException e) {
        return false;
      }
    }
  }

}
