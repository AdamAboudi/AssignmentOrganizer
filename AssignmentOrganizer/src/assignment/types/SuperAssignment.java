package assignment.types;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SuperAssignment implements Comparable<SuperAssignment> {

  private String name;
  private String type;
  private LocalDate dueDate;
  private long daysLeft;
  private int priority;
  private int pow;
  private double daysPow;

  /**
   *  Public Constructor.
   * 
   * @param nam Name
   * @param due DueDate
   * @param prior Priority
   * @param typ Type of Assignment
   */
  public SuperAssignment(String typ, String nam, LocalDate due, int prior) {
    name = nam;
    type = typ;
    dueDate = due;
    setPriority(prior);
    setDaysPow(0);
    daysLeft = daysLeft();
    pow = 0;   
  }
    
  /**
   * Calculate days until due.
   * 
   * @return Days Remaining
   */
  public long daysLeft() {
    
    LocalDate now = LocalDate.now();
    long dura = ChronoUnit.DAYS.between(now, dueDate);
    double temp = (double) dura;
    setDaysPow(Math.pow(2, (-temp + 9)));
    return dura;
  }
    
  //Return number of days left
  public long getDaysLeft() {
    return daysLeft;   
  }

  /**
   *  Set Due Date.
   * 
   * @param dueDate DueDate
   */
  public void setDueOn(LocalDate dueDate) {
    this.dueDate = dueDate;
    daysLeft = daysLeft();
  }

  // Return Due Date
  public LocalDate getDueOn() {
    return dueDate;
  }

  // Set priority
  public void setPriority(int priority) {
    this.priority = priority;
  }

  // Return priority
  public int getPriority() {
    return priority;
  }

  //Get Power
  public int getPow() {
    return pow;
  }

  //Return Power
  public void setPow(int pow) {
    this.pow = pow;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public  String toString() {
    return this.name + " " + this.dueDate + " " + this.priority + " ";
  }

  @Override
  public int compareTo(SuperAssignment o) {
    return -(pow - o.getPow());
  }
  
  //Print by name only
  public String printByName() {
    return this.name;
  }

  public double getDaysPow() {
    return daysPow;
  }

  public void setDaysPow(double daysPow) {
    this.daysPow = daysPow;
  }
}
