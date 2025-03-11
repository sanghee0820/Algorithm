import java.util.*;

class Solution {
    private static final String LEFT = "L";
    private static final String RIGHT = "R";
    private static final String STRAIGHT = "S";
    
    private static final int[][] DIRECTIONS = new int[][]{ {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int[] solution(String[] grid) {
        List<Integer> answer = new ArrayList<>();
        String[][] map = new String[grid.length][grid[0].length()];
        
        for(int index = 0; index < grid.length; index++) {
            map[index] = grid[index].split("");
        }
        boolean[][][] visited = new boolean[map.length][map[0].length][4];
        for(int row = 0; row < map.length; row++) {
            for(int column = 0; column < map[0].length; column++) {
                int[] start = new int[]{row, column};
                for(int direction = 0; direction < 4; direction++) {
                    if(visited[row][column][direction]) {
                        continue;
                    }
                    var cnt = dfs(start, direction, start.clone(), direction, visited, map, 0);
                    if(cnt == -1) {
                        continue;
                    }
                    answer.add(cnt);
                }
            }
        }
        Collections.sort(answer);
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
    
    public int dfs(int[] startPosition, int startDirection, int[] cur, int beforeDirection, boolean[][][] visited, String[][] map, int cnt) {
        int[] currentPos = cur.clone();
        int currentDirection = beforeDirection;
        int currentCnt = cnt;

        while (true) {
            if (visited[currentPos[0]][currentPos[1]][currentDirection]) {
                if (Arrays.equals(currentPos, startPosition)) {
                    return currentCnt;
                }
                return -1;
            }

            visited[currentPos[0]][currentPos[1]][currentDirection] = true;

            int nextDirection = getNextDirections(currentPos, currentDirection, map);

            int nextRow = currentPos[0] + DIRECTIONS[nextDirection][0];
            int nextCol = currentPos[1] + DIRECTIONS[nextDirection][1];

            if (nextRow < 0) {
                nextRow = map.length - 1;
            }
            if (nextRow >= map.length) {
                nextRow = 0;
            }

            if (nextCol < 0) {
                nextCol = map[0].length - 1;
            }
            if (nextCol >= map[0].length) {
                nextCol = 0;
            }
            currentPos[0] = nextRow;
            currentPos[1] = nextCol;
            currentDirection = nextDirection;
            currentCnt++;
        }
    }
    
    private int getNextDirections(int[] cur, int before, String[][] map) {
        if(map[cur[0]][cur[1]].equals(STRAIGHT)) {
            return before;
        }
        
        if(map[cur[0]][cur[1]].equals(LEFT)) {
            if(before == 0) {
                return 2;
            }
            if(before == 1) {
                return 3;
            }
            
            if(before == 2) {
                return 1;
            }
            return 0;
        }
        
        if(before == 0) {
            return 3;
        }
        if(before == 1) {
            return 2;
        }

        if(before == 2) {
            return 0;
        }
        return 1;
    }
    
}