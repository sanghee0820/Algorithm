import java.util.*;
class Food {
    int index;
    int time;
    
    public Food(int index, int time) {
        this.index = index;
        this.time = time;
    }
}

class Solution {
    public int solution(int[] food_times, long k) {
        PriorityQueue<Food> pq = new PriorityQueue<>((a,b) -> a.time - b.time);
        
        long total = 0;
        for(int i = 0; i < food_times.length; i++) {
            total += food_times[i];
            pq.offer(new Food(i + 1, food_times[i]));
        }
        if(total <= k) {
            return -1;
        }
        
        long previous = 0;
        long length = food_times.length;
        
        while ((pq.peek().time - previous) * length <= k) {
            k -= (pq.peek().time - previous) * length;
            previous = pq.peek().time;
            pq.poll();
            length--;
        }
        
        List<Food> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            result.add(pq.poll());
        }
        Collections.sort(result, (a,b) -> a.index - b.index);
        
        return result.get((int)(k % length)).index;
    }
}