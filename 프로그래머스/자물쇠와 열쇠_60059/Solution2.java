import java.util.*;

class Solution2 {
    public boolean solution(int[][] key, int[][] lock) {
        
        int[][] extendedMap = getExtendedMap(key, lock);
            
        for(int index = 0; index < 4; index++) {
            key = rotateKey(key);
            if(checkKeyMatch(key, extendedMap)) {
                return true;
            }
        }
        
        return false;
    }
    
    public int[][] getExtendedMap(int[][] key, int[][] lock) {
        int[][] map = new int[(key.length - 1) * 2 + lock.length][(key.length - 1) * 2 + lock.length];
        
        for(int[] row : map) {
            Arrays.fill(row, -1);
        }
        for(int row = key.length - 1; row < key.length + lock.length - 1; row++) {
            for(int column = key.length - 1; column < key.length + lock.length - 1; column++) {
                map[row][column] = lock[row - key.length + 1][column - key.length + 1];
            }
        }
        
        return map;
    }
    
    public boolean checkKeyMatch(int[][] key, int[][] map) {
        for(int leftTopRow = 0; leftTopRow < map.length - key.length + 1; leftTopRow++) {
            for(int leftTopColumn = 0; leftTopColumn < map.length - key.length + 1; leftTopColumn++) {
                if(checkPositionMatch(leftTopRow, leftTopColumn, key, map)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean checkPositionMatch(int leftTopRow, int leftTopColumn, int[][] key, int[][] map) {
        List<int[]> filled = new ArrayList<>();
        
        for(int row = leftTopRow, keyRow = 0; row < leftTopRow + key.length; row++, keyRow++) {
            for(int column = leftTopColumn, keyColumn = 0; column < leftTopColumn + key.length; column++, keyColumn++) {
                if(map[row][column] == 1 && key[keyRow][keyColumn] == 1) {
                    controlZ(map, filled);
                    return false;
                }
                if(map[row][column] == 0 && key[keyRow][keyColumn] == 1) {
                    filled.add(new int[]{row, column});
                    map[row][column] = 1;
                }
            }
        }
        
        for(int row = key.length - 1; row < map.length - key.length + 1; row++) {
            for(int column = key.length - 1; column < map.length - key.length + 1; column++) {
                if(map[row][column] == 1) {
                    continue;
                }
                
                controlZ(map, filled);
                return false;
            }
        }
        
        controlZ(map, filled);
        return true;
    }
    
    private void controlZ(int[][] map, List<int[]> filled) {
        for(int[] position : filled) {
            map[position[0]][position[1]] = 0;
        }
    }
    
    private int[][] rotateKey(int[][] key) {
        int[][] newKey = new int[key.length][key.length];
        for(int row = 0; row < key.length; row++) {
            for(int column = 0; column < key.length; column++) {
                newKey[column][key.length - row - 1] = key[row][column];
            }
        }
        
        return newKey;
    }
}