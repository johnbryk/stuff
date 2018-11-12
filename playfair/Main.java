import java.util.Scanner;

/**
 * Main class takes input of the form:
 * 
 * MODE (ENCRYPT/DECRYPT)
 * KEY
 * TEXT
 * 
 * and prints the resulting encryption/decryption.
 * 
 * Plaintext can have punctuation, line breaks, etc., but ciphertext is expected to be
 * all caps, all alphabetic, no white space, etc.
 * 
 * To run, compile this and other two classes (Grid and PlayfairCipher), then from command
 * line run:
 * 
 * java Main < input.txt > output.txt
 * 
 * where input.txt is of the form above. Alternately, run in BlueJ or something, but note
 * that it doesn't explicitly prompt you for the mode/key/text.
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