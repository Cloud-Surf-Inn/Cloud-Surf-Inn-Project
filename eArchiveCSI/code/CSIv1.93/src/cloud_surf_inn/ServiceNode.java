package cloud_surf_inn;
import java.time.*;

/* This class defines the ServiceNode datastructure, methods, and its constituent fields. 
 * This class also includes getter and setter methods for all fields.
 * This class will be used to create the node in the queue using to manage the Room Service/Online Ordering system in Cloud Surf Inn.
 * This class will use java's built in functions for dates and times.
 * This class has a compareTo method for use of the Queue's built in comparator.
 */
public class ServiceNode {

	private int quantity;
	private LocalTime time;
	private int DBRoomNum;
	private String type;
	private String note;
	private boolean accepted;
	
	// constructor
	public ServiceNode(int quantity, int DBRoomNum, String type, String note, boolean accepted) {
		this.quantity = quantity;
		this.time = LocalTime.now();
		this.DBRoomNum = DBRoomNum;
		this.type = type;
		this.note = note;
		this.accepted = false;
	}
	
	
	// used to compare ServiceNodes. Ensures that a service node only equals another service node if all fields (especially the room number) are the same
	// if necessary, also override the hashCode() method since equals was overriden as well otherwise disregard this
		@Override
		public boolean equals(Object o) {
			
			if (this == o) {                              // if the object is compared with itself return true
				return true;
			}
			
			if (!(o instanceof ServiceNode)) {          // checks if the given object is an instance of the Cleaning Node class
				return false;
			}
			
			ServiceNode node = (ServiceNode) o;        // casts the Object o to a CleaningNode node for comparison
			
			if (this.quantity == node.quantity && this.time == node.time && this.type == node.type && this.DBRoomNum == node.DBRoomNum && this.note == node.note &&  this.accepted == node.accepted) {                // if all fields match return true
				return true;
			}
			return false;
		}
	
	
	// getters and setters below 
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public int getDBRoomNum() {
		return DBRoomNum;
	}
	public void setDBRoomNum(int DBRoomNum) { 
		this.DBRoomNum = DBRoomNum;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public boolean isAccepted() {
		return accepted;
	}
	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
	
	
	
}
