import java.util.*;

class Solution {
    private static final Integer HOUR_MINUTE = 60;
    
    public int solution(String[][] book_time) {
        List<int[]> timeInfo = new ArrayList<>();
        
        for(String[] time : book_time) {
            var startMinute = timeToMin(time[0]);
            var endMinute = timeToMin(time[1]) + 10;
            
            timeInfo.add(new int[]{startMinute, endMinute});
        }
        
        Collections.sort(timeInfo, (a,b) -> a[0] - b[0]);
        
        int answer = 1;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int[] time : timeInfo) {
            if(pq.isEmpty()) {
                pq.add(time[1]);
                continue;
            }
            var earliest = pq.peek();
            if(time[0] < earliest) {
                answer++;
                pq.add(time[1]);
                continue;
            }
            pq.poll();
            pq.add(time[1]);
        }
        return answer;
    }
    
    public int timeToMin(String time) {
        String[] timeInfo = time.split(":");
        
        var hours = Integer.parseInt(timeInfo[0]);
        var minutes = Integer.parseInt(timeInfo[1]);
        
        return hours * HOUR_MINUTE + minutes;
    }
}