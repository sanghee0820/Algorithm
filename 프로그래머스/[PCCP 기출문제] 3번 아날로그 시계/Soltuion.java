import java.util.*;

class Position{
    private static final Integer MAX_DEGREE = 360;
    private static final double S_PER_SEC = 360 / 60; // 초침이 초당 몇도 움직이는지 -> 1분에 360도
    private static final double M_PER_SEC = S_PER_SEC / 60; // 분침이 초당 몇도 도는지 -> 1시간에 360도
    private static final double H_PER_SEC = M_PER_SEC / 12; // 시침이 초당 몇도 도는지 -> 12시간에 360도
    double sPosition;
    double mPosition;
    double hPosition;
    
    public Position(int h, int m, int s){
        int totalSecond = h * 3600 + m * 60 + s;
        this.sPosition = (S_PER_SEC * totalSecond) % MAX_DEGREE;
        this.mPosition = (M_PER_SEC * totalSecond) % MAX_DEGREE;
        this.hPosition = (H_PER_SEC * totalSecond) % MAX_DEGREE;
    }
    
    public boolean isSameAll(){
        return sPosition == mPosition && sPosition == hPosition;
    }
}
class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = 0;
        var initial = new Position(h1, m1, s1);
        if(initial.isSameAll()){
            answer++;
        }
        while(isProcessing(h1, m1, s1, h2, m2, s2)){
            var current = new Position(h1, m1, s1++);
            if(s1 == 60){
                m1 += 1;
                s1 = 0;
            }
            if(m1 == 60){
                h1 += 1;
                m1 = 0;
            }
            var next = new Position(h1, m1, s1);
            
            answer += calcPass(current, next);
        }
        return answer;
    }
    
    private boolean isProcessing(int h1, int m1, int s1, int h2, int m2, int s2){
        return h1 != h2 || m1 != m2 || s1 != s2;
    }
    
    private int calcPass(Position current, Position next){
        boolean minResult = willPassMin(current, next);
        boolean hourResult = willPassHour(current, next);
        boolean isOnTime = next.isSameAll();
        if(isOnTime){
            return 1;
        }
        if(minResult && hourResult){
            return 2;
        }
        if(minResult || hourResult){
            return 1;
        }
        return 0;
    }
    
    private boolean willPassMin(Position current, Position next){
        return current.mPosition > current.sPosition && (next.mPosition <= next.sPosition || next.sPosition == 0);
    }
    
    private boolean willPassHour(Position current, Position next) {
        return current.hPosition > current.sPosition && (next.hPosition <= next.sPosition || next.sPosition == 0);
    }
}