package controller;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import model.domain.Contract;
import model.domain.Item;
import model.domain.Member;
import view.ItemConsole;
import view.MemberConsole;

/**
 * Main menu class.
 */
public class MainMenu {
  private model.domain.Registry reg;
  private view.MemberConsole viewMember;
  private view.ItemConsole viewItem;
  private view.DateCounterConsole viewDate;
  private model.domain.TimeCounter timeCounter;
  private model.persistence.PersistenceInterFace hard;
  private model.domain.Item item;

  public MainMenu() {
  }

  /**
   * Main menu constructure.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We want this.")
  public MainMenu(model.domain.Registry reg, view.MemberConsole viewMember, view.ItemConsole viewItem,
      view.DateCounterConsole viewDate, model.domain.TimeCounter timeCounter,
      model.persistence.PersistenceInterFace hard) {
    this.reg = reg;
    this.viewMember = viewMember;
    this.viewItem = viewItem;
    this.viewDate = viewDate;
    this.timeCounter = timeCounter;
    this.hard = hard;
    timeCounter.setStartCounter();
  }

  /**
   * runing the mainmenu method.
   */
  public void runMainMenu() {
    boolean running = true;
    loadingHardCodedData();
    while (running) {
      MemberConsole.Choice ch = viewMember.showMenu();
      switch (ch) {
        case AdvanceDays:
          timeCounter.incrementCounter(viewDate.checkDateCounter(timeCounter.getCurrentDate()));
          break;
        case AddNewMember:
          enterNewMember();
          break;
        case Lists:
          viewMember.printMembersInfoS(reg.showMem());
          break;
        case ShowAMemberInfo:
          viewMember.printMembeId(reg.showMem());
          viewMember.printaMemberInfo(reg);
          break;
        case DeleteMember:
          viewMember.printMembeId(reg.showMem());
          reg.deleteMember(viewMember.getMember(reg));
          break;
        case showAllItems:
          viewItem.printAllItemsInfo(reg);
          break;
        case ItemChanging:
          viewMember.printMembeId(reg.showMem());
          itemMenu();
          break;
        case Change:
          viewMember.printMembeId(reg.showMem());
          editMenu();
          break;
        case Listv:
          viewMember.printMembersInfoV(reg.showMem(), viewItem, timeCounter.getCurrentDate());
          break;
        case Exit:
          running = false;
          break;
        default:
        case InvalidInput:
          viewMember.showMenu();
          break;
      }
    }
  }

  /**
   * Edit member menu.
   */
  public void editMenu() {
    boolean running = true;
    Member editedM = viewMember.getMember(reg);
    while (running) {
      MemberConsole.Choices ch = viewMember.editMenu();
      switch (ch) {
        case FirstName:
          changeFirstName(editedM);
          break;
        case LastName:
          changeLastName(editedM);
          break;
        case Email:
          changeEm(editedM, reg);
          break;
        case Phonenumber:
          changeMobileNumber(editedM, reg);
          break;
        case Back:
          running = false;
          break;
        default:
      }
    }
  }

  /**
   * Item menu.
   */
  public void itemMenu() {
    boolean running = true;
    Member editedM = viewMember.getMember(reg);
    while (running) {
      ItemConsole.ItemEvent choise = viewItem.itemMenu();
      switch (choise) {
        case lendItem:
          viewItem.printAllItemsInfo(reg);
          model.domain.Item i = viewItem.getItem(reg);
          lendnewItem(editedM, i);
          break;
        case addItem:
          enterNewOwnedItem(editedM);
          break;
        case deleteItem:
          viewItem.printaMembersItems(editedM, editedM.showItems(), timeCounter.getCurrentDate());
          deleteItem(editedM);
          break;
        case changeItem:
          viewMember.printMembeId(reg.showMem());
          editItemMenu();
          break;
        case viewItem:
          viewItem.printaMembersItems(editedM, editedM.showItems(), timeCounter.getCurrentDate());
          break;
        case viewAnItem:
          viewItem.printaMembersItems(editedM, editedM.showItems(), timeCounter.getCurrentDate());
          viewItem.printanItemInfo(reg, editedM);
          break;
        case exit:
          running = false;
          break;
        default:
      }
    }
  }

  /**
   * Edit an item menu.
   */
  public void editItemMenu() {
    boolean running = true;
    Member editedM = viewMember.getMember(reg);
    viewItem.printaMembersItems(editedM, editedM.showItems(), timeCounter.getCurrentDate());
    Item i = viewItem.getItem(reg);
    while (running) {
      ItemConsole.ItemChoice ch = viewItem.editItemMenu();
      switch (ch) {
        case chanegeName:
          changeItemName(editedM, i);
          break;
        case changeCost:
          i.setCost(viewMember.changeItemCost(editedM, i));
          break;
        case changeCatagory:
          changeItemCatacory(editedM, i);
          break;
        case changeShortdescription:
          changeItemDesc(editedM, i);
          break;
        case Exit:
          running = false;
          break;
        default:
      }
    }
  }

  /**
   * add new member.
   */
  public void enterNewMember() {
    try {
      model.domain.Member newMember = viewMember.enterNewMember(reg, timeCounter.getCurrentDate());
      reg.addNewMember(newMember);
    } catch (Exception e) {
      viewItem.errorPrinter(e);
    }
  }

  /**
   * changing member first name.
   */
  public void changeFirstName(Member m) {
    try {
      m.setFirstName(viewMember.changeMemberName());
    } catch (Exception e) {
      viewItem.errorPrinter(e);
    }
  }

  /**
   * changing member last name.
   */
  public void changeLastName(Member m) {
    try {
      m.setLastName(viewMember.changeMemberName());
    } catch (Exception e) {
      viewItem.errorPrinter(e);
    }
  }

  /**
   * changing member email.
   */
  public void changeEm(Member m, model.domain.Registry r) {
    try {
      m.setEmail(r, viewMember.changeMemberEm(r));
    } catch (Exception e) {
      viewItem.errorPrinter(e);
    }
  }

  /**
   * changing member mobile number.
   */
  public void changeMobileNumber(Member m, model.domain.Registry r) {
    try {
      m.setMopileNumber(m, r, viewMember.changeMemberNum(r));
    } catch (Exception e) {
      viewItem.errorPrinter(e);
    }
  }

  /**
   * changing item name.
   */
  public void changeItemName(Member m, Item i) {
    try {
      i.setName(viewMember.changeItemName(m, i));
    } catch (Exception e) {
      viewItem.errorPrinter(e);
    }
  }

  /**
   * changing item catagory.
   */
  public void changeItemCatacory(Member m, Item i) {
    try {
      i.setCatagory(viewMember.changeItemCatagory(m, i));
    } catch (Exception e) {
      viewItem.errorPrinter(e);
    }
  }

  /**
   * changing item short description.
   */
  public void changeItemDesc(Member m, Item i) {
    try {
      i.setShortDescription(viewMember.changeItemShortdescription(m, i));
    } catch (Exception e) {
      viewItem.errorPrinter(e);
    }
  }

  /**
   * add new item.
   */
  public void enterNewOwnedItem(Member m) {
    try {
      model.domain.Item newItem = viewMember.enterOwnedItem(reg, m, timeCounter.getCurrentDate());
      m.addOwnedItem(newItem);
      newItem.setOwner(m);
    } catch (Exception e) {
      viewItem.errorPrinter(e);
    }
  }

  /**
   * add new item.
   */
  public void lendnewItem(Member lender, Item i) {
    try {
      model.domain.Contract newC = viewItem.newContract(reg, lender, i, timeCounter.getCurrentDate());
      if (newC != null) {
        i.addNewContract(newC);
      }
    } catch (Exception e) {
      viewItem.errorPrinter(e);
    }
  }

  /**
   * Deleting an item.
   */
  public void deleteItem(Member owner) {
    model.domain.Item deletedItem = viewItem.deleteItem(reg, owner, timeCounter.getCurrentDate());
    if (deletedItem != null) {
      deletedItem.deleteBookedContracts(timeCounter.getCurrentDate());
      owner.deleteOwnedItem(deletedItem);
    }
  }

  /**
   * Loading hard coded data.
   */

  public void loadingHardCodedData() {
    hard.addMembers();
    for (Member m : hard.addMembers()) {
      reg.addNewMember(m);

    }
    hard.additemToOwner();
    hard.addHardContract();
    for (Contract c : hard.addHardContract()) {
      item = c.getItem();
      item.addNewContract(c);
    }
  }

}
