import java.util.*;
class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        
        for(int i = 0; i < enemy.length; i++){
            
            pq.add(enemy[i]);
            n -= enemy[i];
            if( k == 0 && n < 0){
                break;
            }
            answer++;
            while(n < 0 && k > 0){
                n += pq.remove();
                k--;
            }
            
        }
        return answer;
    }
}