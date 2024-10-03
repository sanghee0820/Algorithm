import java.util.*;

class Solution2 {
    public int solution(int n, int[][] computers) {
        int[] set = new int[n];
        
        for(int i = 0; i < n; i++){
            set[i] = i;
        }
        
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                if(computers[i][j] == 1){
                    union(set, i, j);
                }
            }
        }
        
        Set<Integer> parent = new HashSet<>();
        for(int i = 0; i < n; i++){
            parent.add(find(set, i));
        }
        
        return parent.size();
    }
    public void union(int[] set, int a, int b){
        var aParent = find(set, a);
        var bParent = find(set, b);
        
        set[bParent] = aParent;
    }
    public int find(int[] set, int a){
        if(set[a] != a) {
            set[a] = find(set, set[a]);
        }
        
        return set[a];
    }
}