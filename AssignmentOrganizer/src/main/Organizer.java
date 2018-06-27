package main;

import assignment.types.*;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;





public class Organizer {

  private static Scanner scan = new Scanner(System.in);
  private static ArrayList<SuperAssignment> AssignmentList = new ArrayList<SuperAssignment>();

  private static final Gson gson = new Gson(); 
  
  static Path assignmentPath;
  
  /**
   * Main method.
   * @param args arguments (none)
   * @throws IOException e
   */

  public static void main(String[] args) throws IOException {
    File file = new File("assignments.json");
    file.createNewFile();
    assignmentPath =  Paths.get(file.getAbsolutePath());
    
    JsonReader reader = new JsonReader(new FileReader(assignmentPath.toString()));
    SuperAssignment[] readAsArray = gson.fromJson(reader, SuperAssignment[].class);
    
    if (readAsArray != null) { 
      AssignmentList = new ArrayList<SuperAssignment>(Arrays.asList(readAsArray));
      sortAssignments();
      showAssignments();
    }
    
    runLoop();  

  
  }
  
  /**
   * Run the main loop of the program.
   */
  public static void runLoop() {
    
    while (true) {
      System.out.println("*********************************");
      System.out.println("What would you like to do? Options: Add, Remove, Quit");
      
      String input = scan.nextLine();
      input = input.toLowerCase();
      switch (input) {

        case "add":
          addToList();
          sortAssignments();
          break;

        case "remove":
          removeFromList();
          sortAssignments();
          break;

        case "quit":
          try {
            updateFile();
          } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          } catch (IOException e) {
            e.printStackTrace();
          }
          
          System.exit(0);
          break;

        default:
          System.out.println("Invalid Entry");
          break;

      }
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
    System.out.println("*********************************");
    if (AssignmentList.size() == 0) {
      System.out.println("No current assignments");
    } else {
      System.out.println("Current Assignments:");
      for (int i = 0; i < AssignmentList.size(); i++) {
        System.out.println(i + 1 + ") " + AssignmentList.get(i).printByName());
      }
    }
  }

  /**
   *  Add a new assignment to the list.
   */
  public static void addToList() {
    String tmp;
    String[] parts;
    System.out.println("What type of assignment would you like to add? Options:"
        + " Assigment, Paper, Project, or Reading. Use Return to go back.");
    String input = scan.nextLine();
    input = input.toLowerCase();

    boolean validEntry = false;
    
    while (!validEntry) {
      
    
      switch (input) {

        case "return":
          validEntry = true;
          break;
          
        case "assignment":
          System.out.println(
                "Please write the Assignment's name, due date(YYYY-MM-DD), priority, difficulty,"
                    + " and estimated completion time separated by spaces."
                    + " Enter \"Return\" to go back to main.");
          tmp = scan.nextLine();
          parts = tmp.split(" ");
          if (parts.length == 1) {
            if (parts[0].equals("return")) {
              validEntry = true;
              break;
            }
          }
          if (parts.length != 5) {
            System.out.println("Invalid Entry");
            break;
          }
          
          validEntry = true;
          AssignmentList.add(new Assignment("Assignment",
              parts[0], 
              LocalDate.parse(parts[1]),
              Integer.parseInt(parts[2]),
              Integer.parseInt(parts[3]),
              Integer.parseInt(parts[4])));
        
          System.out.println("Assignment " + parts[0] + " added to list");
          break;

        case "project":
          System.out.println(
              "Please write the Project's name, due date(YYYY-MM-DD), priority, difficulty,"
                  + " and partners(true/false) separated by spaces. "
                  + "Enter \"Return\" to go back to main.");
          tmp = scan.nextLine();
          parts = tmp.split(" ");
          if (parts.length == 1) {
            if (parts[0].equals("return")) {
              validEntry = true;
              break;
            }
          }
          if (parts.length != 5) {
            System.out.println("Invalid Entry");
            break;
          }
          
          validEntry = true;
          AssignmentList.add(new Project("Project",
              parts[0],
              LocalDate.parse(parts[1]), 
              Integer.parseInt(parts[2]),
              Integer.parseInt(parts[3]), 
              Boolean.valueOf(parts[4])));
          System.out.println("Project " + parts[0] + " added to list");
          break;

        case "reading":
          System.out.println(
              "Please write the Reading's name, due date(YYYY-MM-DD), priority, length in pages,"
                  + " and estimated completion time separated by spaces. "
                  + "Enter \"Return\" to go back to main.");
          tmp = scan.nextLine();
          parts = tmp.split(" ");
          if (parts.length == 1) {
            if (parts[0].equals("return")) {
              validEntry = true;
              break;
            }
          }
          if (parts.length != 5) {
            System.out.println("Invalid Entry");
            break;
          }
          
          validEntry = true;
          AssignmentList.add(new Reading("Reading",
              parts[0], 
              LocalDate.parse(parts[1]),
              Integer.parseInt(parts[2]),
              Integer.parseInt(parts[3]), 
              Integer.parseInt(parts[4])));
          System.out.println("Reading " + parts[0] + " added to list");
          break;

        case "paper":
          System.out.println(
              "Please write the Paper's name, due date(YYYY-MM-DD), priority, length in pages, "
                  + "and estimated completion time separated by spaces. "
                  + "Enter \"Return\" to go back to main.");
          tmp = scan.nextLine();
          parts = tmp.split(" ");
          if (parts.length == 1) {
            if (parts[0].equals("return")) {
              validEntry = true;
              break;
            }
          }
          if (parts.length != 5) {
            System.out.println("Invalid Entry");
            break;
          }
        
          validEntry = true;
          AssignmentList.add(new Paper("Paper",
              parts[0], 
              LocalDate.parse(parts[1]), 
              Integer.parseInt(parts[2]),
              Integer.parseInt(parts[3]), 
              Integer.parseInt(parts[4])));
          System.out.println("Paper " + parts[0] + " added to list");
          break;

        default:
          System.out.println("Invalid Entry");
          System.out.println("What type of assignment would you like to add? Options:"
              + " Assigment, Paper, Project, or Reading. Use Return to go back.");
          input = scan.nextLine();
          input = input.toLowerCase();
          break;
      }
    }
    showAssignments();
    try {
      updateFile();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
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
    
    showAssignments();
    try {
      updateFile();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
  }

  /**
   *  Writes the Current AssignmentList to Assignments.json
   * @throws UnsupportedEncodingException e
   * @throws FileNotFoundException e
   * @throws IOException e
   */
  public static void updateFile() throws UnsupportedEncodingException, 
      FileNotFoundException, IOException {
 
    try (FileWriter filex = new FileWriter(assignmentPath.toString())) {
      SuperAssignment[] asArray = AssignmentList.toArray(
          new SuperAssignment[AssignmentList.size()]);
      String json = gson.toJson(asArray);
      filex.write(json);
  
    }
     
  }
}
