import java.util.*;
class Solution2 {
    private final int[][] DIRECTIONS = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private final String PERSON = "P";
    private final String PARTITION = "X";
    private final String EMPTY = "O";
    
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        for(int index = 0; index < places.length; index++) {
            var map = placeToMap(places[index]);
            answer[index] = isValidMap(map);
        }
        return answer;
    }
    
    
    public String[][] placeToMap(String[] place) {
        String[][] map = Arrays.stream(place).map(i -> i.split("")).toArray(String[][]::new);
        
        return map;
    }
    
    public int isValidMap(String[][] map) {
        for(int row = 0; row < map.length; row++) {
            for(int column = 0; column < map[0].length; column++) {
                if(!map[row][column].equals(PERSON)) {
                    continue;
                }
                if(isValidPosition(map, row, column)) {
                    continue;
                }
                return 0;
            }
        }
        return 1;
    }
    
    public boolean isValidPosition(String[][] map, int row, int column) {
        Queue<int[]> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        int rowSize = map.length;
        int columnSize = map[0].length;
        queue.add(new int[]{row, column, 0});
        visited.add(row * columnSize + column);
                              
        while(!queue.isEmpty()) {
            var cur = queue.poll();
            if(cur[2] > 2) {
                continue;
            }
            if(map[cur[0]][cur[1]].equals(PARTITION)) {
                continue;
            }
            if(map[cur[0]][cur[1]].equals(PERSON) && cur[2] != 0) {
                return false;
            }
            
            for(int[] DIRECTION : DIRECTIONS) {
                var nextRow = cur[0] + DIRECTION[0];
                var nextColumn = cur[1] + DIRECTION[1];
                var nextDistance = cur[2] + 1;
                var key = nextRow * columnSize + nextColumn;
                if(isOutOfRange(nextRow, nextColumn, map)) {
                    continue;
                }
                if(visited.contains(key)) {
                    continue;
                }
                queue.add(new int[]{nextRow, nextColumn, nextDistance});
                visited.add(key);
            }
        }
        
        return true;
    }
    public boolean isOutOfRange(int row, int column, String[][] map) {
        return row < 0 || row >= map.length || column < 0 || column >= map[0].length;
    }
}