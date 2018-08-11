package main.java.assignment.types;

import java.time.LocalDate;

/**
 * Class Reading. Reading Object with parameters for name, length,
 * completion time, due date, and priority. Power is position in queue.
 *
 * @author Adam
 *
 */
public class Reading extends SuperAssignment {

  private long length;
  private int completionTime;

  /**
   *  Public Constructor for Reading.
   *  
   * @param nam Name
   * @param due DueDate
   * @param prior Priority
   * @param len Length
   * @param comp Completion Time
   */
  public Reading(String type, String nam, LocalDate due, int prior, long len, int comp) {
    
    super(type, nam, due, prior);
    type = "Reading";
    length = len;
    completionTime = comp;
    this.setPow(readingPow());
  }

  // Set Estimated completion Time
  public void setcompletionTime(int completionTime) {
    this.completionTime = completionTime;
  }

  // Return Estimated completion Time
  public int getcompletionTime() {
    return completionTime;
  }

  public long getLength() {
    return length;
  }


  public void setLength(int length) {
    this.length = length;
  }

  //Calculate power for a project
  private int readingPow() {
    return (int) (this.length  + this.completionTime + this.getDaysPow() + this.getPriority() * 3);
  }
  
}
