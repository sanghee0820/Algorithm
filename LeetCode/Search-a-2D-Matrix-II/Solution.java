class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = getRow(matrix, target);

        for( ; row >= 0; row --) {
            if(matrix[row][matrix[row].length - 1] < target) {
                return false;
            }
            if(Arrays.binarySearch(matrix[row], target) >= 0) {
                return true;
            }
        }

        return false;
    }

    public int getRow(int[][] matrix, int target) {
        int left = 0;
        int right = matrix.length - 1;
        int answer = 0;

        while(left <= right) {
            int mid = (left + right) / 2;

            if(matrix[mid][0] == target) {
                return mid;
            }

            if(matrix[mid][0] < target) {
                answer = mid;
                left = mid + 1;
                continue;
            }
            right = mid - 1;
        }

        return answer;
    }
}