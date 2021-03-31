import adafruit_dht
import time
import board
# Import socket module  
import socket   

#-------USER SETTINGS--------
MINUTES_BETWEEN_READS = 1
#----------------------------

ROOM_STATE = False
SET_TEMP = -1
dhtSensor = adafruit_dht.DHT22(board.D4)
  
# Create a socket object  
s = socket.socket()
# Define the port on which you want to connect  
port = input("Enter port value: ")
port = int(port)
# connect to the server on local computer  
s.connect(('127.0.0.1', port))  
  
# receive data from the server  
      
      

while True:
    fromServer = s.recv(1024).decode()
    if fromServer == "Booked":
        print("Entering Comfort Mode")
        ROOM_STATE = True
        continue
    if fromServer == "Vacant":
        print("Entering Power-Saving Mode")
        ROOM_STATE = False
        SET_TEMP = -1
        continue
    if fromServer == "Set Temp":
        temp = s.recv(1024).decode()
        print("Temperature Set to: " + temp)
        SET_TEMP = int(temp)
        continue 
    if ROOM_STATE == True and fromServer == "Display Temp" and SET_TEMP == -1:
        try:
            temp_c = dhtSensor.temperature
            temp_f = format(temp_c * 9.0 / 5.0 + 32.0, "2f")
        except RuntimeError:
            print("RuntimeError, trying again...")
            continue		
        s.send(("Current temperature in Farenheit: " + str(temp_f)).encode())
        continue
    if ROOM_STATE == True and fromServer == "Display Temp" and SET_TEMP != -1:
        s.send(("Current temperature in Farenheit: " + str(SET_TEMP)).encode())
        continue
    if ROOM_STATE == False and fromServer == "Display Temp":
        s.send(("Temperature Off").encode())
    
close(s)
	
