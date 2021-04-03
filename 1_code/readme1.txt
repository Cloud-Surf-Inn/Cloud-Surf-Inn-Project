-------- START: HOW TO RUN CLOUD SURF INN --------

Provided below are the steps required to run our program. Before following the steps, please make sure that the following prerequisites are installed on your system: Windows OS (7/8/10), Eclipse IDE version 2020-03 or newer (older versions have the potential to run, however this tutorial is intended for the given version), Java 8, and a stable internet connection.

--------1--------

1. Open up Eclipse IDE to the main screen. The project explorer, console, workspace, and toolbar should all be visible. Go to the toolbar and click the “help” button located all the way to the right. A drop down menu will appear: click on the “Eclipse Marketplace…” option.

From the eclipse marketplace you need to search for and then install “WindowBuilder 1.9.5”. Eclipse IDE should handle the installation if a stable internet connection is present. 

--------2--------

2. Download a .zip file from our team GitHub which can be found at: https://github.com/Software-Engineering-Team-8/Cloud-Surf-Inn. Extract the .zip file into a secure location that can be accessed by Eclipse IDE. Go to your Eclipse IDE toolbar and select “File” located all the way to the left. From here you can select the option “Open Projects from File System…”.

Once this option is selected, a window will appear that will allow you to open a project from a selected registry.

Click on the “Directory…” button and then find the extracted contents of the .zip file that was downloaded earlier. From this file, navigate to the directory “Cloud-Surf-Inn-main/1_code” where you should see a “CSIv1.44” folder, readme.txt, and sqlite-jdbc-3.8.11.1.jar.

Select the folder “CSIv1.44” and then click the “Finish” button at the bottom of the screen. The project should now be visible under the “Project Explorer” located to the left of the main screen.

--------3--------

3. Now that the project is available to be used by the Eclipse IDE, we have to add an external .jar file in order for the project to run correctly. From the “Project Explorer” right click on the “CSIv1.44” project that was added in the previous step. A dropdown menu will appear which will have the option “Properties” at the bottom.

A window will appear that has various options to select from on the left side. Select the option “Java Build Path” and then select “Libraries” located in the middle of the window. 

Select the “Add External JARs…” button on the right side of the window and then once again find the extracted contents of the .zip file that was downloaded. Go to the directory “Cloud-Surf-Inn-main/1_code” (same as before) and then select the “sqlite-jdbc-3.8.11.1.jar” file. Once this is done, click the “Apply and Close” button at the bottom of the screen.

--------4--------

4. We are nearly finished setting up. Go to the “Project Explorer” and navigate to the directory “CSIv1.44/src/cloud_surf_inn” where a handful of .java files should be located. Proceed to double click on the file “CloudMainframe.java” which should then open the file in the workspace on the main screen.

Now you can run our project by clicking on the green run button located below the toolbar’s “Navigate” button. 

The program will now run and display our GUI with all its features. Enjoy!

--------Adding Additional Database Entries:--------

In order to add additional entries (hotel rooms) to the database we must do so by modifying a single line of code in the “CloudMainframe.java” file. The statement “Document doc = builder.parse(“hotelRoom5.xml)” decides what .xml file will be written to the database so it can be used later. 

In order to change the .xml file that is being inputted, we must change the number that comes after “hotelRoom”. For example, “hotelRoom5” would instead have to be “hotelRoom1”, “hotelRoom2”, etc.

There are currently 5 different hotel room entries that are provided with our project, although more are planned to be added in the future. Once multiple rooms are added to the database, they will each provide data that is visible via the GUI.

-------- END: HOW TO RUN CLOUD SURF INN --------
