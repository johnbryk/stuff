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
        String plaintext = "";
        
        while (in.hasNextLine()) {
            plaintext += in.nextLine();
        }
        
        PlayfairCipher cipher = new PlayfairCipher("QUIXOTE");
        
        System.out.println(cipher.encrypt(plaintext));
        
    }
    
}