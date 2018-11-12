import java.util.Random;


/**
 * Playfair grid.
 */
public class Grid
{
    private char[][] rows;
    private static final String ALPHA = "ABCDEFGHIKLMNOPQRSTUVWXYZ";

    public static void swap(int[][] positions) {
        int aux = positions[0][1];
        positions[0][1] = positions[1][1];
        positions[1][1] = aux;
    }
    
    public static void shiftRight(int[][] positions) {
        positions[0][1] = (positions[0][1] + 1) % 5;
        positions[1][1] = (positions[1][1] + 1) % 5;
    }
    
    public static void shiftDown(int[][] positions) {
        positions[0][0] = (positions[0][0] + 1) % 5;
        positions[1][0] = (positions[1][0] + 1) % 5;
    }
    
    public Grid(String keyword) {
        this.rows = new char[5][5];
        char[] letters = (keyword + ALPHA).toCharArray();
        String added = "";
        int index = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (letters[index] == 'J' || added.contains("" + letters[index])) {
                    j--;
                } else {
                    this.rows[i][j] = letters[index];
                    added += letters[index];
                }
                index++;
            }
        }
    }
    
    public int[] findLetter(char letter) {
        int[] position = new int[2];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (letter == this.rows[i][j]) {
                    position[0] = i;
                    position[1] = j;
                }
            }
        }
        return position;
    }
    
    public int[][] findLetters(char[] letters) {
        int[][] positions = new int[letters.length][];
        for (int i = 0; i < letters.length; i++) {
            positions[i] = this.findLetter(letters[i]);
        }
        return positions;
    }
    
    public char getLetter(int[] position) {
        return this.rows[position[0]][position[1]];
    }
    
    public char[] getLetters(int[][] positions) {
        char[] letters = new char[positions.length];
        for (int i = 0; i < positions.length; i++) {
            letters[i] = this.getLetter(positions[i]);
        }
        return letters;
    }
    
    public char[] encryptLetters(char[] letters) {
        int[][] positions = this.findLetters(letters);
        int rule = rule(positions);
        if (rule == 0) {
            swap(positions);
        } else if (rule == 1) {
            shiftDown(positions);
        } else if (rule == 2) {
            shiftRight(positions);
        } else {
            System.out.print("**SERIOUS ERROR**");
        }
        return this.getLetters(positions);
    }
    
    public static int rule(int[][] positions) {
        int rule = 0;
        if (positions[0][0] == positions[1][0]) rule += 1;
        if (positions[0][1] == positions[1][1]) rule += 2;
        return rule;
    }
    
    
}
