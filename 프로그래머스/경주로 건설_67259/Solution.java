import java.util.*;
class Solution {
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    private static final int STRAIGHT_PRICE = 100;
    private static final int CURVE_PRICE = 500;
    private static final int WALL = 1;
    private int[][][] minCosts;
    
    public int solution(int[][] board) {
        minCosts = new int[board.length][board.length][4];
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board.length; column++) {
                Arrays.fill(minCosts[row][column], Integer.MAX_VALUE);
            }
        }
        
        return Math.min(
            dfs(board, 0, 0, 0, 0, 0),
            dfs(board, 0, 0, 1, 0, 0)
        );
    }
    
    private int dfs(int[][] board, int row, int col, int dir, int straight, int curve) {
        if (row == board.length - 1 && col == board.length - 1) {
            return STRAIGHT_PRICE * straight + CURVE_PRICE * curve;
        }
        
        int currentCost = STRAIGHT_PRICE * straight + CURVE_PRICE * curve;
        if (currentCost >= minCosts[row][col][dir]) {
            return Integer.MAX_VALUE;
        }
        minCosts[row][col][dir] = currentCost;
        
        int minResult = Integer.MAX_VALUE;
        for (int next = 0; next < DIRECTIONS.length; next++) {
            int nextRow = row + DIRECTIONS[next][0];
            int nextCol = col + DIRECTIONS[next][1];
            
            if (isWrongPosition(nextRow, nextCol, board)) {
                continue;
            }
            
            int nextCurve = calcNextCurve(curve, next, dir);
            int result = dfs(board, nextRow, nextCol, next, straight + 1, nextCurve);
            minResult = Math.min(minResult, result);
        }
        
        return minResult;
    }
    
    private boolean isWrongPosition(int row, int col, int[][] board) {
        return row < 0 || row >= board.length
            || col < 0 || col >= board.length
            || board[row][col] == WALL;
    }
    
    private int calcNextCurve(int curve, int dir, int before){
        if(dir == before){
            return curve;
        }
        return curve + 1;
    }
}