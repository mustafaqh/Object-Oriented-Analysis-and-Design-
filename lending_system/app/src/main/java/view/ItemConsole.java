package view;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Scanner;
import model.domain.Contract;
import model.domain.Item;
import model.domain.Member;

/**
 * Item console class.
 */
public class ItemConsole {
  Scanner scan;

  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We want this.")
  public ItemConsole(Scanner scan) {
    this.scan = scan;

  }

  /**
   * itemevent enum.
   */
  public static enum ItemEvent {
    addItem,
    deleteItem,
    changeItem,
    viewItem,
    viewAnItem,
    exit, viewAllItems, lendItem,
  }

  /**
   * Item choice enum.
   */
  public static enum ItemChoice {
    chanegeName,
    changeCost,
    changeCatagory,
    changeShortdescription,
    Exit,

  }

  /**
   * Item menu.
   */
  public ItemEvent itemMenu() {
    System.out.println("      *** Item Menu ***   ");
    System.out.println("1- to add item");
    System.out.println("2- to lend an item from the lending system");
    System.out.println("3- to delete an item");
    System.out.println("4- to change an item information");
    System.out.println("5- to view all items for a member");
    System.out.println("6- to view an item for a member");
    System.out.println("Any key- to go back");

    String input = scan.nextLine().toLowerCase();
    if (input.equals("1")) {
      return ItemEvent.addItem;
    }
    if (input.equals("2")) {
      return ItemEvent.lendItem;
    }
    if (input.equals("3")) {
      return ItemEvent.deleteItem;
    }
    if (input.equals("4")) {
      return ItemEvent.changeItem;
    }
    if (input.equals("5")) {
      return ItemEvent.viewItem;
    }
    if (input.equals("6")) {
      return ItemEvent.viewAnItem;
    }
    return ItemEvent.exit;
  }

  /**
   * Edit item menu.
   */
  public ItemChoice editItemMenu() {
    System.out.println("Choose between these options:");
    System.out.println("1.Name");
    System.out.println("2.Cost");
    System.out.println("3.Catagory");
    System.out.println("4.Shortdescription");
    System.out.println("Any key- to go back");
    String input = scan.nextLine().toLowerCase();
    if (input.equals("1")) {
      return ItemChoice.chanegeName;
    }
    if (input.equals("2")) {
      return ItemChoice.changeCost;
    }
    if (input.equals("3")) {
      return ItemChoice.changeCatagory;
    }
    if (input.equals("4")) {
      return ItemChoice.changeShortdescription;
    }
    return ItemChoice.Exit;
  }

  /**
   * Print the error message by throw from model.
   */
  public void errorPrinter(Exception e) {
    System.out.println("Error message: " + e.getMessage());
  }

  /**
   * printing items info.
   */
  public void printaMembersItemsInfo(model.domain.Member m, Iterable<model.domain.Item> ownedItems, int currentTime) {
    System.out.println("\tItems are owned by " + m.getFirstName() + " " + m.getLastName());
    if (m.numberOwnedItems() == 0) {
      System.out.println("\tNo items");
    }
    for (Item i : ownedItems) {
      System.out.println("\tItem:" + "\n \t   Name: " + i.getName()
          + "\n \t   Cost: " + i.getCost()
          + "\n \t   Catagory: " + i.getCatagory()
          + "\n \t   Creation date: " + i.getCreationDate()
          + "\n \t   Short description: " + i.getShortDescription()
          + "\n \t   Owner: " + i.getOwner().getFirstName() + " " + i.getOwner().getLastName()
          + "\n \t   Id: " + i.getId()
          + "\n");
      printLender(m, i, currentTime);

    }
  }


  /**
   * printing items info for a member with only name and id.
   */
  public void printaMembersItems(model.domain.Member m, Iterable<model.domain.Item> ownedItems, int currentTime) {
    System.out.println("\tItems are owned by " + m.getFirstName() + " " + m.getLastName());
    if (m.numberOwnedItems() == 0) {
      System.out.println("\tNo items");
    }
    for (Item i : ownedItems) {
      System.out.println("\tItem:" + "\n \t   Name: " + i.getName()
          + "\n \t   Id: " + i.getId()
          + "\n");
    }
  }

  /**
   * printing the item lender.
   */
  public void printLender(model.domain.Member m, Item i, int currentTime) {
    Contract c = i.getCurrentContract(currentTime);
    if (c != null) {
      System.out.println("\t   This item " + i.getName() + " is lended to " + c.getLender().getFirstName());
      System.out.println("\t   The start date from lending the item is " + c.getStartDate());
      System.out.println("\t   The end date to lend the item is " + c.getEndDate()
          + "\n");
    }
  }

  /**
   * printing an item info.
   */
  public void printanItemInfo(model.domain.Registry reg, model.domain.Member m) {
    if (m.numberOwnedItems() == 0) {
      System.out.println("\tThis member has no Items");
    } else {
      System.out.println("Enter item id: ");
      String id = scan.nextLine();
      for (Item i : m.showItems()) {
        if (i.getId().equals(id)) {
          System.out.println("Item     Name: " + i.getName()
              + "\n \t Catagory: " + i.getCatagory() + "\n \t Cost: " + i.getCost() + "\n \t Creation date: "
              + i.getCreationDate()
              + "\n \t Short description: " + i.getShortDescription()
              + "\n \t Id: " + i.getId()
              + "\n \t Owner: " + i.getOwner().getFirstName() + " " + i.getOwner().getLastName()
              + "\n");
          printanItemContract(i);
        }
      }
    }
  }

  /**
   * printing an item info.
   */
  public void printanItemInfo(Item i) {
    System.out.println("Item     Name: " + i.getName()
        + "\n \t Catagory: " + i.getCatagory()
        + "\n \t Cost: " + i.getCost()
        + "\n \t Creation date: " + i.getCreationDate()
        + "\n \t Short description: " + i.getShortDescription()
        + "\n \t Id: " + i.getId()
        + "\n \t Owner: " + i.getOwner().getFirstName() + " " + i.getOwner().getLastName()
        + "\n");
  }

  /**
   * printing an item contract.
   */
  public void printanItemContract(Item i) {
    for (Contract c : i.showContract()) {
      System.out.println("Contrct:" + "  Start date: " + c.getStartDate()
          + "\n \t End date: " + c.getEndDate()
          + "\n \t Lender: " + c.getLender().getFirstName() + " " + c.getLender().getLastName()
          + "\n");
    }
  }

  /**
   * getting an item by item id.
   */
  public Item getItem(model.domain.Registry reg) {
    System.out.println("Enter item Id");
    String id = scan.nextLine();
    while (!reg.findItemId(id)) {
      System.out.println("No such item with this id");
      System.out.println("Try again");
      id = scan.nextLine();
    }
    return reg.findItem(id);
  }

  /**
   * returning item id.
   */
  public String getItemId(model.domain.Registry reg) {
    System.out.println("Enter Item Id");
    String id = scan.nextLine();
    while (!reg.findItemId(id)) {
      System.out.println("Invalid Id- try again");
      id = scan.nextLine();
    }
    return id;
  }

  /**
   * printing all items full info.
   */
  public void printAllItemsInfo(model.domain.Registry reg) {
    for (Item i : reg.getMembersItems()) {
      printanItemInfo(i);
    }
  }



  /**
   * making a new contract.
   */
  public Contract newContract(model.domain.Registry reg, Member lender, Item i, int timeCounter) {
    if (i.getOwner().equals(lender)) {
      System.out.println("This item is owned by YOU! ");
      return null;
    }
    System.out.println("Enter start date of lending the item more than current date: " + timeCounter);
    int startDate = scan.nextInt();
    scan.nextLine();
    while (startDate < timeCounter) {
      System.out.println("Enter start date of lending the item more than current date: " + timeCounter);
      startDate = scan.nextInt();
      scan.nextLine();
    }
    System.out.println("Enter end date of lending the item: ");
    final int endDate = scan.nextInt();
    scan.nextLine();
    if (startDate > endDate) {
      System.out.println("The end date should be higher than start date");
      return null;
    }
    // if (!i.isAvailableInPeriod(startDate, endDate)) {
    //   System.out.println("This item is unavailable during this period ");
    //   return null;
    // }
    if (!(lender.creditsCheck(lender, i, (endDate - startDate + 1)))) {
      System.out.println("You have not enough credits");
      return null;
    }
    return new Contract(lender, i.getOwner(), i, startDate, endDate);

  }

  /**
   * Delete item.
   */
  public Item deleteItem(model.domain.Registry reg, model.domain.Member m, int currentTime) {
    if (m.numberOwnedItems() == 0) {
      System.out.println("This member has no items");
      return null;
    }
    Item i = getItem(reg);
    if (!i.getOwner().equals(m)) {
      System.out.println("You Can Not delete " + i.getName()
          + " it is owned by another member");
      return null;
    }
    if (!(i.getOwner().deleteItemAbility(i, currentTime))) {
      System.out.println("You Can Not delete " + i.getName()
          + " item because it is already lended !");
      return null;
    }
    return i;
  }
}
