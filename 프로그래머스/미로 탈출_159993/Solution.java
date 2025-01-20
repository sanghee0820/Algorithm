import java.util.*;
class Solution {
    private static final String START = "S";
    private static final String END = "E";
    private static final String LEVER = "L";
    
    private static final String PATH = "O";
    private static final String WALL = "X";
    
    private static final int[][] DIRECTIONS = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int solution(String[] maps) {
        int answer = 0;
        String[][] map = Arrays.stream(maps).map((row) -> row.split("")).toArray(String[][]::new);
        
        int[] startPosition = new int[2];
        int[] endPosition = new int[2];
        int[] leverPosition = new int [2];
        
        for(int row = 0; row < map.length; row++) {
            for(int column = 0; column < map[0].length; column++) {
                if(map[row][column].equals(START)) {
                    startPosition = new int[]{row, column};
                    continue;
                }
                if(map[row][column].equals(END)) {
                    endPosition = new int[]{row, column};
                    continue;
                }
                if(map[row][column].equals(LEVER)) {
                    leverPosition = new int[]{row, column};
                    continue;
                }
            }
        }
        
        var startToLever = findDistance(map, startPosition, leverPosition);
        System.out.println(startToLever);
        if(startToLever < 0) {
            return -1;
        }
        answer += startToLever;
        
        var leverToEnd = findDistance(map, leverPosition, endPosition);
        if(leverToEnd < 0) {
            return -1;
        }
        answer += leverToEnd;
        
        return answer;
    }
    
    public int findDistance(String[][] map, int[] startPosition, int[] endPosition) {
        var height = map.length;
        var width = map[0].length;
        boolean[][] visited = new boolean[height][width];
        visited[startPosition[0]][startPosition[1]] = true;
        
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startPosition[0], startPosition[1], 0});
        
        while(!queue.isEmpty()) {
            var current = queue.poll();
            if(current[0] == endPosition[0] && current[1] == endPosition[1]) {
                return current[2];
            }
            for(int[] next : DIRECTIONS) {
                var nextRow = current[0] + next[0];
                var nextColumn = current[1] + next[1];
                
                if(isWrongPosition(height, width, nextRow, nextColumn)) {
                    continue;
                }
                if(visited[nextRow][nextColumn]) {
                    continue;
                }
                if(map[nextRow][nextColumn].equals(WALL)) {
                    continue;
                }
                queue.add(new int[]{nextRow, nextColumn, current[2] + 1});
                visited[nextRow][nextColumn] = true;
            }
        }
        
        return -1;
    }
    
    public boolean isWrongPosition(int height, int width, int row, int column) {
        return row < 0 || column < 0 || row >= height || column >= width;
    }
}