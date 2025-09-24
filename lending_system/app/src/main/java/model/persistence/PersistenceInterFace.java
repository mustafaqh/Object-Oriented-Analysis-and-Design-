package model.persistence;

import java.util.ArrayList;
import model.domain.Contract;
import model.domain.Member;

/**
 * presistence interface.
 */
public interface PersistenceInterFace {

  public ArrayList<Member> addMembers();

  public void additemToOwner();

  public ArrayList<Contract> addHardContract();
}
