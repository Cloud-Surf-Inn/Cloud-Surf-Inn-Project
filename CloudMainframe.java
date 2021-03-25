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
			Document doc = builder.parse("hotelTest3.xml");   // EXPERIMENT
			
			//-------- Code for determining if message is flt, mrp, eta, sta --------
			
			NodeList nodeList=doc.getElementsByTagName("*");
			
			int rowSize = test.getRowCount();
			
			String[][] dataArray = new String[rowSize][9]; // 2d array used for db integration w/ subsystems
			int dataCount = 0; // used to help fill dataArray
			
			//-------- Code for pulling flight info based on message type --------
			
		    Element element = (Element)nodeList.item(3);
		    msgType = element.getNodeName();
		    System.out.println("Type of inputted message: " + msgType);
		    
		    if (msgType.compareTo("flt") == 0)
		    {
		    	System.out.println("Message type is flt");
		    }
		    else
		    {
		    	System.out.println("Message type is NOT flt");
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
					String brNum = atList.item(5).getTextContent();
					String vacancy = atList.item(7).getTextContent();
					String tempControl = atList.item(9).getTextContent();
					String tlc = atList.item(11).getTextContent();
					String cReq = atList.item(13).getTextContent();
					String bedNum = atList.item(15).getTextContent();
					String sReq = atList.item(17).getTextContent();
					
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
						dataArray[dataCount][2] = rs.getString("brNum");
						dataArray[dataCount][3] = rs.getString("vacancy");
						dataArray[dataCount][4] = rs.getString("tempControl");
						dataArray[dataCount][5] = rs.getString("tlc");
						dataArray[dataCount][6] = rs.getString("cReq");
						dataArray[dataCount][7] = rs.getString("bedNum");
						dataArray[dataCount][8] = rs.getString("sReq");
						dataCount++;
					}
					System.out.println("--End of Hotel Rooms in Table--");
					System.out.println(" ");
					
					cloud_surf_inn_window windowTest = new cloud_surf_inn_window(dataArray);
					windowTest.runWindow(dataArray);
					
					for (int k =0; k < rowSize; k++)
					{
						for (int l = 0; l < 9; l++)
						{
							System.out.println(dataArray[k][l]);
						}
					}
					//put integrated code here
					
					if (rowcount == 0) // rowcount will equal 0 if there is no other hits on the given aid
					{
						test.addHotelData(roomId, flrNum, brNum, vacancy, tempControl, tlc, cReq, bedNum, sReq);
						System.out.println("Room confirmed.");
					}
					else
					{
						System.out.println("Room already exists within table.");
					}
					//aid = "AAL2101";
					//test.updateHotelData(roomId, flrNum, brNum, vacancy, tempControl, tlc, cReq, bedNum, sReq);
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