How to Run Code:
Directions to run code are in the "doc" directory in this folder in the document "technical_documentation".

The "doc" directory contains files for the reports, presentations, and other documents.
Contains: Report_1.pdf, Report_2.pdf, Report_3.pdf, Demo1_CSI.pptx, Demo2_CSI.pptx, technical_documentation.pdf

All Files:
CleaningNode.java
Written by: Juan Escudero
Tested by: Zach LeMunyon
Debugged by: Ryder Morrello
This class defines the CleaningNode datastructure, methods, and its constituent fields. This class also includes getter and setter methods for all fields. This class will be used to create the node in the priority queues/heaps using to manage the housekeeping system in Cloud Surf Inn. This class will use java's built in functions for dates and times. This class has a compareTo method for use of the PriorityQueue's built in comparator. This class features a helper method, priority Calculator, that is used to determine the CleaningNode's priority.

CloudMainframe.java
Written by: 
Tested by: 
Debugged by: 


HKPRHeap.java
Written by: Juan Escudero
Tested by: Zach LeMunyon
Debugged by: Ryder Morrello
This is the class for the priority queue of all housekeeping requests a housekeeper has accepted. It utilizes java's priority queue datastructure (which is a minheap). The priority queue will consist of CleaningNodes. The priority will be dictated by time. Priority will be calculated finding the number of hours until the request must be serviced.  The "natural ordering" used by these heaps is established by the CleaningNode class itself rather than by a custom comparator. The "priority" field of the CleaningNode class is used to establish priority. Each housekeeper will have one of these heaps.

HPHeap.java
Written by: Juan Escudero
Tested by: Zach LeMunyon
Debugged by: Ryder Morrello
This is the class for the priority queue of all housekeeping requests. It utilizes java's priority queue datastructure (which is a minheap). The priority queue will consist of CleaningNodes. The priority will be dictated by time. Priority will be calculated finding the number of hours until the request must be serviced.  The "natural ordering" used by these heaps is established by the CleaningNode class itself rather than by a custom comparator. The "priority" field of the CleaningNode class is used to establish priority. This priority queue stores all housekeeping requests for all housekeepers to accept. Only 1 will exist at a time.

Payment.java
* FILE NOT USED *

SQLiteCustomer.java
Written by: Christian Kline
Tested by: Christian Kline
Debugged by: Christian Kline
This is the class that is used for creating the database that stores all the customers along with their respective information. This class initializes a connection with the rest of our application via JDBC. Our code utilizes JDBC by importing an external .jar file.


SQLiteDriver.java
Written by: Christian Kline
Tested by: Christian Kline
Debugged by: Christian Kline
This is the class that is used for creating the database that stores all the hotel rooms along with their respective information. This class initializes a connection with the rest of our application via JDBC. Our code utilizes JDBC by importing an external .jar file.


ServiceNode.java
Written by: Juan Escudero
Tested by: Zach LeMunyon
Debugged by: Ryder Morrello
This class defines the ServiceNode datastructure, methods, and its constituent fields. This class also includes getter and setter methods for all fields. This class will be used to create the node in the queue using to manage the Room Service/Online Ordering system in Cloud Surf Inn. This class will use java's built in functions for dates and times. This class has a compareTo method for use of the Queue's built in comparator.

ServiceQueue.java
Written by: Juan Escudero
Tested by: Zach LeMunyon
Debugged by: Ryder Morrello
This is the class for the queue of all room service requests. It utilizes java's Queue datastructure and is thus a FIFO datastructure. The queue will consist of ServiceNodes. This queue stores all service requests for all service staff to accept and manage. This queue implements Java's built-in Linked List class

TCPsock.java
Written by: Sebastian Matiz
Tested by: Sebastian Matiz
Debugged by: Sebastian Matiz
This class is the main controller of our automatic temperature system. Our rasberry pi communicates directly with this class in order to feed temperature data to our system. The rasberry pi can also be controlled remotely from this class.

TooManyBeds.java
Written by: JP Dangler
Tested by: Christian Kline
Debugged by: Christian Kline
The purpose of this class is to display a message to the user if he/she requests a room that has too many beds. This message will only be displayed if there isn't a room in the database that has the requested number of beds.


UserNode.java
Written by: Juan Escudero
Tested by: Zach LeMunyon
Debugged by: Ryder Morrello
This class stores the user information for login information. Specifically, username, password, notifications, housekeeping requests, and room numbers are stored in this object.

cloud_surf_inn_window.java
Written by: Ryder Morrello
Tested by: Zach LeMunyon
Debugged by: Juan Escudero
This is the class that initiates the user interface. Pages must be accessed by logging in as a customer, staff member, or manager. All three have different permissions and specific windows that pop up when logging in as a specific user.

oneTimePassword.java
Written by: Bharath Selvaraj
Tested by: Sidonia Mohan
Debugged by: Sidonia Mohan
This class is used by our program in order to create a one-time-password in order create a 4 digit pin that the customer can use to access their room. This pin is randomly created and only utilizes numbers (0-9).

qR.java
* FILE NOT USED *


roomMatcher.java
Written by: Christian Kline
Tested by: Christian Kline
Debugged by: JP Dangler
This class contains the main algorithm that is used by Cloud Surf Inn in order to pair customers with a room. The algorithm pulls information from the hotel database and compares that data to the preferences that were inputted by the customer. Our program will then perform comparison logic in order to determine which room best fits the customer's desires.


viewDatabase.java
Written by: JP Dangler
Tested by: JP Dangler
Debugged by: JP Dangler
This class is primarily used for creating a pop-up window which displays all current rooms in a given hotel along with their respective data. The customer can then use this list in order to choose a room they want.
