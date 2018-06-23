package assignment.types;

import java.time.LocalDate;


/**
 * Class Assignment. Assignment Object with parameters for name, difficulty, 
 * completion time, due date, and priority. Power is position in queue.
 * 
 * @author Adam
 *
 */
public class Assignment extends SuperAssignment{

  private int difficulty;
  private int completionTime;

  /**
   *  Public Constructor.
   * @param nam name
   * @param due dueDate
   * @param prior priority
   * @param diff difficulty
   * @param comp completion time
   */
  public Assignment(String type, String nam, LocalDate due, int prior, int diff, int comp) {
    
    super(type, nam, due, prior);
    difficulty = diff;
    completionTime = comp;
    this.setPow(assignmentPow());

  }

  

  // Set Difficulty Parameter
  public void setDifficulty(int difficulty) {
    this.difficulty = difficulty;
  }

  // Return Difficulty Parameter
  public int getDifficulty() {
    return difficulty;
  }

  // Set Estimated completion Time
  public void setcompletionTime(int completionTime) {
    this.completionTime = completionTime;
  }

  // Return Estimated completion Time
  public int getcompletionTime() {
    return completionTime;
  }

  //Calculate power for an assignment
  public int assignmentPow() {
    return (int) (this.difficulty  + this.completionTime + this.daysPow + this.priority * 3);
  }
    
  @Override
    public String toString() {
    return this.type + " " + super.toString() + this.difficulty + " " + this.completionTime;
  }
    

}