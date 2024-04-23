import java.util.*;
class Solution {
    public int solution(int[][] board, int[][] skills) {
        int[][] cumulativeSum = new int[board.length + 2][board[0].length + 2];
        
        for(int[] skill : skills){
            int command;
            if(skill[0] == 1){
                command = -1;
            }else{
                command = 1;
            }
            
            cumulativeSum[skill[1] + 1][skill[2] + 1] += command * skill[5];
            cumulativeSum[skill[3] + 2][skill[2] + 1] += (-command) * skill[5];
            cumulativeSum[skill[3] + 2][skill[4] + 2] += command * skill[5];
            cumulativeSum[skill[1] + 1][skill[4] + 2] += (-command) * skill[5];
            
        }
        
        
        int building = 0;
        for(int row = 1; row < cumulativeSum.length - 1; row++){
            for(int column = 1; column < cumulativeSum[0].length - 1; column++){
                cumulativeSum[row][column] += cumulativeSum[row][column - 1];
            }    
        }
        for(int row = 1; row < cumulativeSum.length - 1; row++){
            for(int column = 1; column < cumulativeSum[0].length - 1; column++){
                cumulativeSum[row][column] += cumulativeSum[row - 1][column];
                board[row - 1][column - 1] = board[row - 1][column - 1] + cumulativeSum[row][column];
                if(board[row - 1][column - 1] > 0){
                    building++;
                }
            }    
        }
        return building;
    }
}