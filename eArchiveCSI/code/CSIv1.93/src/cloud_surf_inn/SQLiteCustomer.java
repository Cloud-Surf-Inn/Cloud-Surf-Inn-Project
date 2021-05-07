package cloud_surf_inn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteCustomer {
	
	private static Connection con;
	private static boolean hasData = false;
	
	public ResultSet displayCustomers() throws ClassNotFoundException, SQLException {
		if (con == null) {
			getConnection(); // This method is used for displaying pre-existing hotel entries
		}                    // NOTE: the result set can only be traversed through ONCE (limitations of sqlite)
		
		Statement state = con.createStatement();
		ResultSet res = state.executeQuery("SELECT name, password, cardName, cardNum, cardExp, cvvNum, license, roomNum, roomPassword FROM customer");
		return res;
	}

	private void getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:customerDataBase01.db");   // NAME OF DB FILE THAT IS STORED IN C: DRIVE
		initialise();
	}

	private void initialise() throws SQLException { // Method that actually creates the database
		if ( !hasData ) {
			hasData = true;
			
			Statement state = con.createStatement();
			ResultSet res = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='customer'");
			if ( !res.next() ) {
				System.out.println("Creating database..."); // only prints when initially run
				// building the table
				Statement state2 = con.createStatement();
				
				state2.execute("CREATE TABLE customer(id integer,"  // Statement that creates the db itself
						+ "name varchar(60)," + "password varchar(60)," + "cardName varchar(60)," + "cardNum varchar(60)," + "cardExp varchar(60),"
						+ "cvvNum varchar(60)," + "license varchar(60)," + "roomNum varchar(60)," + "roomPassword varchar(60),"
						+ "primary key(id));"); 
			}
		}	
	}


	public void addCustomerData (String name, String password, String cardName, String cardNum, String cardExp, String cvvNum, String license, String roomNum, String roomPassword) throws ClassNotFoundException, SQLException {
	
		if (con == null) { // Creates database if not created already
			getConnection();
		}
		
		PreparedStatement prep = con.prepareStatement("INSERT INTO customer(name, password, cardName, cardNum, cardExp, cvvNum, license, roomNum, roomPassword) values(?,?,?,?,?,?,?,?,?);");
		prep.setString(1, name);
		prep.setString(2, password);
		prep.setString(3, cardName); // This method adds arguments to database (already created at this point)
		prep.setString(4, cardNum); // Each tag is assigned to its own number (i.e. fps = 4, aid = 1, ara = 10, etc.)
		prep.setString(5, cardExp);
		prep.setString(6, cvvNum);
		prep.setString(7, license);
		prep.setString(8, roomNum);
		prep.setString(9, roomPassword);
		prep.execute();
			
		System.out.println("Cutomer Added (customerData): " + name + ", in room " + roomNum);
		System.out.println(" ");
	}
	
	public void updateCustomerRoom (String name, String roomNum) throws ClassNotFoundException, SQLException {
		
		if (con == null) { // Creates database if not created already
			getConnection();
		}
		
		String sql = "UPDATE customer SET roomNum = ?"  // Here, name is taken at the end with WHERE
				+ "WHERE name = ?";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, roomNum); 
		pstmt.setString(2, name);
		pstmt.executeUpdate();
		
		System.out.println("Customer Room Updated.");
	}
	
	public void updateCustomerPW (String name, String roomPassword) throws ClassNotFoundException, SQLException {
		
		if (con == null) { // Creates database if not created already
			getConnection();
		}
		
		String sql = "UPDATE customer SET roomPassword = ?"  // Here, name is taken at the end with WHERE
				+ "WHERE name = ?";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, roomPassword); 
		pstmt.setString(2, name);
		pstmt.executeUpdate();
		
		System.out.println("Customer Password Updated.");
	}
	
	public void updateCustomerPayment (String name, String cardName, String cardNum, String cardExp, String cvvNum, String license) throws ClassNotFoundException, SQLException {
		
		if (con == null) { // Creates database if not created already
			getConnection();
		}
		
		String sql = "UPDATE customer SET cardName = ?,"  // Here, name is taken at the end with WHERE
				+ "cardNum = ?," + "cardExp = ?," + "cvvNum = ?," // This method will only update message that have a matching name
				+ "license = ?" + "WHERE name = ?";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, cardName);
		pstmt.setString(2, cardNum);
		pstmt.setString(3, cardExp);
		pstmt.setString(4, cvvNum);
		pstmt.setString(5, license);
		pstmt.setString(6, name); 
		pstmt.executeUpdate();
		
		System.out.println("Customer Payment Updated.");
	}
	
	
	public void updateCustomerData (String name, String password, String cardName, String cardNum, String cardExp, String cvvNum, String license, String roomNum, String roomPassword) throws ClassNotFoundException, SQLException {
		
		if (con == null) { // Creates database if not created already
			getConnection();
		}
		
		String sql = "UPDATE customer SET password = ?,"  // Here, name is taken at the end with WHERE
				+ "cardName = ?," + "cardNum = ?," + "cardExp = ?," // This method will only update message that have a matching name
				+ "cvvNum = ?," + "license = ?," + "roomNum = ?,"
				+ "roomPassword = ?" + "WHERE name = ?";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, password);
		pstmt.setString(2, cardName);
		pstmt.setString(3, cardNum);
		pstmt.setString(4, cardExp);
		pstmt.setString(5, cvvNum);
		pstmt.setString(6, license);
		pstmt.setString(7, roomNum);
		pstmt.setString(8, roomPassword);
		pstmt.setString(9, name); 
		pstmt.executeUpdate();
		
		System.out.println("Customer Updated.");
	}
	
	public int getRowCount() throws ClassNotFoundException, SQLException 
	{
		  Statement stmt = con.createStatement();
	      //Query to get the number of rows in a table
	      String query = "select count(*) from customer";
	      //Executing the query
	      ResultSet rs = stmt.executeQuery(query);
	      //Retrieving the result
	      rs.next();
	      int count = rs.getInt(1);
	      System.out.println("Number of entries in the database: "+count);
	      return count;
	}
}