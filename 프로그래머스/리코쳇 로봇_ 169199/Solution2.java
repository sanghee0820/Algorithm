import java.util.*;
class Solution2 {
    private static final int[][] DIRECTIONS = new int[][]{ {1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final String START = "R";
    private static final String END = "G";
    private static final String WALL = "D";
    public int solution(String[] board) {
        int[] start = new int[2];
        int[] end = new int[2];
        String[][] map = new String[board.length][board[0].length()];
        for(int row = 0; row < board.length; row++) {
            String curBoard = board[row];
            map[row] = curBoard.split("");
            for(int column = 0; column < map[row].length; column++) {
                if(map[row][column].equals(START)) {
                    start[0] = row;
                    start[1] = column;
                }
                if(map[row][column].equals(END)) {
                    end[0] = row;
                    end[1] = column;
                }
            }
        }
        
        int answer = bfs(start, end, map);
        return answer;
    }
    
    public int bfs(int[] start, int[] end, String[][] map) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start[0], start[1], 0});
        Set<Integer> visited = new HashSet<>();
        visited.add(start[0] * map[0].length + start[1]);
        
        while(!queue.isEmpty()) {
            var cur = queue.poll();
            if(cur[0] == end[0] && cur[1] == end[1]) {
                return cur[2];
            }
            
            for(int[] DIRECTION : DIRECTIONS) {
                var next = cur.clone();
                
                while(isValidPosition(next[0] + DIRECTION[0], next[1] + DIRECTION[1], map) &&
                      isNotWALL(next[0] + DIRECTION[0], next[1] + DIRECTION[1], map)) {
                    next[0] += DIRECTION[0];
                    next[1] += DIRECTION[1];
                }
                if(visited.contains(next[0] * map[0].length + next[1])) {
                    continue;
                }
                next[2] += 1;
                visited.add(next[0] * map[0].length + next[1]);
                queue.add(next);
            }
        }
        return -1;
    }
    
    public boolean isValidPosition(int row, int column, String[][] map) {
        return 0 <= row && row < map.length && 0 <= column && column < map[0].length;
    }
    
    public boolean isNotWALL(int row, int column, String[][] map) {
        return !map[row][column].equals(WALL);
    }
}