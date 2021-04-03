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

In order to test the housekeeping system:
To schedule housekeeping as a customer:
    Launch the Cloud Surf Inn project as detailed in the readme1.txt.
    Log into the user page as a customer using "user" as the username and password.
    Navigate to the "Housekeeping" page.
    Select the desired Day and Hour which housekeeping is to be scheduled (i.e SATURDAY at 5).
    Click the "Submit" button to submit the housekeeping request.
    The test is complete, the submitted request will appear in the "Active Request" box!
To accept a housekeeping request as a housekeeper:
    Launch the Cloud Surf Inn project as detailed in the readme1.txt after having scheduled at least one housekeeping request in the "Housekeeping" page.
    Log into the user page as a housekeeper using "hp" as the username and password.
    Navigate to the "Housekeeper" page.
    The available housekeeping requests will be listed in the "Incoming Requests" box.
    Click "Accept Request" to accept a request.
    The test is complete; the accepted request will appear in the "Accepted Requests" box.
To mark that a housekeeping request has been completed (as a housekeeper):
    Launch the Cloud Surf Inn project as detailed in the readme1.txt after having accepted at least one housekeeping request.
    Log into the user page as a housekeeper using "hp" as the username and password.
    Navigate to the "Housekeeper" page.
    The accpeted housekeeping requests will be listed in the "Accepted Requests" box.
    Click the "Complete Active Request" button.
    The test is complete; the active request has been marked as completed and removed from the "Accepted Requests" box!

In order to test the power saving system:
Launch Cloud Surf Inn.
Log into user page as cutomer using "user" as username and password.
Navigate to "Room Control"
Select "Check-In"
Select "Show"
The current room temperature will show.
Enter Desired temperature and select update
Select "Show"
The desired  temperature should be shown
Select "Check-Out"
Select Show
The current temperature in the room will show "Temperature Off"
