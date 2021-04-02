package cloud_surf_inn;

public class roomMatcher
{
	public static int match(String[][] argDatabase, String occupants, String kitchen, String jacuzzi, String bidet) {
		System.out.println("Hello, welcome to Cloud Surf Inn's Room Matching System!!");
	    String database[][] = argDatabase;
	    
	    int numRooms = 0;
		numRooms = database.length;
	    
	    int dataFake[][] = new int[database.length][database[0].length];
	    
	   for(int j = 0; j < numRooms; j++)
	   {
		   for(int k = 0; k < 8; k++)
		   {
			   dataFake[j][k] = Integer.parseInt(database[j][k]);
		   }
	   }
	    
	    // dataBase has 3 rooms, each with 7 pieces of data
		//chris structure to match: {Room #, flr #, # bed, vacancy, kitchen, jacuzzi, bidet, prefRank}
	
		String pref1, pref2, pref3, pref4;
		int CustomerRoom = 0;
		int[] arr = new int[numRooms];
		
	    pref1 = occupants; 
	    pref2 = kitchen;
	    pref3 = jacuzzi;
	    pref4 = bidet;
	    int varPref1 = Integer.parseInt(pref1);
	    
		for(int i = 0; i < numRooms; i++)
		{
		    //# beds match
			int var = Integer.parseInt(database[i][2]);
			
		    if (var >  varPref1)
		    	dataFake[i][7]++;   
		    	
		    if (var == varPref1)
		        dataFake[i][7] = dataFake[i][7] + 2; 
		        
		    //kitchen match
		    if (database[i][4] == pref2)
		    	dataFake[i][7] = dataFake[i][7] + 2; 
			     
			//jacuzzi match
		    if (database[i][5] == pref3)
		    	dataFake[i][7] = dataFake[i][7] + 2; 
		      
		    //bidet match
		    if (database[i][6] == pref4)
		    	dataFake[i][7] = dataFake[i][7] + 2; 
		            
		    //vacancy must be 0  
		    if (database[i][3] == "0")
		    	dataFake[i][7]++;
		}

        arr = dataFake[0];
        
        //look through, find highest prefRank
		for(int j = 1; j < numRooms; j++)
		{
		    if (dataFake[j][7] > arr[7])
		        arr = dataFake[j];
		}
		
		//reset prefRank to 0 for each room.
		for(int j = 1; j < numRooms; j++)
		{
		   dataFake[j][7] = 0;
		}
		  
		CustomerRoom = arr[0];
		System.out.println("Your room number is: " + CustomerRoom);
		
		return CustomerRoom;
		
	}
}