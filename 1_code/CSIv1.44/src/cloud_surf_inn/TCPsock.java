package cloud_surf_inn;

import java.io.*;  
import java.net.*; 
import java.lang.*;

public class TCPsock {

static Socket connected;
static DataOutputStream dout;
static BufferedReader din;
static int portNum = 54322;
public static Socket activateControl(String state) {  
	try {
		String toPi = state;
	    String fromClient;     
	    ServerSocket jSock = new ServerSocket(portNum); 
	    System.out.println ("TCPServer Waiting for client on port " + portNum);
	    connected = jSock.accept();
	    System.out.println( " THE CLIENT"+" "+ connected.getInetAddress() +":"+connected.getPort()+" IS CONNECTED ");
	        
	    dout=new DataOutputStream(connected.getOutputStream());  
	    din = new BufferedReader(new InputStreamReader (connected.getInputStream()));
	        
	         
	    if(toPi.equals("exit"))return null;
	    dout.writeUTF(toPi);
	
	            
	    return connected;
	    
    }
    catch(Exception e){
        e.printStackTrace();}   
	return null;
	
}

public static void deactivateControl(String state) {
	try {
		
		dout.writeUTF(state);
	}
	catch(Exception e){
	    e.printStackTrace();}  
	portNum++;
}
public static void activateTemp(String temp) {
	try {  
		dout=new DataOutputStream(connected.getOutputStream());  
		dout.writeUTF("SetTemp");
		dout.writeUTF(temp);
	}
	catch(Exception e){
	    e.printStackTrace();}  
	
}

public static String displayTemp() {
	String fromPi;
	try {
		dout.writeUTF("DisplayTemp");
		fromPi = din.readLine();
		return fromPi;
	}
	catch(Exception e){
	    e.printStackTrace();}  
	return null;

}
 public static void fan_on_high() {
	 try {
		 dout.writeUTF("FanOnHigh");
		 System.out.println("Ventilation ON HIGH"); 
	 }
	 catch(Exception e){
		    e.printStackTrace();}  
		return;
 }
 public static void fan_on_low() {
	 try {
		 dout.writeUTF("FanOnLow");
		 System.out.println("Ventilation ON LOW"); 
	 }
	 catch(Exception e){
		    e.printStackTrace();}  
		return;
 }
 public static void fan_off() {
	 try {
		 dout.writeUTF("FanOff");
		 System.out.println("Ventilation OFF"); 
	 }
	 catch(Exception e){
		    e.printStackTrace();}  
		return;
 }
 public static void light_on() {
	 try {
		 dout.writeUTF("LightOn");
		 System.out.println("Lights ON"); 
	 }
	 catch(Exception e){
		    e.printStackTrace();}  
		return;
 }
 public static void light_off() {
	 try {
		 dout.writeUTF("LightOff");
		 System.out.println("Lights OFF"); 
	 }
	 catch(Exception e){
		    e.printStackTrace();}  
		return;
 }
 
 public static void comfort_mode() {
	 try {
		 dout.writeUTF("ComfortMode");
		 System.out.println("Comfort Mode");
	 }
	 catch(Exception e){
		    e.printStackTrace();}  
		return;
	 
 }
 public static void leave() {
	 try {
		 dout.writeUTF("Leave");
		 System.out.println("Leaving");
	 }
	 catch(Exception e){
		    e.printStackTrace();}  
		return;
 }
 
 public static void arrive() {
	 try {
		 dout.writeUTF("Arrive");
		 System.out.println("Arriving");
	 }
	 catch(Exception e){
		    e.printStackTrace();}  
		return;
 }

}


