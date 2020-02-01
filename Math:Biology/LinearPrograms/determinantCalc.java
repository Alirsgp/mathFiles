import java.util.*;

public class determinantCalc {
    public static void main(String [] args) {
        System.out.println("Determinant Calculator");
        double [][] matrix = new double[4][4];
        // Elementary row operations of type 3 do not change determinant. 
        // 3 types: Type 1: Interchanging any two equations in the system
        // Type 2: Multiplying any equation in the system by a nonzero constant
        // Type 3: Adding a multiple of one equation to another
        matrix[0][0] = 2;
        matrix[0][1] = 1;
        matrix[0][2] = 3;
        matrix[0][3] = -2;
        // 2 0 0 1

        matrix[1][0] = 0;
        matrix[1][1] = -2;
        matrix[1][2] = -2;
        matrix[1][3] = 0;
        // 2 1 3 -2


        matrix[2][0] = 2;
        matrix[2][1] = 0;
        matrix[2][2] = 0;
        matrix[2][3] = 1;
        // -2 -3 -5 2

        matrix[3][0] = 4;
        matrix[3][1] = -4;
        matrix[3][2] = 4;
        matrix[3][3] = -6;

        double [][] oneDM = new double[1][1];
        oneDM[0][0] = 5;
        // for (int row = 0; row < matrix.length; row += 1) {
        //     for (int col = 0; col < matrix.length; col += 1) {
        //         System.out.println(matrix[row][col]);
        //     }
        // }

        double det = det(matrix);
        System.out.println("Determinant is " + det);


    }

    public static double twoBytwoDet(double [][] input) {
        if (input.length == 1) {
            return input[0][0];
        } else {
            return (input[0][0] * input[1][1]) - (input[0][1] * input[1][0]);
        }
    }

    public static double det(double [][] input) {
        if (input.length <= 2) {
            return twoBytwoDet(input);
        }
        double sum = 0;
        for (int i = 0; i < input.length; i += 1) {
            sum += Math.pow(-1, 1 + i + 1) * input[0][i] * det(getNewMat(i, input));
        }
        return sum;
    }

    public static double[][] getNewMat(int colOmit, double [][] input) {
        int inputLen = input.length;
        double[][] ans = new double[inputLen - 1][inputLen - 1];
        int nextColAns = 0;
        
        for (int i = 1; i < inputLen; i += 1) {
            for (int j = 0; j < inputLen; j += 1) {
                if (j != colOmit) {
                    ans[i - 1][nextColAns] = input[i][j];
                    nextColAns += 1;
                    if (nextColAns >= inputLen - 1) {
                        nextColAns = 0;
                    }
                }
            }
        }
        return ans;
    }
}