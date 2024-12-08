class Solution {
    public void rotate(int[][] matrix) {
        int length = matrix.length;
        
        for(int row = 0; row < length / 2; row++){
            for(int column = 0; column < length; column++){
                int temp = matrix[row][column];
                matrix[row][column] = matrix[length - row - 1][column];
                matrix[length - row - 1][column] = temp;
            }
        }

        for(int row = 0; row < length; row++){
            for(int column = row; column < length; column ++){
                int temp = matrix[row][column];
                matrix[row][column] = matrix[column][row];
                matrix[column][row] = temp;
            }
        }
    }
}