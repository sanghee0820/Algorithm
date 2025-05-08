class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int top = 0;
        int bottom = matrix.length - 1;

        while (top <= bottom) {
            int mid = (top + bottom) / 2;
            if (matrix[mid][0] <= target && matrix[mid][matrix[mid].length - 1] >= target) {
                int index = Arrays.binarySearch(matrix[mid], target);
                return index >= 0;
            }
            
            if (matrix[mid][0] > target) {
                bottom = mid - 1;
            } else {
                top = mid + 1;
            }
        }

        return false;
    }
}