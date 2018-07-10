package main;

import assignment.types.Assignment;
import assignment.types.Paper;
import assignment.types.Project;
import assignment.types.Reading;
import assignment.types.SuperAssignment;

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
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Organizer {

  private static Scanner scan = new Scanner(System.in);
  static HashMap<String, SuperAssignment> AssignmentMap = new HashMap<String, SuperAssignment>();
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
      for (SuperAssignment assignment : readAsArray) {
        AssignmentMap.put(assignment.getName(), assignment);
      }
      
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
          break;

        case "remove":
          removeFromList();
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

    ArrayList<SuperAssignment> assignmentList = 
        new ArrayList<SuperAssignment>(AssignmentMap.values());
    Collections.sort(assignmentList);
    Collections.reverse(assignmentList);
  }
  
  /**
   * Display current assignments on the list.
   */
  public static void showAssignments() {
    System.out.println("*********************************");
    ArrayList<SuperAssignment> assignmentList =
        new ArrayList<SuperAssignment>(AssignmentMap.values());
    if (assignmentList.size() == 0) {
      System.out.println("No current assignments");
    } else {
      System.out.println("Current Assignments:");
      for (int i = 0; i < assignmentList.size(); i++) {
        System.out.println(i + 1 + ") " + assignmentList.get(i).printByName());
      }
    }
  }

  /**
   *  Add a new assignment to the list.
   */
  public static void addToList() {
  
    boolean validType = false;
    
    while (!validType) {

      System.out.println("What type of assignment would you like to add? Options:"
          + " Assigment, Paper, Project, or Reading. Enter \"Return\" to go back.");
      String input = scan.nextLine();
      input = input.toLowerCase();

      switch (input) {

        case "return":
          validType = true;
          break;
          
        case "assignment":
          addAssignment();
          validType = true;
          break;
         
        case "project":  
          addProject();
          validType = true;
          break;

        case "reading":
          addReading();
          validType = true;
          break;
        
        case "paper":
          addPaper();
          validType = true;
          break;
             
        default:
          break;
      }
    }
    sortAssignments();
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
    
    boolean validEntry = false;

    while (!validEntry) {
      System.out.println("Enter the name of the assignment to remove."
          + " Enter \"Return\" to go back.");
      String input = scan.nextLine();
      input = input.toLowerCase();
      if (input.equals("return")) {
        validEntry = true;
        break;
      }
      
      if (AssignmentMap.containsKey(input)) {
        AssignmentMap.remove(input);
        validEntry = true;
        System.out.println(input + " removed");
      } else {
        System.out.println(input + " not found in AssignmentList");
      }
    }
    sortAssignments();
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
      SuperAssignment[] temp = (SuperAssignment[]) AssignmentMap.values().toArray(
          new SuperAssignment[AssignmentMap.size()]);
      String json = gson.toJson(temp);
      filex.write(json); 
    }    
  }
  
  /**
   * Checks for legal date (if date is before current date, legalDate is false).
   * @param due Attempted due date
   * @return boolean on legality of date
   */
  public static boolean legalDate(LocalDate due) {
  
    if (due.isBefore(LocalDate.now())) {
      return false;
    }
    return true; 
  }
  
  /**
   * Add a Project to the List.
   */
  public static void addProject() {
    
    String tmp;
    String[] parts;
    boolean validEntry = false;
    
    while (!validEntry) {
      
      System.out.println(
          "Please write the Project's name, due date(YYYY-MM-DD), priority, difficulty,"
              + " and partners(true/false) separated by spaces. "
              + "Enter \"Return\" to go back to main.");
      tmp = scan.nextLine();
      parts = tmp.split(" ");
      if (parts.length == 1) {
        if (parts[0].equals("return")) {
          validEntry = true;
          return;
        }
      }
      if (parts.length == 5 && legalDate(LocalDate.parse(parts[1]))) {
      
        AssignmentMap.put(parts[0], new Project("Project",
            parts[0],
            LocalDate.parse(parts[1]), 
            Integer.parseInt(parts[2]),
            Integer.parseInt(parts[3]), 
            Boolean.valueOf(parts[4])));   
        System.out.println("Project " + parts[0] + " added to list");
        validEntry = true;
      } else {
        System.out.println("Invalid Entry");
      }    
    }
  }
  
  /**
   * Add a Paper to the List.
   */
  public static void addPaper() {
    
    String tmp;
    String[] parts;
    boolean validEntry = false;
    
    while (!validEntry) {
      
      System.out.println(
          "Please write the Paper's name, due date(YYYY-MM-DD), priority, length in pages, "
              + "and estimated completion time separated by spaces. "
              + "Enter \"Return\" to go back to main.");
      tmp = scan.nextLine();
      parts = tmp.split(" ");
      if (parts.length == 1) {
        if (parts[0].equals("return")) {
          validEntry = true;
          return;
        }
      }
      if (parts.length == 5 && legalDate(LocalDate.parse(parts[1]))) {
      
        AssignmentMap.put(parts[0], new Paper("Project",
            parts[0],
            LocalDate.parse(parts[1]), 
            Integer.parseInt(parts[2]),
            Integer.parseInt(parts[3]), 
            Integer.parseInt(parts[4])));
        System.out.println("Paper " + parts[0] + " added to list");
        validEntry = true;

      } else {
        System.out.println("Invalid Entry");
      }    
    }
  }
  
  /**
   * Add an Assignment to the List.
   */
  public static void addAssignment() {
    
    String tmp;
    String[] parts;
    boolean validEntry = false;    
    
    while (!validEntry) {
      
      System.out.println(
          "Please write the Assignment's name, due date(YYYY-MM-DD), priority, difficulty,"
              + " and estimated completion time separated by spaces."
              + " Enter \"Return\" to go back to main.");
      tmp = scan.nextLine();
      parts = tmp.split(" ");
      if (parts.length == 1) {
        if (parts[0].equals("return")) {
          validEntry = true;
          return;
        }
      }
      if (parts.length == 5 && legalDate(LocalDate.parse(parts[1]))) {
      
        AssignmentMap.put(parts[0],new Assignment("Project",
            parts[0],
            LocalDate.parse(parts[1]), 
            Integer.parseInt(parts[2]),
            Integer.parseInt(parts[3]), 
            Integer.parseInt(parts[4])));
    
        System.out.println("Assignment " + parts[0] + " added to list");
        validEntry = true;

      } else {
        System.out.println("Invalid Entry");
      }
    }
  }
  
  
  /**
   * Add a Reading to the List.
   */
  public static void addReading() {
    
    String tmp;
    String[] parts;
    boolean validEntry = false;
   
    while (!validEntry) {
      
      System.out.println(
          "Please write the Reading's name, due date(YYYY-MM-DD), priority, length in pages,"
              + " and estimated completion time separated by spaces. "
              + "Enter \"Return\" to go back to main.");
      tmp = scan.nextLine();
      parts = tmp.split(" ");
      if (parts.length == 1) {
        if (parts[0].equals("return")) {
          validEntry = true;
          return;
        }
      }
      if (parts.length == 5 && legalDate(LocalDate.parse(parts[1]))) {
      
        AssignmentMap.put(parts[0], new Reading("Project",
            parts[0],
            LocalDate.parse(parts[1]), 
            Integer.parseInt(parts[2]),
            Integer.parseInt(parts[3]), 
            Integer.parseInt(parts[4])));
    
        System.out.println("Reading " + parts[0] + " added to list");
        validEntry = true;

      } else {
        System.out.println("Invalid Entry");
      }   
    }
  } 
}
