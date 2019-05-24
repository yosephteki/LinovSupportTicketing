/**
 * 
 */
package LinovSupport.Ticketing.encrypt;

import java.nio.charset.Charset;
import java.util.Random;

/**
 * @author Yosep Teki
 *
 */
public class RandomString {
//	public void randomPass() {
//	    byte[] array = new byte[7]; // length is bounded by 7
//	    new Random().nextBytes(array);
//	    String generatedString = new String(array, Charset.forName("UTF-8"));
//	    
//	    System.out.println(generatedString);
//	}
	public String getPass() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 7) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

}
