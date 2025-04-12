```java
import java.util.*;
class Solution {
    private static final Integer STRAIGHT = 100;
    private static final Integer CORNER = 500;
    private static final int[][] DIRECTIONS = new int[][]{ {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
    private static final Integer WALL = 1;
    public int solution(int[][] board) {
        int[][][] minCosts = new int[board.length][board.length][4];
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board.length; column++) {
                Arrays.fill(minCosts[row][column], Integer.MAX_VALUE);
            }
        }
        
        return Math.min(
            dfs(0, 0, minCosts, board, 0, 0),
            dfs(0, 0, minCosts, board, 2, 0)
        );
    }
    
    public int dfs(int row, int column, int[][][] minCosts, int[][] board, int beforeDirection, int total) {
        int rowMaxIndex = board.length - 1;
        int columnMaxIndex = board[0].length - 1;
        
        if(rowMaxIndex == row && columnMaxIndex == column) {
            return total;
        }
        
        if(total > minCosts[row][column][beforeDirection]) {
            return Integer.MAX_VALUE;
        }
        minCosts[row][column][beforeDirection] = total;
        
        int minResult = Integer.MAX_VALUE;
        for(int index = 0; index < DIRECTIONS.length; index++) {
            int[] direction = DIRECTIONS[index];
            
            int nextRow = row + direction[0];
            int nextColumn = column + direction[1];
            
            if(isOutOfbound(nextRow, nextColumn, rowMaxIndex, columnMaxIndex)) {
                continue;
            }
            
            if(board[nextRow][nextColumn] == WALL) {
                continue;
            }
            int nextCost = total + STRAIGHT;
            if(beforeDirection != index) {
                nextCost += CORNER;
            }
            
            minResult = Math.min(minResult, dfs(nextRow, nextColumn, minCosts, board, index, nextCost));
        }
        return minResult;
    }
    
    private boolean isOutOfbound(int row, int column, int rowMax, int columnMax) {
        return row < 0 || column < 0 || row > rowMax || column > columnMax;
    }
}
```