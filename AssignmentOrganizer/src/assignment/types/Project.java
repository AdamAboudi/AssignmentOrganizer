package assignment.types;

import java.time.LocalDate;

/**
 * Class Project. Project Object with parameters for name, difficulty,
 *  partners, due date, and priority. Power is position in queue.
 * 
 * @author Adam
 *
 */
public class Project extends SuperAssignment {

  private int difficulty;
  private boolean partners;

  /**
   *  Public Constructor.
   *  
   * @param nam Name
   * @param due DueDate
   * @param prior Priority
   * @param diff Difficulty
   * @param part Partners (boolean)
   */
  public Project(String type, String nam, LocalDate due, int prior, int diff, boolean part) {
    
    super(type, nam, due, prior);
    difficulty = diff;
    type = "Project";
    partners = part;
    this.setPow(projectPow());
  }
  
  // Set difficulty Parameter
  public void setdifficulty(int difficulty) {
    this.difficulty = difficulty;
  }

  // Return difficulty Parameter
  public int getdifficulty() {
    return difficulty;
  }


  //Set Partners
  public void setPartners(boolean part) {
    this.partners = part; 
  }
  
  public boolean getPartners() {
    return this.partners;
  }
  
  
  /**
   * Calculate power for a project.
   */
  public int projectPow() {
    if (partners) {
      return (int) (this.difficulty  + 10 + this.getDaysPow() + this.getPriority());  
    } else {
      return (int) (this.difficulty  + this.getDaysPow() + this.getPriority());
    }
  }
  
  @Override
  public String toString() {
    return this.getType() + " " + super.toString() + this.difficulty + " " + this.partners;
  }
}
