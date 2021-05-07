package cloud_surf_inn;
import java.time.*;
import java.util.*;



/* This is the class for the queue of all room service requests. It utilizes java's Queue datastructure and is thus a FIFO datastructure.
 * The queue will consist of ServiceNodes.
 * This queue stores all service requests for all service staff to accept and manage. 
 * This queue implements Java's built-in Linked List class
 */
public class ServiceQueue {
	
	private Queue<ServiceNode> ServiceQueue;
	
	// the constructor for the serviceQueue queue
	public ServiceQueue() {
			ServiceQueue = new LinkedList<ServiceNode>();
	}
	
	// this method is used to find the size of the current ServiceQueue
	// it relies on the built in size function of a Java Queue
	public int size() {
		return ServiceQueue.size();
	}
	
	// this method is used to peek at the head of the ServiceQueue (return but NOT remove the head)
	// it relies on the built in peek function of a Java Queue
	// thus, result will be null if ServiceQueue is empty
	public ServiceNode peek() {
			return ServiceQueue.peek();
	}
		
	// this method is used to poll the head of the ServiceQueue(return AND remove the head)
	// it relies on the built in poll function of a Java Queue
	// thus, result will be null if ServiceQueue is empty
	public ServiceNode poll() {
				return ServiceQueue.poll();
		}
					
	// this method is used to return an array consisting of the contents of the ServiceQueue as of right NOW
	// returns the array
	public ServiceNode[] toArray() {
				ServiceNode[] array = new ServiceNode[ServiceQueue.size()];
				ServiceQueue.toArray(array);
				return array;
			}
	
	
	// create and add a service node to the ServiceQueue
	public void addNode(int quantity, int DBRoomNum, String type, String note) {
		ServiceNode node = new ServiceNode(quantity, DBRoomNum, type, note, false);
		ServiceQueue.add(node);
	}
	
	// this overloaded addNode is the same as the normal addNode EXCEPT it takes an already created ServiceNode as its only argument
	public void addNode(ServiceNode node) {
			ServiceQueue.add(node);
		}

}
