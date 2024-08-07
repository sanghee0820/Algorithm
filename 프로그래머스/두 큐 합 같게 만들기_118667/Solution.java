import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Long queue1Sum = 0L;
        Long queue2Sum = 0L;
        Queue<Integer> que1 = new LinkedList<>();
        Queue<Integer> que2 = new LinkedList<>();
        
        for(int i = 0; i < queue1.length; i++){
            queue1Sum += queue1[i];
            que1.add(queue1[i]);
            queue2Sum += queue2[i];
            que2.add(queue2[i]);
        }
        
        Long target = (queue1Sum + queue2Sum) / 2;
        boolean failure = true;
        int answer = 0;
        for(int i = 0; i < queue1.length * 3 - 2; i++){
            if(queue1Sum.equals(Long.valueOf(target))){
                failure = false;
                break;
            }
            
            answer++;    
            if(queue1Sum < queue2Sum){
                int value = que2.poll();
                queue1Sum += Long.valueOf(value);
                queue2Sum -= Long.valueOf(value);
                que1.add(value);
                continue;
            }
            int value = que1.poll();
            queue2Sum += Long.valueOf(value);
            queue1Sum -= Long.valueOf(value);
            que2.add(value);
        }
        
        if(failure){
            answer = -1;
        }
        return answer;
    }
}