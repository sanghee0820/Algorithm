import java.util.*;
import java.util.stream.Collectors;
class Solution {
    
    public int solution(int[] scoville, int K) {
        int answer = 0;
        int first = -1, second = -1;
        
        List<Integer> scovilles = Arrays.stream(scoville)
                    .boxed()
                    .collect(Collectors.toList());
        PriorityQueue<Integer> pq = new PriorityQueue<>(scovilles);
        
        while((first = pq.remove()) < K){
            if(pq.isEmpty()){
                answer = -1;
                break;
            }
            second = pq.remove();
            pq.add(first + (second * 2));
            answer++;
            
        }
        return answer;
    }
}