import java.util.*;
class Solution {
    
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        
        int[][] map = new int[n][m];
        for(int[] puddle : puddles){
            map[puddle[1] - 1][puddle[0] - 1] = -1;
        }
        
        map[0][0] = 1;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(map[i][j] == -1){
                    continue;
                }
                checkLeft(i, j, map);
                checkUpper(i, j, map);
            }
        }
        answer = map[n-1][m-1];
        return answer;
    }
    
    public void checkLeft(int row, int column, int[][] map){
        int leftRow = row;
        int leftColumn = column - 1;
        if(leftColumn < 0){
            return;
        }
        if(map[leftRow][leftColumn] == -1){
            return;
        }
        map[row][column] = (map[leftRow][leftColumn] + map[row][column])%1_000_000_007;
    }
    
    public void checkUpper(int row, int column, int[][] map){
        int upperRow = row - 1;
        int upperColumn = column;
        if(upperRow < 0){
            return;
        }
        if(map[upperRow][upperColumn] == -1){
            return;
        }
        map[row][column] = (map[upperRow][upperColumn] + map[row][column])%1_000_000_007;
    }
}