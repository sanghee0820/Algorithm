import java.util.*;

class Position {
    int row;
    int column;
    String path;
    
    public Position(int row, int column, String before, String move){
        this.row = row;
        this.column = column;
        this.path = before.concat(move);
    }
}
class Solution {
    private PriorityQueue<String> answers = new PriorityQueue<>();
    private static final int[][] DIRECTIONS = new int[][]{{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    private static final String[] DIRECTION_STRS = new String[]{"d", "l", "r", "u"};
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {

        int length = distance(x, y, r, c);
        if((k - length) % 2 == 1 || k < length){
            return "impossible";
        }
        
        var answer = dfs(n, m, k, r-1, c-1, new Position(x-1, y-1, "",""));
        if(answer.equals(null)){
            return "impossible";
        }
        return answer;
    }
    
    public String dfs(int n, int m, int k, int r, int c, Position current){
        if(current.path.length() == k){
            if(current.row == r && current.column == c){
                return current.path;
            }
        }
        if(current.path.length() + distance(current.row, current.column, r, c) > k){
            return null;
        }
        for(int i = 0; i < 4; i++){
            var nextDirection = DIRECTIONS[i];
            var nextDirectionStr = DIRECTION_STRS[i];
            var nextRow = current.row + nextDirection[0];
            var nextColumn = current.column + nextDirection[1];
            if(isInValidPosition(n, m, nextRow, nextColumn)){
                continue;
            }
            var nextPosition = new Position(nextRow, nextColumn, current.path, nextDirectionStr);
            String result = dfs(n, m, k, r, c, nextPosition);
            if (result != null) {
                return result;
            }
        }
        
        return null;
    }
    
    public boolean isInValidPosition(int n, int m, int row, int column){
        return row < 0 || row >= n || column < 0 || column >= m;
    }
    
    private int distance(int x, int y, int r, int c){
        return (int)Math.abs(x-r) + (int)Math.abs(y-c);
    }
}