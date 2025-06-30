import java.util.*;
class Solutio2 {
    public int solution(int n, int[][] wires) {
        Map<Integer, List<Integer>> trees = new HashMap<>();
        for(int[] wire : wires) {
            trees.putIfAbsent(wire[0], new ArrayList<>());
            trees.putIfAbsent(wire[1], new ArrayList<>());
            trees.get(wire[0]).add(wire[1]);
            trees.get(wire[1]).add(wire[0]);
        }
        
        List<Integer> treeSizes = new ArrayList<>();
        getTreeSizes(trees, 1, -1, treeSizes);
        
        
        int answer = 101;
        for(int size : treeSizes) {
            answer = Math.min(answer, Math.abs(n - 2 * size));
        }
        return answer;
    }
    
    public int getTreeSizes(Map<Integer, List<Integer>> trees, int cur, int before, List<Integer> treeSizes) {
        int count = 1;
        for(int next : trees.get(cur)) {
            if(next == before) {
                continue;
            }
            count += getTreeSizes(trees, next, cur, treeSizes);
        }
        treeSizes.add(count);
        return count;
    }
}