import java.util.*;

class Solution {
    private int max = 0;
    private final int[][] DIRECTIONS = new int[][]{{0, 1}, {1,0}, {-1, 0}, {0, -1}};
    public int solution(int[][] land) {
        int[] size = new int[land[0].length];
        
        boolean[][] visited = new boolean[land.length][land[0].length];
        
        for(int row = 0; row < land.length; row++){
            for(int column = 0; column < land[0].length; column++){
                if(navigable(land, visited, row, column)){
                    explore(land, visited, size, row, column);
                }
            }
        }
        return max;
    }
    
    private void explore(int[][] land, boolean[][] visited, int[] size, int row, int column){
        Queue<int[]> queue = new LinkedList<>();
        Set<Integer> visitedColumns = new HashSet<>();
        
        queue.add(new int[]{row, column});
        visitedColumns.add(column);
        visited[row][column] = true;
        int count = 1;
        
        while(!queue.isEmpty()){
            int[] current = queue.poll();
            visited[current[0]][current[1]] = true;
            
            for(int[] direction : DIRECTIONS){
                int nextRow = current[0] + direction[0];
                int nextColumn = current[1] + direction[1];
                if(navigable(land, visited, nextRow, nextColumn)){
                    count++;
                    visitedColumns.add(nextColumn);
                    queue.add(new int[]{nextRow, nextColumn});
                    visited[nextRow][nextColumn] = true;
                }
            }
        }
        
        for(Integer visitedColumn : visitedColumns){
            size[visitedColumn] += count;
            max = Math.max(size[visitedColumn], max);
        }
        
    }
    
    private boolean navigable(int[][] land, boolean[][] visited, int row, int column){
        if(row < 0 || land.length <= row){
            return false;
        }
        
        if(column < 0 || land[0].length <= column){
            return false;
        }
        return land[row][column] == 1 && !visited[row][column];
    }
}