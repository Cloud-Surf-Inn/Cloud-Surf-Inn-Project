package cloud_surf_inn;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.BorderLayout;

public class TooManyBeds {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void runWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TooManyBeds window = new TooManyBeds();
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
	public TooManyBeds() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 339, 233);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTextArea txtrSorryOurHotel = new JTextArea();
		
		txtrSorryOurHotel.append("Sorry, our hotel cannot accomodate \r\nthat many beds!");
		
		JTextArea textArea = new JTextArea();
		textArea.setText("");
		frame.getContentPane().add(textArea, BorderLayout.NORTH);
	}

}
