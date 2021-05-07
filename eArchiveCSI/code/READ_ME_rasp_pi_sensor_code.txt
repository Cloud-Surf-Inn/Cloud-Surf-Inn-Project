Rasp_Pi_Sensor_Code:
--------------------

Ras_Pi_Sensor_Code.py is the the code is the TCP client socket between the communication between
the Raspberry Pi and our main Application. This code handles incoming messages being sent from our main application.
Each message is handled in their own function for modularity. The Raspberry Pi code serves as a controller for our 
power control hub which houses are fans, lights, motion detection, and temperature snesor. Throughout the code
we see GPIO.output(*device*, GPIO.LOW OR GPIO.HIGH). This indicates whether or not to supply the specific device with power
oor to read from such device. 

Note: The only messages that you can send are, "Booked", "Vacant", "FanOff", "FanOnHigh", "FanOnLow", "LightOn", "LightOff", "SetTemp", "DisplayTemp". Other messages will not be handled.
