import java.util.*;

class Solution {
    public long solution(int price, int money, int count) {
        long totalPirce = 0L;
        for(int i = 1; i <= count; i++){
            totalPirce = totalPirce + price * i;
        }
        
        long answer = totalPirce > money ? totalPirce - money : 0L;
        return answer;
    }
}