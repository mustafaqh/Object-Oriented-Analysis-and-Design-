package model.domain;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;

/**
 * Item class.
 */
public class Item {
  private String category;
  private String name;
  private String shortDescription;
  private int cost;
  private int creationDate;
  private Member owner;
  private String id;

  private ArrayList<Contract> contracts = new ArrayList<>();

  /**
   * Item constructure.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We want this.")
  public Item(String name, String category, String shortDescription, int cost, int creationDate, String id) {
    model.domain.validation.StringValid str = new model.domain.validation.StringValid();
    if (!str.stringLengthChecker(name, 3)) {
      throw new IllegalArgumentException("Invalid item name");
    }
    if (!str.nameChecker(category)) {
      throw new IllegalArgumentException("Invalid item catagory");
    }
    if (!str.stringLengthChecker(shortDescription, 3)) {
      throw new IllegalArgumentException("Invalid item description");
    }
    this.category = category;
    this.name = name;
    this.shortDescription = shortDescription;
    this.cost = cost;
    this.id = id;
    this.creationDate = creationDate;
  }

  /**
   * returning the owner of the item.

   * @return owner.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We want this.")
  public Member getOwner() {
    return owner;
  }

  /**
   * Items owner.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We want this.")
  public void setOwner(Member m) {
    this.owner = m;
  }

  /**
   * returning the name of the item.

   * @return name.
   */
  public String getName() {
    return name;
  }

  /**
   * Set name.
   */
  public void setName(String name) {
    model.domain.validation.StringValid str = new model.domain.validation.StringValid();
    if (!str.stringLengthChecker(name, 3)) {
      throw new IllegalArgumentException("Invalid item name");
    }
  }

  /**
   * returning the ctegory of the item.

   * @return category.
   */
  public String getCatagory() {
    return category;
  }

  /**
   * Set catagory.
   */
  public void setCatagory(String catagory) {
    model.domain.validation.StringValid str = new model.domain.validation.StringValid();
    if (!str.nameChecker(category)) {
      throw new IllegalArgumentException("Invalid item catagory");
    }
    this.category = catagory;
  }

  /**
   * returning short description of the item.

   * @return name.
   */
  public String getShortDescription() {
    return shortDescription;
  }

  /**
   * Set short description.
   */
  public void setShortDescription(String shortDescription) {
    model.domain.validation.StringValid str = new model.domain.validation.StringValid();
    if (!str.nameChecker(shortDescription)) {
      throw new IllegalArgumentException("Invalid item short description");
    }
    this.shortDescription = shortDescription;
  }

  /**
   * returning the price of the item.

   * @return cost.
   */
  public int getCost() {
    return cost;
  }

  /**
   * Set cost.
   */
  public void setCost(int cost) {
    this.cost = cost;
  }

  /**
   * returning creation date of the item.

   * @return creation date.
   */
  public int getCreationDate() {
    return creationDate;
  }

  /**
   * returning the id of the item.

   * @return id.
   */
  public String getId() {
    return id;
  }

  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We want this.")
  public Iterable<Contract> showContract() {
    return contracts;
  }

  /**
   * Checking if the item is available during a preiod of time.
   */
  public boolean isAvailableInPeriod(int start, int end) {
    for (Contract contract : contracts) {
      if ((start >= contract.getStartDate() && start <= contract.getEndDate())
          || (end >= contract.getStartDate() && end <= contract.getEndDate())
          || (start <= contract.getStartDate() && end >= contract.getEndDate())) {
        return false;
      }
    }
    return true;
  }

  /**
   * Checking if the item is taken.
   */
  public boolean isTaken(Contract contract, int time) {
    if (time == contract.getStartDate()
        || time == contract.getEndDate()
        || time < contract.getStartDate() && time > contract.getEndDate()) {
      return true;
    }
    return false;
  }

  /**
   * Checking if the item is already lended.
   */
  public boolean alreadyLended(int currentTime) {
    for (Contract contract : contracts) {
      if (currentTime == contract.getStartDate()
          || currentTime == contract.getEndDate()
          || currentTime < contract.getStartDate() && currentTime > contract.getEndDate()) {
        return true;
      }
    }
    return false;
  }

  /**
   * Adding a new contract.
   */
  public void addNewContract(Contract c) {
    int days = c.getEndDate() - c.getStartDate() + 1;
    c.getOwner().increaseCredits(c.getItem().getCost() * days);
    c.getLender().decreaseCredits(c.getItem().getCost() * days);
    contracts.add(c);
  }

  /**
   * Getting the current contract.
   */
  public Contract getCurrentContract(int currentTime) {
    for (Contract c : contracts) {
      if ((isTaken(c, currentTime))) {
        return c;
      }
    }
    return null;
  }

  /**
   * Deleting a contract for a booked item.
   */
  public void deleteBookedContracts(int currentTime) {
    for (Contract c : contracts) {
      if (!(isTaken(c, currentTime)) && c.getStartDate() > currentTime) {
        int days = c.getEndDate() - c.getStartDate() + 1;
        c.getLender().increaseCredits(this.getCost() * days);
        c.getOwner().decreaseCredits(days * this.getCost());
      }
    }
  }

}
