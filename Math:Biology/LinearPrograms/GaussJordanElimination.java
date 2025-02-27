import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Matrix {
	// Order of the matrix is m X n
	private int m, n;
	double[][] matrix = null;
	double[][] inverse = null;

	public Matrix(int m, int n) {
		this.m = m;
		this.n = n;
	}
	public Matrix(int order) {
		this.n = this.m = order;
	}
	public Matrix() {
		this.m = this.n = 2;
	}

	public boolean createMatrix() {
		if(m != 0 && n != 0) {
			matrix = new double[m][n];
			return true;
		}
		return false;
	}
	private boolean isMatrixCreated() {
		return matrix != null;
	}
	private void fillMatrixRandom() {
		if(!isMatrixCreated()) createMatrix();
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = Math.random() * 1000;
			}
		}
	}
	public void fillMatrix() {
		try {
			// Create the matrix in memory
			// if not already created
			if(!isMatrixCreated()) createMatrix();

			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader in = new BufferedReader(isr);

			for(int i = 0; i < matrix.length; i++) {
				for(int j = 0; j < matrix[i].length; j++) {
					matrix[i][j] = Double.parseDouble(in.readLine());
				}
			}
		} catch(IOException e) {
			System.out.println("\n----- Error 001 -----");
			System.out.println("IOException in function fillMatrix()");
		} catch(NullPointerException e) {
			System.out.println("\n----- Error 002 -----");
			System.out.println("NullPointerException in function fillMatrix()");
		} catch(NumberFormatException e) {
			System.out.println("\n----- Error 003 -----");
			System.out.println("Number parsing exception in function fillMatrix()");
		}
	}
	public void printMatrix() {
		for(double[] arr : matrix) {
			for(double d : arr) {
				System.out.print(String.format("%.5e", d) + "\t\t");
			}
			System.out.println();
		}
	}
	private boolean reduceRow(double[] r1, double[] r2, int pos) {
		try {
			if(r1.length != r2.length) return false;

			double factor = r2[pos] / r1[pos];
			for(int i = pos; i < r1.length; i++) {
				r2[i] -= factor * r1[i];
			}
			return true;
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("\n----- Error 004 -----");
			System.out.println("Array index out of range in function reduceRow()");
		}
		return false;
	}
	public void process() {
		fillMatrixRandom();
		try {
			if(!isMatrixCreated()) createMatrix();

			// k is the index of row with max pivot
			int k;
			// swapTemp is the temporary row swapping variable
			double[] swapTemp;

			for(int i = 0; i < matrix.length; i++) {
				k = i;
				for(int j = i + 1; j < matrix.length; j++) {
					if(matrix[j][i] > matrix[k][i]) {
						k = j;
					}
				}

				if(matrix[k][i] == 0) {
					// Mark this column as free column
					// Will solve later -> not now
					System.out.println("\n----- Error 005 -----");
					System.out.println("System of equations contains free variables");
					System.exit(0);
				}

				// Swap the rows i and k
				swapTemp = matrix[i];
				matrix[i] = matrix[k];
				matrix[k] = swapTemp;

				// Iterate over all subsequent rows
				for(int j = 0; j < matrix.length; j++) {
					// Reduce row by row
					if(i != j && !reduceRow(matrix[i], matrix[j], i)) {
						System.out.println("\n----- Error 006 -----");
						System.out.println("System of equations cannot be solved by this program");
						System.exit(0);
					}
				}
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) throws Exception {
		Matrix m = new Matrix(4);
		m.process();
		m.printMatrix();
	}
}