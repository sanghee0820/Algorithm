import java.util.*;
class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        for(int row = 1; row < triangle.length; row++){
            for(int column = 0; column < triangle[row].length; column++){
                checkUpper(row, column, triangle);
            }
        }
        
        for(int data : triangle[triangle.length - 1]){
            answer = Math.max(answer, data);
        }
        return answer;
    }
    
    public void checkUpper(int row, int column, int[][] triangle){
        int beforeRow = row - 1;
        int beforeLeftColumn = column - 1;
        int beforeRightColumn = column;
        
        int cur = triangle[row][column];
        if(beforeLeftColumn > -1){
            triangle[row][column] =
                            Math.max(
                                triangle[beforeRow][beforeLeftColumn] + cur,
                                triangle[row][column]);
        }
        if(beforeRightColumn < triangle[beforeRow].length){
            triangle[row][column] =
                            Math.max(
                                triangle[beforeRow][beforeRightColumn] + cur,
                                triangle[row][column]);
        }
    }
}