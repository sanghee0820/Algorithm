import java.util.*;
class Solution {
    private static final Integer ROW_SIZE = 51;
    private static final Integer COLUMN_SIZE = 51;
    private int[] parents;
    public String[] solution(String[] commands) {
        parents = initUnionFind();
        var map = initMap();
        List<String> answer = new ArrayList<>();
        
        for(String command : commands) {
            String[] commandData = command.split(" ");
            
            if(commandData[0].equals("UPDATE") && commandData.length == 4) {
                update(Integer.parseInt(commandData[1]), Integer.parseInt(commandData[2]), commandData[3], map);
                continue;
            }
            if(commandData[0].equals("UPDATE") && commandData.length == 3) {
                update(commandData[1], commandData[2], map);
                continue;
            }
            if(commandData[0].equals("MERGE")) {
                merge(Integer.parseInt(commandData[1]),
                      Integer.parseInt(commandData[2]),
                      Integer.parseInt(commandData[3]),
                      Integer.parseInt(commandData[4]),
                      map
                     );
                continue;
            }
            
            if(commandData[0].equals("UNMERGE")) {
                unMerge(Integer.parseInt(commandData[1]),
                        Integer.parseInt(commandData[2]),
                        map
                     );
                continue;
            }
            if(commandData[0].equals("PRINT")) {
                var root = find(Integer.parseInt(commandData[1]) * ROW_SIZE + Integer.parseInt(commandData[2]));
                answer.add(map[root / ROW_SIZE][root % ROW_SIZE]);
            }
        }
        return answer.stream().toArray(String[]::new);
    }
    
    private int[] initUnionFind() {
        var parents = new int[ROW_SIZE * COLUMN_SIZE];
    
        for(int index = 0; index < parents.length; index++) {
            parents[index] = index;
        }
        return parents;
    }
    
    private String[][] initMap() {
        var map = new String[ROW_SIZE][COLUMN_SIZE];
        
        for(int rowIndex = 0; rowIndex < ROW_SIZE; rowIndex++) {
            Arrays.fill(map[rowIndex], "EMPTY");
        }
        
        return map;
    }
    
    private void union(int a, int b) {
        var aParents = find(a);
        var bParents = find(b);
        
        parents[bParents] = aParents;
    }
    
    private int find(int target) {
        if(parents[target] == target) {
            return target;
        }
        
        parents[target] = find(parents[target]);
        return parents[target];
    }
    
    private void merge(int r1, int c1, int r2, int c2, String[][] map) {
        int root1 = find(r1 * ROW_SIZE + c1);
        int root2 = find(r2 * ROW_SIZE + c2);

        if (root1 == root2) return; 

        String value1 = map[root1 / ROW_SIZE][root1 % ROW_SIZE];
        String value2 = map[root2 / ROW_SIZE][root2 % ROW_SIZE];

        union(r1 * ROW_SIZE + c1, r2 * ROW_SIZE + c2);

        int newRoot = root1;
        if (value1.equals("EMPTY")) {
            map[newRoot / ROW_SIZE][newRoot % ROW_SIZE] = value2;
            return;
        }
        map[newRoot / ROW_SIZE][newRoot % ROW_SIZE] = value1;
    }
    
    private void unMerge(int r, int c, String[][] map) {
        var root = find(r * ROW_SIZE + c);
        var rootData = map[root / ROW_SIZE][root % ROW_SIZE];
        
        List<Integer> unMergeIndex = new ArrayList<>();
        for(int index = 0; index < parents.length; index++) {
            if(find(index) == root) {
                unMergeIndex.add(index);
            }
        }
        
        for(Integer index : unMergeIndex) {
            parents[index] = index;
            map[index / ROW_SIZE][index % ROW_SIZE] = "EMPTY";
        }
        map[r][c] = rootData;
    }
    
    private void update(int r, int c, String value, String[][] map) {
        var root = find(r * ROW_SIZE + c);
        map[root / ROW_SIZE][root % ROW_SIZE] = value;
    }
    
    private void update(String value1, String value2, String[][] map) {
        for(int row = 1; row < ROW_SIZE; row++) {
            for(int column = 1; column < COLUMN_SIZE; column++) {
                var root = find(row * ROW_SIZE + column);
                if(map[root / ROW_SIZE][root % ROW_SIZE].equals(value1)) {
                    map[root / ROW_SIZE][root % ROW_SIZE] = value2;
                }
            }
        }
    }
}