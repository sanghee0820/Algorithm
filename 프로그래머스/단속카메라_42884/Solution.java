import java.util.*;
class Solution {
    
    public int solution(int[][] routes) {
        
        Arrays.sort(routes, (a,b) -> {
            return a[1] - b[1];
        });
        
        
        int answer = 1;
        int cur = routes[0][1];
        for(int[] route : routes){
            if(cur < route[0]){
                answer ++;
                cur = route[1];
            }
        }
        return answer;
    }
}