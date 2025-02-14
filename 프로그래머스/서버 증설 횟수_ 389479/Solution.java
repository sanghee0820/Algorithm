import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int server = 1;
        
        for(int time = 0; time < 24; time++) {
            int player = players[time];
            while(!pq.isEmpty() && pq.peek() <= time) {
                server--;
                pq.poll();
            }
            
            int remain = player - server * m;
            if(remain < 0) {
                continue;
            }
            
            while(remain >= 0) {
                answer++;
                server++;
                pq.add(time + k);
                remain -= m;
            }
        }
        return answer;
    }
}