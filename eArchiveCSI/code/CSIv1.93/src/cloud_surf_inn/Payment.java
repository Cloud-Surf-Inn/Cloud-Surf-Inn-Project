package cloud_surf_inn;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JInternalFrame;
import javax.swing.JToolBar;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Payment {

	private JFrame frame;
	private JTextField txtPleaseInputYour;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField txtDriversLicenseNumber;
	private JTextField txtCreditdebitCardNumber;
	private JTextField txtCardExpirationDate;
	private JTextField txtCvv;
	private JTextField txtNameOnCard;
	
	public String license = null;
	public String cardNum = null;
	public String cardExp = null;
	public String cardCVV = null;
	public String name = null;

	/**
	 * Launch the application.
	 */
	public static void runWindow(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Payment window = new Payment();
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
	public Payment() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public String[] sendArray() {
		String[] paymentArr = new String[5];
		paymentArr[0] = license;
		paymentArr[1] = cardNum;
		paymentArr[2] = cardExp;
		paymentArr[3] = cardCVV;
		paymentArr[4] = name;
								
		return paymentArr;
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(UIManager.getColor("Button.background"));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton Done = new JButton("Done!");
		Done.setBounds(335, 227, 89, 23);
		frame.getContentPane().add(Done);
		Done.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		    	license = textField.getText();
		    	cardNum = textField_1.getText();
		    	cardExp = textField_2.getText();
		    	cardCVV = textField_3.getText();
		    	name = textField_4.getText();
		    	
		    	frame.dispose();
		    }
		});
		
		JButton NVM = new JButton("Nevermind.");
		NVM.setBounds(10, 227, 89, 23);
		frame.getContentPane().add(NVM);
		NVM.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		       frame.dispose();
		    }
		});
		
		txtPleaseInputYour = new JTextField();
		txtPleaseInputYour.setEditable(false);
		txtPleaseInputYour.setForeground(UIManager.getColor("CheckBox.focus"));
		txtPleaseInputYour.setBackground(UIManager.getColor("Button.background"));
		txtPleaseInputYour.setHorizontalAlignment(SwingConstants.CENTER);
		txtPleaseInputYour.setText("Please input your payment information.");
		txtPleaseInputYour.setBounds(13, 11, 411, 20);
		frame.getContentPane().add(txtPleaseInputYour);
		txtPleaseInputYour.setColumns(10);
		
		textField = new JTextField();
		textField.setBounds(243, 65, 122, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(243, 96, 122, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(243, 127, 122, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(243, 158, 122, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(243, 189, 122, 20);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		txtDriversLicenseNumber = new JTextField();
		txtDriversLicenseNumber.setHorizontalAlignment(SwingConstants.TRAILING);
		txtDriversLicenseNumber.setText("Driver's License Number");
		txtDriversLicenseNumber.setEditable(false);
		txtDriversLicenseNumber.setBounds(10, 65, 180, 20);
		frame.getContentPane().add(txtDriversLicenseNumber);
		txtDriversLicenseNumber.setColumns(10);
		
		txtCreditdebitCardNumber = new JTextField();
		txtCreditdebitCardNumber.setHorizontalAlignment(SwingConstants.TRAILING);
		txtCreditdebitCardNumber.setText("Credit/Debit Card Number");
		txtCreditdebitCardNumber.setEditable(false);
		txtCreditdebitCardNumber.setBounds(10, 96, 180, 20);
		frame.getContentPane().add(txtCreditdebitCardNumber);
		txtCreditdebitCardNumber.setColumns(10);
		
		txtCardExpirationDate = new JTextField();
		txtCardExpirationDate.setHorizontalAlignment(SwingConstants.TRAILING);
		txtCardExpirationDate.setText("Card Expiration Date");
		txtCardExpirationDate.setEditable(false);
		txtCardExpirationDate.setBounds(10, 127, 180, 20);
		frame.getContentPane().add(txtCardExpirationDate);
		txtCardExpirationDate.setColumns(10);
		
		txtCvv = new JTextField();
		txtCvv.setHorizontalAlignment(SwingConstants.TRAILING);
		txtCvv.setText("CVV");
		txtCvv.setEditable(false);
		txtCvv.setBounds(10, 158, 180, 20);
		frame.getContentPane().add(txtCvv);
		txtCvv.setColumns(10);
		
		txtNameOnCard = new JTextField();
		txtNameOnCard.setHorizontalAlignment(SwingConstants.TRAILING);
		txtNameOnCard.setText("Name On Card");
		txtNameOnCard.setEditable(false);
		txtNameOnCard.setBounds(10, 189, 180, 20);
		frame.getContentPane().add(txtNameOnCard);
		txtNameOnCard.setColumns(10);
	}
}
