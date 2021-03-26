package cloud_surf_inn;
import java.util.*;
public class oneTimePassword{

    char[] makeKey(int len){

        String numbers = "0123456789abcdefghijklmnopqrstuvwxyz";
        Random rndm_method = new Random();
        char[] otp = new char[len];
            for (int i = 0; i < len; i++){
              otp[i] =
              numbers.charAt(rndm_method.nextInt(numbers.length()));
            }
        return otp;
    }
}