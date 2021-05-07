package cloud_surf_inn;
import java.time.*;
import java.util.*;

/* This is the class for the priority queue of all housekeeping requests. It utilizes java's priority queue datastructure (which is a minheap).
 * The priority queue will consist of CleaningNodes.
 * The priority will be dictated by time. Priority will be calculated finding the number of hours until the request must be serviced. 
 * The "natural ordering" used by these heaps is established by the CleaningNode class itself rather than by a custom comparator. The "priority" field of the CleaningNode class is used to establish priority.
 * This priority queue stores all housekeeping requests for all housekeepers to accept. Only 1 will exist at a time.
 */
public class HPHeap  {
	
	private PriorityQueue<CleaningNode> HPHeap;
	
	// the constructor for the HPHeap priority queue
	public HPHeap() {
		HPHeap = new PriorityQueue<CleaningNode>();
	}
	
	// this method is used to find the size of the current HPHeap
	// it relies on the built in size function of a Java Priority Queue
	public int size() {
		return HPHeap.size();
	}
	
	// this method is used to peek at the head of the HPHeap (return but NOT remove the head)
	// it relies on the built in peek function of a Java Priority Queue
	// thus, result will be null if HPHeap is empty
	public CleaningNode peek() {
			return HPHeap.peek();
	}
	
	// this method is used to poll the head of the HPHeap (return AND remove the head)
	// it relies on the built in poll function of a Java Priority Queue
	// thus, result will be null if HPHeap is empty
		public CleaningNode poll() {
				return HPHeap.poll();
		}
	
	// this method is used to check if CleaningNode is present in HPHeap (uses the overriden equals method in CleaningNode)
	// returns true if contained and false otherwise
			public boolean contains(CleaningNode node) {
					return HPHeap.contains(node);
			}
	
	// create and add cleaning node, BUT do a check for priority and if it is one of several negative values, do not add it and instead print out error message 
	// this method will create and add a cleaning node to the HPHeap but check for priority to handle errors.
	// node cannot be added if it is already present in the HPHeap
	// this method returns a boolean. True indicates the cleaning node was added successfully, and false indicates some error occurred. 
	public int addNode(String day, String hour, boolean cleaned, int DBRoomNum) {
		
		CleaningNode newNode = new CleaningNode(day, hour, cleaned, DBRoomNum);                   // creating the new node to be added
		int priority = newNode.getPriority();                                                     // getting the priority from the cleaning node
		int result;
		
		if (HPHeap.contains(newNode) == true) {                                                   // if the new node is already present print a message saying so and exit gracefully
			return -3;
		}
		
		if (priority < 0) {                                                                       // if the priority is negative we error handle
			switch (priority) {
			case -1 :
				return -1;
			case -2:
				return -2;
			default :
				break;
			}
		}
		// if the priority is positive we can proceed to add the node to the HPHeap
		boolean added = HPHeap.add(newNode);                                                   // adding the new node to the HPHeap
		if (added == false) {                                                                  // handling the error in case the node cannot be added to the Heap
			return -4;
		}
		result = 0;                                                                        // if we reach this point, the new node has been successfully added and we can return true
		return result;
	}
	
	// this overloaded addNode is the same as the normal addNode EXCEPT it takes an already created CleaningNode as its only argument
	// node cannot be added if it is already present in the HPHeap
	// this method returns a boolean. True indicates the cleaning node was added successfully, and false indicates some error occurred
	public boolean addNode(CleaningNode node) {
		
		int priority = node.getPriority();                                                        // getting the priority from the given cleaning node
		boolean result;
		
		if (HPHeap.contains(node) == true) {                                                   // if the new node is already present print a message saying so and exit gracefully
			System.out.println("A copy of this node is already present.");
			return false;
		}
		
		if (priority < 0) {                                                                       // if the priority is negative we error handle
			switch (priority) {
			case -1 :
				System.out.println("Attempted to schedule housekeeping within 2 hours of NOW. Please try again with a valid time and day.");
				result = false;
				return result;
			case -2:
				System.out.println("ERROR: Section of code within the priorityCalculator method in CleaningNode class reached!");
				result = false;
				return result;
			default :
				break;
			}
		}
		// if the priority is positive we can proceed to add the node to the HPHeap
		boolean added = HPHeap.add(node);                                                      // adding the node to the HPHeap
		if (added == false) {                                                                  // handling the error in case the node cannot be added to the Heap
			System.out.println("ERROR: Cleaning Node unable to be added to HPHeap.");
			result = false;
			return result;
		}
		result = true;                                                                        // if we reach this point, the new node has been successfully added and we can return true
		return result;
	}
	
	
	// this method will remove a CleaningNode given a cleaning Node and nothing else (overload removeNode methods are possible in the event we wish to remove all nodes with a certain parameter)
	// will rely on the room number provided by the node itself
	// this method returns a DEEPCOPY of the cleaning node that was removed from the HPHeap (the same node that was passed as an argument). If the cleaning node is null, this means that the removal process was unsuccessful for some reason. 
	public CleaningNode removeNode(CleaningNode node) {
		boolean removed = HPHeap.remove(node);
		
		if (removed == false) {                                                                                               // If the removal process was unsuccessful, print the error message and return the NULL cleaning node.
			System.out.println("ERROR: The given node was not able to be removed from HPHeap.");
			CleaningNode returned = null;
			return returned;
		}
		// if removed was true then the node was removed from HPHeap. We can proceed to make a deep copy of the removed node that will be returned
		CleaningNode deepCopy = new CleaningNode(node.getDay(), node.getHour(), node.getCleaned(), node.getDBRoomNum());      // creating a deep copy of the given node for returning purposes
		deepCopy.setPriority(node.getPriority());                                                                             // explicitly setting the priority of the copy to the priority of the given node (priority should not change; this is done as a precaution)
		return deepCopy;
	}
	
	
	// this method will DELETE a CleaningNode given a cleaning Node and nothing else 
	// Unlike removeNode, this method DELETES it entirely without returning the deleted node.
	// this method returns true if the node was deleted and false otherwise.
	public boolean deleteNode(CleaningNode node) {
			boolean removed = HPHeap.remove(node);
			
			if (removed == false) {                                                                                       // If the removal process was unsuccessful, print the error message and return false.
				System.out.println("ERROR: The given node was not able to be deleted from HPHeap.");
				return false;
			}
			// if removed was true then the node was deleted from HPHeap
			return true;
			
		}
	
	// this method is used to return a SORTED array consisting of the contents of the HPHeap as of right NOW
    // returns the sorted array
    public CleaningNode[] toSRTDArray() {
            CleaningNode[] array = new CleaningNode[HPHeap.size()];
            Arrays.sort(HPHeap.toArray(array));
            return array;
    }
	
	// this method will DELETE all CleaningNodes with the given room number
	// this method relies on an iterator inherited from the Priority Queue class
	// this method returns true if the deletions were successful and false otherwise.
		public boolean deleteNode(int DBRoomNum) {
				
			Iterator values = HPHeap.iterator();                                            // creates an iterator to iterate through the elements in the current HPHeap
				
			while(values.hasNext()) {                                                       // while we can iterate through the elements in the iterator/heap
				CleaningNode currentNode = (CleaningNode) values.next();
				if (currentNode.getDBRoomNum() == DBRoomNum) {                             // if the room numbers are the same, we can delete this node with the remove method from the Iterator
					values.remove();                  
				}
				continue;
			}
			// if we reach this point, we were able to successfully delete all nodes with the given room number
			return true;
			}
		
		
		// main method for testing purposes
	public static void main(String[] args) {
		
		HPHeap test = new HPHeap();
//		CleaningNode testNode = new CleaningNode("WEDNESDAY", "22", false, 1);
//		CleaningNode example = new CleaningNode("FRIDAY", "12", false, 20);
//		CleaningNode highPriority = new CleaningNode("THURSDAY", "04", false, 5);
//		test.addNode(example);
//		test.addNode(example);
//		test.addNode(testNode);
//		test.addNode(highPriority);
//		test.addNode("THURSDAY", "3", false, 100);
//		int size = test.size();
//		System.out.println("The current size is: " + size);
//		CleaningNode tipTop = test.peek();
//		System.out.println("The priority of this should be the minimum possible which is '2': " + tipTop.getPriority());
//		CleaningNode highest = test.poll();
//		size = test.size();
//		System.out.println("The priority of this should be the minimum possible which is '2' (size is now 3): " + highest.getPriority());
//		test.addNode("MONDAY", "13", false, 1);
//		test.deleteNode(1);
//		size = test.size();
//		System.out.println("The current size is: " + size);
//		//CleaningNode removed = test.removeNode(example);
//		test.addNode("SATURDAY", "8", false, 50);
//		CleaningNode bruh = new CleaningNode("SATURDAY", "8", false, 50);
//		CleaningNode removed = test.removeNode(bruh);
//		test.deleteNode(example);
//		size = test.size();
//		System.out.println("The current size is: " + size);
		
		// Test adding, removing, and deleting several different nodes
		
		CleaningNode lowPriority = new CleaningNode("MONDAY", "12", false, 1);
		lowPriority.setPriority(168);
		CleaningNode lowestPriority = new CleaningNode("SATURDAY", "12", false, 1);
		lowestPriority.setPriority(168);
		CleaningNode medPriority = new CleaningNode("TUESDAY", "12", false, 1);
		medPriority.setPriority(50);
		CleaningNode bruh = new CleaningNode("WEDNESDAY", "12", false, 1);
		bruh.setPriority(150);
		CleaningNode highPriority = new CleaningNode("THURSDAY", "12", false, 1);
		highPriority.setPriority(2);
		CleaningNode ok = new CleaningNode("SUNDAY", "12", false, 1);
		ok.setPriority(10);
		test.addNode(lowPriority);
		test.addNode(lowestPriority);
		test.addNode(medPriority);
		test.addNode(bruh);
		test.addNode(highPriority);
		test.addNode(ok);
		
		while (test.size() > 0) {
			CleaningNode print = test.poll();
			System.out.println("The current head's priority is: " + print.getPriority());
		}
		int num = 1;
		
		

	}

}
