import java.util.*;

class Solution {
    private static final Integer M_TO_SEC = 60;
    private static final Integer H_TO_SEC = M_TO_SEC * 60;
    
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        Integer playTime = timeToSec(play_time);
        Integer advTime = timeToSec(adv_time);
        long[] playTimeList = new long[playTime + 1];
        
        for(String log : logs){
            String[] logInfo = log.split("-");
            Integer startTime = timeToSec(logInfo[0]);
            Integer endTime = timeToSec(logInfo[1]);
            playTimeList[startTime]++;
            playTimeList[endTime]--;
        }
        
        for(int i = 1; i <= playTime; i++){
            playTimeList[i] = playTimeList[i] + playTimeList[i - 1];
        }
        for(int i = 1; i <= playTime; i++){
            playTimeList[i] = playTimeList[i] + playTimeList[i - 1];
        }
        
        Long maxTime = playTimeList[advTime - 1];
        Integer startTime = 0;
        
        for(int i = advTime; i <= playTime; i++){
            Long current = playTimeList[i] - playTimeList[i - advTime];
            if(maxTime < current){
                maxTime = current;
                startTime = i - advTime + 1;
            }
        }
        answer = secToTime(startTime);
        return answer;
    }
    
    private Integer timeToSec(String time){
        String[] timeInfo = time.split(":");
        return Integer.parseInt(timeInfo[0]) * H_TO_SEC + Integer.parseInt(timeInfo[1]) * M_TO_SEC + Integer.parseInt(timeInfo[2]);
    }
    
    private String secToTime(Integer time){
        Integer hour = time / H_TO_SEC;
        Integer min = time % H_TO_SEC / M_TO_SEC;
        Integer sec  = time % H_TO_SEC % M_TO_SEC;
        
        return String.format("%02d:%02d:%02d", hour, min, sec);
    }
}