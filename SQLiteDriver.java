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
		ResultSet res = state.executeQuery("SELECT roomId, flrNum, brNum, vacancy, tempControl, tlc, cReq, bedNum, sReq FROM user");
		return res;
	}

	private void getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:hotelDataBase2.db");   // NAME OF DB FILE THAT IS STORED IN C: DRIVE
		initialise();
	}

	private void initialise() throws SQLException { // Method that actually creates the database
		if ( !hasData ) {
			hasData = true;
			
			Statement state = con.createStatement();
			ResultSet res = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='user'");
			if ( !res.next() ) {
				System.out.println("Building the User table with prepopulated values."); // only prints when initially run
				// building the table
				Statement state2 = con.createStatement();
				
				state2.execute("CREATE TABLE user(id integer,"  // Statement that creates the db itself
						+ "roomId varchar(60)," + "flrNum varchar(60)," + "brNum varchar(60)," + "vacancy varchar(60)," + "tempControl varchar(60),"
						+ "tlc varchar(60)," + "cReq varchar(60)," + "bedNum varchar(60)," + "sReq varchar(60),"
						+ "primary key(id));"); 
			}
		}	
	}


	public void addHotelData (String roomId, String flrNum, String brNum, String vacancy, String tempControl, String tlc, String cReq, String bedNum, String sReq) throws ClassNotFoundException, SQLException {
	
		if (con == null) { // Creates database if not created already
			getConnection();
		}
		
		PreparedStatement prep = con.prepareStatement("INSERT INTO user(roomId, flrNum, brNum, vacancy, tempControl, tlc, cReq, bedNum, sReq) values(?,?,?,?,?,?,?,?,?);");
		prep.setString(1, roomId);
		prep.setString(2, flrNum);
		prep.setString(3, brNum); // This method adds arguments to database (already created at this point)
		prep.setString(4, vacancy); // Each tag is assigned to its own number (i.e. fps = 4, aid = 1, ara = 10, etc.)
		prep.setString(5, tempControl);
		prep.setString(6, tlc);
		prep.setString(7, cReq);
		prep.setString(8, bedNum);
		prep.setString(9, sReq);
		prep.execute();
			
		System.out.println("Room Added (roomData): " + roomId + ", on floor " + flrNum);
		System.out.println(" ");
	}
	
	public void updateHotelData (String roomId, String flrNum, String brNum, String vacancy, String tempControl, String tlc, String cReq, String bedNum, String sReq) throws ClassNotFoundException, SQLException {
		
		if (con == null) { // Creates database if not created already
			getConnection();
		}
		
		String sql = "UPDATE user SET flrNum = ?,"  // Here, aid is taken at the end with WHERE
				+ "brNum = ?," + "vacancy = ?," + "tempControl = ?," // This method will only update message that have a matching aid
				+ "tlc = ?," + "cReq = ?," + "bedNum = ?,"
				+ "sReq = ?" + "WHERE roomId = ?";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, flrNum); // Similar to the addFltData method, each tag has it's own corresponding number
		pstmt.setString(2, brNum);
		pstmt.setString(3, vacancy);
		pstmt.setString(4, tempControl);
		pstmt.setString(5, tlc);
		pstmt.setString(6, cReq);
		pstmt.setString(7, bedNum);
		pstmt.setString(8, sReq);
		pstmt.setString(9, roomId);
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

