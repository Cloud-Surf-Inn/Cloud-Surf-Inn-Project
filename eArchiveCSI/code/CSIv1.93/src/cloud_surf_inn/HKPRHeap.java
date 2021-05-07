package cloud_surf_inn;
import java.time.*;
import java.util.*;

/* This is the class for the priority queue of all housekeeping requests a housekeeper has accepted. It utilizes java's priority queue datastructure (which is a minheap).
 * The priority queue will consist of CleaningNodes.
 * The priority will be dictated by time. Priority will be calculated finding the number of hours until the request must be serviced. 
 * The "natural ordering" used by these heaps is established by the CleaningNode class itself rather than by a custom comparator. The "priority" field of the CleaningNode class is used to establish priority.
 * Each housekeeper will have one of these heaps.
 */
public class HKPRHeap {
	
private PriorityQueue<CleaningNode> HKPRHeap;
	
	// the constructor for the HKPRHeap priority queue
	public HKPRHeap() {
		HKPRHeap = new PriorityQueue<CleaningNode>();
	}
	
	// this method is used to find the size of the current HKPRHeap
	// it relies on the built in size function of a Java Priority Queue
	public int size() {
		return HKPRHeap.size();
	}
	
	// this method is used to peek at the head of the HKPRHeap (return but NOT remove the head)
	// it relies on the built in peek function of a Java Priority Queue
	// thus, result will be null if HKPRHeap is empty
	public CleaningNode peek() {
			return HKPRHeap.peek();
	}
	
	// this method is used to poll the head of the HKPRHeap (return AND remove the head)
	// it relies on the built in poll function of a Java Priority Queue
	// thus, result will be null if HKPRHeap is empty
		public CleaningNode poll() {
				return HKPRHeap.poll();
		}
	
		// this method is used to check if CleaningNode is present in HKPRHeap (uses the overriden equals method in CleaningNode)
		// returns true if contained and false otherwise
				public boolean contains(CleaningNode node) {
						return HKPRHeap.contains(node);
				}
	
	
	// this addNode takes an already created CleaningNode as its only argument. An overloaded addNode that creates a node is not necessary since a housekeeper is not able to create requests, only accept them.
	// node cannot be added if it is already present in the HKPRHeap
	// this method returns a boolean. True indicates the cleaning node was added successfully, and false indicates some error occurred
	// DIFFERENCE: We must check to make sure no other node with the same day and time are present in the HKPRHeap before adding a new node to it
	public boolean addNode(CleaningNode node) {
		
		Iterator values = HKPRHeap.iterator();                                                                                  	// creates an iterator to iterate through the elements in the current HKPRHeap
		
		while(values.hasNext()) {                                                                                               	// while we can iterate through the elements in the iterator/heap
			CleaningNode currentNode = (CleaningNode) values.next();
			if (currentNode.getDay() == node.getDay() && currentNode.getHour() == node.getHour()) {                             	// if day and hour of the given node are the same as the day and hour of another node, we do not add this node
				System.out.println("Another request occurring at the same time already exists. This request cannot be accepted.");   
				return false;                                                                                                       
			}
			continue;
		}
		// at this point, it is clear that no other node occurring at the same time (day and hour)  exists so we can  attempt to add it normally.
		
		int priority = node.getPriority();                                                        // getting the priority from the given cleaning node
		boolean result;
		
		if (HKPRHeap.contains(node) == true) {                                                   // if the new node is already present print a message saying so and exit gracefully
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
		// if the priority is positive we can proceed to add the node to the HKPRHeap
		boolean added = HKPRHeap.add(node);                                                      // adding the node to the HKPRHeap
		if (added == false) {                                                                  // handling the error in case the node cannot be added to the Heap
			System.out.println("ERROR: Cleaning Node unable to be added to HKPRHeap.");
			result = false;
			return result;
		}
		result = true;                                                                        // if we reach this point, the new node has been successfully added and we can return true
		return result;
	}
	
	
	// this method will remove a CleaningNode given a cleaning Node and nothing else (overload removeNode methods are possible in the event we wish to remove all nodes with a certain parameter)
	// will rely on the room number provided by the node itself
	// this method returns a DEEPCOPY of the cleaning node that was removed from the HKPRHeap (the same node that was passed as an argument). If the cleaning node is null, this means that the removal process was unsuccessful for some reason. 
	// DIFFERENCE: Housekeeper can only remove a node IF there are 4 hours or more until request is scheduled (priority is gte 4).
	public CleaningNode removeNode(CleaningNode node) {
		
		if (node.getPriority() < 4) {                                                                                                  // check to ensure that the node being removed has a priority gte 4. If less than 4, print message and return NULL cleaning node.
			System.out.println("This request is scheduled for less than 4 hours from now. It cannot no longer be removed");
			CleaningNode returned = null;
			return returned;
		}
			
		boolean removed = HKPRHeap.remove(node);
		
		if (removed == false) {                                                                                               // If the removal process was unsuccessful, print the error message and return the NULL cleaning node.
			System.out.println("ERROR: The given node was not able to be removed from HKPRHeap.");
			CleaningNode returned = null;
			return returned;
		}
		// if removed was true then the node was removed from HKPRHeap. We can proceed to make a deep copy of the removed node that will be returned
		CleaningNode deepCopy = new CleaningNode(node.getDay(), node.getHour(), node.getCleaned(), node.getDBRoomNum());      // creating a deep copy of the given node for returning purposes
		deepCopy.setPriority(node.getPriority());                                                                             // explicitly setting the priority of the copy to the priority of the given node (priority should not change; this is done as a precaution)
		return deepCopy;
	}
	
	// this method is used to return a SORTED array consisting of the contents of the HKPRHeap as of right NOW
    // returns the sorted array
    public CleaningNode[] toSRTDArray() {
            CleaningNode[] array = new CleaningNode[HKPRHeap.size()];
            Arrays.sort(HKPRHeap.toArray(array));
            return array;
    }
	
	// this method will DELETE a CleaningNode given a cleaning Node and nothing else 
	// Unlike removeNode, this method DELETES it entirely without returning the deleted node.
	// this method returns true if the node was deleted and false otherwise.
	public boolean deleteNode(CleaningNode node) {
			boolean removed = HKPRHeap.remove(node);
			
			if (removed == false) {                                                                                       // If the removal process was unsuccessful, print the error message and return false.
				System.out.println("ERROR: The given node was not able to be deleted from HKPRHeap.");
				return false;
			}
			// if removed was true then the node was deleted from HKPRHeap
			return true;
			
		}
	
	// this method will DELETE all CleaningNodes with the given room number
	// this method relies on an iterator inherited from the Priority Queue class
	// this method returns true if the deletions were successful and false otherwise.
		public boolean deleteNode(int DBRoomNum) {
				
			Iterator values = HKPRHeap.iterator();                                            // creates an iterator to iterate through the elements in the current HKPRHeap
				
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
		// TODO Auto-generated method stub

	}

}
