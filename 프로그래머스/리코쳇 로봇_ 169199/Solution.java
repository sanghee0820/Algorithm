import java.util.*;
class Solution {
    private final List<List<Integer>> directions = new ArrayList(List.of(List.of(0,1), List.of(1,0), List.of(-1,0), List.of(0,-1)));
    private String[][] map = null;
    public int solution(String[] boards) {
        
        map = new String[boards.length][boards[0].length()];
        int startRow = -1;
        int startColumn = -1;
        int endRow = -1;
        int endColumn = -1;
        
        for(int i = 0; i < boards.length; i++){
            String board = boards[i];
            String[] data = board.split("");
            for(int j = 0; j < data.length; j++){
                map[i][j] = data[j];
                if(data[j].equals("R")){
                    startRow = i;
                    startColumn = j;
                } 
                if(data[j].equals("G")){
                    endRow = i;
                    endColumn = j;
                }
            }
        }
        
        return BFS(startRow, startColumn, endRow, endColumn);
    }
    
    public int BFS(int startRow, int startColumn, int endRow, int endColumn){
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.add(List.of(startRow, startColumn, 0));
        List<List<Integer>> visited = new ArrayList<>();
        visited.add(List.of(startRow, startColumn));
        while(!queue.isEmpty()){
            List<Integer> cur = queue.remove();
            
            for(List<Integer> direction: directions){
                List<Integer> next = new ArrayList(cur);
                
                next.set(2, next.get(2) + 1);
                while( (next.get(0) + direction.get(0) >= 0 && next.get(0) + direction.get(0) < map.length)
                     && (next.get(1) + direction.get(1) >= 0 && next.get(1) + direction.get(1) < map[0].length)
                     ){
                    next.set(0, next.get(0) + direction.get(0));
                    next.set(1, next.get(1) + direction.get(1));
                    if((map[next.get(0)][next.get(1)].equals("D"))){
                        next.set(0, next.get(0) - direction.get(0));
                        next.set(1, next.get(1) - direction.get(1));
                        break;
                    }
                    
                }
                if(next.get(0) == endRow && next.get(1) == endColumn){
                    return next.get(2);
                }
                if(visited.contains(List.of(next.get(0), next.get(1)))){
                    continue;
                }
                queue.add(List.of(next.get(0), next.get(1), next.get(2)));
                visited.add(List.of(next.get(0), next.get(1)));
            }
        }
        return -1;
    }
}
