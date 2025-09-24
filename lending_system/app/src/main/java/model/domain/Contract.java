package model.domain;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Contract class.
 */
public class Contract {
  private int startDate;
  private int endDate;
  private Member owner;
  private Member lender;
  private Item item;

  /**
   * Item Contract.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We want this.")
  public Contract(Member lender, Member owner,
      Item item, int startDate, int endDate) {
    if (owner.equals(lender)) {
      throw new IllegalArgumentException("Invalid lender- item owned by lender");
    }
    if (endDate < startDate) {
      throw new IllegalArgumentException("Invalid date");
    }
    if (!item.isAvailableInPeriod(startDate, endDate)) {
      throw new IllegalArgumentException("Invalid date- item is lended");
    }
    if (!(lender.creditsCheck(lender, item, (endDate - startDate + 1)))) {
      throw new IllegalArgumentException("Invalid lended- lender has not enough credits");
    }
    this.lender = lender;
    this.owner = owner;
    this.item = item;
    this.startDate = startDate;
    this.endDate = endDate;

  }

  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We want this.")
  public Member getOwner() {
    return owner;
  }

  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We want this.")
  public Member getLender() {
    return lender;
  }

  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We want this.")
  public Item getItem() {
    return item;
  }

  public int getStartDate() {
    return startDate;
  }

  public int getEndDate() {
    return endDate;
  }

}
