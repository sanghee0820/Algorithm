// 상담중이 아닌 멘토 + 유형 일치
// 기다린시간 = 상담 요청 ~ 상담 시작 시간
// 상담이 끝나면 대기 인원 중 즉시 시작
// 먼저 상담 요청한 참가자가 우선
// Answer = 기다린 시간

import java.util.*;
import java.util.stream.*;

class TimeInfo{
    int startTime;
    int endTime;
    
    public TimeInfo(int start, int consultTime){
        this.startTime = start;
        this.endTime = startTime + consultTime;
    }
}
class Solution {
    
    private List<int[]> combinations = new ArrayList<>();
    
    public int solution(int k, int n, int[][] reqs) {
        int answer = Integer.MAX_VALUE;
        var timeInfos = generateMap(reqs);
        dfs(new int[k + 1], 0, n - k, 1);
        
        for(int[] mentors : combinations){
            int time = 0;
            for(Integer key : timeInfos.keySet()){
                time += timeCheck(timeInfos.get(key), mentors[key] + 1);
            }
            answer = Math.min(time, answer);
        }
        return answer;
    }
    
    private void dfs(int[] temp, int current, int max, int startIndex){
        if(current == max){
            combinations.add(temp.clone());
            return;
        }
        
        for(int i = startIndex; i < temp.length; i++){
            temp[i]++;
            dfs(temp, current + 1, max, i);
            temp[i]--;
        }
    }
    
    private Map<Integer, List<TimeInfo>> generateMap(int[][] reqs){
        Map<Integer, List<TimeInfo>> timeInfos = new HashMap<>();
        for(int[] req : reqs){
            int consultType = req[2];
            int startTime = req[0];
            int consultTime = req[1];
            timeInfos.putIfAbsent(consultType, new ArrayList<>());
            timeInfos.get(consultType).add(new TimeInfo(startTime, consultTime));
        }
        return timeInfos;
    }
    
    private int timeCheck(List<TimeInfo> timeInfos, int mentorCnt){
        PriorityQueue<TimeInfo> pq = new PriorityQueue<>((a,b) -> {
            return a.endTime - b.endTime;
        });
        
        for(int i = 0; i < mentorCnt; i++){
            pq.add(new TimeInfo(0, 0));
        }
        
        int watingTime = 0;
        for(TimeInfo current : timeInfos){
            var fastestTime = pq.poll();
            
            if(fastestTime.endTime < current.startTime){    
                pq.add(current);
                continue;
            }
            
            var newTime = new TimeInfo(fastestTime.endTime, current.endTime - current.startTime);
            pq.add(newTime);
            watingTime += fastestTime.endTime - current.startTime;
        }
        return watingTime;
    }
}