import java.util.Random;
/**
 * Write a description of class HillCipher here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class HillCipher
{
    // instance variables - replace the example below with your own
    private static final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    private int blockSize;
    private MatrixMod key;
    private Random random;

    public HillCipher(int blockSize, MatrixMod key) {
        this.blockSize = blockSize;
        this.key = key;
        this.random = new Random();
    }
    
    public String encrypt(String plaintext) {
        plaintext = prepareString(plaintext);
        int blocks = plaintext.length() / this.blockSize;
        int[] plainlist = stringToList(plaintext);
        int[] cipherlist = new int[plainlist.length];
        
        for (int i = 0; i < blocks; i++) {
            int[] block = new int[blockSize];
            for (int j = 0; j < blockSize; j++) {
                block[j] = plainlist[i * blockSize + j];
            }
            int[] cipherblock = this.key.times(block);
            for (int j = 0; j < blockSize; j++) {
                cipherlist[i * blockSize + j] = cipherblock[j];
            }
        }
        
        String ciphertext = listToString(cipherlist);
        return ciphertext;
    }
    
    public String prepareString(String text) {
        text = text.toUpperCase();
        text = strip(text);
        int extra = (this.blockSize - text.length()) % this.blockSize;
        text = pad(text, extra);
        return text;
    }
    
    public static String strip(String text) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            if (ALPHA.contains("" + text.charAt(i))) {
                result += text.charAt(i);
            }
        }
        return result;
    }
    
    public String pad(String text, int extra) {
        for (int i = 0; i < extra; i++) {
            text += ALPHA.charAt(this.random.nextInt(26));
        }
        return text;
    }
    
    public static int[] stringToList(String text) {
        int[] result = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            result[i] = ALPHA.indexOf(text.charAt(i));
        }
        return result;
    }
    
    public static String listToString(int[] list) {
        String result = "";
        for (int i = 0; i < list.length; i++) {
            result += ALPHA.charAt(list[i]);
        }
        return result;
    }

}
