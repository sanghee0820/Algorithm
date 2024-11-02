import java.util.*;

class Solution {
    public int solution(int[] mats, String[][] park) {
        int answer = 0;
        Set<Integer> left = new HashSet<>();
        
        for(int row = 0; row < park.length; row ++){
            for(int column = 0; column < park[0].length; column ++){
                if(park[row][column].equals("-1")){
                    left.addAll(calcLeft(park, row, column));
                }
            }
        }
        
        Arrays.sort(mats);
        for(int mat : mats){
            if(left.contains(mat)){
                answer = mat;
            }
        }
        
        if(answer == 0){
            answer = -1;
        }
        return answer;
    }
    
    public Set<Integer> calcLeft(String[][] park, int row, int column){
        Set<Integer> left = new HashSet<>();
        int size = 1;
        int nextRow = row + 1;
        int nextColumn = column + 1;
        
        while(true){
            boolean empty = true;
            left.add(size);
            
            if(nextRow >= park.length || nextColumn >= park[0].length){
                break;
            }
            
            for(int currentColumn = column; currentColumn <= nextColumn; currentColumn++){
                if(park[nextRow][currentColumn].equals("-1")){
                    continue;
                }
                empty = false;
            }
            
            for(int currentRow = row; currentRow <= nextRow; currentRow++){
                if(park[currentRow][nextColumn].equals("-1")){
                    continue;
                }
                empty = false;
            }

            if(empty){
                size++;
                nextRow++;
                nextColumn++;
                continue;
            }
            break;
        }
        return left;
    }
}