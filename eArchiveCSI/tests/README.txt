
testing_power_control_hub.java:
-------------------------------

This java file is used to test our power control hub with the raspberry pi. A Java TCP Server Socket is set up and connects
to the Python TCP Client Socket. From here we have a while loop that takes in command line input that the user can type in.
The command line input is used to manually send TCP messages to the Python socket which is listening on the Raspberry Pi. This
was how the many functionalities of the power control hub were tested.

When running the code the we see "From GUI/Database to Pi: " on the command line. Here we can type in what we would like to send to
the Raspberry Pi.
*If you type in "SetTemp", "Enter Desired Temp: " we be shown and you will then type in the desired temperature.

Note: The only messages that you can send are, "Booked", "Vacant", "FanOff", "FanOnHigh", "FanOnLow", "LightOn", "LightOff", "SetTemp", "DisplayTemp". Other messages will not be handled.
