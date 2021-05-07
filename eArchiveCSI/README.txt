How to Run Code:
*directions to run code here*

All Files:
CleaningNode.java
This class defines the CleaningNode datastructure, methods, and its constituent fields. This class also includes getter and setter methods for all fields. This class will be used to create the node in the priority queues/heaps using to manage the housekeeping system in Cloud Surf Inn. This class will use java's built in functions for dates and times. This class has a compareTo method for use of the PriorityQueue's built in comparator. This class features a helper method, priority Calculator, that is used to determine the CleaningNode's priority.

CloudMainframe.java


HKPRHeap.java
This is the class for the priority queue of all housekeeping requests a housekeeper has accepted. It utilizes java's priority queue datastructure (which is a minheap). The priority queue will consist of CleaningNodes. The priority will be dictated by time. Priority will be calculated finding the number of hours until the request must be serviced.  The "natural ordering" used by these heaps is established by the CleaningNode class itself rather than by a custom comparator. The "priority" field of the CleaningNode class is used to establish priority. Each housekeeper will have one of these heaps.

HPHeap.java
This is the class for the priority queue of all housekeeping requests. It utilizes java's priority queue datastructure (which is a minheap). The priority queue will consist of CleaningNodes. The priority will be dictated by time. Priority will be calculated finding the number of hours until the request must be serviced.  The "natural ordering" used by these heaps is established by the CleaningNode class itself rather than by a custom comparator. The "priority" field of the CleaningNode class is used to establish priority. This priority queue stores all housekeeping requests for all housekeepers to accept. Only 1 will exist at a time.

Payment.java


SQLiteCustomer.java


SQLiteDriver.java


ServiceNode.java
This class defines the ServiceNode datastructure, methods, and its constituent fields. This class also includes getter and setter methods for all fields. This class will be used to create the node in the queue using to manage the Room Service/Online Ordering system in Cloud Surf Inn. This class will use java's built in functions for dates and times. This class has a compareTo method for use of the Queue's built in comparator.

ServiceQueue.java
This is the class for the queue of all room service requests. It utilizes java's Queue datastructure and is thus a FIFO datastructure. The queue will consist of ServiceNodes. This queue stores all service requests for all service staff to accept and manage. This queue implements Java's built-in Linked List class

TCPsock.java


TooManyBeds.java


UserNode.java
This class stores the user information for login information. Specifically, username, password, notifications, housekeeping requests, and room numbers are stored in this object.

cloud_surf_inn_window.java
This is the class that initiates the user interface. Pages must be accessed by logging in as a customer, staff member, or manager. All three have different permissions and specific windows that pop up when logging in as a specific user.

oneTimePassword.java


qR.java


roomMatcher.java


viewDatabase.java
