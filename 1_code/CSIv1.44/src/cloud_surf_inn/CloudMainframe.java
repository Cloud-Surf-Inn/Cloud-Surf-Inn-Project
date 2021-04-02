package cloud_surf_inn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.util.Scanner;

public class CloudMainframe {

	public static void main(String[] args) {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		SQLiteDriver test = new SQLiteDriver();
		ResultSet rs;
		
		try {
			
			rs = test.displayUsers();
			int rowcount = 0; // used for determining if an aid already exists in database
			String msgType = null;
			
			// -------- Start of Parser --------
			
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("hotelRoom5.xml");   // EXPERIMENT
			
			//-------- Code for determining if message is flt, mrp, eta, sta --------
			
			NodeList nodeList=doc.getElementsByTagName("*");
			
			int rowSize = test.getRowCount();
			
			String[][] dataArray = new String[rowSize][13]; // 2d array used for db integration w/ subsystems
			int dataCount = 0; // used to help fill dataArray
			
			//-------- Code for pulling flight info based on message type --------
			
		    Element element = (Element)nodeList.item(3);
		    msgType = element.getNodeName();
		    System.out.println("Type of inputted message: " + msgType);
		    
		    if (msgType.compareTo("roomData") == 0)
		    {
		    	System.out.println("Message type is roomData");
		    }
		    else
		    {
		    	System.out.println("Message type is NOT roomData");
		    }
		    
		    //-------- END OF: Code for pulling flight info based on message type --------
			
			NodeList roomList = doc.getElementsByTagName("roomData");    // Pulls out all elements if tag 'roomData' exists in xml doc
			
			for(int i=0;i<roomList.getLength();i++) // roomList is only 1 column (loop might not be necessary)
			{
				Node r = roomList.item(i);
				if(r.getNodeType()==Node.ELEMENT_NODE)
				{
					Element room = (Element) r;
					NodeList atList = room.getChildNodes(); 
						
					String roomId = atList.item(1).getTextContent();
					String flrNum = atList.item(3).getTextContent();
					String bedNum = atList.item(5).getTextContent();
					String vacancy = atList.item(7).getTextContent();
					String kitchen = atList.item(9).getTextContent();
					String jczi = atList.item(11).getTextContent();
					String bidet = atList.item(13).getTextContent();
					String prefRank = atList.item(15).getTextContent();
					String tempControl = atList.item(17).getTextContent();
					String curTemp = atList.item(19).getTextContent();
					String tlc = atList.item(21).getTextContent();
					String cReq = atList.item(23).getTextContent();
					String sReq = atList.item(25).getTextContent();
					
					// -------- Below code goes displays flights in table --------
					// Code also does logic to determine if a flight exists already by going through and comparing aid for each flight
					
					System.out.println("--Current Hotel Rooms in Table--");
					String temp = null;
					
					while(rs.next()) { // result set loop (can only be traversed once)
						temp = rs.getString("roomId");
						
						System.out.println(rs.getString("roomId") + " " + rs.getString("flrNum") + " " + rs.getString("vacancy"));
						if (temp.compareTo(roomId) == 0) // Nice hack to compare string tags
						{
							rowcount++; // Increments rowcount if there is a hit
							//System.out.println("True(Strings are equal)");
						}
						else
						{
							//System.out.println("False");
						}
						dataArray[dataCount][0] = rs.getString("roomId");  // inputting data into dataArray so it can be used by gui / other subsystems
						dataArray[dataCount][1] = rs.getString("flrNum");
						dataArray[dataCount][2] = rs.getString("bedNum");
						dataArray[dataCount][3] = rs.getString("vacancy");
						dataArray[dataCount][4] = rs.getString("kitchen");
						dataArray[dataCount][5] = rs.getString("jczi");
						dataArray[dataCount][6] = rs.getString("bidet");
						dataArray[dataCount][7] = rs.getString("prefRank");
						dataArray[dataCount][8] = rs.getString("tempControl");
						dataArray[dataCount][9] = rs.getString("curTemp");
						dataArray[dataCount][10] = rs.getString("tlc");
						dataArray[dataCount][11] = rs.getString("cReq");
						dataArray[dataCount][12] = rs.getString("sReq");
						dataCount++;
					}
					System.out.println("--End of Hotel Rooms in Table--");
					System.out.println(" ");
					
					//----------------NEW CODE BELOW----------------
					
					
					cloud_surf_inn_window windowTest = new cloud_surf_inn_window(dataArray);
					windowTest.runWindow(dataArray);
					
					/*
					Scanner scan = new Scanner(System.in);
					System.out.print("Would you like to add an entry:");
					int testInt = scan.nextInt();
					System.out.println(testInt);
					*/
					
					/*
					for (int k =0; k < rowSize; k++)
					{
						for (int l = 0; l < 13; l++)
						{
							System.out.println(dataArray[k][l]);
						}
					}
					*/
					
					
					// scan.close();
					
					
					
					//----------------NEW CODE ABOVE----------------
					
					if (rowcount == 0) // rowcount will equal 0 if there is no other hits on the given aid
					{
						test.addHotelData(roomId, flrNum, bedNum, vacancy, kitchen, jczi, bidet, prefRank, tempControl, curTemp, tlc, cReq, sReq);
						System.out.println("Room confirmed.");
					}
					else
					{
						System.out.println("Room already exists within table.");
					}
					//aid = "AAL2101";
					//test.updateHotelData(roomId, flrNum, bedNum, vacancy, kitchen, jczi, bidet, prefRank, tempControl, curTemp, tlc, cReq, sReq);
				}
			}
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}