package model.domain.validation;

import java.util.regex.Pattern;

/**
 * StringValid class.
 */
public class StringValid {

  /**
   * Checking the if the name is string.
   */
  public boolean nameChecker(String str) {
    boolean matching = Pattern.matches("^[A-Za-z]\\w{2,15}$", str);
    if (!matching) {
      return false;
    }
    return true;
  }

  /**
   * Checking the length.
   */
  public boolean stringLengthChecker(String str, int length) {
    if (str.length() < length) {
      return false;
    } else {
      return true;
    }

  }
}
