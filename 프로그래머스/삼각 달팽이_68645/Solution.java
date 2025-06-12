import java.util.*;
class Solution {
    public int[] solution(int n) {
        int[][] tower = new int[n][n];
        for(int[] row : tower) {
            Arrays.fill(row, -1);
        }
        
        int cur = 1;
        int row = 0;
        int column = 0;
        while(true) {
            // 왼쪽 아래
            boolean added = false;
            while(row + 1 < n && tower[row + 1][column] == -1) {
                tower[row][column] = cur;
                cur++;
                row++;
                added = true;
            }
            // 오른쪽
            while(column < row && tower[row][column + 1] == -1) {
                tower[row][column] = cur;
                cur++;
                column++;
                added = true;
            }
            
            // 위쪽 대각선
            while (row - 1 >= 0 && column - 1 >= 0 && tower[row - 1][column - 1] == -1) {
                tower[row][column] = cur++;
                row--;
                column--;
                added = true;
            }
            
            if (!added) {
                tower[row][column] = cur;
                break;
            }
        }
        
        List<Integer> answer = new ArrayList<>();
        for(row = 0; row < n; row++) {
            for(column = 0; column <= row; column++) {
                answer.add(tower[row][column]);
            }
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}