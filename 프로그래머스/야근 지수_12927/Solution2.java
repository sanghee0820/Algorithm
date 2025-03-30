import java.util.*;
class Solution2 {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b - a);
        for(int work : works) {
            pq.add(work);
        }
        
        while(n != 0 && !pq.isEmpty()) {
            var curWork = pq.poll();
            curWork--;
            n--;
            if(curWork == 0) {
                continue;
            }
            pq.add(curWork);
        }
        while(!pq.isEmpty()) {
            answer += Math.pow(pq.poll(), 2);
        }
        return answer;
    }
}