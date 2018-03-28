package AssignmentTypes;

import java.time.*;

//Class Reading. Reading Object with parameters for name, length, completion time, due date, and priority. Power is position in queue.
public class Reading extends superAssignment{

	private long length;
	private int completionTime;

	// Public Constructor
	public Reading(String nam, LocalDate due, int prior, long len, int comp) {
		
		super(nam, due, prior);
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
		public int readingPow() {
			return (int) (this.length  + this.completionTime + this.daysPow + this.priority * 3) ;
		}

		@Override
		public String toString() {
			return "Reading "+ super.toString() + this.length + " " + this.completionTime;
		}

}
