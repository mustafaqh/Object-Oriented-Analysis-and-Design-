package model.domain.validation;

import java.util.regex.Pattern;

/**
 * EmailValid class.
 */
public class EmailValid {

  /**
   * Checking the email.
   */
  public boolean emailChecker(String email) {
    String matchingPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    boolean matching = Pattern.matches(matchingPattern, email);
    if (!matching) {
      return false;    
    }
    return true;
  }

  /**
   * Checking if the email is already used.
   */
  public boolean emailUsedChecker(model.domain.Registry n, String email) {
    boolean used = !n.checkEmail(email);
    if (used) {
      return false;
    }
    return true;
  } 
}
