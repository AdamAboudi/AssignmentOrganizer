package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import assignment.types.*;

import java.io.FileNotFoundException;

public class Organizer {

  private static Scanner scan = new Scanner(System.in);
  private static ArrayList<SuperAssignment> AssignmentList = new ArrayList<SuperAssignment>();

  /**
   * Main method.
   * @param args arguments (none)
   * @throws IOException e
   */
  public static void main(String[] args) throws IOException {

    File assignments = new File("Assignments.txt");
    assignments.createNewFile();

    try (BufferedReader br = new BufferedReader(new FileReader(assignments))) {
      String line;

      if ((line = br.readLine()) == null) {
        System.out.println("No current assignments");
      } else {
        while ((line = br.readLine()) != null) {
          String[] parts = line.split(" ");

          switch (parts[0]) {

            case "Assignment":
              AssignmentList.add(new Assignment(parts[1],
                  LocalDate.parse(parts[2]),
                  Integer.parseInt(parts[3]),
                  Integer.parseInt(parts[4]), 
                  Integer.parseInt(parts[5])));
              break;

            case "Project":
              AssignmentList.add(new Project(parts[1], 
                  LocalDate.parse(parts[2]), 
                  Integer.parseInt(parts[3]),
                  Integer.parseInt(parts[4]), 
                  Boolean.valueOf(parts[5])));
              break;

            case "Reading":
              AssignmentList.add(new Reading(parts[1], 
                  LocalDate.parse(parts[2]), 
                  Integer.parseInt(parts[3]),
                  Integer.parseInt(parts[4]), 
                  Integer.parseInt(parts[5])));
              break;

            case "Paper":
              AssignmentList.add(new Paper(parts[1],
                  LocalDate.parse(parts[2]), 
                  Integer.parseInt(parts[3]),
                  Integer.parseInt(parts[4]), 
                  Integer.parseInt(parts[5])));
              break;

            default:
              System.out.println("Invalid Text File Entry");
              break;
          }

        }
        sortAssignments();
        showAssignments();
      }

    }

    System.out.println("*********************************");

    System.out.println("What would you like to do? Options: Add, Remove, Quit, Show");

    while (true) {
      String input = scan.nextLine();
      input = input.toLowerCase();
      switch (input) {

        case "add":
          addToList();
          sortAssignments();
          break;

        case "show":
          showAssignments();
          break;

        case "remove":
          removeFromList();
          sortAssignments();
          break;

        case "quit":
          updateTxtFile();
          System.exit(0);
          break;

        default:
          System.out.println("Invalid Entry");
          break;

      }
      System.out.println("What would you like to do? Options: Add, Remove, Quit, Show");
    }
  }

  /**
   *  Sorts Assignments (descending) based on power.
   */
  public static void sortAssignments() {

    Collections.sort(AssignmentList);
    Collections.reverse(AssignmentList);
  }
  
  /**
   * Display current assignments on the list.
   */
  public static void showAssignments() {
    System.out.println("Current Assignments:");
    for (int i = 0; i < AssignmentList.size(); i++) {
      System.out.println(i + 1 + ") " + AssignmentList.get(i).printByName());
    }
  }

  /**
   *  Add a new assignment to the list.
   */
  public static void addToList() {
    String tmp;
    String[] parts;
    System.out.println("What type of assignment would you like to add?");
    String input = scan.nextLine();
    input = input.toLowerCase();

    switch (input) {

      case "assignment":
        System.out.println(
            "Please write the Assignment's name, due date(YYYY-MM-DD), priority, difficulty,"
            + " and estimated completion time separated by spaces.");
        tmp = scan.nextLine();
        parts = tmp.split(" ");
        AssignmentList.add(new Assignment(parts[0], 
            LocalDate.parse(parts[1]),
            Integer.parseInt(parts[2]),
            Integer.parseInt(parts[3]),
            Integer.parseInt(parts[4])));
        break;

      case "project":
        System.out.println(
            "Please write the Project's name, due date(YYYY-MM-DD), priority, difficulty,"
            + " and partners(true/false) separated by spaces.");
        tmp = scan.nextLine();
        parts = tmp.split(" ");
        AssignmentList.add(new Project(parts[0],
            LocalDate.parse(parts[1]), 
            Integer.parseInt(parts[2]),
            Integer.parseInt(parts[3]), 
            Boolean.valueOf(parts[4])));
        break;

      case "reading":
        System.out.println(
            "Please write the Reading's name, due date(YYYY-MM-DD), priority, length in pages,"
            + " and estimated completion time separated by spaces.");
        tmp = scan.nextLine();
        parts = tmp.split(" ");
        AssignmentList.add(new Reading(parts[0], 
            LocalDate.parse(parts[1]),
            Integer.parseInt(parts[2]),
            Integer.parseInt(parts[3]), 
            Integer.parseInt(parts[4])));
        break;

      case "paper":
        System.out.println(
            "Please write the Paper's name, due date(YYYY-MM-DD), priority, length in pages, "
            + "and estimated completion time separated by spaces.");
        tmp = scan.nextLine();
        parts = tmp.split(" ");
        AssignmentList.add(new Paper(parts[0], 
            LocalDate.parse(parts[1]), 
            Integer.parseInt(parts[2]),
            Integer.parseInt(parts[3]), 
            Integer.parseInt(parts[4])));
        break;

      default:
        System.out.println("Invalid Entry");
        break;
    }
  }

  /**
   *  Remove an assignment from the list.
   */
  public static void removeFromList() {
    System.out.println("What is the name of the assignment you would like to remove?");
    String input = scan.nextLine();
    input = input.toLowerCase();
    boolean found = false;

    for (int i = 0; i < AssignmentList.size(); i++) {
      if (AssignmentList.get(i).getName().toLowerCase().equals(input)) {
        AssignmentList.remove(i);
        found = true;
        System.out.println(input + " removed");
      }
    }
    if (!found) {
      System.out.println(input + " not found in AssignmentList");
    }
  }

  /**
   *  Writes the Current AssignmentList to Assignments.txt
   * @throws UnsupportedEncodingException e
   * @throws FileNotFoundException e
   * @throws IOException e
   */
  public static void updateTxtFile() throws UnsupportedEncodingException, 
      FileNotFoundException, IOException {
    try (Writer writer = new BufferedWriter(new OutputStreamWriter(
          new FileOutputStream("Assignments.txt"), "utf-8"))) {
      for (int i = 0; i < AssignmentList.size(); i++) {
        writer.write(AssignmentList.get(i).toString());
        writer.write(System.getProperty("line.separator"));

      }
    }
  }
}
