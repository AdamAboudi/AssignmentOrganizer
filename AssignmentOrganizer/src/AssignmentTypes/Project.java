package AssignmentTypes;

import java.time.LocalDate;

//Class Project. Project Object with parameters for name, difficulty, partners, due date, and priority. Power is position in queue.
public class Project extends superAssignment{

	private int difficulty;
	private boolean partners;

	// Public Constructor
	public Project(String nam, LocalDate due, int prior, int diff, boolean part) {
		
		super(nam, due, prior);
		difficulty = diff;
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
	
	
	//Calculate power for a project
	public int projectPow() {
		if(partners) {
			 return (int) (this.difficulty  + 10 + this.daysPow + this.priority);
			
		}
		else {
			 return (int) (this.difficulty  + this.daysPow + this.priority);

		}
	}
	
	@Override
	public String toString() {
		return "Project "+ super.toString() + this.difficulty + " " + this.partners;
	}

}
