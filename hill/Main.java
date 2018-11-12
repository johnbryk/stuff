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
        
        int[][] rows = {{1, 1, 1},
                        {3, 1, 2},
                        {2, 1, 1}};
        MatrixMod key = new MatrixMod(rows, 26);
        HillCipher cipher = new HillCipher(3, key);
        
        System.out.println(cipher.encrypt(plaintext));
        
    }
    
}