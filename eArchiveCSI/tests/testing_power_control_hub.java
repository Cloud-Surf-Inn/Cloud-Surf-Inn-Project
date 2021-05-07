import java.io.*;  
import java.net.*; 
import java.lang.*;

public class testing_power_control_hub {

public static void main(String[] args) {  


    String fromClient; 
    try{      
        ServerSocket jSock = new ServerSocket(54321); 
        System.out.println ("TCPServer Waiting for client on port 54321");
        Socket connected = jSock.accept();
        System.out.println( " THE CLIENT"+" "+ connected.getInetAddress() +":"+connected.getPort()+" IS CONNECTED ");
        
        DataOutputStream dout=new DataOutputStream(connected.getOutputStream());  
        BufferedReader din = new BufferedReader(new InputStreamReader (connected.getInputStream()));
        
        while ( true ){
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("From GUI/Database to Pi: ");
            String toPi = reader.readLine();
            System.out.println(toPi); 
            if(toPi.equals("exit")) break;
            dout.writeUTF(toPi);

            if(toPi.equals("DisplayTemp")){
                fromClient = din.readLine(); 
                System.out.println(fromClient);
                continue;
            }
            if(toPi.equals("SetTemp")){
                System.out.print("Enter Desired Temp: ");
                

                String temp = reader.readLine();
                dout.writeUTF(temp);
                continue;
            }



        }


        dout.close();  
        din.close();
        jSock.close();
    }

    catch(Exception e){
        e.printStackTrace();}   


}
}