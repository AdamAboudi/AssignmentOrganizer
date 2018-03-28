package AssignmentTypes;
import java.time.*;
//Class Assignment. Assignment Object with parameters for name, difficulty, completion time, due date, and priority. Power is position in queue.
public class Assignment extends superAssignment{

	private int difficulty;
	private int completionTime;

	// Public Constructor
	public Assignment(String nam, LocalDate due, int prior, int diff, int comp) {
		
		super(nam, due, prior);
		
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
			return (int) (this.difficulty  + this.completionTime + this.daysPow + this.priority * 3) ;
		}
		
		@Override
		public String toString() {
			return "Assignment "+ super.toString() + this.difficulty + " " + this.completionTime;
		}
		

}
