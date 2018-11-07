
/**
 * Matrices modulo something.
 */
public class MatrixMod
{
    private int[][] rows;
    private int mod;
    private int m;
    private int n;
    
    public static void validate(int[][] rows) {
        int n = rows[0].length;
        for (int[] row : rows) {
            if (row.length != n) {
                throw new IllegalArgumentException("Matrix rows must be of equal length.");
            }
        }
    }
    
    public MatrixMod(int[][] rows, int mod)
    {
        validate(rows);
        this.rows = rows;
        this.m = rows.length;
        this.n = rows[0].length;
        for (int[] row : rows) {
            for (int i = 0; i < this.n; i++) {
                row[i] %= mod;
            }
        }
        this.mod = mod;
    }
    
    public int[] times(int[] vector) {
        if (this.n != vector.length) {
            throw new IllegalArgumentException("Vector length must equal number of columns in matrix.");
        }
        int[] result = new int[this.m];
        for (int i = 0; i < this.m; i++) {
            for (int j = 0; j < this.n; j++) {
                result[i] += this.rows[i][j] * vector[j];
                result[i] %= this.mod;
            }
        }
        return result;
    }
    
    public MatrixMod times(MatrixMod that) {
        if (this.n != that.m) {
            throw new IllegalArgumentException("Can only multilply m-by-n matrices and n-by-p matrices.");
        }
        int[][] cols = new int[that.n][];
        MatrixMod aux = that.transpose();
        for (int j = 0; j < that.n; j++) {
            cols[j] = this.times(aux.rows[j]);
        }
        return new MatrixMod(cols, this.mod).transpose();
    }
    
    public MatrixMod minor(int k, int l) {
        int[][] rows = new int[this.m - 1][];
        for (int i = 0; i < this.m - 1; i++) {
            int di = 0;
            if (i >= k) di = 1;
            rows[i] = new int[this.n - 1];
            for (int j = 0; j < this.n - 1; j++) {
                int dj = 0;
                if (j >= l) dj = 1;
                rows[i][j] = this.rows[i + di][j + dj];
            }
        }
        return new MatrixMod(rows, this.mod);
    }
    
    public int cofactor(int k, int l) {
        int u = 1;
        if ((k + l) % 2 == 1) u = -1;
        return u * this.minor(k, l).det();
    }
    
    public int det() {
        if (this.m != this.n) {
            throw new IllegalArgumentException("Cannot find determinant of nonsquare matrices.");
        } else if (this.m == 1) {
            return this.rows[0][0];
        }
        int result = 0;
        for (int j = 0; j < this.n; j++) {
            result += this.rows[0][j] * this.cofactor(0, j);
            result %= this.mod;
        }
        return result;
    }
    
    public MatrixMod transpose() {
        int[][] rows = new int[this.n][this.m];
        for (int i = 0; i < this.m; i++) {
            for (int j = 0; j < this.n; j++) {
                rows[j][i] = this.rows[i][j];
            }
        }
        return new MatrixMod(rows, this.mod);
    }

}
