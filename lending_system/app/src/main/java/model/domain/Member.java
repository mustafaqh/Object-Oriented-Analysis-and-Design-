package model.domain;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;

/**
 * Member class.
 */
public class Member {
  protected String firstName;
  protected String lastName;
  protected String email;
  protected String mobileNumber;
  private String id;
  private int creationDate;
  protected int credits;
  private ArrayList<Item> ownedItems = new ArrayList<>();

  /**
   * Member constructure.
   */
  public Member(String firstName, String lastName, String email, String mobileNumber, int creationDate, String id) {
    model.domain.validation.StringValid str = new model.domain.validation.StringValid();
    model.domain.validation.EmailValid eml = new model.domain.validation.EmailValid();
    model.domain.validation.NumberValid num = new model.domain.validation.NumberValid();
    if (!str.nameChecker(firstName)) {
      throw new IllegalArgumentException("Invalid Firstname");
    }
    if (!str.nameChecker(lastName)) {
      throw new IllegalArgumentException("Invalid Lastname");
    }
    if (!eml.emailChecker(email)) {
      throw new IllegalArgumentException("Invalid Email");
    }
    if ((!num.checkNumber(mobileNumber)) || (!str.stringLengthChecker(mobileNumber, 5))) {
      throw new IllegalArgumentException("Invalid mobile number");
    }
    this.firstName = firstName;
    this.lastName = lastName;
    this.mobileNumber = mobileNumber;
    this.id = id;
    this.creationDate = creationDate;
    this.email = email;
  }

  /**
   * Method return the current date.

   * @return the date
   */
  public int getDate() {
    return creationDate;
  }

  /**
   * returning the first name of the member.

   * @return first name.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Set first name.
   */
  public void setFirstName(String firstName) {
    model.domain.validation.StringValid str = new model.domain.validation.StringValid();
    if (!str.nameChecker(firstName)) {
      throw new IllegalArgumentException("Invalid Name");
    }
    this.firstName = firstName;
  }

  /**
   * returning the last name of the member.

   * @return last name.
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Set last name.
   */
  public void setLastName(String lastName) {
    model.domain.validation.StringValid str = new model.domain.validation.StringValid();
    if (!str.nameChecker(lastName)) {
      throw new IllegalArgumentException("Invalid Name");
    }
    this.lastName = lastName;
  }

  /**
   * returning the email of the member.

   * @return email.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Set email.
   */
  public void setEmail(Registry r, String email) {
    model.domain.validation.EmailValid eml = new model.domain.validation.EmailValid();
    if (!eml.emailChecker(email)) {
      throw new IllegalArgumentException("Invalid Email");
    }
    if (!eml.emailUsedChecker(r, email)) {
      throw new IllegalArgumentException("Invalid Email-already used");
    }

    this.email = email;
  }

  /**
   * returning the phonenumber of the member.

   * @return phonenumber.
   */
  public String getMobileNumber() {
    return mobileNumber;
  }

  /**
   * Set mobile number.
   */
  public void setMopileNumber(Member m, Registry r, String mopileNumber) {
    model.domain.validation.StringValid str = new model.domain.validation.StringValid();
    model.domain.validation.NumberValid numb = new model.domain.validation.NumberValid();
    if (!numb.checkNumber(mopileNumber) || !str.stringLengthChecker(mopileNumber, 5)) {
      throw new IllegalArgumentException("Invalid mobilenumber");
    }
    if (!numb.numberUsedCheker(r, mopileNumber)) {
      throw new IllegalArgumentException("Invalid mobilenumber-already used");
    }

    this.mobileNumber = mopileNumber;
  }

  /**
   * returning the id of the member.

   * @return id.
   */
  public String getId() {
    return id;
  }

  /**
   * Adding a new item to the list.
   */
  public void addOwnedItem(Item i) {
    ownedItems.add(i);
    i.setOwner(this);
    credits += 100;
  }

  /**
   * returning credits of the member.

   * @return credits.
   */
  public int getCredits() {
    return credits;
  }

  /**
   * returning owned items list.

   * @return owned item list.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We want this.")
  public Iterable<Item> showItems() {
    return ownedItems;
  }

  /**
   * returning the number of owned items.

   * @return owned items number.
   */
  public int numberOwnedItems() {
    int size = 0;
    size = ownedItems.size();
    return size;
  }

  /**
   * deleting an item.
   */
  public void deleteOwnedItem(Item i) {
    ownedItems.remove(i);
  }

  /**
   * changing credits of the member.
   * 
   */
  public void decreaseCredits(int x) {
    this.credits = this.credits - x;
  }

  /**
   * changing credits of the member.
   * 
   */
  public void increaseCredits(int x) {
    this.credits = credits + x;
  }

  /**
   * checking if the member has enough credits.
   */
  public boolean creditsCheck(Member lender, Item i, int days) {
    if (lender.getCredits() >= (i.getCost() * days)) {
      return true;
    }
    return false;
  }

  /**
   * Deleting a only a bookd item.
   */
  public boolean deleteItemAbility(Item i, int currentTime) {
    if (i.alreadyLended(currentTime)) {
      return false;
    }
    return true;
  }

  /**
   * Deleting item.
   */
  public void deleteItem(Item i, int currentTime) {
    if (deleteItemAbility(i, currentTime)) {
      i.getOwner().deleteOwnedItem(i);
    }
  }

  public void setCredits(int credits) {
    this.credits = credits;
  }
}