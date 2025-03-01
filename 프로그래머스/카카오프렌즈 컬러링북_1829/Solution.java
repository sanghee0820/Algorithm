import java.util.*;
class Solution {
    
    private final int[][] DIRECTIONS = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        
        boolean[][] visited = new boolean[m][n];
        
        for(int row = 0; row < m; row++) {
            for(int column = 0; column < n; column++) {
                if(visited[row][column]) {
                    continue;
                }
                
                if(picture[row][column] == 0) {
                    visited[row][column] = true;
                    continue;
                }
                
                numberOfArea++;
                maxSizeOfOneArea = Math.max(bfs(row, column, visited, picture), maxSizeOfOneArea);
            }
        }
        
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    public int bfs(int row, int column, boolean[][] visited, int[][] picture) {
        int type = picture[row][column];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, column});
        visited[row][column] = true;
        
        int cnt = 1;
        while(!queue.isEmpty()) {
            var cur = queue.poll();
            
            for(int[] DIRECTION : DIRECTIONS) {
                var nextRow = cur[0] + DIRECTION[0];
                var nextColumn = cur[1] + DIRECTION[1];
                
                if(isInvalidPosition(nextRow, nextColumn, picture)) {
                    continue;
                }
                
                if(visited[nextRow][nextColumn]) {
                    continue;
                }
                if(picture[nextRow][nextColumn] != type) {
                    continue;
                }
                
                visited[nextRow][nextColumn] = true;
                cnt++;
                queue.add(new int[]{nextRow, nextColumn});
            }
        }
        
        return cnt;
    }
    
    public boolean isInvalidPosition(int row, int column, int[][] picture) {
        return row < 0 || column < 0 || row >= picture.length || column >= picture[0].length;
    }
}