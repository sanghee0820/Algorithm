class Solution {
    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for(int row = 1; row < grid.length; row++) {
            dp[row][0] = dp[row - 1][0] + grid[row][0];
        }
        for(int colmn = 1; colmn < grid[0].length; colmn++) {
            dp[0][colmn] = dp[0][colmn - 1] + grid[0][colmn];
        }
        for(int row = 1; row < grid.length; row++) {
            for(int column = 1; column < grid[0].length; column++) {
                dp[row][column] = Math.min(dp[row - 1][column], dp[row][column - 1]) + grid[row][column];
            }
        }

        return dp[grid.length - 1][grid[0].length - 1];
    }
}