package cloud_surf_inn;
import java.util.*;
public class oneTimePassword{

    public String makeKey(){

        String SALTCHARS = "1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 4) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
    
    static int accountExists(ArrayList<UserNode> accounts, String username, String password) {
    	for (int i = 0; i < accounts.size(); i++)
    		if (username.equals(accounts.get(i).getUsername()) && password.equals(accounts.get(i).getPassword()))
    			return i;
    	return -1;
    }


	static int accntExists2D(String[][] customerArray, String username, String password) {
		for (int i = 0; i < customerArray.length; i++)
			if (username.equals(customerArray[i][0]) && password.equals(customerArray[i][1]))
				return i;
		return -1;
	}
}