import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        
        for(int i = 0; i < n ; i++){
            var tree = makeTree(i, wires);
            var depth = searchDepth(tree, -1, 1);
            answer = Math.min(Math.abs(n - 2 * depth), answer);
        }
        return answer;
    }
    
    private Map<Integer, List<Integer>> makeTree(int deleteIndex, int[][] wires){
        Map<Integer, List<Integer>> tree = new HashMap<>();
        
        for(int i = 0; i < wires.length; i++){
            var wire = wires[i];
            tree.putIfAbsent(wire[0], new ArrayList<>());
            tree.putIfAbsent(wire[1], new ArrayList<>());
            if(i == deleteIndex){
                continue;
            }
            
            tree.get(wire[0]).add(wire[1]);
            tree.get(wire[1]).add(wire[0]);
        }
        
        return tree;
    }
    
    private int searchDepth(Map<Integer, List<Integer>> tree, int before, int current){
        var nexts = tree.get(current);
        int depth = 0;
        for(Integer next : nexts){
            if(next == before){
                continue;
            }
            depth += searchDepth(tree, current, next);
        }
        
        return depth + 1;
    }
}