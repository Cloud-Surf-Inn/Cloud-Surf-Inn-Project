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
	        
	            //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	            //System.out.print("From GUI/Database to Pi: ");
	            //String toPi = reader.readLine();
	            //System.out.println(toPi); 
	    if(toPi.equals("exit"))return null;
	    dout.writeUTF(toPi);
	
	            /*if(toPi.equals("DisplayTemp")){
	                fromClient = din.readLine(); 
	                System.out.println(fromClient);
	                continue;
	            }
	            */
	            //if(toPi.equals("SetTemp")){
	            //    System.out.print("Enter Desired Temp: ");
	                
	                //String temp = reader.readLine();
	                //dout.writeUTF(temp);
	            //continue;
	     
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
/*
public static DataOutputStream setOut() {
	try {
		DataOutputStream dout=new DataOutputStream(connected.getOutputStream());  
		return dout;
	}
	catch(Exception e){
	    e.printStackTrace();}  
	return null;
}
public static BufferedReader setIn(Socket connected) {
	try {
		BufferedReader din=new BufferedReader(new InputStreamReader (connected.getInputStream()));
		return din;
	}
	catch(Exception e){
	    e.printStackTrace();}  
	return null;
}
*/
}

