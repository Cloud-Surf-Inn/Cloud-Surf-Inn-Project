import adafruit_dht
from time import sleep
import board
import socket  
import RPi.GPIO as GPIO 


ROOM_STATE = False
SET_TEMP = -1
dhtSensor = adafruit_dht.DHT22(board.D4)
  
# Create a socket object  
s = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
# Define the port on which you want to connect  
port = input("Enter port value: ")
port = int(port)
# connect to the server on local computer  
s.connect(('169.254.6.50', port))  
  
# receive data from the server  

light = 17
fan_1 = 27
fan_2 = 22
R = 23
G = 24
B = 25

GPIO.setup(light, GPIO.OUT)
GPIO.setup(fan_1, GPIO.OUT)
GPIO.setup(fan_2, GPIO.OUT)
GPIO.setup(R, GPIO.OUT)
GPIO.setup(G, GPIO.OUT)
GPIO.setup(B, GPIO.OUT)


GPIO.output(light, GPIO.LOW)
GPIO.output(fan_1, GPIO.LOW)
GPIO.output(fan_2, GPIO.LOW)
GPIO.output(R, GPIO.HIGH)
GPIO.output(G, GPIO.LOW)
GPIO.output(B, GPIO.LOW)

def booked():
    print("Entering Comfort Mode")
    ROOM_STATE = True
    
def vacant():
    print("Entering Power-Saving Mode")
    ROOM_STATE = False
    GPIO.output(light, GPIO.LOW)
    GPIO.output(R, GPIO.LOW)
    GPIO.output(G, GPIO.HIGH)
    GPIO.output(B, GPIO.LOW)
    GPIO.output(fan_1, GPIO.HIGH)
    GPIO.output(fan_2, GPIO.HIGH)
    sleep(25)
    GPIO.output(R, GPIO.HIGH)
    GPIO.output(G, GPIO.LOW)
    GPIO.output(B, GPIO.LOW)
    GPIO.output(fan_1, GPIO.LOW)
    GPIO.output(fan_2, GPIO.LOW)
    SET_TEMP = -1

def fan_off():
    print("Ventilation Off")
    GPIO.output(R, GPIO.HIGH)
    GPIO.output(G, GPIO.LOW)
    GPIO.output(B, GPIO.LOW)
    GPIO.output(fan_1, GPIO.LOW)
    GPIO.output(fan_2, GPIO.LOW)
    
def fan_on_high():
    print("Ventilation On High")
    GPIO.output(R, GPIO.LOW)
    GPIO.output(G, GPIO.HIGH)
    GPIO.output(B, GPIO.LOW)
    GPIO.output(fan_1, GPIO.HIGH)
    GPIO.output(fan_2, GPIO.HIGH)

def fan_on_low():
    print("Ventilation On Low")
    GPIO.output(R, GPIO.LOW)
    GPIO.output(G, GPIO.LOW)
    GPIO.output(B, GPIO.HIGH)
    GPIO.output(fan_1, GPIO.HIGH)
    GPIO.output(fan_2, GPIO.LOW)
        

def set_temp(word):
    print("Temperature Set to: " + word)
    SET_TEMP = int(word)
    
def light_on():
    print("Lights On")
    GPIO.output(light, GPIO.HIGH)

def light_off():
    print("Lights Off")
    GPIO.output(light, GPIO.LOW)

def display_temp():
    if ROOM_STATE != "Vacant":
        if SET_TEMP == -1:
            try:
                temp_c = dhtSensor.temperature
                temp_f = format(temp_c * 9.0 / 5.0 + 32.0, "0f")
            except RuntimeError:
                print("RuntimeError, trying again...")
                s.send("RuntimeError, trying again...\n").encode() 
                return		
            s.send(("Current temperature in Farenheit: " + str(temp_f) + '\n').encode())
            return
        s.send(("Current temperature in Farenheit: " + str(SET_TEMP) + '\n').encode())
        return
    s.send(("Temperature Off" + '\n').encode())
    
    
    

while True:
    fromServer =s.recv(1024).decode()
    word = ""
    num = 0
    for char in fromServer:
        letToNum = ord(char) 
        if letToNum > 64 and letToNum < 91 or letToNum > 96 and letToNum < 123:   
            word = word + char
    #print(word)
    if word == "Booked":
        booked()
        continue
    if word == "Vacant":
        vacant()
        continue
    if word == "FanOff":
        fan_off()
        continue
    if word == "FanOnHigh":
        fan_on_high()
        continue
    if word == "FanOnLow":
        fan_on_low()
        continue
    if word == "LightOn":
        light_on()
        continue
    if word == "LightOff":
        light_off()
        continue
    if word == "SetTemp":
        temp = s.recv(1024).decode()
        word = ""
        for char in temp:
            letToNum = ord(char) 
            if letToNum > 47 and letToNum < 58:   
                word = word + char
        set_temp(word)
        continue 
    if word == "DisplayTemp":
        display_temp()
        continue 
    
close(s)


	

