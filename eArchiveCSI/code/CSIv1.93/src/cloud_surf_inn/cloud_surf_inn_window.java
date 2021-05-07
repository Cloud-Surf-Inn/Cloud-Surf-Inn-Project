package cloud_surf_inn;
import java.io.*;
import java.util.ArrayList;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import java.awt.FlowLayout;
import javax.swing.JRadioButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

public class cloud_surf_inn_window {

	private JFrame frame;
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JTextField txtUsername_CA;
	private JPasswordField txtPassword1_CA;
	private JPasswordField txtPassword2_CA;
	private JTextField txt_Occupants;
	private JTextField txt_Desired_Temp;
	private JTextField txt_card_name;
	private JTextField txt_card_number;
	private JTextField txt_card_exp;
	private JTextField txt_card_cvv;
	private JTextField txt_liscense;
	private JTextField txt_se_username;
	private JTextField txt_se_password;
	private JTextField txt_hk_username;
	private JTextField txt_hk_password;
	private String[][] database;
	private String[][] databaseCustomer;
	JTextArea txt_Active_Requests = new JTextArea();
	
	// --- CREATING OBJECT FOR DATABASES ---
	
	SQLiteDriver testGUI = new SQLiteDriver();
	SQLiteCustomer testCustomerGUI = new SQLiteCustomer();
	ResultSet rsGUI;
	ResultSet customerResultSetGUI;
	int rowCount = 0, desiredCount = 0;
	String tempUserName = null, desiredRoom = null;
	
	//ALL ACCOUNTS FOR LOG IN ARE SPECIFIED HERE
	ArrayList<UserNode> customers = new ArrayList<UserNode>();
	ArrayList<UserNode> managers = new ArrayList<UserNode>();
	ArrayList<UserNode> employees = new ArrayList<UserNode>();
	ArrayList<UserNode> housekeepers = new ArrayList<UserNode>();
	ArrayList<UserNode> activeStaff = new ArrayList<UserNode>();
	int signedIn;
	int arrayListSignIn;
	int selectedCustomer;
	
	//String testString = customers.get(0).getUsername();
	
	oneTimePassword key = new oneTimePassword();
	
	JButton btnLogOut = new JButton("Log Out");
	JTextArea txtOutput = new JTextArea();
	
	private JTextField textField;
	
	HPHeap hkrequests = new HPHeap();
	ServiceQueue serviceQueue = new ServiceQueue();
	ServiceQueue serviceOngoing = new ServiceQueue();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH.mm.ss");

	/**
	 * Launch the application.
	 */
	public void runWindow(String[][] database1, String[][] databaseCustomer1) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cloud_surf_inn_window window = new cloud_surf_inn_window(database1, databaseCustomer1);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public cloud_surf_inn_window(String[][] database2, String[][] databaseCustomer2) {
		initialize(database2, databaseCustomer2);
		database = database2; // initializing global variables
		databaseCustomer = databaseCustomer2;
	}

	public void initialize(String[][] database, String[][] databaseCustomer) {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cloud Surf Inn");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 80));
		lblNewLabel.setBounds(208, 11, 574, 88);
		frame.getContentPane().add(lblNewLabel);
		
		txtOutput.setEditable(false);
		txtOutput.setBounds(0, 513, 984, 148);
		frame.getContentPane().add(txtOutput);
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 11));
		btnNewButton.setBounds(872, 67, 61, 23);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		
		btnLogOut.setFont(new Font("Arial", Font.PLAIN, 11));
		btnLogOut.setEnabled(false);
		btnLogOut.setBounds(844, 33, 89, 23);
		frame.getContentPane().add(btnLogOut);
		
		tabbedPane.setFont(new Font("Arial", Font.PLAIN, 11));
		tabbedPane.setBounds(0, 110, 984, 406);
		frame.getContentPane().add(tabbedPane);
		
		/*Home Page**************************************************************************/
		
		JPanel Page_Home = new JPanel();
		tabbedPane.addTab("     Home     ", null, Page_Home, null);
		Page_Home.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Welcome to the Cloud Surf Inn!");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(285, 11, 406, 35);
		Page_Home.add(lblNewLabel_1);
		
		ImageIcon icon = new ImageIcon("homepage.jpg");
		JLabel image = new JLabel(icon);
		image.setBounds(0, 176, 617, -176);
		Page_Home.add(image);
		
		JTextArea txtAbout = new JTextArea();
		txtAbout.setLineWrap(true);
		txtAbout.setText("Cloud Surf Inn is a\r\nfully automated\r\nhotel whose goals\r\nare to provide an\r\nelevated customer\r\n"
				+ "experience, reduce\r\ncontact with staff,\r\nand increase\r\nsustainability within\r\na luxury hotel.");
		txtAbout.setEditable(false);
		txtAbout.setFont(new Font("Arial", Font.PLAIN, 15));
		txtAbout.setBounds(34, 95, 138, 196);
		Page_Home.add(txtAbout);
		
		JLabel lblNewLabel_33 = new JLabel("About Cloud Surf Inn");
		lblNewLabel_33.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_33.setBounds(10, 64, 226, 35);
		Page_Home.add(lblNewLabel_33);
		
		JLabel lblImage = new JLabel("This is the picture");
		lblImage.setFont(new Font("Arial", Font.PLAIN, 20));
		lblImage.setBounds(197, 42, 772, 326);
		lblImage.setIcon(new ImageIcon("homepage.jpg"));
		Page_Home.add(lblImage);
		
		/*Log in Page************************************************************************/
		TCPsock tempObject = new TCPsock();
		
		JPanel Page_Log_In = new JPanel();
		tabbedPane.addTab("     Log In     ", null, Page_Log_In, null);
		Page_Log_In.setLayout(null);
		
		JLabel lblNewLabel_13 = new JLabel("Check In/Out");
		lblNewLabel_13.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_13.setBounds(600, 270, 177, 23);
		Page_Log_In.add(lblNewLabel_13);
		
		JButton btn_Check_In = new JButton("Check In");
		btn_Check_In.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_Check_In.setBounds(566, 314, 89, 23);
		Page_Log_In.add(btn_Check_In);
		btn_Check_In.setEnabled(false);
		btn_Check_In.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				database[desiredCount][8] = "Comfort Mode";
				database[desiredCount][10] = "CM: " + LocalDateTime.now();
				
				try {
					testGUI.updateHotelTempControl(desiredRoom, "Comfort Mode");
					testGUI.updateHotelTLC(desiredRoom, "CM: " + LocalDateTime.now());
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					// tempObject.activateControl("Booked");   
				}
				catch(Exception a){
					a.printStackTrace();} 
			}
		});
		
		JButton btn_Check_Out = new JButton("Check Out");
		btn_Check_Out.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_Check_Out.setBounds(682, 314, 99, 23);
		Page_Log_In.add(btn_Check_Out);
		btn_Check_Out.setEnabled(false);
		btn_Check_Out.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				database[desiredCount][8] = "Power Saving Mode";
				database[desiredCount][10] = "PSM: " + LocalDateTime.now();
				
				try {
					testGUI.updateHotelTempControl(desiredRoom, "Power Saving Mode");
					testGUI.updateHotelTLC(desiredRoom, "PSM: " + LocalDateTime.now());
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//tempObject.deactivateControl("Vacant"); 
			}
		});
		
		JButton btn_Manager = new JButton("Manager");
		btn_Manager.setFont(new Font("Arial", Font.PLAIN, 11));
		JButton btn_Staff = new JButton("Staff");
		btn_Staff.setFont(new Font("Arial", Font.PLAIN, 11));
		JButton btn_Customer = new JButton("Customer");
		btn_Customer.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_Manager.setBackground(Color.LIGHT_GRAY);
		btn_Manager.setBounds(99, 68, 89, 23);
		Page_Log_In.add(btn_Manager);
		btn_Manager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_Manager.setBackground(Color.GREEN);
				btn_Staff.setBackground(Color.LIGHT_GRAY);
				btn_Customer.setBackground(Color.LIGHT_GRAY);
			}
		});
		
		btn_Staff.setBackground(Color.LIGHT_GRAY);
		btn_Staff.setBounds(226, 68, 89, 23);
		Page_Log_In.add(btn_Staff);
		btn_Staff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_Manager.setBackground(Color.LIGHT_GRAY);
				btn_Staff.setBackground(Color.GREEN);
				btn_Customer.setBackground(Color.LIGHT_GRAY);
			}
		});
		
		btn_Customer.setBackground(Color.LIGHT_GRAY);
		btn_Customer.setBounds(354, 68, 110, 23);
		Page_Log_In.add(btn_Customer);
		btn_Customer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_Manager.setBackground(Color.LIGHT_GRAY);
				btn_Staff.setBackground(Color.LIGHT_GRAY);
				btn_Customer.setBackground(Color.GREEN);
			}
		});
		
		JLabel lblNewLabel_3 = new JLabel("Type of User for Log In");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(153, 28, 277, 23);
		Page_Log_In.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Log In");
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(205, 139, 75, 38);
		Page_Log_In.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Username:");
		lblNewLabel_5.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_5.setBounds(166, 205, 89, 14);
		Page_Log_In.add(lblNewLabel_5);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Arial", Font.PLAIN, 11));
		txtUsername.setBounds(243, 203, 86, 20);
		Page_Log_In.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Password:");
		lblNewLabel_6.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_6.setBounds(166, 244, 89, 14);
		Page_Log_In.add(lblNewLabel_6);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Arial", Font.PLAIN, 11));
		txtPassword.setBounds(243, 241, 86, 20);
		Page_Log_In.add(txtPassword);
		
		JButton btn_Log_In = new JButton("Log In");
		btn_Log_In.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_Log_In.setBounds(226, 298, 89, 23);
		Page_Log_In.add(btn_Log_In);
		
		JLabel lblNewLabel_7 = new JLabel("OR");
		lblNewLabel_7.setFont(new Font("Arial", Font.PLAIN, 30));
		lblNewLabel_7.setBounds(469, 139, 89, 64);
		Page_Log_In.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Create an Account");
		lblNewLabel_8.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_8.setBounds(655, 46, 238, 23);
		Page_Log_In.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Username:");
		lblNewLabel_9.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_9.setBounds(667, 104, 110, 14);
		Page_Log_In.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Password:");
		lblNewLabel_10.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_10.setBounds(667, 129, 97, 14);
		Page_Log_In.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Verify Password:");
		lblNewLabel_11.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_11.setBounds(641, 155, 123, 14);
		Page_Log_In.add(lblNewLabel_11);
		
		txtUsername_CA = new JTextField();
		txtUsername_CA.setFont(new Font("Arial", Font.PLAIN, 11));
		txtUsername_CA.setBounds(735, 101, 97, 20);
		Page_Log_In.add(txtUsername_CA);
		txtUsername_CA.setColumns(10);
		
		txtPassword1_CA = new JPasswordField();
		txtPassword1_CA.setFont(new Font("Arial", Font.PLAIN, 11));
		txtPassword1_CA.setBounds(735, 127, 97, 20);
		Page_Log_In.add(txtPassword1_CA);
		
		txtPassword2_CA = new JPasswordField();
		txtPassword2_CA.setFont(new Font("Arial", Font.PLAIN, 11));
		txtPassword2_CA.setBounds(735, 152, 97, 20);
		Page_Log_In.add(txtPassword2_CA);
		
		managers.add(new UserNode("man", "man"));
		employees.add(new UserNode("staff", "staff"));
		housekeepers.add(new UserNode("maria", "maria"));
		housekeepers.get(housekeepers.size()-1).userHeap = new HKPRHeap();
		housekeepers.add(new UserNode("deborah", "deborah"));
		housekeepers.get(housekeepers.size()-1).userHeap = new HKPRHeap();
		customers.add(new UserNode("user", "user"));
		
		btn_Log_In.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUsername.getText();
				String password = String.valueOf(txtPassword.getPassword());
				String userType = "none";
				if (btn_Manager.getBackground() == Color.GREEN)
					userType = "manager";
				if (btn_Staff.getBackground() == Color.GREEN)
					userType = "staff";
				if (btn_Customer.getBackground() == Color.GREEN)
					userType = "customer";
				if (userType.equals("manager") && oneTimePassword.accountExists(managers, username, password) >= 0) {
					signedIn = oneTimePassword.accountExists(managers, username, password);
					txtOutput.append("Manager " + managers.get(signedIn).getUsername() + " has logged in\n");
					addManagerTab();
					logInOperation();
				}
				else if (userType.equals("staff") && oneTimePassword.accountExists(employees, username, password) >= 0) {
					signedIn = oneTimePassword.accountExists(employees, username, password);
					txtOutput.append("Staff Member " + employees.get(signedIn).getUsername() + " has logged in\n");
					addStaffTab();
					logInOperation();
				}
				else if (userType.equals("staff") && oneTimePassword.accountExists(housekeepers, username, password) >= 0) {
					signedIn = oneTimePassword.accountExists(housekeepers, username, password);
					txtOutput.append("Houskeeper " + housekeepers.get(signedIn).getUsername() + " has logged in\n");
					addHousekeeperTab();
					logInOperation();
				}
				else if (userType.equals("customer") && oneTimePassword.accntExists2D(databaseCustomer, username, password) >= 0) {
					signedIn = oneTimePassword.accntExists2D(databaseCustomer, username, password);
					selectedCustomer = signedIn;
					arrayListSignIn = 0;
					txtOutput.append("Customer " + databaseCustomer[signedIn][0] + " has logged in\n");
					if (customers.get(arrayListSignIn).getNotification().size() != 0)
						txtOutput.append("Notifications: " + customers.get(arrayListSignIn).getNotification() + "\n");
					logInOperation();
					btn_Check_In.setEnabled(true);
					btn_Check_Out.setEnabled(true);
				}
				else
					txtOutput.append("Log In failed\n");
				txtUsername.setText("");
				txtPassword.setText("");
			}
		});
		
		JButton btn_Create_Account = new JButton("Create Account");
		btn_Create_Account.setFont(new Font("Arial", Font.PLAIN, 11));
		Page_Log_In.add(btn_Create_Account);
		btn_Create_Account.setBounds(682, 202, 133, 23);
		btn_Create_Account.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String create_username = txtUsername_CA.getText();
				String create_password1 = String.valueOf(txtPassword1_CA.getPassword());
				String create_password2 = String.valueOf(txtPassword2_CA.getPassword());
				if (create_password1.equals(create_password2)) { //if the passwords match, then create a new customer account
					customers.add(new UserNode(create_username, create_password1));
					txtUsername_CA.setText("");
					txtPassword1_CA.setText("");
					txtPassword2_CA.setText("");
				}
				else
					txtOutput.append("Passwords do not match.\n");
				
				// Code below checks if username already exists in db
				// if it doesnt: add username and password to db
				
				//  1 ---- customers.size()-1   < -- range of below for loop
				
				tempUserName = customers.get(customers.size()-1).getUsername();
					
				for (int counter2 = 0; counter2 < (databaseCustomer.length-1); counter2++) { // loop to traverse customer 2d array
													
					if (databaseCustomer[counter2][0].compareTo(tempUserName) == 0) // Nice hack to compare string tags
					{
						rowCount++; // Increments rowcount if there is a hit
						System.out.println("True (Strings are equal)");
						System.out.println(tempUserName);
						System.out.println(databaseCustomer[counter2][0]);
					}
				}								
					
				if (rowCount == 0) // rowcount will equal 0 if there is no other hits on the given username
				{
					String varUsername = customers.get(customers.size()-1).getUsername();
					String varPassword = customers.get(customers.size()-1).getPassword();
					
					databaseCustomer[databaseCustomer.length-1][0] = varUsername;
					databaseCustomer[databaseCustomer.length-1][1] = varPassword;
				
					try {
						testCustomerGUI.addCustomerData(varUsername, varPassword, null, null, null, null, null, null, null);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("Customer confirmed.");
					btn_Create_Account.setEnabled(false);
				}
				else
				{
					System.out.println("Customer already exists within database.");
				}
				System.out.println(databaseCustomer.length);
				System.out.println(databaseCustomer[databaseCustomer.length-1][0]);
				rowCount = 0;
						
			}
		});
		
		
		
		/*Book a Room Page*************************************************************/	
		
		JPanel Page_Book_a_Room = new JPanel();
		tabbedPane.addTab("     Book a Room     ", null, Page_Book_a_Room, null);
		Page_Book_a_Room.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(554, 219, 182, 20);
		Page_Book_a_Room.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("How many occupants will\r \nbe staying in your room?");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_2.setToolTipText("");
		lblNewLabel_2.setBounds(98, 21, 298, 27);
		Page_Book_a_Room.add(lblNewLabel_2);
		
		txt_Occupants = new JTextField();
		txt_Occupants.setFont(new Font("Arial", Font.PLAIN, 11));
		txt_Occupants.setBounds(181, 53, 86, 20);
		Page_Book_a_Room.add(txt_Occupants);
		txt_Occupants.setColumns(10);
		
		JRadioButton rdbtn_Bidet = new JRadioButton("Bidet Included?");
		rdbtn_Bidet.setFont(new Font("Arial", Font.PLAIN, 11));
		rdbtn_Bidet.setBounds(181, 98, 132, 23);
		Page_Book_a_Room.add(rdbtn_Bidet);
		
		JRadioButton rdbtn_Kitchen = new JRadioButton("Kitchen Included?");
		rdbtn_Kitchen.setFont(new Font("Arial", Font.PLAIN, 11));
		rdbtn_Kitchen.setBounds(181, 124, 145, 23);
		Page_Book_a_Room.add(rdbtn_Kitchen);
		
		JRadioButton rdbtn_Jacuzzi = new JRadioButton("Jacuzzi Included?");
		rdbtn_Jacuzzi.setFont(new Font("Arial", Font.PLAIN, 11));
		rdbtn_Jacuzzi.setBounds(181, 150, 145, 23);
		Page_Book_a_Room.add(rdbtn_Jacuzzi);
		
		JButton btn_Find_a_Room = new JButton("Find a Room");
		btn_Find_a_Room.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_Find_a_Room.setBounds(168, 218, 145, 23);
		Page_Book_a_Room.add(btn_Find_a_Room);
		btn_Find_a_Room.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String occupants = txt_Occupants.getText();
				String bidet = null;
				if (rdbtn_Bidet.isSelected())
					bidet = "1";
				else
					bidet = "0";
				String kitchen = null;
				if (rdbtn_Kitchen.isSelected())
					kitchen = "1";
				else
					kitchen = "0";
				String jacuzzi = null;
				if (rdbtn_Jacuzzi.isSelected())
					jacuzzi = "1";
				else
					jacuzzi = "0";
				int roomNumber = 0;
				roomMatcher matchObject = new roomMatcher();
				roomNumber = matchObject.match(database, occupants, kitchen, jacuzzi, bidet);
				txtOutput.append("Your room number is " + roomNumber + "\n");
			}
		});
		
		JButton btn_Book_Room = new JButton("View Room Database");
		btn_Book_Room.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_Book_Room.setBounds(554, 150, 216, 23);
		Page_Book_a_Room.add(btn_Book_Room);
		btn_Book_Room.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewDatabase windowTest = new viewDatabase(database);
				windowTest.displayRooms(database);
				//send the user to a view of all rooms so they may select the one they like
			}
		});
		
		JButton bookThisRoom = new JButton("Book This Room");
		bookThisRoom.setBounds(742, 218, 132, 23);
		Page_Book_a_Room.add(bookThisRoom);
		bookThisRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//send the user to a view of all rooms so they may select the one they like
				desiredRoom = textField.getText();
				System.out.println(desiredRoom);
				
				for (int counter1 = 0; counter1 < (database.length); counter1++) { // loop to traverse customer 2d array
					
					if (database[counter1][0].compareTo(desiredRoom) == 0 && rowCount == 0 && database[counter1][3].compareTo("0") == 0) // Nice hack to compare string tags
					{
						rowCount++; // Increments rowcount if there is a hit
						System.out.println("True (Strings are equal)");
						System.out.println("Room " + desiredRoom + " exists!!");
						desiredCount = counter1;
					}
				}
				if (rowCount > 0)
				{
					String pin = key.makeKey();
					
					database[desiredCount][3] = "1";
					databaseCustomer[selectedCustomer][7] = desiredRoom;
					databaseCustomer[selectedCustomer][8] = pin;
					try {
						testGUI.updateHotelVacancy(desiredRoom, "1");
						testCustomerGUI.updateCustomerRoom(databaseCustomer[selectedCustomer][0], desiredRoom);
						testCustomerGUI.updateCustomerPW(databaseCustomer[selectedCustomer][0], pin);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("Success!! You have been given room: " + desiredRoom);
					txtOutput.append("The pin for your room is " + pin + "\n");
				}
				else
					System.out.println("Failure!! Room doesn't exist or is already vacant!!");
				rowCount = 0;
			}
			
		});
		

		
		JTextPane txtpnToViewOur = new JTextPane();
		txtpnToViewOur.setBackground(UIManager.getColor("Button.background"));
		txtpnToViewOur.setText("To view our room selection, \r\nclick \"View Room Database\"");
		txtpnToViewOur.setBounds(584, 70, 223, 93);
		Page_Book_a_Room.add(txtpnToViewOur);
		btn_Book_Room.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//send the user to a view of all rooms so they may select the one they like
				viewDatabase windowTest = new viewDatabase(database);
                windowTest.displayRooms(database);
			}
		});
        

		
		
	
		/*Room Control Page*******************************************************************/
		JPanel Page_Room_Control = new JPanel();
		tabbedPane.addTab("     Room Control     ", null, Page_Room_Control, null);
		tabbedPane.setEnabledAt(3, false);
		Page_Room_Control.setLayout(null);
		
		JLabel lbl_Current_Temp = new JLabel("Current Temperature: ");
		lbl_Current_Temp.setFont(new Font("Arial", Font.PLAIN, 11));
		lbl_Current_Temp.setBounds(50, 66, 250, 14);
		Page_Room_Control.add(lbl_Current_Temp);
		
		JLabel lblNewLabel_14 = new JLabel("Desired Temperature:                                \u00B0F");
		lblNewLabel_14.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_14.setBounds(50, 102, 263, 14);
		Page_Room_Control.add(lblNewLabel_14);
		
		txt_Desired_Temp = new JTextField();
		txt_Desired_Temp.setFont(new Font("Arial", Font.PLAIN, 11));
		txt_Desired_Temp.setBounds(164, 100, 73, 20);
		Page_Room_Control.add(txt_Desired_Temp);
		txt_Desired_Temp.setColumns(10);
		
		JButton btn_Temp_Update = new JButton("Update");
		btn_Temp_Update.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_Temp_Update.setBounds(50, 144, 89, 23);
		Page_Room_Control.add(btn_Temp_Update);
		btn_Temp_Update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp = txt_Desired_Temp.getText();
				if(tempObject.dout == null) {
					System.out.println("Could Not Update"); 
				}
				else {
					double double_temp = Double.parseDouble(temp);
					if(double_temp >= 40 && double_temp <= 90) {  // Condition for temp being acceptable
						// tempObject.activateTemp(temp);
						database[desiredCount][9] = temp;
						database[desiredCount][10] = "Customer Update: " + LocalDateTime.now();
						try {
							testGUI.updateHotelCurTemp(desiredRoom, temp);
							testGUI.updateHotelTLC(desiredRoom, "Customer Update: " + LocalDateTime.now());
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}					
					}
				}
			}
		});
		
		JButton btn_Comfort_Mode = new JButton("Comfort Mode");
		btn_Comfort_Mode.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_Comfort_Mode.setBounds(164, 144, 149, 23);
		Page_Room_Control.add(btn_Comfort_Mode);
		btn_Comfort_Mode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				database[desiredCount][8] = "Comfort Mode";
				database[desiredCount][10] = "CM: " + LocalDateTime.now();
				
				try {
					testGUI.updateHotelTempControl(desiredRoom, "Comfort Mode");
					testGUI.updateHotelTLC(desiredRoom, "CM: " + LocalDateTime.now());
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					tempObject.comfort_mode(); 
				}
				catch(Exception a){
					a.printStackTrace();} 
			}
		});
		JButton btn_Temp_Show = new JButton("Show");
		btn_Temp_Show.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_Temp_Show.setBounds(310, 62, 89, 23);
		Page_Room_Control.add(btn_Temp_Show);
		
		JLabel lblNewLabel_32 = new JLabel("Temperature");
		lblNewLabel_32.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_32.setBounds(114, 28, 179, 23);
		Page_Room_Control.add(lblNewLabel_32);
		btn_Temp_Show.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tempObject.displayTemp();
				lbl_Current_Temp.setText(tempObject.displayTemp()); 
			}
		});
		
		JLabel lblNewLabel_100 = new JLabel("Ventilation");
		lblNewLabel_100.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_100.setBounds(600, 28, 177, 23);
		Page_Room_Control.add(lblNewLabel_100);
		
		JButton btn_Fan_On_High = new JButton("High");
		btn_Fan_On_High.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_Fan_On_High.setBounds(501, 62, 89, 23);
		Page_Room_Control.add(btn_Fan_On_High);
		btn_Fan_On_High.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try {
					tempObject.fan_on_high(); 
				}
				catch(Exception a){
					a.printStackTrace();} 
			}
		});
		
		JButton btn_Fan_On_Low = new JButton("Low");
		btn_Fan_On_Low.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_Fan_On_Low.setBounds(600, 62, 89, 23);
		Page_Room_Control.add(btn_Fan_On_Low);
		btn_Fan_On_Low.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try {
					tempObject.fan_on_low(); 
				}
				catch(Exception a){
					a.printStackTrace();} 
			}
		});
		
		JButton btn_Fan_Off = new JButton("Off");
		btn_Fan_Off.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_Fan_Off.setBounds(699, 62, 89, 23);
		Page_Room_Control.add(btn_Fan_Off);
		btn_Fan_Off.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try {
					tempObject.fan_off(); 
				}
				catch(Exception a){
					a.printStackTrace();} 
			}
		});
		
		JLabel lblNewLabel_102 = new JLabel("Lighting");
		lblNewLabel_102.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_102.setBounds(600, 123, 177, 23);
		Page_Room_Control.add(lblNewLabel_102);
		
		JButton btn_Light_On = new JButton("On");
		btn_Light_On.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_Light_On.setBounds(550, 160, 89, 23);
		Page_Room_Control.add(btn_Light_On);
		btn_Light_On.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try {
					tempObject.light_on();
				}
				catch(Exception a){
					a.printStackTrace();} 
			}
		});
		
		JButton btn_Light_Off = new JButton("Off");
		btn_Light_Off.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_Light_Off.setBounds(650, 160, 89, 23);
		Page_Room_Control.add(btn_Light_Off);
		btn_Light_Off.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try {
					tempObject.light_off();
				}
				catch(Exception a){
					a.printStackTrace();} 
			}
		});
		
		
		JLabel lblNewLabel_MotionSensor = new JLabel("Motion Sensor");
		lblNewLabel_MotionSensor.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_MotionSensor.setBounds(578, 233, 177, 23);
		Page_Room_Control.add(lblNewLabel_MotionSensor);
		
		JButton btn_Leave = new JButton("Leave");
		btn_Leave.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_Leave.setBounds(650, 266, 89, 23);
		Page_Room_Control.add(btn_Leave);
		btn_Leave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try {
					tempObject.leave();
				}
				catch(Exception a){
					a.printStackTrace();} 
			}
		});
		
		JButton btn_Arrive = new JButton("Arrive");
		btn_Arrive.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_Arrive.setBounds(550, 266, 89, 23);
		Page_Room_Control.add(btn_Arrive);
		btn_Arrive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try {
					tempObject.arrive();
				}
				catch(Exception a){
					a.printStackTrace();} 
			}
		});
		
		
		/*Housekeeping Page*******************************************************************/
		JPanel Page_Housekeeping = new JPanel();
		tabbedPane.addTab("     Housekeeping     ", null, Page_Housekeeping, null);
		tabbedPane.setEnabledAt(4, false);
		Page_Housekeeping.setLayout(null);
		
		JComboBox cBox_day = new JComboBox();
		cBox_day.setFont(new Font("Arial", Font.PLAIN, 11));
		cBox_day.setModel(new DefaultComboBoxModel(new String[] {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"}));
		cBox_day.setMaximumRowCount(7);
		cBox_day.setBounds(206, 99, 101, 22);
		Page_Housekeeping.add(cBox_day);
		
		JComboBox cBox_hour = new JComboBox();
		cBox_hour.setFont(new Font("Arial", Font.PLAIN, 11));
		cBox_hour.setModel(new DefaultComboBoxModel(new String[] {"0","1","2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		cBox_hour.setMaximumRowCount(12);
		cBox_hour.setBounds(383, 99, 64, 22);
		Page_Housekeeping.add(cBox_hour);
		
		JButton btn_Submit_Housekeeping = new JButton("Submit");
		btn_Submit_Housekeeping.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_Submit_Housekeeping.setBounds(508, 99, 89, 23);
		Page_Housekeeping.add(btn_Submit_Housekeeping);
		
		JLabel lblNewLabel_26 = new JLabel("Day");
		lblNewLabel_26.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_26.setBounds(231, 70, 76, 22);
		Page_Housekeeping.add(lblNewLabel_26);
		
		JLabel lblNewLabel_27 = new JLabel("Hour");
		lblNewLabel_27.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_27.setBounds(383, 74, 131, 18);
		Page_Housekeeping.add(lblNewLabel_27);
		
		JLabel lblNewLabel_28 = new JLabel("Active Requests");
		lblNewLabel_28.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_28.setBounds(243, 136, 200, 22);
		Page_Housekeeping.add(lblNewLabel_28);
		
		txt_Active_Requests.setEditable(false);
		txt_Active_Requests.setFont(new Font("Arial", Font.PLAIN, 13));
		txt_Active_Requests.setBounds(202, 169, 431, 149);
		Page_Housekeeping.add(txt_Active_Requests);
		btn_Submit_Housekeeping.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String day = (String)cBox_day.getSelectedItem();
				String hour = (String)cBox_hour.getSelectedItem();
				int result = hkrequests.addNode(day, hour, false, Integer.parseInt(databaseCustomer[selectedCustomer][7]));
				if (result == -1)
					txtOutput.append("Attempted to schedule housekeeping within 2 hours of NOW. Please try again with a valid time and day.\n");
				else if (result == -2)
					txtOutput.append("ERROR: Section of code within the priorityCalculator method in CleaningNode class reached!\n");
				else if (result == -3)
					txtOutput.append("This request already exists.\n");
				else if (result == -4)
					txtOutput.append("ERROR: Cleaning Node unable to be added to HPHeap.\n");
				else if (result == 0) {
					txtOutput.append("Request for housekeeping on " + day + " at " + hour + " has been received!\n");
					txt_Active_Requests.append(day + " " + hour + "\n");
					hkrequests.addNode(day, hour, false, Integer.parseInt(databaseCustomer[selectedCustomer][7]));
				}
			}
		});
		
		
		/*TV Page*****************************************************************************/
		
		
		JPanel Page_TV = new JPanel();
		tabbedPane.addTab("     TV     ", null, Page_TV, null);
		Page_TV.setLayout(null);
		
		JTextArea txtrChannel = new JTextArea();
		txtrChannel.setFont(new Font("Arial", Font.PLAIN, 20));
		txtrChannel.setBackground(Color.LIGHT_GRAY);
		txtrChannel.setText(
				  "    Channel                    Name\r\n"
				+ "       1                       ESPN\r\n"
				+ "       2                        CBS\r\n"
				+ "       3                        TNT\r\n"
				+ "       4                        NBC\r\n"
				+ "       5                        FOX\r\n"
				+ "       6                        TBS\r\n"
				+ "       7                        ABC\r\n"
				+ "       8                        FS1\r\n"
				+ "      13                        PBS");
		txtrChannel.setEditable(false);
		txtrChannel.setBounds(306, 46, 327, 276);
		Page_TV.add(txtrChannel);
		
		/*Room Service Page*******************************************************************/
		JPanel Page_Room_Service = new JPanel();
		tabbedPane.addTab("     Room Service     ", null, Page_Room_Service, null);
		Page_Room_Service.setLayout(null);
		
		JComboBox cBox_Qty = new JComboBox();
		cBox_Qty.setFont(new Font("Arial", Font.PLAIN, 11));
		cBox_Qty.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		cBox_Qty.setMaximumRowCount(5);
		cBox_Qty.setBounds(207, 113, 43, 22);
		Page_Room_Service.add(cBox_Qty);
		
		JLabel lblNewLabel_21 = new JLabel("Quantity");
		lblNewLabel_21.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_21.setBounds(194, 80, 109, 22);
		Page_Room_Service.add(lblNewLabel_21);
		
		JComboBox cBox_Item = new JComboBox();
		cBox_Item.setFont(new Font("Arial", Font.PLAIN, 11));
		cBox_Item.setModel(new DefaultComboBoxModel(new String[] {"Towel", "Pillow", "Blanket", "Cot", "Toilet Paper", "Paper Towel", "Tissue Box", "Bar Soap", "Washcloth", "Shampoo", "Conditioner", "Other"}));
		cBox_Item.setBounds(302, 113, 69, 22);
		Page_Room_Service.add(cBox_Item);
		
		JLabel lblNewLabel_22 = new JLabel("Item");
		lblNewLabel_22.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_22.setBounds(312, 80, 81, 22);
		Page_Room_Service.add(lblNewLabel_22);
		
		JTextArea txt_special = new JTextArea();
		txt_special.setFont(new Font("Arial", Font.PLAIN, 13));
		txt_special.setBounds(207, 182, 546, 81);
		Page_Room_Service.add(txt_special);
		
		JButton btn_Request_Item = new JButton("Submit");
		btn_Request_Item.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_Request_Item.setBounds(207, 274, 89, 23);
		Page_Room_Service.add(btn_Request_Item);
		btn_Request_Item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serviceQueue.addNode(Integer.parseInt((String)cBox_Qty.getSelectedItem()), Integer.parseInt(databaseCustomer[selectedCustomer][7]), (String)cBox_Item.getSelectedItem(), txt_special.getText());
				txt_special.setText("");
				txtOutput.append("Your request has been added to the queue.\n");
			}
		});
		
		JLabel lblNewLabel_23 = new JLabel("Additional Notes");
		lblNewLabel_23.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_23.setBounds(207, 146, 200, 25);
		Page_Room_Service.add(lblNewLabel_23);
		
		
		/*Payment Page************************************************************************/
		JPanel Page_Payment = new JPanel();
		tabbedPane.addTab("     Payment     ", null, Page_Payment, null);
		Page_Payment.setLayout(null);
		
		JLabel lblNewLabel_15 = new JLabel("Name on card:");
		lblNewLabel_15.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_15.setBounds(241, 58, 103, 14);
		Page_Payment.add(lblNewLabel_15);
		
		JLabel lblNewLabel_16 = new JLabel("Card number:");
		lblNewLabel_16.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_16.setBounds(241, 83, 87, 14);
		Page_Payment.add(lblNewLabel_16);
		
		JLabel lblNewLabel_17 = new JLabel("Expiration date:");
		lblNewLabel_17.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_17.setBounds(241, 108, 103, 14);
		Page_Payment.add(lblNewLabel_17);
		
		JLabel lblNewLabel_18 = new JLabel("CVV: ");
		lblNewLabel_18.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_18.setBounds(241, 133, 46, 14);
		Page_Payment.add(lblNewLabel_18);
		
		JLabel lblNewLabel_19 = new JLabel("Driver's Liscense Number:");
		lblNewLabel_19.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_19.setBounds(241, 158, 173, 14);
		Page_Payment.add(lblNewLabel_19);
		
		txt_card_name = new JTextField();
		txt_card_name.setFont(new Font("Arial", Font.PLAIN, 11));
		txt_card_name.setBounds(389, 55, 86, 20);
		Page_Payment.add(txt_card_name);
		txt_card_name.setColumns(10);
		
		txt_card_number = new JTextField();
		txt_card_number.setFont(new Font("Arial", Font.PLAIN, 11));
		txt_card_number.setText("");
		txt_card_number.setBounds(389, 80, 86, 20);
		Page_Payment.add(txt_card_number);
		txt_card_number.setColumns(10);
		
		txt_card_exp = new JTextField();
		txt_card_exp.setFont(new Font("Arial", Font.PLAIN, 11));
		txt_card_exp.setBounds(389, 105, 86, 20);
		Page_Payment.add(txt_card_exp);
		txt_card_exp.setColumns(10);
		
		txt_card_cvv = new JTextField();
		txt_card_cvv.setFont(new Font("Arial", Font.PLAIN, 11));
		txt_card_cvv.setBounds(389, 130, 86, 20);
		Page_Payment.add(txt_card_cvv);
		txt_card_cvv.setColumns(10);
		
		txt_liscense = new JTextField();
		txt_liscense.setFont(new Font("Arial", Font.PLAIN, 11));
		txt_liscense.setBounds(389, 155, 86, 20);
		Page_Payment.add(txt_liscense);
		txt_liscense.setColumns(10);
		
		JLabel lblNewLabel_20 = new JLabel("Enter payment information to start your tab");
		lblNewLabel_20.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_20.setBounds(222, 33, 253, 14);
		Page_Payment.add(lblNewLabel_20);
		
		JButton btn_payment_submition = new JButton("Submit");
		btn_payment_submition.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_payment_submition.setBounds(307, 196, 89, 23);
		Page_Payment.add(btn_payment_submition);
		
		btn_payment_submition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String cardName = txt_card_name.getText();
				String cardNumber = txt_card_number.getText();
				String cardExp = txt_card_exp.getText();
				String cardCvv = txt_card_cvv.getText();
				String liscense = txt_liscense.getText();
				
				databaseCustomer[selectedCustomer][2] = cardName;
				databaseCustomer[selectedCustomer][3] = cardNumber;
				databaseCustomer[selectedCustomer][4] = cardExp;
				databaseCustomer[selectedCustomer][5] = cardCvv;
				databaseCustomer[selectedCustomer][6] = liscense;
				
				try {
					testCustomerGUI.updateCustomerPayment(databaseCustomer[selectedCustomer][0], cardName, cardNumber, cardExp, cardCvv, liscense);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("Your payment information has been updated!!");
				txtOutput.append("Your payment information has been updated!!");
			}
		});
		
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLogOut.setEnabled(false);
				if (tabbedPane.getTabCount() >= 9)			//remove last tab when logging out if it exists
					tabbedPane.removeTabAt(8);
				btn_Manager.setBackground(Color.LIGHT_GRAY);		//Make log out empty all fields in code
				btn_Staff.setBackground(Color.LIGHT_GRAY);
				btn_Customer.setBackground(Color.LIGHT_GRAY);
				txtUsername.setText("");
				txtPassword.setText("");
				txtUsername_CA.setText("");
				txtPassword1_CA.setText("");
				txtPassword2_CA.setText("");
				txt_Occupants.setText("");
				txt_Desired_Temp.setText("");
				txt_Active_Requests.setText("");
				txt_special.setText("");
				txt_card_name.setText("");
				txt_card_number.setText("");
				txt_card_exp.setText("");
				txt_card_cvv.setText("");
				txt_liscense.setText("");
				txtOutput.setText("");
				rdbtn_Bidet.setSelected(false);
				rdbtn_Kitchen.setSelected(false);
				rdbtn_Jacuzzi.setSelected(false);
				tabbedPane.setEnabledAt(2, false); //cannot access tabs unless signed in
				tabbedPane.setEnabledAt(3, false);
				tabbedPane.setEnabledAt(4, false);
				tabbedPane.setEnabledAt(5, false);
				tabbedPane.setEnabledAt(6, false);
				tabbedPane.setEnabledAt(7, false);
				tabbedPane.setSelectedIndex(1);
				btn_Check_In.setEnabled(false);
				btn_Check_Out.setEnabled(false);
				txtOutput.append("Log Out Complete\n");
			}
		});
		
		tabbedPane.setEnabledAt(2, false); //cannot access tabs unless signed in
		tabbedPane.setEnabledAt(3, false);
		tabbedPane.setEnabledAt(5, false);
		tabbedPane.setEnabledAt(6, false);
		tabbedPane.setEnabledAt(7, false);
	}
	
	/*Housekeeper Page**********************************************************************/
	public void addHousekeeperTab() {
		JPanel Page_Housekeeper = new JPanel();
		tabbedPane.addTab("     Housekeeper     ", null, Page_Housekeeper, null);
		Page_Housekeeper.setLayout(null);
		
		JTextArea txtIncomingRequests = new JTextArea();
		txtIncomingRequests.setFont(new Font("Arial", Font.PLAIN, 13));
		txtIncomingRequests.setBounds(45, 68, 314, 167);
		txtIncomingRequests.setEditable(false);
		Page_Housekeeper.add(txtIncomingRequests);
		for (int i = 0; i < hkrequests.toSRTDArray().length; i++) { //print incoming requests
			txtIncomingRequests.append(hkrequests.toSRTDArray()[i].getDay() + " " + hkrequests.toSRTDArray()[i].getHour() + "\n");
		}
		
		JLabel lblNewLabel_29 = new JLabel("Incoming Requests");
		lblNewLabel_29.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_29.setBounds(45, 43, 159, 14);
		Page_Housekeeper.add(lblNewLabel_29);
		
		JTextArea txtAcceptedRequests = new JTextArea();
		txtAcceptedRequests.setFont(new Font("Arial", Font.PLAIN, 13));
		txtAcceptedRequests.setBounds(542, 66, 314, 169);
		txtAcceptedRequests.setEditable(false);
		Page_Housekeeper.add(txtAcceptedRequests);
		if (housekeepers.get(signedIn).userHeap != null) //print accepted requests if they exist
			for (int i = 0; i < (housekeepers.get(signedIn).userHeap).toSRTDArray().length; i++) { 
				txtIncomingRequests.append((housekeepers.get(signedIn).userHeap).toSRTDArray()[i].getDay() + " " + (housekeepers.get(signedIn).userHeap).toSRTDArray()[i].getHour() + "\n");
			}
		
		JButton btnAcceptRequest = new JButton("Accept Request");
		btnAcceptRequest.setBounds(155, 265, 142, 23);
		Page_Housekeeper.add(btnAcceptRequest);
		btnAcceptRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (hkrequests.toSRTDArray().length == 0)
					txtOutput.append("There are no requests to be fulfilled at this time.\n");
				else {
					(housekeepers.get(signedIn).userHeap).addNode(hkrequests.poll());
					txtAcceptedRequests.setText("");
					CleaningNode[] cleanerRequests = (housekeepers.get(signedIn).userHeap).toSRTDArray();
					for (int i = 0; i < cleanerRequests.length; i++) {
						txtAcceptedRequests.append(cleanerRequests[i].getDay() + " " + cleanerRequests[i].getHour() + "\n");
					}
					txtIncomingRequests.setText("");
					CleaningNode[] tempRequests = hkrequests.toSRTDArray();
					for (int i = 0; i < tempRequests.length; i++) {
						txtIncomingRequests.append(tempRequests[i].getDay() + " " + tempRequests[i].getHour() + "\n");
					}
					String hkeeperPW = key.makeKey();
					txtOutput.append("Your temporary room key is: " + hkeeperPW + "\n");
				}
			}
		});
		
		JLabel lblNewLabel_30 = new JLabel("Accepted Requests");
		lblNewLabel_30.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_30.setBounds(542, 43, 134, 14);
		Page_Housekeeper.add(lblNewLabel_30);
		
		JButton btnRequestComplete = new JButton("Complete Active Request");
		btnRequestComplete.setBounds(610, 265, 182, 23);
		Page_Housekeeper.add(btnRequestComplete);
		btnRequestComplete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//completedRequest.setCleaned = true; //IF WE CHOOSE TO DO STUFF WITH CLEANED IT WOULD BE HERE
				//completedRequest = null;
				if (housekeepers.get(signedIn).userHeap.peek() == null)
					txtOutput.append("There is no request to be competed\n");
				else {
					CleaningNode[] cleanerRequests = (housekeepers.get(signedIn).userHeap).toSRTDArray();
					txtAcceptedRequests.setText("");
					customers.get(0).addNotification("Your request on " + cleanerRequests[0].getDay() + " at " + cleanerRequests[0].getHour() + " has been completed.");
					(housekeepers.get(signedIn).userHeap).poll();		//the head of housekeeper deleted
					cleanerRequests = (housekeepers.get(signedIn).userHeap).toSRTDArray();
					for (int i = 0; i < cleanerRequests.length; i++) {
						txtAcceptedRequests.append(cleanerRequests[i].getDay() + " " + cleanerRequests[i].getHour() + "\n");
					}
				}
			}
		});
	}
	
	/*Staff Page**************************************************************************/
	public void addStaffTab() {
		JPanel Page_Staff = new JPanel();
		Page_Staff.setLayout(null);
		tabbedPane.addTab("     Staff     ", null, Page_Staff, null);
		
		JLabel lblNewLabel_24_1 = new JLabel("Clock In/Out");
		lblNewLabel_24_1.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_24_1.setBounds(164, 59, 95, 14);
		Page_Staff.add(lblNewLabel_24_1);
		
		JButton btn_Clock_In_1 = new JButton("Clock In");
		btn_Clock_In_1.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_Clock_In_1.setBounds(104, 84, 89, 23);
		Page_Staff.add(btn_Clock_In_1);
		btn_Clock_In_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activeStaff.add(employees.get(signedIn));
				txtOutput.append("Successfully Clocked In\n");
			}
		});
		
		JButton btn_Clock_Out_1 = new JButton("Clock Out");
		btn_Clock_Out_1.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_Clock_Out_1.setBounds(203, 84, 89, 23);
		Page_Staff.add(btn_Clock_Out_1);
		btn_Clock_Out_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (activeStaff.contains(employees.get(signedIn))) {
					activeStaff.remove(employees.get(signedIn));
					txtOutput.append("Successfully Clocked Out\n");
				}
				else
					txtOutput.append("You must clock in before clocking out.\n");
			}
		});
		
		JButton btn_Room_Database_1 = new JButton("View Room Database");
		btn_Room_Database_1.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_Room_Database_1.setBounds(616, 55, 185, 23);
		Page_Staff.add(btn_Room_Database_1);
		btn_Room_Database_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewDatabase windowTest = new viewDatabase(database);
                windowTest.displayRooms(database);
                //send the user to a view of all rooms so they may select the one they like
            }});
		
		JButton btn_Change_Guest_Reservation_1 = new JButton("Change Guest Reservation");
		btn_Change_Guest_Reservation_1.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_Change_Guest_Reservation_1.setBounds(616, 84, 185, 23);
		Page_Staff.add(btn_Change_Guest_Reservation_1);
		
		JTextArea txt_serviceRequests = new JTextArea();
		txt_serviceRequests.setLineWrap(true);
		txt_serviceRequests.setEditable(false);
		txt_serviceRequests.setFont(new Font("Arial", Font.PLAIN, 11));
		txt_serviceRequests.setBounds(119, 153, 233, 158);
		Page_Staff.add(txt_serviceRequests);
		ServiceNode[] tempRequests = serviceQueue.toArray();
		for (int i = 0; i < tempRequests.length; i++) { //print submitted service requests to the text box
			txt_serviceRequests.append(tempRequests[i].getQuantity() + " " + tempRequests[i].getType() + "\n");
		}
		
		JLabel lblNewLabel_25_1 = new JLabel("Incoming Service Requests");
		lblNewLabel_25_1.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_25_1.setBounds(119, 128, 140, 14);
		Page_Staff.add(lblNewLabel_25_1);
		
		JTextArea txt_acceptedRequests = new JTextArea();
		txt_acceptedRequests.setLineWrap(true);
		txt_acceptedRequests.setEditable(false);
		txt_acceptedRequests.setFont(new Font("Arial", Font.PLAIN, 11));
		txt_acceptedRequests.setBounds(439, 149, 502, 158);
		Page_Staff.add(txt_acceptedRequests);
		
		JButton btn_acceptRequest = new JButton("Accept Request");
		btn_acceptRequest.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_acceptRequest.setBounds(164, 322, 140, 23);
		Page_Staff.add(btn_acceptRequest);
		btn_acceptRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (serviceQueue.peek() == null)
					txtOutput.append("There is no service to be accepted at the moment.\n");
				else {
					ServiceNode tempRequest = serviceQueue.poll();
					tempRequest.setAccepted(true);
					serviceOngoing.addNode(tempRequest);
					customers.get(0).addNotification("Your request for " + serviceOngoing.peek().getQuantity() + " " + serviceOngoing.peek().getType() + " has been accepted at time "
					+ LocalTime.now().format(formatter));
					txt_acceptedRequests.append(tempRequest.getQuantity() + " " + tempRequest.getType() + " to room " + tempRequest.getDBRoomNum() + " at time " +
					LocalTime.now().format(formatter) + "\nNote: " + tempRequest.getNote() + "\n");
					txt_serviceRequests.setText("");
					ServiceNode[] tempRequests = serviceQueue.toArray();
					for (int i = 0; i < tempRequests.length; i++) { //print submitted service requests to the text box
						txt_serviceRequests.append(tempRequests[i].getQuantity() + " " + tempRequests[i].getType());
					}
				}
			}
		});
		
		JLabel lblNewLabel_40 = new JLabel("Ongoing Service Requests");
		lblNewLabel_40.setBounds(439, 128, 185, 14);
		Page_Staff.add(lblNewLabel_40);
		
		JButton btn_completeRequest = new JButton("Complete Request");
		btn_completeRequest.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_completeRequest.setBounds(616, 322, 140, 23);
		Page_Staff.add(btn_completeRequest);
		btn_completeRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (serviceOngoing.peek() == null)
					txtOutput.append("There is no accepted task to be completed.\n");
				else {
					customers.get(0).addNotification("Your request for " + serviceOngoing.peek().getQuantity() + " " + serviceOngoing.peek().getType() + " has been completed at time "
				    + LocalTime.now().format(formatter));
					serviceOngoing.poll();
					txt_acceptedRequests.setText("");
					txtOutput.append("Task successfully completed.\n");
					ServiceNode[] tempRequests = serviceOngoing.toArray();
					for (int i = 0; i < tempRequests.length; i++) { //print submitted service requests to the text box
						txt_acceptedRequests.append(tempRequests[i].getQuantity() + " " + tempRequests[i].getType() + " to room " + tempRequests[i].getDBRoomNum() + " at time " +
						LocalTime.now().format(formatter) + "\nNote: " + tempRequests[i].getNote() + "\n");
					}
				}
			}
		});
	}
	
	/*Manager Page************************************************************************/
	public void addManagerTab() {
		JPanel Page_Manager = new JPanel();
		tabbedPane.addTab("     Manager     ", null, Page_Manager, null);
		Page_Manager.setLayout(null);
		
		JLabel lblNewLabel_24 = new JLabel("Clock In/Out");
		lblNewLabel_24.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_24.setBounds(164, 59, 95, 14);
		Page_Manager.add(lblNewLabel_24);
		
		JButton btn_Clock_In = new JButton("Clock In");
		btn_Clock_In.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_Clock_In.setBounds(104, 84, 89, 23);
		Page_Manager.add(btn_Clock_In);
		btn_Clock_In.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//add code for manager clocking in
			}
		});
		
		JButton btn_Clock_Out = new JButton("Clock Out");
		btn_Clock_Out.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_Clock_Out.setBounds(203, 84, 89, 23);
		Page_Manager.add(btn_Clock_Out);
		btn_Clock_Out.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//add code for manager clocking out
			}
		});
		
		JButton btn_Room_Database = new JButton("View Room Database");
		btn_Room_Database.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_Room_Database.setBounds(616, 55, 185, 23);
		Page_Manager.add(btn_Room_Database);
		btn_Room_Database.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewDatabase windowTest = new viewDatabase(database);
                windowTest.displayRooms(database);
                //send the user to a view of all rooms so they may select the one they like
            }
        });
		
		JButton btn_Change_Guest_Reservation = new JButton("Change Guest Reservation");
		btn_Change_Guest_Reservation.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_Change_Guest_Reservation.setBounds(616, 84, 185, 23);
		Page_Manager.add(btn_Change_Guest_Reservation);
		
		JTextArea txt_actives = new JTextArea();
		txt_actives.setEditable(false);
		txt_actives.setFont(new Font("Arial", Font.PLAIN, 11));
		txt_actives.setBounds(119, 153, 233, 158);
		Page_Manager.add(txt_actives);
		
		JLabel lblNewLabel_25 = new JLabel("Active Employees");
		lblNewLabel_25.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_25.setBounds(119, 128, 140, 14);
		Page_Manager.add(lblNewLabel_25);
		
		JButton btn_update_actives = new JButton("Update");
		btn_update_actives.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_update_actives.setBounds(212, 322, 95, 23);
		Page_Manager.add(btn_update_actives);
		btn_update_actives.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < activeStaff.size(); i++) {
					txt_actives.append(activeStaff.get(i).getUsername());
				}
			}
		});
		
		String msterKey = null;
		msterKey = key.makeKey();
		JLabel lblNewLabel_31 = new JLabel("The Master Key is " + msterKey);
		lblNewLabel_31.setBounds(616, 27, 185, 14);
		Page_Manager.add(lblNewLabel_31);
		
		JLabel lblNewLabel_34 = new JLabel("Create Service Employee Account");
		lblNewLabel_34.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_34.setBounds(439, 164, 224, 32);
		Page_Manager.add(lblNewLabel_34);
		
		txt_se_username = new JTextField();
		txt_se_username.setFont(new Font("Arial", Font.PLAIN, 11));
		txt_se_username.setBounds(543, 204, 86, 20);
		Page_Manager.add(txt_se_username);
		txt_se_username.setColumns(10);
		
		JLabel lblNewLabel_35 = new JLabel("Username");
		lblNewLabel_35.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_35.setBounds(449, 207, 84, 14);
		Page_Manager.add(lblNewLabel_35);
		
		JLabel lblNewLabel_36 = new JLabel("Password");
		lblNewLabel_36.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_36.setBounds(449, 243, 84, 14);
		Page_Manager.add(lblNewLabel_36);
		
		txt_se_password = new JTextField();
		txt_se_password.setFont(new Font("Arial", Font.PLAIN, 11));
		txt_se_password.setBounds(543, 240, 86, 20);
		Page_Manager.add(txt_se_password);
		txt_se_password.setColumns(10);
		
		JLabel lblNewLabel_37 = new JLabel("Create Housekeeper Account");
		lblNewLabel_37.setBounds(734, 173, 201, 14);
		Page_Manager.add(lblNewLabel_37);
		
		JLabel lblNewLabel_38 = new JLabel("Username");
		lblNewLabel_38.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_38.setBounds(744, 207, 84, 14);
		Page_Manager.add(lblNewLabel_38);
		
		txt_hk_username = new JTextField();
		txt_hk_username.setFont(new Font("Arial", Font.PLAIN, 11));
		txt_hk_username.setBounds(838, 204, 86, 20);
		Page_Manager.add(txt_hk_username);
		txt_hk_username.setColumns(10);
		
		JLabel lblNewLabel_39 = new JLabel("Password");
		lblNewLabel_39.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_39.setBounds(743, 240, 85, 14);
		Page_Manager.add(lblNewLabel_39);
		
		txt_hk_password = new JTextField();
		txt_hk_password.setFont(new Font("Arial", Font.PLAIN, 11));
		txt_hk_password.setBounds(838, 240, 86, 20);
		Page_Manager.add(txt_hk_password);
		txt_hk_password.setColumns(10);
		
		JButton btn_se = new JButton("Create");
		btn_se.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_se.setBounds(497, 268, 89, 23);
		Page_Manager.add(btn_se);
		btn_se.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				employees.add(new UserNode(txt_se_username.getText(), txt_se_password.getText()));
				txt_se_username.setText("");
				txt_se_password.setText("");
				txtOutput.append("Service Employee Account Created!\n");
			}
		});
		
		JButton btn_hk = new JButton("Create");
		btn_hk.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_hk.setBounds(786, 268, 89, 23);
		Page_Manager.add(btn_hk);
		btn_hk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				housekeepers.add(new UserNode(txt_hk_username.getText(), txt_hk_password.getText()));
				housekeepers.get(housekeepers.size()-1).userHeap = new HKPRHeap();
				txt_hk_username.setText("");
				txt_hk_password.setText("");
				txtOutput.append("Housekeeper Account Created!\n");
			}
		});
	}
	
	public void logInOperation() {
		tabbedPane.setEnabledAt(2, true);
		tabbedPane.setEnabledAt(3, true);
		tabbedPane.setEnabledAt(4, true);
		tabbedPane.setEnabledAt(5, true);
		tabbedPane.setEnabledAt(6, true);
		tabbedPane.setEnabledAt(7, true);
		btnLogOut.setEnabled(true);
	}
}