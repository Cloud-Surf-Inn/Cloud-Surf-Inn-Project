package cloud_surf_inn;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class viewDatabase {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void displayRooms(String[][] displayDB1) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					viewDatabase window = new viewDatabase(displayDB1);
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
	public viewDatabase(String[][] displayDB2) {
		initialize(displayDB2);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String[][] displayDB) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JTextArea ta = new JTextArea();
		frame.getContentPane().add(ta, BorderLayout.CENTER);
		JScrollPane sp = new JScrollPane(ta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		frame.add(sp);
		
		for (int i = 0; i < displayDB.length; i++)
		{
			ta.append("Room Number - "+displayDB[i][0]+ "\n"
					+ "Floor Number - "+displayDB[i][1]+ " "
					+ "Number of Beds - "+displayDB[i][2]+ " "
					+ "Vacancy - "+displayDB[i][3]+ " "
					+ "Kitchen - "+displayDB[i][4]+ " "
					+"Jacuzzi - " +displayDB[i][5]+ " "
					+"Bidet - " +displayDB[i][6]+ "\n\n");
		}
	}

}