package cloud_surf_inn;
import java.time.*;
import java.util.*;

/* This class defines the CleaningNode datastructure, methods, and its constituent fields. 
 * This class also includes getter and setter methods for all fields.
 * This class will be used to create the node in the priority queues/heaps using to manage the housekeeping system in Cloud Surf Inn.
 * This class will use java's built in functions for dates and times.
 * This class has a compareTo method for use of the PriorityQueue's built in comparator.
 * This class features a helper method, priority Calculator, that is used to determine the CleaningNode's priority
 */

public class CleaningNode implements Comparable<CleaningNode> {
	
	// these are the fields used by the CleaningNode class; they are all private.
	
	private String day;
	private String hour;
	private boolean cleaned;
	private int DBRoomNum;
	private int priority;
	
	
	public CleaningNode(String day, String hour, boolean cleaned, int DBRoomNum) {
		this.day = day;
		this.hour = hour;
		this.cleaned = cleaned;
		this.DBRoomNum = DBRoomNum;
		this.priority = priorityCalculator(day, hour);
	}
	
	// used to compare priorities within the heap classes  
	// returns negative value if THIS node is less than given node, positive if the converse is true, and 0 if they are equal
	// overrides the default compareTo method to establish the "natural ordering" used by Java's priority queue class
	// using this compareTo in addition to making the CleaningNode class "Comparable" allows us to sort the priority queues without needing to create a custom comparator
	@Override                                                                          // overrides the standard compareTo() method 
	public int compareTo(CleaningNode node) {
		if (this.priority < node.priority) {
			return -1;
		} else if (this.priority > node.priority) {
			return 1;
		}
		return 0;
	}
	
	// used to compare Cleaning Nodes. Ensures that a cleaning node only equals another cleaning node if all fields (especially the room number) are the same
	// if necessary, also override the hashCode() method since equals was overriden as well otherwise disregard this
	@Override
	public boolean equals(Object o) {
		
		if (this == o) {                              // if the object is compared with itself return true
			return true;
		}
		
		if (!(o instanceof CleaningNode)) {          // checks if the given object is an instance of the Cleaning Node class
			return false;
		}
		
		CleaningNode node = (CleaningNode) o;        // casts the Object o to a CleaningNode node for comparison
		
		if (this.day == node.day && this.hour == node.hour && this.cleaned == node.cleaned && this.DBRoomNum == node.DBRoomNum && this.priority == node.priority ) {                // if all fields match return true
			return true;
		}
		return false;
	}
	
	// this helper method will be used to find the number of hours = priority of the request using the CURRENT date for reference
	// can be updated to utilize server time if necessary
	// is based upon the constraint that requests only be booked
	// will return a positive priority if the given arguments fit the constraints of a valid schedule
	// negative values indicate invalid priorities or errors
	// -1 indicates given arguments imply a scheduled time within 2 hours of NOW (invalid time) 
	// -2 indicates section of code that should not have been reached was reached
	public static int priorityCalculator(String day, String hour) {
		LocalDate localDate = java.time.LocalDate.now();                    // the current date
		
		// inspecting the day and the current day
		DayOfWeek currentDay = DayOfWeek.from(localDate);                   // the current day of the week found from the local date
		int currentVal = currentDay.getValue();                             // integer value of current day of the week 
		DayOfWeek dayWeek = DayOfWeek.valueOf(day);                         // take the String day and make it a DayOfWeek object (MONDAY, TUESDAY, etc.)
		int dayVal = dayWeek.getValue();                                    // integer value of day field argument
		
		// inspecting the hour and current hour
		LocalTime currentTime = LocalTime.now();                            // the current time
		int currentHour = currentTime.getHour();                            // the hour of the current time
		int hourNum = Integer.parseInt(hour);                               // integer value of the hour field argument assuming that the String consists of the hour digits (i.e 1, 5, 10, 23) and no leading 0's (i.e 05)
		
		// we will now proceed to compare days of the week and times to calculate the priority
		// if the day of the week is the same and time is in the past or exactly the present, schedule is 1 week in the future
		// if day of the week is the same and time is in the future and within 2 hours, set priority to -1 (constraint that appointment must be at least 2 hours in advance)
		// if day of the week is the same 2 hours or more, schedule it for today
		// otherwise (if day of the week is different) priority is hours until the day and time
		
		int priority;                                        // the priority to be returned
		int twelveAM;                                        // will be used to contain the number of hours until 12 AM of a specified day from TODAY
		
		if (currentVal == dayVal) {                          // if the day of the week is the same
			
			if (hourNum <= currentHour) {                    	// if given hour is less than or equal to the current hour, cleaning is scheduled for next week 
				twelveAM = (7*24) - currentHour;         	// twelveAM contains the number of hours until 12 AM the following week that corresponds to the current time
				priority = twelveAM + hourNum;               	// return the priority as hours until 12 AM next week + the scheduled time (in hours). Max value is 168 in the case appointment is exactly next week.
				return priority;
			}
			
			if ( (hourNum - currentHour) < 2) {              	// if given hour - current hour is less than 2 (meaning appointment is within 2 hours within current time), return special priority to indicate user error
				priority = -1;                               	// priority of -1 indicates user attempted to schedule within 2 hours (constraint indicates this cannot occur)
				return priority;
			}
			
			if ( (hourNum - currentHour) >= 2 ) {            	// if given hour - current hour is gte 2 (meaning appointment  2 hours or more than the current time), calculate priority for TODAY
				priority = hourNum - currentHour;            	// return the priority as the difference between the scheduled time and the current time (in hours). Minimum value is 2 if appointment is exactly 2 hours from now.
				return priority;
			}
		}
		// from this point forward the day of the week is different
		int diff = dayVal - currentVal;                        // diff represents the difference of the integer values of the days of the week; can be negative or positive but not 0 because that case was handled already
		if (diff > 0) {                                     // if the difference is positive (meaning that the schedule day is for later THIS week)
			twelveAM = (diff*24) - currentHour;             // twelveAM contains the number of hours until 12 AM of the day of the week specified by dayVal from TODAY 
			priority = twelveAM + hourNum;               	// return the priority as hours until 12 AM next week + the scheduled time (in hours). Max value is 168 in the case appointment is exactly next week.
			return priority;
		} else if (diff < 0) {                              // if the difference is positive (meaning that the scheduled day is early NEXT week)
		    int nextWeek = diff + 7;                               // nextWeek contains the number of days until the given day NEXT week
			twelveAM = ((nextWeek)*24) - currentHour;              // twelveAM contains the number of hours until 12 AM of the day of the week specified by dayVal from TODAY (is next WEEK) 
			priority = twelveAM + hourNum;               	       // return the priority as hours until 12 AM next week of the specified day + the scheduled time (in hours).
			return priority;
		}
		// This should never be reached; priority will be -2 to indicate this error
		priority = -2;
		return priority;
	}
	
	// these are the getter/setter methods used to get or set the fields within any given instance of the CleaningNode class 
	// In practice, most of the setters will not be used but are for testing purposes. The reason is because room numbers, request dates, and times are not manipulated at all but used to organize the housekeeping process.
	public String getDay() {
		return day;
	}
	
	public String getHour() {
		return hour;
	}
	
	public boolean getCleaned() {
		return cleaned;
	}
	
	public int getDBRoomNum() {
		return DBRoomNum;
	}
	
	public int getPriority() {
		return priority;
	}
	
	public void setDay(String day) {
		this.day = day;
	}
	
	public void setHour(String hour) {
		this.hour = hour;
	}
	
	public void setCleaned(boolean cleaned) {
		this.cleaned = cleaned;
	}
	
	public void setDBRoomNum(int DBRoomNum) {
		this.DBRoomNum = DBRoomNum;
	}
	
	public void setPriority(int priority) {
		this.priority = priority;
	}

	// main method for testing purposes 
	public static void main(String[] args) {
		
		CleaningNode testNode = new CleaningNode("WEDNESDAY", "22", false, 1);
		int priority = testNode.getPriority();
		System.out.println("The priority is: " + priority);
		
		CleaningNode testCopy = new CleaningNode("WEDNESDAY", "22", false, 1);
		boolean shouldBeTrue = testNode.equals(testCopy);
		System.out.println("Value of shouldBeTrue is: " + shouldBeTrue);

	}

}
