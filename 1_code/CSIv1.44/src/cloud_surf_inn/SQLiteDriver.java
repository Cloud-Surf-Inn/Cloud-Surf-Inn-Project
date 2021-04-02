package cloud_surf_inn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteDriver {
	
	private static Connection con;
	private static boolean hasData = false;
	
	public ResultSet displayUsers() throws ClassNotFoundException, SQLException {
		if (con == null) {
			getConnection(); // This method is used for displaying pre-existing hotel entries
		}                    // NOTE: the result set can only be traversed through ONCE (limitations of sqlite)
		
		Statement state = con.createStatement();
		ResultSet res = state.executeQuery("SELECT roomId, flrNum, bedNum, vacancy, kitchen, jczi, bidet, prefRank, tempControl, curTemp, tlc, cReq, sReq FROM user");
		return res;
	}

	private void getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:hotelDataBaseDEMO2.db");   // NAME OF DB FILE THAT IS STORED IN C: DRIVE
		initialise();
	}

	private void initialise() throws SQLException { // Method that actually creates the database
		if ( !hasData ) {
			hasData = true;
			
			Statement state = con.createStatement();
			ResultSet res = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='user'");
			if ( !res.next() ) {
				System.out.println("Creating database..."); // only prints when initially run
				// building the table
				Statement state2 = con.createStatement();
				
				state2.execute("CREATE TABLE user(id integer,"  // Statement that creates the db itself
						+ "roomId varchar(60)," + "flrNum varchar(60)," + "bedNum varchar(60)," + "vacancy varchar(60)," + "kitchen varchar(60),"
						+ "jczi varchar(60)," + "bidet varchar(60)," + "prefRank varchar(60)," + "tempControl varchar(60)," + "curTemp varchar(60),"
						+ "tlc varchar(60)," + "cReq varchar(60)," + "sReq varchar(60),"
						+ "primary key(id));"); 
			}
		}	
	}


	public void addHotelData (String roomId, String flrNum, String bedNum, String vacancy, String kitchen, String jczi, String bidet, String prefRank, String tempControl, String curTemp, String tlc, String cReq, String sReq) throws ClassNotFoundException, SQLException {
	
		if (con == null) { // Creates database if not created already
			getConnection();
		}
		
		PreparedStatement prep = con.prepareStatement("INSERT INTO user(roomId, flrNum, bedNum, vacancy, kitchen, jczi, bidet, prefRank, tempControl, curTemp, tlc, cReq, sReq) values(?,?,?,?,?,?,?,?,?,?,?,?,?);");
		prep.setString(1, roomId);
		prep.setString(2, flrNum);
		prep.setString(3, bedNum); // This method adds arguments to database (already created at this point)
		prep.setString(4, vacancy); // Each tag is assigned to its own number (i.e. fps = 4, aid = 1, ara = 10, etc.)
		prep.setString(5, kitchen);
		prep.setString(6, jczi);
		prep.setString(7, bidet);
		prep.setString(8, prefRank);
		prep.setString(9, tempControl);
		prep.setString(10, curTemp);
		prep.setString(11, tlc);
		prep.setString(12, cReq);
		prep.setString(13, sReq);
		prep.execute();
			
		System.out.println("Room Added (roomData): " + roomId + ", on floor " + flrNum);
		System.out.println(" ");
	}
	
	public void updateHotelData (String roomId, String flrNum, String bedNum, String vacancy, String kitchen, String jczi, String bidet, String prefRank, String tempControl, String curTemp, String tlc, String cReq, String sReq) throws ClassNotFoundException, SQLException {
		
		if (con == null) { // Creates database if not created already
			getConnection();
		}
		
		String sql = "UPDATE user SET flrNum = ?,"  // Here, aid is taken at the end with WHERE
				+ "bedNum = ?," + "vacancy = ?," + "kitchen = ?," // This method will only update message that have a matching aid
				+ "jczi = ?," + "bidet = ?," + "prefRank = ?,"
				+ "tempControl = ?," + "curTemp = ?," + "tlc = ?,"
				+ "cReq = ?," 
				+ "sReq = ?" + "WHERE roomId = ?";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, flrNum); // Similar to the addFltData method, each tag has it's own corresponding number
		pstmt.setString(2, bedNum);
		pstmt.setString(3, vacancy);
		pstmt.setString(4, kitchen);
		pstmt.setString(5, jczi);
		pstmt.setString(6, bidet);
		pstmt.setString(7, prefRank);
		pstmt.setString(8, tempControl);
		pstmt.setString(9, curTemp);
		pstmt.setString(10, tlc);
		pstmt.setString(11, cReq);
		pstmt.setString(12, sReq);
		pstmt.setString(13, roomId);
		pstmt.executeUpdate();
		
		System.out.println("Room Updated.");
	}
	
	public int getRowCount() throws ClassNotFoundException, SQLException 
	{
		  Statement stmt = con.createStatement();
	      //Query to get the number of rows in a table
	      String query = "select count(*) from user";
	      //Executing the query
	      ResultSet rs = stmt.executeQuery(query);
	      //Retrieving the result
	      rs.next();
	      int count = rs.getInt(1);
	      System.out.println("Number of entries in the database: "+count);
	      return count;
	}
}

