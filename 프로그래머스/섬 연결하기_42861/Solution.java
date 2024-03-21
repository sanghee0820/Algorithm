import java.util.*;
class Solution {
    class Path{
        int start;
        int end;
        int cost;
        public Path(int start, int end, int cost){
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
    
    private int[] ary;
    private Set<Integer> contain = new HashSet<>();
    
    public int find(int index){
        while(ary[index] != index){
            index = ary[index];
        }
        return index;
    }
    
    public boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot != bRoot){
            ary[find(a)] = ary[find(b)];
            return true;
        }
        return false;
    }
    public int solution(int n, int[][] costs) {
        
        int answer = 0;
        
        List<Path> paths = new ArrayList<>();
        for(int[] cost : costs){
            paths.add(new Path(cost[0],cost[1],cost[2]));
        }
        paths.sort((a,b) -> {
            return a.cost - b.cost;
        });
        
        ary = new int[n];
        for(int i = 0; i < n; i++){
            ary[i] = i;
        }
        for(Path path : paths){
            if(union(path.start, path.end)){
                contain.add(path.start);
                contain.add(path.end);
                answer += path.cost;
            }
        }
        return answer;
    }
}