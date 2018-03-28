package AssignmentTypes;
import java.time.*;
import java.time.temporal.ChronoUnit;

public class superAssignment implements Comparable<superAssignment> {

	public String name;
	public LocalDate dueDate;
	public long daysLeft;
	public int priority;
	public int pow;
	public double daysPow;

	// Public Constructor
	public superAssignment(String nam, LocalDate due, int prior) {
		name = nam;
		dueDate = due;
		priority = prior;
		daysPow = 0;
		daysLeft = DaysLeft();
		pow = 0;
		
	}
	
	
	//Calculate days until due
	public long DaysLeft() {
		
		LocalDate now = LocalDate.now();
		long dura = ChronoUnit.DAYS.between(now, dueDate);
		double temp = (double) dura;
		daysPow = Math.pow(2, (-temp + 9));
		return dura;
	}
	
	
	//Return number of days left
	public long getDaysLeft() {
		return daysLeft;
		
	}

	// Set Due Date
	public void setDueOn(LocalDate dueDate) {
		this.dueDate = dueDate;
		daysLeft = DaysLeft();

	}

	// Return Due Date
	public LocalDate getDueOn() {
		return dueDate;
	}

	// Set priority
	public void setpriority(int priority) {
		this.priority = priority;
	}

	// Return priority
	public int getpriority() {
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

	public  String toString(){
		return this.name + " " + this.dueDate + " " + this.priority + " ";
	}


	@Override
	public int compareTo(superAssignment o) {
		// TODO Auto-generated method stub
 		return pow - o.getPow();

	}
	
	public String printByName(){
		return this.name;
	}

}
