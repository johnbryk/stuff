import java.util.Scanner;

/**
 * Write a description of class Main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Main
{
 
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        String mode = in.nextLine().toUpperCase();
        String key = in.nextLine().toUpperCase();
        String text = "";
        while (in.hasNextLine()) {
            text += in.nextLine();
        }        
        
        PlayfairCipher cipher = new PlayfairCipher(key);

        if (mode.equals("ENCRYPT")) {
            System.out.println(cipher.encrypt(text));
        } else if (mode.equals("DECRYPT")) {
            System.out.println(cipher.decrypt(text));
        }
    }

    
}