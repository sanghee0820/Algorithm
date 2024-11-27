import java.util.*;

class Job{
    int inTime;
    int leftTime;
    
    public Job(int inTime, int leftTime){
        this.inTime = inTime;
        this.leftTime = leftTime;
    }
    
    public boolean isEnd(){
        return this.leftTime <= 0;
    }
    public String toString(){
        return "In Time : " + inTime + " Left Time : " + leftTime + "\n";
    }
}

class Solution {
    public int solution(int[][] jobs) {
        List<Integer> jobTimes = new ArrayList<>();
        Arrays.sort(jobs, (a, b) -> {
            return a[0] - b[0];
        });
        PriorityQueue<Job> pq = new PriorityQueue<>((a,b) -> {
            if(a.leftTime == b.leftTime){
                return a.inTime - b.inTime;
            }
            return a.leftTime - b.leftTime;
        });
        
        int time = 0;
        int jobIndex = 0;
        Job current = null;
        while(true){
             
            while(jobs.length > jobIndex && jobs[jobIndex][0] == time){
                pq.add(new Job(jobs[jobIndex][0], jobs[jobIndex][1]));
                jobIndex++;
            }
            
            if(current == null && pq.size() > 0){
                current = pq.poll();
            }
            
            if(current != null){
                current.leftTime--;
            }
            
            if(current != null && current.isEnd()){
                jobTimes.add(time - current.inTime + 1);
                current = null;
            }
            
            if(current == null && jobs.length == jobIndex && pq.size() == 0){
                break;
            }
            time++;
        }
        
        int answer = jobTimes.stream().mapToInt(i -> i).sum() / jobTimes.size();
        return answer;
    }
}