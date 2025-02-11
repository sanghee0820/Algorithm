import java.util.*;
class Solution {
    
    private static final int[][] DIRECTIONS = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        
        String[][] map = new String[storage.length + 2][storage[0].length() + 2];
        boolean[][] existsMap = new boolean[storage.length + 2][storage[0].length() + 2];
        for(int row = 1; row <= storage.length; row++) {
            String[] storageRow = storage[row - 1].split("");
            for(int column = 1; column <= storage[0].length(); column++) {
                map[row][column] = storageRow[column - 1];
                existsMap[row][column] = true;
            }
        }
        
        for(String request : requests) {
            if(request.length() == 2) {
                crain(map, existsMap, request);
                continue;
            }
            
            forkLift(map, existsMap, request);
        }
        
        
        for(int row = 1; row <= storage.length; row++) {
            for(int column = 1; column <= storage[0].length(); column++) {
                if(existsMap[row][column]) {
                    answer++;
                }
            }
        }
        
        return answer;
    }
    
    public void crain(String[][] map, boolean[][] existsMap, String request) {
        var goods = request.split("")[0];
        
        for(int row = 1; row < map.length; row++) {
            for(int column = 1; column < map[0].length; column++) {
                if(goods.equals(map[row][column])) {
                    existsMap[row][column] = false;
                }
            }
        }
        
    }
    
    public void forkLift(String[][] map, boolean[][] existsMap, String request) {
        var goods = request;
        Queue<int[]> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        int[] start = new int[]{0, 0};
        queue.add(start);
        visited.add(0);
        
        while(!queue.isEmpty()) {
            var current = queue.poll();
            if(existsMap[current[0]][current[1]]) {
                if(goods.equals(map[current[0]][current[1]])) {
                    existsMap[current[0]][current[1]] = false;
                }
                continue;
            }
            
            for(int[] DIRECTION : DIRECTIONS) {
                var nextRow = current[0] + DIRECTION[0];
                var nextColumn = current[1] + DIRECTION[1];
                if(isInValidPosition(nextRow, nextColumn, map)) {
                    continue;
                }

                if(visited.contains(nextRow * map[0].length + nextColumn)) {
                    continue;
                }

                visited.add(nextRow * map[0].length + nextColumn);
                queue.add(new int[]{nextRow, nextColumn});
            }
        }
    }
    
    private boolean isInValidPosition(int row, int column, String[][] map) {
        return row < 0 || row >= map.length || column < 0 || column >= map[0].length;
    }
}