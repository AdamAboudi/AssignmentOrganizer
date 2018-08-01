package main.java.assignment.types;

import java.time.LocalDate;

/**
 * Class Paper. Paper Object with parameters for name, length, 
 * completion time, due date, and priority. Power is position in queue.
 * 
 * @author Adam
 *
 */
public class Paper extends SuperAssignment {

  private long length;
  private int completionTime;

  /**
   *  Public Constructor for Paper.
   *  
   * @param nam name
   * @param due dueDate
   * @param prior Priority
   * @param len Length
   * @param comp Completion Time
   */
  public Paper(String type, String nam, LocalDate due, int prior, long len, int comp) {
      
    super(type, nam, due, prior);
    type = "Paper";  
    length = len;
    completionTime = comp;
    setPriority(prior);
    this.setPow(paperPow());
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
   
  //Calculate power for a paper
  private int paperPow() {
    return (int) (this.length   + this.completionTime + this.getDaysPow() + this.getPriority() * 3);
  }
   
  @Override
   public String toString() {
    return this.getType() + " " + super.toString() + this.length + " " + this.completionTime;
  }
}
