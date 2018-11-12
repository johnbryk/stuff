import java.util.Random;

/**
 * Write a description of class PlayfairCipher here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PlayfairCipher
{
    // instance variables - replace the example below with your own
    private static final String ALPHA = "ABCDEFGHIKLMNOPRQRSTUVWXYZ";
    
    private String keyword;
    private Grid grid;
    private Random random;

    /**
     * Constructor for objects of class PlayfairCipher
     */
    public PlayfairCipher(String keyword)
    {
        this.keyword = keyword;
        this.grid = new Grid(keyword);
        this.random = new Random();
    }
    
    public String encrypt(String plaintext) {
        plaintext = prepareString(plaintext);
        int blocks = plaintext.length() / 2;
        String ciphertext = "";
        
        for (int i = 0; i < blocks; i++) {
            char[] block = new char[2];
            for (int j = 0; j < 2; j++) {
                block[j] = plaintext.charAt(2 * i + j);
            }
            char[] cipherblock = this.grid.encryptLetters(block);
            for (int j = 0; j < 2; j++) {
                ciphertext += cipherblock[j];
            }
        }
        
        return ciphertext;
    }
    
    public String decrypt(String ciphertext) {
        int blocks = ciphertext.length() / 2;
        String plaintext = "";
        
        for (int i = 0; i < blocks; i++) {
            char[] block = new char[2];
            for (int j = 0; j < 2; j++) {
                block[j] = plaintext.charAt(2 * i + j);
            }
            char[] plainblock = this.grid.decryptLetters(block);
            for (int j = 0; j < 2; j++) {
                plaintext += plainblock[j];
            }
        }
        
        return ciphertext;
    } 
       
    public String prepareString(String text) {
        text = text.toUpperCase();
        text = strip(text);
        text = fixDoubles(text);
        int extra = text.length() % 2;
        text = pad(text, extra);
        return text;
    }
    
    public static String strip(String text) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            if (ALPHA.contains("" + text.charAt(i))) {
                result += text.charAt(i);
            } else if (text.charAt(i) == 'J') {
                result += 'I';
            }
        }
        return result;
    }
    
    public static String fixDoubles(String text) {
        String result = "";
        int adjust = 0;
        for (int i = 0; i < text.length(); i++) {
            if ((i + adjust) % 2 == 1 && text.charAt(i) == text.charAt(i - 1)) {
                result += "Q" + text.charAt(i);
                adjust++;
            } else {
                result += text.charAt(i);
            }
        }
        return result;
    }
    
    public String pad(String text, int extra) {
        for (int i = 0; i < extra; i++) {
            text += ALPHA.charAt(this.random.nextInt(25));
        }
        return text;
    }
}
