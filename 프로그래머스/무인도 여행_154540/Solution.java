import java.util.*;
class Solution {
    private static final String SEA = "X";
    private static final int[][] DIRECTIONS = new int[][]{{-1, 0}, {1, 0}, {0,-1}, {0, 1}};
    
    public int[] solution(String[] maps) {
        List<Integer> answer = new ArrayList<>();
        
        String[][] map = new String[maps.length][maps[0].length()];
        boolean[][] visited = new boolean[maps.length][maps[0].length()];
        for(int row = 0; row < maps.length; row++){
            map[row] = maps[row].split("");
        }
        
        for(int row = 0; row < maps.length; row++){
            for(int column = 0; column < maps[0].length(); column++){
                if(map[row][column].equals(SEA) || visited[row][column]){
                    continue;
                }
                answer.add(bfs(map, visited, row, column));
            }
        }
        if(answer.size() == 0){
            answer.add(-1);
        }
        Collections.sort(answer);
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    
    public int bfs(String[][] map, boolean[][] visited, int row, int column){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, column});
        
        int days = 0;
        while(!queue.isEmpty()){
            int[] index = queue.poll();
            if(visited[index[0]][index[1]]){
                continue;
            }
            days += Integer.parseInt(map[index[0]][index[1]]);
            visited[index[0]][index[1]] = true;
            
            for(int[] direction : DIRECTIONS){
                int nextRow = index[0] + direction[0];
                int nextColumn = index[1] + direction[1];
                if(isInvalidIndex(map, nextRow, nextColumn)){
                    continue;
                }
                if(map[nextRow][nextColumn].equals(SEA)){
                    continue;
                }
                queue.add(new int[]{nextRow, nextColumn});
            }
        }
        return days;
    }
    
    public boolean isInvalidIndex(String[][] map, int row, int columm){
        return 0 > row || row >= map.length
            || 0 > columm || columm >= map[0].length;
    }
}