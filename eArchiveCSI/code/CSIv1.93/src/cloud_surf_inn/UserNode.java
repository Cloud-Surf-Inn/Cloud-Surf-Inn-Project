package cloud_surf_inn;
import java.util.*;

// this class stores the user information for login information 
public class UserNode {

	private String username;
	private String password;
	private ArrayList<String> notification;    // will contain any and all notifications that must be displayed to the current user in their interface
	public HKPRHeap userHeap;
	private int roomNum;		//could be used to store person's room number for orders and such
	
	public UserNode(String username, String password) {
		this.username = username;
		this.password = password;
		this.setNotification(new ArrayList<String>());
		this.userHeap = null;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<String> getNotification() {
		return notification;
	}

	public void setNotification(ArrayList<String> notification) {
		this.notification = notification;
	}
	
	public void addNotification(String notif) {
		notification.add(notif);
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}
}
