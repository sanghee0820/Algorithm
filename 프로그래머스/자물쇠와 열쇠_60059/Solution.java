import java.util.*;

class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        int[][] lockKeyMap = new int[lock.length + key.length * 2][lock.length + key.length * 2];
        for(int row = key.length; row < key.length + lock.length; row++){
            for(int column = key.length; column < key.length + lock.length; column++){
                lockKeyMap[row][column] = lock[row - key.length][column - key.length];
            }
        }
        
        for(int rotateCnt = 0; rotateCnt < 4; rotateCnt++){
            key = rotate(key);
            if(matchKey(lockKeyMap, key, lock)){
                answer = true;
                break;
            }
        }
        return answer;
    }
    
    public boolean matchKey(int[][] lockKeyMap, int[][] key, int[][] lock){
        for(int leftTopRow = 0; leftTopRow < key.length + lock.length; leftTopRow++){
            for(int leftTopColumn = 0; leftTopColumn < key.length + lock.length; leftTopColumn++){
                addKey(lockKeyMap, key, leftTopRow, leftTopColumn);
                if(match(lockKeyMap, key, lock)){
                    return true;
                }
                popKey(lockKeyMap, key, leftTopRow, leftTopColumn);
            }
        }
        return false;
    }
    
    public void addKey(int[][] lockKeyMap, int[][] key, int leftTopRow, int leftTopColumn){
        for(int row = leftTopRow; row < leftTopRow + key.length; row++){
            for(int column = leftTopColumn; column < leftTopColumn + key.length; column++){
                lockKeyMap[row][column] += key[row - leftTopRow][column - leftTopColumn];
            }
        }
    }
    
    public void popKey(int[][] lockKeyMap, int[][] key, int leftTopRow, int leftTopColumn){
        for(int row = leftTopRow; row < leftTopRow + key.length; row++){
            for(int column = leftTopColumn; column < leftTopColumn + key.length; column++){
                lockKeyMap[row][column] -= key[row - leftTopRow][column - leftTopColumn];
            }
        }
    }
    
    public boolean match(int[][] lockKeyMap, int[][] key, int[][] lock){
        for(int row = key.length; row < key.length + lock.length; row++){
            for(int column = key.length; column < key.length + lock.length; column++){
                if(lockKeyMap[row][column] == 1){
                    continue;
                }
                return false;
            }
        }
        return true;
    }
    
    public int[][] rotate(int[][] key){
        int n = key.length;
        int[][] newKey = new int[n][n];
        
        
        for (int row = 0; row < n; row++) {
            for (int column = 0; column < n; column++) {
                newKey[column][n - 1 - row] = key[row][column];
            }
        }
        
        return newKey;
    }
}