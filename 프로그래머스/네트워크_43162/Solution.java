import java.util.*;
class Solution {
    private List<Integer> networks = new ArrayList<>();
    
    public int find(int child){
        while(networks.get(child) != child){
            child = networks.get(child);
        }
        return child;
    }
    
    public void union(int a, int b){
        int parentA = find(a);
        int parentB = find(b);
        networks.set(parentB, parentA);
    }
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        for(int i = 0; i < n; i++){
            networks.add(i);
        }
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                if(computers[i][j] == 1){
                    union(i, j);
                }
            }
        }
        
        Set<Integer> networkParent = new HashSet<>();
        for(int i = 0; i < n; i++){
            networkParent.add(find(i));
        }
        answer = networkParent.size();
        return answer;
    }
}