import java.util.*;
class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int work : works){
            pq.add(work);
        }
        
        while(true){
            if(n == 0){
                break;
            }
            
            int top = pq.remove();
            if(top == 0){
                break;
            }
            pq.add(top - 1);
            n--;
        }
        
        for(int work : pq){
            answer += Math.pow(work, 2);
        }
        return answer;
    }
}