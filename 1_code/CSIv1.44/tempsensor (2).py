import adafruit_dht
from time import sleep
import board
import socket  
import RPi.GPIO as GPIO 
import threading
import time 


dhtSensor = adafruit_dht.DHT22(board.D4)
  

ARRIVE_LEAVE_LIGHT = 16
light = 17
fan_1 = 27
fan_2 = 22
R = 23
G = 24
B = 25

GPIO.setup(ARRIVE_LEAVE_LIGHT, GPIO.OUT)
GPIO.setup(light, GPIO.OUT)
GPIO.setup(fan_1, GPIO.OUT)
GPIO.setup(fan_2, GPIO.OUT)
GPIO.setup(R, GPIO.OUT)
GPIO.setup(G, GPIO.OUT)
GPIO.setup(B, GPIO.OUT)

port = 54322


def booked():
    print("Entering Comfort Mode")
    x = threading.Thread(target=fan_on)
    x.start()
    x.join()
    return True
    
def vacant():
    print("Entering Power-Saving Mode")
    GPIO.output(ARRIVE_LEAVE_LIGHT, GPIO.HIGH)
    ROOM_STATE = False
    GPIO.output(light, GPIO.LOW)
    
    return -1, False
    
def fan_off():
    print("Ventilation Off")
    GPIO.output(R, GPIO.HIGH)
    GPIO.output(G, GPIO.LOW)
    GPIO.output(B, GPIO.LOW)
    GPIO.output(fan_1, GPIO.LOW)
    GPIO.output(fan_2, GPIO.LOW)
    if ARRIVE_LEAVE == True:
        global RGB
        RGB = "100"
    
    
def fan_on_high():
    print("Ventilation On High")
    GPIO.output(R, GPIO.LOW)
    GPIO.output(G, GPIO.HIGH)
    GPIO.output(B, GPIO.LOW)
    GPIO.output(fan_1, GPIO.HIGH)
    GPIO.output(fan_2, GPIO.HIGH)
    if ARRIVE_LEAVE == True:
        global RGB
        RGB = "010"
    
    

def fan_on_low():
    print("Ventilation On Low")
    GPIO.output(R, GPIO.LOW)
    GPIO.output(G, GPIO.LOW)
    GPIO.output(B, GPIO.HIGH)
    GPIO.output(fan_1, GPIO.HIGH)
    GPIO.output(fan_2, GPIO.LOW)
    if ARRIVE_LEAVE == True:
        global RGB
        RGB = "001"
        

def set_temp(word):
    print("Temperature Set to: " + word)

    
def light_on():
    print("Lights On")
    GPIO.output(light, GPIO.HIGH)

def light_off():
    print("Lights Off")
    GPIO.output(light, GPIO.LOW)

def display_temp():
    if ROOM_STATE == True:
        if SET_TEMP == -1:
            try:
                temp_c = dhtSensor.temperature
                temp_f = format(temp_c * 9.0 / 5.0 + 32.0, "0f")
            except RuntimeError:
                print("RuntimeError, trying again...")
                s.send(("RuntimeError, trying again...\n").encode()) 
                return		
            s.send(("Current temperature in Farenheit: " + str(temp_f) + '\n').encode())
            return
        s.send(("Current temperature in Farenheit: " + str(SET_TEMP) + '\n').encode())
        return
    s.send(("Temperature Off" + '\n').encode())
    return 

def comfort_mode():
    print("Entering Comfort Mode")
    return -1

def power_saving_mode():
    print("Entering Power Saving Mode")
    GPIO.output(R, GPIO.HIGH)
    GPIO.output(G, GPIO.LOW)
    GPIO.output(B, GPIO.LOW)
    GPIO.output(fan_1, GPIO.LOW)
    GPIO.output(fan_2, GPIO.LOW)
    GPIO.output(light, GPIO.LOW)
    global ROOM_STATE
    ROOM_STATE = False
    
def leave_timer():
    print("Leave")
    sleep(15)
    if ARRIVE_LEAVE == True:
        return
    power_saving_mode()
    x = threading.Thread(target=fan_on)
    x.start()
    return
    
    

def arrive():
    global ARRIVE_LEAVE
    ARRIVE_LEAVE = True
    global ROOM_STATE
    ROOM_STATE = True
    GPIO.output(light, GPIO.HIGH)
    print("Arrive")
    print("Applying Last Power Control Setting")
    if RGB == "100":
        GPIO.output(R, GPIO.HIGH)
        GPIO.output(G, GPIO.LOW)
        GPIO.output(B, GPIO.LOW)
        GPIO.output(fan_1, GPIO.LOW)
        GPIO.output(fan_2, GPIO.LOW)
        return
    if RGB == "010":
        GPIO.output(R, GPIO.LOW)
        GPIO.output(G, GPIO.HIGH)
        GPIO.output(B, GPIO.LOW)
        GPIO.output(fan_1, GPIO.HIGH)
        GPIO.output(fan_2, GPIO.HIGH)
        return
    if RGB == "001":
        GPIO.output(R, GPIO.LOW)
        GPIO.output(G, GPIO.LOW)
        GPIO.output(B, GPIO.HIGH)
        GPIO.output(fan_1, GPIO.HIGH)
        GPIO.output(fan_2, GPIO.LOW)
        return
        
    
    
def fan_on():
    fan_on_high()
    sleep(10)
    if RGB == "100" or ARRIVE_LEAVE == False or ROOM_STATE == False:
        fan_off()
    
    
while True:
    ROOM_STATE = False
    SET_TEMP = -1
    ARRIVE_LEAVE = False

    RGB = "100"

    # Create a socket object  
    s = socket.socket(socket.AF_INET,socket.SOCK_STREAM)

    # connect to the server on local computer  
    print("Waiting to connect on port: ", port) 
    s.connect(('192.168.1.167', port))  
      
    # receive data from the server  
    GPIO.output(ARRIVE_LEAVE_LIGHT, GPIO.LOW)
    GPIO.output(light, GPIO.LOW)
    GPIO.output(fan_1, GPIO.LOW)
    GPIO.output(fan_2, GPIO.LOW)
    GPIO.output(R, GPIO.HIGH)
    GPIO.output(G, GPIO.LOW)
    GPIO.output(B, GPIO.LOW)

    while True:
        fromServer =s.recv(1024).decode()
        word = ""
        num = 0
        for char in fromServer:
            letToNum = ord(char) 
            if letToNum > 64 and letToNum < 91 or letToNum > 96 and letToNum < 123:   
                word = word + char
        if word == "Booked":
            ROOM_STATE = booked()
            continue
        if word == "Vacant":
            SET_TEMP, ROOM_STATE = vacant()
            x = threading.Thread(target=fan_on)
            x.start()
            x.join()
            break
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
        if word == "ComfortMode":
            SET_TEMP = comfort_mode()
            continue
        if word == "SetTemp":
            temp = s.recv(1024).decode()
            word = ""
            for char in temp:
                letToNum = ord(char) 
                if letToNum > 47 and letToNum < 58:   
                    word = word + char
            set_temp(word)
            SET_TEMP = word
            continue 
        if word == "DisplayTemp":
            display_temp()
            continue 
        if word == "Leave":
            GPIO.output(ARRIVE_LEAVE_LIGHT, GPIO.LOW)
            ARRIVE_LEAVE = False
            x = threading.Thread(target=leave_timer)
            x.start()
            continue
        if word == "Arrive":
            GPIO.output(ARRIVE_LEAVE_LIGHT, GPIO.HIGH)
            arrive()
            continue
    port = port + 1
