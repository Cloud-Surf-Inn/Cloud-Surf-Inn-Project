In order to test the room matching system:
Launch Cloud Surf Inn project code as normal.
Log into user page as customer using "user" as username and password.
Navigate to the "Book a room"
Input "4" for occupants, and check that wish for the room to have a bidet, kitchen, and jacuzzi.
Now click the "Book a room button"
The database contains 3 rooms currently that may fit these requirements: 
a room with all 3 amenities and 5 beds - room 231
a room with only 2 of the amenities and 4 beds - room 117, 
or a room with 3 beds and all 3 amenities - room 249.
The room matcher should give you room 231, as this prioritizes having all 3 amenities
but will allow for an extra bed to give you all of them, as no other room is available with 4 beds and all amenities.


In order to test the mobile key system:
Launch Cloud Surf Inn project code.
Log into user page as cutomer using "user" as username and password.
Navigate to the "Book a room"
After preferences are applied for the room booking, click "Find a Room" button.
The system will provide a 10 character alphanumeric pin for the specific room assigned to the customer. 
