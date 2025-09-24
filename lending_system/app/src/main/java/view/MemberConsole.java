package view;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Scanner;
import model.domain.Item;
import model.domain.Member;

/**
 * Member console.
 */
public class MemberConsole {
  Scanner scan;
  String invalidInfo = "    Invalid informaion, try again: ";

  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We want this.")
  public MemberConsole(Scanner scan) {
    this.scan = scan;
  }

  /**
   * Enumeration.
   */
  public static enum Choice {
    AddNewMember,
    DeleteMember,
    ShowAMemberInfo,
    Lists,
    Listv,
    Change,
    ItemChanging,
    showAllItems,
    InvalidInput,
    AdvanceDays,
    Exit, showMembersV, ShowMembers,
  }

  /**
   * Enumeration.
   */
  public static enum Choices {
    FirstName,
    LastName,
    Email,
    Phonenumber,
    Back,
  }

  /**
   * Menu.
   */
  public Choice showMenu() {
    System.out.println("");
    System.out.println("    ****        Main application menu:         **** ");
    System.out.println("Time- to advance current date");
    System.out.println("Add- to add new member");
    System.out.println("Delete- to delete a member");
    System.out.println("Change- to edit a member");
    System.out.println("Select- to look at a specific members full information.");
    System.out.println("List S- to list all members in a simple way ");
    System.out.println("List V- to list all members in a verbose way ");
    System.out.println("Items -to show all items in lending system");
    System.out.println("Item -to jump to member items menu");
    System.out.println("Exit- to exit");
    String input = scan.nextLine().toLowerCase();
    if (input.equals("add")) {
      return Choice.AddNewMember;
    } else if (input.equals("select")) {
      return Choice.ShowAMemberInfo;
    } else if (input.equals("list s")) {
      return Choice.Lists;
    } else if (input.equals("list v")) {
      return Choice.Listv;
    } else if (input.equals("delete")) {
      return Choice.DeleteMember;
    } else if (input.equals("change")) {
      return Choice.Change;
    } else if (input.equals("items")) {
      return Choice.showAllItems;
    } else if (input.equals("item")) {
      return Choice.ItemChanging;
    } else if (input.equals("time")) {
      return Choice.AdvanceDays;
    } else if (input.equals("exit")) {
      return Choice.Exit;
    } else {
      return Choice.InvalidInput;
    }
  }

  /**
   * Edit menu.
   */
  public Choices editMenu() {
    System.out.println("Choose between these options:");
    System.out.println("1.First name");
    System.out.println("2.Last name");
    System.out.println("3.Email");
    System.out.println("4.Phonenumber");
    System.out.println("Any key- to go back");
    String input = scan.nextLine().toLowerCase();
    if (input.equals("1")) {
      return Choices.FirstName;
    }
    if (input.equals("2")) {
      return Choices.LastName;
    }
    if (input.equals("3")) {
      return Choices.Email;
    }
    if (input.equals("4")) {
      return Choices.Phonenumber;
    }
    return Choices.Back;
  }

  /**
   * Printing members information simple way.
   */
  public void printMembersInfoS(Iterable<model.domain.Member> registeredMembers) {
    for (model.domain.Member m : registeredMembers) {
      System.out.println("Member   Full Name: " + m.getFirstName() + " "
          + m.getLastName() + "\n \t Email: " + m.getEmail() + "\n \t Credits: "
          + m.getCredits()
          + "\n \t Number owned items: " + m.numberOwnedItems()
          + "\n");
    }
  }

  /**
   * Printing members information verbose way.
   */
  public void printMembersInfoV(Iterable<model.domain.Member> registeredMembers, view.ItemConsole viewItem,
      int currentTime) {
    for (model.domain.Member m : registeredMembers) {
      System.out.println("Member  Full Name: " + m.getFirstName() + " "
          + m.getLastName() + "\n \tEmail: " + m.getEmail());
      viewItem.printaMembersItemsInfo(m, m.showItems(), currentTime);
    }
  }

  /**
   * Printing members id and full name.
   */
  public void printMembeId(Iterable<model.domain.Member> registeredMembers) {
    for (model.domain.Member m : registeredMembers) {
      System.out.println("Member   full name: " + m.getFirstName() + " "
          + m.getLastName() + "\n \t Id: "
          + m.getId()
          + "\n");
    }
  }

  /**
   * printing a member information.
   */
  public void printaMemberInfo(model.domain.Registry n) {
    String id = getMemberId(n);
    Member m = n.findMember(id);
    System.out.println("Member   First name: " + m.getFirstName()
        + "\n \t Last name: " + m.getLastName()
        + "\n \t Email: " + m.getEmail()
        + "\n \t Phonenumber: " + m.getMobileNumber()
        + "\n \t Creation date: " + m.getDate()
        + "\n \t Number owned items: " + m.numberOwnedItems()
        + "\n \t Credits: " + m.getCredits()
        + "\n \t Id: " + m.getId());
    for (Item i : m.showItems()) {
      System.out.println("         Items:   " + "       \n \t  Name: " + i.getName()
          + "           \n \t    Id: " + i.getId());
    }
  }

  /**
   * Entering a new member.
   */
  public Member enterNewMember(model.domain.Registry n, int timeCounter) {
    System.out.println("Enter member first Name..");
    final String firstName = nameChecker(scan);
    System.out.println("Enter member last Name..");
    final String lastName = nameChecker(scan);
    System.out.println("Enter email..");
    String email = emailChecker(n, scan);
    System.out.println("Enter phone Number..");
    String phoneNumber = numberChecker(n, scan, 5);
    System.out.println("member added");
    return new Member(firstName, lastName, email, phoneNumber, timeCounter,
        n.idGenerator());
  }

  /**
   * Changing a member name.
   */
  public String changeMemberName() {
    System.out.println("Enter member new name: ");
    String name = nameChecker(scan);
    System.out.println("Name changed");
    return name;
  }

  /**
   * Changing a member email.
   */
  public String changeMemberEm(model.domain.Registry n) {
    System.out.println("Enter member new email: ");
    String email = emailChecker(n, scan);
    System.out.println("Email changed");
    return email;
  }

  /**
   * Changing a member phonenumber.
   */
  public String changeMemberNum(model.domain.Registry n) {
    System.out.println("Enter member new phonenumber: ");
    String num = numberChecker(n, scan, 5);
    System.out.println("Phonenumber changed");
    return num;
  }

  /**
   * method to return name after checking.

   * @return name.
   */
  public String nameChecker(Scanner scan) {
    model.domain.validation.StringValid str = new model.domain.validation.StringValid();
    String input = scan.nextLine();
    while (!str.nameChecker(input)) {
      System.out.println(invalidInfo);
      input = scan.nextLine();
    }
    return input;
  }

  /**
   * method to return string after checking.

   * @return string.
   */
  public String stringLengthChecker(Scanner scan, int length) {
    model.domain.validation.StringValid str = new model.domain.validation.StringValid();
    String input = scan.nextLine();
    while (!str.stringLengthChecker(input, length)) {
      System.out.println(invalidInfo);
      input = scan.nextLine();
    }
    return input;
  }

  /**
   * method to return number after checking.

   * @return number.
   */
  public String numberChecker(model.domain.Registry n, Scanner scan, int length) {
    model.domain.validation.NumberValid str = new model.domain.validation.NumberValid();
    model.domain.validation.StringValid st = new model.domain.validation.StringValid();
    String input = scan.nextLine();
    while (!str.checkNumber(input) || !st.stringLengthChecker(input, length) || !str.numberUsedCheker(n, input)) {
      if (!str.checkNumber(input)) {
        System.out.println("invaild Number, number should be only digits !");
        input = scan.nextLine();
      } else if (!st.stringLengthChecker(input, length)) {
        System.out.println("number must be more than 5 digits");
        input = scan.nextLine();
      } else {
        System.out.println("number is already used, please enter a new number");
        input = scan.nextLine();
      }
    }
    return input;
  }

  /**
   * method to return email after checking.

   * @return email
   */
  public String emailChecker(model.domain.Registry n, Scanner scan) {
    String input = scan.nextLine();
    model.domain.validation.EmailValid email = new model.domain.validation.EmailValid();
    while (!email.emailChecker(input)) {
      System.out.println("Invalid email, try again: ");
      input = scan.nextLine();
    }
    while (!email.emailUsedChecker(n, input)) {
      System.out.println("This email is already used, try again: ");
      input = scan.nextLine();
    }
    return input;
  }

  /**
   * method to return number after checking.

   * @return number.
   */
  public int costChecker(Scanner scan) {
    model.domain.validation.NumberValid num = new model.domain.validation.NumberValid();
    int value = 0;
    String input = scan.nextLine();
    while (!num.intChecker(input)) {
      System.out.println("Please add an integer number: ");
      input = scan.nextLine();
    }
    value = Integer.parseInt(input);
    return value;
  }

  /**
   * Adding a new item to a member.
   */
  public Item enterOwnedItem(model.domain.Registry n, model.domain.Member m, int timeCounter) {
    System.out.println("Enter item Name: ");
    final String name = nameChecker(scan);
    System.out.println("1.Tool\n" + "2.Vehicle\n" + "3.Game\n" + "4.Toy\n" + "5.Sport\n" + "6.Other\n"
        + "select one of altrenatives ");
    // final String category = nameChecker(scan);
    String catagorIn = scan.nextLine();
    String category = "";
    if (catagorIn.equals("1")) {
      category = "Tool";
    } else if (catagorIn.equals("2")) {
      category = "Vehicle";
    } else if (catagorIn.equals("3")) {
      category = "Game";
    } else if (catagorIn.equals("4")) {
      category = "Toy";
    } else if (catagorIn.equals("5")) {
      category = "Sport";
    } else {
      System.out.println("enter catagory :");
      category = nameChecker(scan);
    }
    System.out.println("Enter items short description: ");
    String shortDescription = stringLengthChecker(scan, 3);
    System.out.println("Enter a cost per day to lend the item:");
    int cost = costChecker(scan);
    System.out.println("Item added");
    return new Item(name, category, shortDescription, cost, timeCounter, n.idGenerator());
  }

  /**
   * returning member id.
   */
  public String getMemberId(model.domain.Registry n) {
    System.out.println("Enter member Id");
    String id = scan.nextLine();
    while (!n.findMemberId(id)) {
      System.out.println("Invalid Id- try again");
      id = scan.nextLine();
    }
    return id;
  }

  /**
   * returning member.
   */
  public Member getMember(model.domain.Registry n) {
    System.out.println("Enter member Id");
    String id = scan.nextLine();
    while (!n.findMemberId(id)) {
      System.out.println("Invalid Id- try again");
      id = scan.nextLine();
    }
    ;
    return n.findMember(id);
  }

  /**
   * Changing an item name.
   */
  public String changeItemName(Member m, Item i) {
    if (!(i.getOwner().equals(m))) {
      System.out.println("You cannot change, because it's owned by another member ");
      return null;
    }
    System.out.println("Enter new name: ");
    String name = scan.nextLine();
    System.out.println("Name changed");
    return name;
  }

  /**
   * Changing an item cost.
   */
  public int changeItemCost(Member m, Item i) {
    if (!(i.getOwner().equals(m))) {
      System.out.println("You cannot change, because it's owned by another member ");
      return 0;
    }
    System.out.println("Enter new cost: ");
    int cost = costChecker(scan);
    System.out.println("Cost changed");
    return cost;
  }

  /**
   * Changing an item catagory.
   */
  public String changeItemCatagory(Member m, Item i) {
    if (!(i.getOwner().equals(m))) {
      System.out.println("You cannot change, because it's owned by another member ");
      return null;
    }
    System.out.println("Enter new catagory: ");
    String catagory = nameChecker(scan);
    System.out.println("Catagory changed");
    return catagory;
  }

  /**
   * Changing an item shortdescription.
   */
  public String changeItemShortdescription(Member m, Item i) {
    if (!(i.getOwner().equals(m))) {
      System.out.println("You cannot change, because it's owned by another member ");
      return null;
    }
    System.out.println("Enter new shortdescription: ");
    String shortdescription = scan.nextLine();
    System.out.println("Shortdescription changed");
    return shortdescription;
  }

}
