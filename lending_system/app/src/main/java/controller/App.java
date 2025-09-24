package controller;

import java.util.Scanner;
import model.domain.Registry;
import model.domain.TimeCounter;
import model.persistence.HardCodedData;
import view.DateCounterConsole;
import view.ItemConsole;
import view.MemberConsole;


/**
 * Responsible for staring the application.
 */

public class App {

  /**
   * Application starting point.

   * @param args command line arguments.
   * 
   */

  public static void main(String[] args) {

    view.MemberConsole view = new MemberConsole(new Scanner(System.in, "UTF8"));

    view.ItemConsole viewItem = new ItemConsole(new Scanner(System.in, "UTF8"));

    view.DateCounterConsole viewDate = new DateCounterConsole(new Scanner(System.in, "UTF8"));

    model.domain.Registry club = new Registry();

    model.domain.TimeCounter timeCounter = new TimeCounter();
    model.persistence.PersistenceInterFace hard = new HardCodedData();

    controller.MainMenu mainM = new MainMenu(club, view, viewItem, viewDate, timeCounter, hard);

    mainM.runMainMenu();

  }

}
