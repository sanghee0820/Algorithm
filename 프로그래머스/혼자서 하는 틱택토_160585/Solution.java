import java.util.*;

class Solution {
    private static final Integer BOARD_SIZE = 3;
    private static final String X = "X";
    private static final String O = "O";
    
    public int solution(String[] board) {
        String[][] map = Arrays.stream(board).map(i -> i.split("")).toArray(String[][]::new);
        
        if(isInvalidPosition(map)) {
            return 0;
        }
        
        return 1;
    }
    
    public boolean isInvalidOXCount(String[][] map, boolean oFinished, boolean xFinished) {
        int oCount = 0;
        int xCount = 0;
        
        for(int row = 0; row < BOARD_SIZE; row++) {
            for(int column = 0; column < BOARD_SIZE; column++) {
                if(map[row][column].equals(O)) {
                    oCount++;
                    continue;
                }
                if(map[row][column].equals(X)) {
                    xCount++;
                }
            }
        }
        if(!oFinished && !xFinished) {  
            return oCount < xCount || oCount > xCount + 1;
        }

        if(oFinished) {
            return oCount != xCount + 1;
        }

        return oCount != xCount;
        
    }
    
    public boolean isInvalidPosition(String[][] map) {
        var oFinished = isFinished(map, O);
        var xFinished = isFinished(map, X);
        if(oFinished && xFinished) {
            return true;
        }
        return isInvalidOXCount(map, oFinished, xFinished);
    }
    
    public boolean isFinished(String[][] map, String target) {
        boolean finished = false;
        
        for(int row = 0; row < BOARD_SIZE; row++) {
            finished = map[row][0].equals(target) && map[row][1].equals(target) && map[row][2].equals(target) || finished;
        }
        for(int column = 0; column < BOARD_SIZE && !finished; column++) {
            finished = map[0][column].equals(target) && map[1][column].equals(target) && map[2][column].equals(target) || finished;
        }
        
        finished = map[0][0].equals(target) && map[1][1].equals(target) && map[2][2].equals(target) || finished;
        finished = map[2][0].equals(target) && map[1][1].equals(target) && map[0][2].equals(target) || finished;
        
        return finished;
        
    }
}