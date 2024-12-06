import java.util.*;
class Solution {
    private static final Integer PLACE_SIZE = 5;
    private static final String PARTITION = "X";
    private static final String EMPTY_SPACE = "O";
    private static final String HUMAN_SPACE = "P";

    private static final int[][] DIRECTIONS = new int[][]{{1, 0}, {0, 1}, {0, -1}};
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        for(int index = 0; index < places.length; index++){
            String[][] map = Arrays.stream(places[index]).map(i -> i.split("")).toArray(String[][]::new);
            answer[index] = isValidSeat(map);
        }
        return answer;
    }
    
    private int isValidSeat(String[][] map){
        for(int row = 0; row < PLACE_SIZE; row++){
            for(int column = 0; column < PLACE_SIZE; column++){
                if(map[row][column].equals(PARTITION) || map[row][column].equals(EMPTY_SPACE)){
                    continue;
                }
                var valid = checkPosition(row, column, map);
                if(valid) {
                    continue;
                }
                return 0;
            }
        }
        return 1;
    }
    
    private boolean checkPosition(int row, int column, String[][] map){
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        visited.add(row * PLACE_SIZE + column);
        queue.add(row * PLACE_SIZE + column);
        
        boolean valid = true;
        while(!queue.isEmpty()){
            var target = queue.poll();
            int currentRow = target / PLACE_SIZE;
            int currentColumn = target % PLACE_SIZE;
            
            for(int[] DIRECTION : DIRECTIONS){
                int nextRow = currentRow + DIRECTION[0];
                int nextColumn = currentColumn + DIRECTION[1];
                
                if(isInvalidPosition(nextRow, nextColumn)){
                    continue;
                }
                if(visited.contains(nextRow * PLACE_SIZE + nextColumn)){
                    continue;
                }
                if(calcDistance(row, column, nextRow, nextColumn) > 2){
                    continue;
                }
                if(map[nextRow][nextColumn].equals(PARTITION)){
                    continue;
                }
                if(map[nextRow][nextColumn].equals(HUMAN_SPACE)){
                    valid = false;
                    break;
                }
                visited.add(nextRow * PLACE_SIZE + nextColumn);
                queue.add(nextRow * PLACE_SIZE + nextColumn);
            }
        }
        return valid;
    }
    
    private boolean isInvalidPosition(int row, int column){
        return 0 > row || row >= PLACE_SIZE || 0 > column || column >= PLACE_SIZE;
    }
    
    private int calcDistance(int row, int column, int currentRow, int currentColumn){
        return Math.abs(row - currentRow) + Math.abs(column - currentColumn);
    }
}