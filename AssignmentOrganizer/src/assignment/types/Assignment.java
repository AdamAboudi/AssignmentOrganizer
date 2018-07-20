package assignment.types;

import java.time.LocalDate;

/**
 * Class Assignment. Assignment Object with parameters for name, difficulty, 
 * completion time, due date, and priority. Power is position in queue.
 * 
 * @author Adam
 *
 */
public class Assignment extends SuperAssignment {

  private int difficulty;
  private int completionTime;

  /**
   *  Public Constructor for Assignment.
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

  public void setDifficulty(int difficulty) {
    this.difficulty = difficulty;
  }

  public int getDifficulty() {
    return difficulty;
  }

  public void setcompletionTime(int completionTime) {
    this.completionTime = completionTime;
  }

  public int getcompletionTime() {
    return completionTime;
  }

  private int assignmentPow() {
    return (int) (this.difficulty  + this.completionTime + this.getDaysPow() 
      + this.getPriority() * 3);
  }
    
  @Override
    public String toString() {
    return this.getType() + " " + super.toString() + this.difficulty + " " + this.completionTime;
  }
}
