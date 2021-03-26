package cloud_surf_inn;

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
import java.awt.FlowLayout;
import javax.swing.JRadioButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

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

	/**
	 * Launch the application.
	 */
	public void runWindow(String[][] database1) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cloud_surf_inn_window window = new cloud_surf_inn_window(database1);
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
	public cloud_surf_inn_window(String[][] database2) {
		initialize(database2);
	}

	public void initialize(String[][] database) {
		HPHeap hkrequests = new HPHeap();
		oneTimePassword key = new oneTimePassword();
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cloud Surf Inn");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 80));
		lblNewLabel.setBounds(208, 11, 574, 88);
		frame.getContentPane().add(lblNewLabel);
		
		JTextArea txtOutput = new JTextArea();
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
		
		JButton btnLogOut = new JButton("Log Out");
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
		
		JLabel lblNewLabel_1 = new JLabel("Welcome to the CLOUD SURF INN");
		lblNewLabel_1.setBounds(300, 135, 227, 14);
		Page_Home.add(lblNewLabel_1);
		
		/*Log in Page************************************************************************/
		JPanel Page_Log_In = new JPanel();
		tabbedPane.addTab("     Log In     ", null, Page_Log_In, null);
		Page_Log_In.setLayout(null);
		
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
		lblNewLabel_8.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_8.setBounds(717, 72, 120, 14);
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
		txtUsername_CA.setBounds(735, 101, 86, 20);
		Page_Log_In.add(txtUsername_CA);
		txtUsername_CA.setColumns(10);
		
		txtPassword1_CA = new JPasswordField();
		txtPassword1_CA.setFont(new Font("Arial", Font.PLAIN, 11));
		txtPassword1_CA.setBounds(737, 126, 84, 20);
		Page_Log_In.add(txtPassword1_CA);
		
		txtPassword2_CA = new JPasswordField();
		txtPassword2_CA.setFont(new Font("Arial", Font.PLAIN, 11));
		txtPassword2_CA.setBounds(740, 152, 97, 20);
		Page_Log_In.add(txtPassword2_CA);
		
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
				if (userType.equals("manager") && username.equals("man") && password.equals("man")) {
					txtOutput.append("Manager has logged in\n");
					addManagerTab();
					btnLogOut.setEnabled(true);
				}
				else if (userType.equals("staff") && username.equals("staff") && password.equals("staff")) {
					txtOutput.append("Staff has logged in\n");
					addStaffTab();
					btnLogOut.setEnabled(true);
				}
				else if (userType.equals("staff") && username.equals("hk") && password.equals("hk")) {
					txtOutput.append("Housekeeper has logged in\n");
					addHousekeeperTab(hkrequests.toSRTDArray(),hkrequests);
					btnLogOut.setEnabled(true);
				}
				else if (userType.equals("customer") && username.equals("user") && password.equals("user")) {
					txtOutput.append("Customer has logged in\n");
					btnLogOut.setEnabled(true);
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
				txtUsername_CA.setText("");
				txtPassword1_CA.setText("");
				txtPassword2_CA.setText("");
			}
		});
		
		/*Book a Room Page*************************************************************/
		JPanel Page_Book_a_Room = new JPanel();
		tabbedPane.addTab("     Book a Room     ", null, Page_Book_a_Room, null);
		Page_Book_a_Room.setLayout(null);
		
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
		
		JLabel lblNewLabel_12 = new JLabel("To select your own room click \"Book Room\"");
		lblNewLabel_12.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_12.setBounds(484, 93, 459, 27);
		Page_Book_a_Room.add(lblNewLabel_12);
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
				char[] pin = key.makeKey(10);
				int roomNumber = 0;
				roomMatcher matchObject = new roomMatcher();
				roomNumber = matchObject.match(database, occupants, kitchen, jacuzzi, bidet);
				txtOutput.append("Your room number is " + roomNumber + "\n");
				txtOutput.append("The key for your room is " + pin + "\n");
			}
		});
		
		JButton btn_Book_Room = new JButton("Book Room");
		btn_Book_Room.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_Book_Room.setBounds(647, 150, 89, 23);
		Page_Book_a_Room.add(btn_Book_Room);
		btn_Book_Room.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//send the user to a view of all rooms so they may select the one they like
			}
		});
		
		
		/*Room Control Page*******************************************************************/
		JPanel Page_Room_Control = new JPanel();
		tabbedPane.addTab("     Room Control     ", null, Page_Room_Control, null);
		Page_Room_Control.setLayout(null);
		
		JLabel lblNewLabel_13 = new JLabel("Check In/Out");
		lblNewLabel_13.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_13.setBounds(199, 37, 99, 14);
		Page_Room_Control.add(lblNewLabel_13);
		
		JButton btn_Check_In = new JButton("Check In");
		btn_Check_In.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_Check_In.setBounds(125, 62, 89, 23);
		Page_Room_Control.add(btn_Check_In);
		btn_Check_In.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//update database that user has checked in
			}
		});
		
		JButton btn_Check_Out = new JButton("Check Out");
		btn_Check_Out.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_Check_Out.setBounds(231, 62, 99, 23);
		Page_Room_Control.add(btn_Check_Out);
		btn_Check_Out.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//update database that user has checked out
			}
		});
		
		JLabel lbl_Current_Temp = new JLabel("Current Temperature: ");
		lbl_Current_Temp.setFont(new Font("Arial", Font.PLAIN, 11));
		lbl_Current_Temp.setBounds(150, 170, 134, 14);
		Page_Room_Control.add(lbl_Current_Temp);
		
		JLabel lblNewLabel_14 = new JLabel("Desired Temperature:                                \u00B0F");
		lblNewLabel_14.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_14.setBounds(150, 195, 263, 14);
		Page_Room_Control.add(lblNewLabel_14);
		
		txt_Desired_Temp = new JTextField();
		txt_Desired_Temp.setFont(new Font("Arial", Font.PLAIN, 11));
		txt_Desired_Temp.setBounds(274, 195, 73, 20);
		Page_Room_Control.add(txt_Desired_Temp);
		txt_Desired_Temp.setColumns(10);
		
		JButton btn_Temp_Update = new JButton("Update");
		btn_Temp_Update.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_Temp_Update.setBounds(209, 232, 89, 23);
		Page_Room_Control.add(btn_Temp_Update);
		btn_Temp_Update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp = txt_Desired_Temp.getText();
				TCPsock tempObject = new TCPsock();
				tempObject.activateControl();
			}
		});
		
		
		/*Housekeeping Page*******************************************************************/
		JPanel Page_Housekeeping = new JPanel();
		tabbedPane.addTab("     Housekeeping     ", null, Page_Housekeeping, null);
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
		lblNewLabel_26.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_26.setBounds(231, 74, 46, 14);
		Page_Housekeeping.add(lblNewLabel_26);
		
		JLabel lblNewLabel_27 = new JLabel("Hour");
		lblNewLabel_27.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_27.setBounds(383, 74, 64, 14);
		Page_Housekeeping.add(lblNewLabel_27);
		
		JLabel lblNewLabel_28 = new JLabel("Active Requests");
		lblNewLabel_28.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_28.setBounds(247, 144, 113, 14);
		Page_Housekeeping.add(lblNewLabel_28);
		
		JTextArea txt_Active_Requests = new JTextArea();
		txt_Active_Requests.setEditable(false);
		txt_Active_Requests.setFont(new Font("Arial", Font.PLAIN, 13));
		txt_Active_Requests.setBounds(202, 169, 431, 149);
		Page_Housekeeping.add(txt_Active_Requests);
		btn_Submit_Housekeeping.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String day = (String)cBox_day.getSelectedItem();
				String hour = (String)cBox_hour.getSelectedItem();
				int result = hkrequests.addNode(day, hour, false, 12345);
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
					hkrequests.addNode(day, hour, false, 12345);
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
		lblNewLabel_21.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_21.setBounds(207, 85, 64, 14);
		Page_Room_Service.add(lblNewLabel_21);
		
		JComboBox cBox_Item = new JComboBox();
		cBox_Item.setFont(new Font("Arial", Font.PLAIN, 11));
		cBox_Item.setModel(new DefaultComboBoxModel(new String[] {"Towel", "Pillow", "Blanket", "Cot"}));
		cBox_Item.setBounds(302, 113, 69, 22);
		Page_Room_Service.add(cBox_Item);
		
		JLabel lblNewLabel_22 = new JLabel("Item");
		lblNewLabel_22.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_22.setBounds(302, 85, 46, 14);
		Page_Room_Service.add(lblNewLabel_22);
		
		JButton btn_Request_Item = new JButton("Submit");
		btn_Request_Item.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_Request_Item.setBounds(416, 113, 89, 23);
		Page_Room_Service.add(btn_Request_Item);
		btn_Request_Item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int qty = Integer.parseInt((String)cBox_Qty.getSelectedItem());
				String item = (String)cBox_Item.getSelectedItem();
			}
		});
		
		JTextArea txt_special = new JTextArea();
		txt_special.setFont(new Font("Arial", Font.PLAIN, 13));
		txt_special.setBounds(207, 182, 546, 81);
		Page_Room_Service.add(txt_special);
		
		JLabel lblNewLabel_23 = new JLabel("Special Request");
		lblNewLabel_23.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_23.setBounds(207, 157, 109, 14);
		Page_Room_Service.add(lblNewLabel_23);
		
		JButton btn_Special_Request = new JButton("Submit");
		btn_Special_Request.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_Special_Request.setBounds(664, 274, 89, 23);
		Page_Room_Service.add(btn_Special_Request);
		btn_Special_Request.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String request = txt_special.getText();
			}
		});
		
		
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
				rdbtn_Bidet.setEnabled(false);
				rdbtn_Kitchen.setEnabled(false);
				rdbtn_Jacuzzi.setEnabled(false);
				txtOutput.append("Log Out Complete\n");
			}
		});
	}
	
	/*Housekeeper Page**********************************************************************/
	public void addHousekeeperTab(CleaningNode[] requests, HPHeap hkrequests) {
		HKPRHeap maria = new HKPRHeap();
		
		JPanel Page_Housekeeper = new JPanel();
		tabbedPane.addTab("     Housekeeper     ", null, Page_Housekeeper, null);
		Page_Housekeeper.setLayout(null);
		
		JTextArea txtIncomingRequests = new JTextArea();
		txtIncomingRequests.setFont(new Font("Arial", Font.PLAIN, 13));
		txtIncomingRequests.setBounds(45, 68, 314, 167);
		txtIncomingRequests.setEditable(false);
		Page_Housekeeper.add(txtIncomingRequests);
		for (int i = 0; i < requests.length; i++) {
			txtIncomingRequests.append(requests[i].getDay() + " " + requests[i].getHour() + "\n");
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
		//print accepted nodes
		
		JButton btnAcceptRequest = new JButton("Accept Request");
		btnAcceptRequest.setBounds(155, 265, 142, 23);
		Page_Housekeeper.add(btnAcceptRequest);
		btnAcceptRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maria.addNode(hkrequests.poll());
				txtAcceptedRequests.setText("");
				CleaningNode[] mariaRequests = maria.toSRTDArray();
				for (int i = 0; i < mariaRequests.length; i++) {
					txtAcceptedRequests.append(mariaRequests[i].getDay() + " " + mariaRequests[i].getHour() + "\n");
				}
				txtIncomingRequests.setText("");
				CleaningNode[] tempRequests = hkrequests.toSRTDArray();
				for (int i = 0; i < tempRequests.length; i++) {
					txtIncomingRequests.append(tempRequests[i].getDay() + " " + tempRequests[i].getHour() + "\n");
				}
				//error handle for accepting a node when there is none there
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
				CleaningNode[] mariaRequests = maria.toSRTDArray();
				txtAcceptedRequests.setText("");
				System.out.println("Your request on " + mariaRequests[0].getDay() + " at " + mariaRequests[0].getHour());
				//make message print to output box
				maria.poll();		//the head of housekeeper deleted
				mariaRequests = maria.toSRTDArray();
				for (int i = 0; i < mariaRequests.length; i++) {
					txtAcceptedRequests.append(mariaRequests[i].getDay() + " " + mariaRequests[i].getHour() + "\n");
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
				//add code for staff member checking in
			}
		});
		
		JButton btn_Clock_Out_1 = new JButton("Clock Out");
		btn_Clock_Out_1.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_Clock_Out_1.setBounds(203, 84, 89, 23);
		Page_Staff.add(btn_Clock_Out_1);
		btn_Clock_Out_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//add code for staff member checking out
			}
		});
		
		JButton btn_Room_Database_1 = new JButton("View Room Database");
		btn_Room_Database_1.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_Room_Database_1.setBounds(616, 55, 185, 23);
		Page_Staff.add(btn_Room_Database_1);
		
		JButton btn_Change_Guest_Reservation_1 = new JButton("Change Guest Reservation");
		btn_Change_Guest_Reservation_1.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_Change_Guest_Reservation_1.setBounds(616, 84, 185, 23);
		Page_Staff.add(btn_Change_Guest_Reservation_1);
		
		JTextArea txt_Status_1 = new JTextArea();
		txt_Status_1.setFont(new Font("Arial", Font.PLAIN, 11));
		txt_Status_1.setBounds(119, 153, 233, 158);
		Page_Staff.add(txt_Status_1);
		
		JLabel lblNewLabel_25_1 = new JLabel("Input Service Status");
		lblNewLabel_25_1.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_25_1.setBounds(119, 128, 140, 14);
		Page_Staff.add(lblNewLabel_25_1);
		
		JButton btn_Update_Customer_1 = new JButton("Update Customer");
		btn_Update_Customer_1.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_Update_Customer_1.setBounds(212, 322, 140, 23);
		Page_Staff.add(btn_Update_Customer_1);
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
				//add code for manager checking out
			}
		});
		
		JButton btn_Clock_Out = new JButton("Clock Out");
		btn_Clock_Out.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_Clock_Out.setBounds(203, 84, 89, 23);
		Page_Manager.add(btn_Clock_Out);
		btn_Clock_Out.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//add code for manager checking out
			}
		});
		
		JButton btn_Room_Database = new JButton("View Room Database");
		btn_Room_Database.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_Room_Database.setBounds(616, 55, 185, 23);
		Page_Manager.add(btn_Room_Database);
		
		JButton btn_Change_Guest_Reservation = new JButton("Change Guest Reservation");
		btn_Change_Guest_Reservation.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_Change_Guest_Reservation.setBounds(616, 84, 185, 23);
		Page_Manager.add(btn_Change_Guest_Reservation);
		
		JTextArea txt_Status = new JTextArea();
		txt_Status.setFont(new Font("Arial", Font.PLAIN, 11));
		txt_Status.setBounds(119, 153, 233, 158);
		Page_Manager.add(txt_Status);
		
		JLabel lblNewLabel_25 = new JLabel("Input Service Status");
		lblNewLabel_25.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_25.setBounds(119, 128, 140, 14);
		Page_Manager.add(lblNewLabel_25);
		
		JButton btn_Update_Customer = new JButton("Update Customer");
		btn_Update_Customer.setFont(new Font("Arial", Font.PLAIN, 11));
		btn_Update_Customer.setBounds(212, 322, 140, 23);
		Page_Manager.add(btn_Update_Customer);
		
		JLabel lblNewLabel_31 = new JLabel("The Master Key is 0123456789");
		lblNewLabel_31.setBounds(616, 215, 185, 14);
		Page_Manager.add(lblNewLabel_31);
		btn_Update_Customer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String status = txt_Status.getText();
			}
		});
	}
}