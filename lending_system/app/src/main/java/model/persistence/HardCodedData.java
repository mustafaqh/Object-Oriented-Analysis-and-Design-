package model.persistence;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import java.util.ArrayList;
import model.domain.Contract;
import model.domain.Item;
import model.domain.Member;

/**
 * Hardcoded data class.
 */
public class HardCodedData implements PersistenceInterFace {

  // Creating Hard Coded Members.
  Member m1 = new Member("Micky", "mouse", "micky.mouse@gmail.com", "0798126549", 0, "ADF123");
  Member m2 = new Member("Donald", "Duck", "donald.duck@hotmail.com", "0932406594", 0, "ERT998");
  Member m3 = new Member("Spider", "Man", "spider.man@yahoo.com", "0798761200", 0, "PLM432");
  Member m4 = new Member("Super", "Man", "Man.S@gmail.com", "0798761201", 0, "OKN678");
  Member m5 = new Member("Miny", "Mouse", "Miny@enigma.com", "123456790", 0, "ZAQ698");

  // Ctreating hard Coded Items.
  Item item1 = new Item("bicycle", "Toys", "a nice bmx bicycle to children over 12 year", 50, 0, "BMQ223");
  Item item2 = new Item("shovel", "Tools", "a tool with which to dig dirt", 10, 0, "ZXS123");
  Item item3 = new Item("need for speed", "Game", "car race game to play on pc", 25, 0, "OIU777");
  Item item4 = new Item("hocky bat", "Sport", "nice bat made from good material", 20, 0, "YUQ567");
  Item item5 = new Item("Assassin's creed", "Game", "nice fighting game over 15", 30, 0, "MNB009");
  Item item6 = new Item("Train", "Toys", "nice toy for childers which like trains", 15, 0, "THM766");
  Item item7 = new Item("Saws", "Tools", "A sharp Saws for wood", 25, 0, "QSD432");
  Item item8 = new Item("basket ball", "Sport", "nice ball", 20, 0, "BSK001");
  // creating hard coded contraacts.
  // Contract c1 = new Contract(m3, m1, item2, 5, 7);
  // Contract c2 = new Contract(m4, m5, item4, 0, 3);

  @CheckReturnValue
  @Override
  public ArrayList<Member> addMembers() {
    ArrayList<Member> theMembers = new ArrayList<>();
    m1.setCredits(100);
    theMembers.add(m1);
    m2.setCredits(0);
    theMembers.add(m2);
    m3.setCredits(100);
    theMembers.add(m3);
    m4.setCredits(100);
    theMembers.add(m4);
    m5.setCredits(200);
    theMembers.add(m5);
    return theMembers;
  }

  @Override
  public void additemToOwner() {
    m1.addOwnedItem(item1);
    m1.addOwnedItem(item2);
    m1.addOwnedItem(item8);
    m1.addOwnedItem(item7);
    m1.addOwnedItem(item6);
    item1.setOwner(m1);
    item2.setOwner(m1);
    item6.setOwner(m1);
    item7.setOwner(m1);
    item8.setOwner(m1);
    

    m3.addOwnedItem(item3);
    item3.setOwner(m3);

    m4.addOwnedItem(item5);
    item5.setOwner(m4);

    m5.addOwnedItem(item4);
    item4.setOwner(m5);
  }

  
  @Override
  public ArrayList<Contract> addHardContract() {
    Contract c1 = new Contract(m3, m1, item2, 5, 7);
    Contract c2 = new Contract(m4, m5, item4, 0, 3);
    ArrayList<Contract> theContracts = new ArrayList<>();
    theContracts.add(c1);
    theContracts.add(c2);
    return theContracts;
  }
}