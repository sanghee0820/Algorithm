import java.util.*;

class Solution2 {
    private static final String SEA = "X";
    private static final int[][] DIRECTIONS = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    public int[] solution(String[] maps) {
        List<Integer> answer = new ArrayList<>();
        String[][] map = new String[maps.length][maps[0].length()];
        
        for(int row = 0; row < map.length; row++) {
            map[row] = maps[row].split("");
        }
        
        Set<Integer> visited = new HashSet<>();
        for(int row = 0; row < map.length; row++) {
            for(int column = 0; column < map[0].length; column++) {
                if(map[row][column].equals(SEA)) {
                    continue;
                }
                
                if(visited.contains(row * map[0].length + column)) {
                    continue;
                }
                answer.add(bfs(row, column, map, visited));
            }
        }
        Collections.sort(answer);
        if(answer.size() == 0) {
            answer.add(-1);
        }
        return answer.stream().mapToInt(i->i).toArray();
    }
    
    public int bfs(int row, int column, String[][] map, Set<Integer> visited) {
        int rowSize = map.length;
        int columnSize = map[0].length;
        visited.add(row * columnSize + column);
        
        Queue<Integer> queue = new LinkedList<>();
        queue.add(row * columnSize + column);
        int days = Integer.parseInt(map[row][column]);
        while(!queue.isEmpty()) {
            var cur = queue.poll();
            
            int curRow = cur / columnSize;
            int curColumn = cur % columnSize;
            
            for(int[] DIRECTION : DIRECTIONS) {
                int nextRow = curRow + DIRECTION[0];
                int nextColumn = curColumn + DIRECTION[1];
                if(visited.contains(nextRow * columnSize + nextColumn)) {
                    continue;
                }
                
                if(isInvalidPosition(nextRow, nextColumn, map)) {
                    continue;
                }
                
                if(map[nextRow][nextColumn].equals(SEA)) {
                    continue;
                }
                
                days += Integer.parseInt(map[nextRow][nextColumn]);
                visited.add(nextRow * columnSize + nextColumn);
                
                queue.add(nextRow * columnSize + nextColumn);
            }
        }
        
        return days;
    }
    
    public boolean isInvalidPosition(int row, int column, String[][] map) {
        return row < 0 || column < 0 || row >= map.length || column >= map[0].length;
    }
}