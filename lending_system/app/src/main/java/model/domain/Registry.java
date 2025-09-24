package model.domain;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.Random;

/**
 * Club class.
 */
public class Registry {
  private ArrayList<Member> registeredMembers = new ArrayList<>();
  private ArrayList<Item> allItems = new ArrayList<Item>();

  Random random = new Random();

  /**
   * adding a new member to the list.
   */
  public Member addNewMember(Member m) {
    if (!checkEmail(m.getEmail())) {
      throw new IllegalArgumentException("Invalid Email -already used");
    }
    if (!checkNum(m.getMobileNumber())) {
      throw new IllegalArgumentException("Invalid mobilenumber -already used");
    }
    if (!idValidchecker(m.getId())) {
      throw new IllegalArgumentException("Invalid id -already used");
    }
    registeredMembers.add(m);
    return m;
  }



  /**
   * Method to change last name.
   */
  public void changeMemberLn(String id, String ln) {
    for (Member m : registeredMembers) {
      if (m.getId().equals(id)) {
        m.setLastName(ln);
      }
    }
  }

  /**
   * Method to change email.
   */
  public void changeMemberEm(Member m, Registry r, String em) {
    if (checkEmail(m.getEmail())) {
      throw new IllegalArgumentException("Invalid Email -already used");
    }
    m.setEmail(r, em);
  }

  

  /**
   * Method to generate a unique id.

   * @return unique id.
   */
  public String idGenerator() {
    String alphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    String id = "";
    int length = 6;
    for (int i = 0; i < length; i++) {
      int ind = random.nextInt(alphaNumeric.length());
      char randomCharacter = alphaNumeric.charAt(ind);
      id += randomCharacter;
    }
    while (!idValidchecker(id)) {
      id = idGenerator();
    }
    return id;
  }

  /**
   * Checking if id is valid.
   */
  public boolean idValidchecker(String id) {
    for (Member m : registeredMembers) {
      for (Item i : getMembersItems()) {
        if (m.getId().equals(id) && i.getId().equals(id)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Method to check if the email is unique.
   */
  public boolean checkEmail(String email) {
    for (Member m : registeredMembers) {
      if (m.getEmail().equals(email)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Method to check if the number is unique.
   */
  public boolean checkNum(String num) {
    for (Member m : registeredMembers) {
      if (m.getMobileNumber().equals(num)) {
        return false;
      }
    }
    return true;
  }

  /**
   * returning all the registered members in the list.

   * @return members list.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We want this.")
  public Iterable<Member> showMem() {
    return registeredMembers;

  }

  /**
   * Method to delete a member.
   */
  public void deleteMember(Member m) {
    registeredMembers.remove(m);
  }

  /**
   * Method to find member id.
   */
  public boolean findMemberId(String theId) {
    for (Member m : registeredMembers) {
      if (m.getId().equals(theId)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Method to find member id.
   */
  public Member findMember(String theId) {
    for (Member m : registeredMembers) {
      if (m.getId().equals(theId)) {
        return m;
      }
    }
    return null;
  }

  /**
   * Method to return all items in the system.

   * @return all items.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We want this.")
  public Iterable<Item> getMembersItems() {
    allItems.clear();
    for (Member m : registeredMembers) {
      for (Item i : m.showItems()) {
        this.allItems.add(i);
      }
    }
    return allItems;
  }

  /**
   * finding an item id.
   */
  public boolean findItemId(String theId) {
    for (Item i : getMembersItems()) {
      if (i.getId().equals(theId)) {
        return true;
      }
    }
    return false;
  }

  /**
   * finding an item.
   */
  public Item findItem(String theId) {
    for (Item i : getMembersItems()) {
      if (i.getId().equals(theId)) {
        return i;
      }
    }
    return null;
  }

}
