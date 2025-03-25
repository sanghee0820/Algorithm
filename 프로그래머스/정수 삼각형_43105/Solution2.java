import java.util.*;
class Solution2 {
    public int solution(int[][] triangle) {
        int answer = 0;
        
        for(int row = 1; row < triangle.length; row++) {
            for(int column = 0; column < triangle[row].length; column++) {
                int temp = 0;
                if(column - 1 >= 0) {
                    temp = Math.max(temp, triangle[row][column] + triangle[row - 1][column - 1]);
                }
                
                if(column < triangle[row - 1].length) {
                    temp = Math.max(temp, triangle[row][column] + triangle[row - 1][column]);
                }
                triangle[row][column] = temp;
            }
        }
        
        for(int column = 0; column < triangle[triangle.length - 1].length; column++) {
            answer = Math.max(answer, triangle[triangle.length - 1][column]);
        }
        return answer;
    }
}