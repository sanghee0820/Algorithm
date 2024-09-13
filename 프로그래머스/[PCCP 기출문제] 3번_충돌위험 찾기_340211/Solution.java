import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        Map<Integer, List<Integer>> visitedMap = new HashMap<>();
        int totalRow = 100;
        int totalColumn = 100;
        
        for(int[] route : routes){
            int moveCount = 1;
            for(int i = 1; i < route.length; i++){
                int[] startPosition = points[route[i - 1] - 1];
                int[] endPosition = points[route[i] - 1];
                
                int startRow = startPosition[0] - 1;
                int startCol = startPosition[1] - 1;
                int endRow = endPosition[0] - 1;
                int endCol = endPosition[1] - 1;
                if (startRow < endRow) {
                    for (int row = (i > 1) ? startRow + 1 : startRow; row <= endRow; row++) {
                        int key = row * totalRow + startCol;
                        addMove(visitedMap, key, moveCount++);
                    }
                } else if (startRow > endRow) {
                    for (int row = (i > 1) ? startRow - 1 : startRow; row >= endRow; row--) {
                        int key = row * totalRow + startCol;
                        addMove(visitedMap, key, moveCount++);
                    }
                }

                if (startCol < endCol) {
                    for (int col = (i == 1 && startRow == endRow) ? startCol : startCol + 1; col <= endCol; col++) {
                        int key = endRow * totalRow + col;
                        addMove(visitedMap, key, moveCount++);
                    }
                } else if(startCol > endCol){
                    for (int col = (i == 1 && startRow == endRow) ? startCol : startCol - 1; col >= endCol; col--) {
                        int key = endRow * totalRow + col;
                        addMove(visitedMap, key, moveCount++);
                    }
                }
            }
        }
        
        int answer = 0;
        for(Integer key : visitedMap.keySet()){
            var visited = visitedMap.get(key);
            var duplicated = visited.stream()
                .filter(i -> Collections.frequency(visited, i) > 1)
                .collect(Collectors.toSet());
            answer += duplicated.size();
        }
        
        return answer;
    }
    
    public void addMove(Map<Integer, List<Integer>> visitedMap, int key, int cnt){
        visitedMap.putIfAbsent(key, new ArrayList<>());
        visitedMap.get(key).add(cnt);
    }
}