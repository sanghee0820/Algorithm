import java.util.*;

class Video{
    int current;
    int totalPlayTime;
    int opStartTime;
    int opEndTime;
    
    public Video(String totalPlayTime, String current, String opStart, String opEnd){
        this.totalPlayTime = strTimeToIntTime(totalPlayTime);
        this.current = strTimeToIntTime(current);
        this.opStartTime = strTimeToIntTime(opStart);
        this.opEndTime = strTimeToIntTime(opEnd);
        if(isCurrentBetweenOp()){
            this.current = this.opEndTime;
        }
    }
    
    private int strTimeToIntTime(String time){
        String[] timeInfo = time.split(":");
        int minSec = Integer.valueOf(timeInfo[0]) * 60;
        int sec = Integer.valueOf(timeInfo[1]);
        return minSec + sec;
    }
    
    private boolean isCurrentBetweenOp(){
        return opStartTime <= current && current <= opEndTime;
    }
    
    public void prev(){
        this.current = Math.max(current - 10, 0);
        if(isCurrentBetweenOp()){
            this.current = this.opEndTime;
        }
    }
    
    public void next(){
        this.current = Math.min(current + 10, totalPlayTime);
        if(isCurrentBetweenOp()){
            this.current = this.opEndTime;
        }
    }
    
    public String currentTimeToString(){
        int min = current / 60;
        int sec = current % 60;
        
        return String.format("%02d:%02d", min, sec);
    }
    
    public String toString(){
        return this.currentTimeToString();
    }
}
class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        var video = new Video(video_len, pos, op_start, op_end);
        for(String command : commands){
            if(command.equals("prev")){
                video.prev();
                continue;
            }
            video.next();
        }
        return video.currentTimeToString();
    }
}