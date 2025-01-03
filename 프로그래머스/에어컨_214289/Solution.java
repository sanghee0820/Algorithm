import java.util.*;
class Solution {
    private static final Integer TEMPERATURE_BASE = 11;
    private static final Integer HUMAN = 1;
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        // row : 시간만큼 / -10도 -> Index 1, 40도 -> Index 51
        int[][] dp = new int[onboard.length + 1][53];
        for(int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dp[0][temperature + TEMPERATURE_BASE] = 0;
        
        boolean heater = temperature < t1;
        for(int index = 1; index <= onboard.length; index++) {
            int startIndex;
            int endIndex;
            if(onboard[index - 1] == HUMAN) {
                startIndex = t1 + TEMPERATURE_BASE;
                endIndex = t2 + TEMPERATURE_BASE;
            } else {
                startIndex = 1;
                endIndex = 51;
            }
            
            for(int temp = startIndex; temp <= endIndex; temp++) {
                //온도가 유지된 경우 -> 온도 유지, temperature와 같으면 0
                if(temp == temperature + TEMPERATURE_BASE) {
                    dp[index][temp] = Math.min(dp[index][temp], dp[index - 1][temp]);
                } else {
                    dp[index][temp] = Math.min(dp[index][temp], dp[index - 1][temp] + b < 0 ? Integer.MAX_VALUE : dp[index - 1][temp] + b);
                }
                // 히터일 경우 -1은 에어컨을 끈 것과 같음.
                if(heater) {
                    //온도가 감소한 경우 히터 끔 0
                    dp[index][temp] = Math.min(dp[index][temp], dp[index - 1][temp + 1]);
                    //온도가 증가한 경우 -> 히터 켬 a
                    dp[index][temp] = Math.min(dp[index][temp], dp[index - 1][temp - 1] + a < 0 ? Integer.MAX_VALUE : dp[index - 1][temp - 1] + a);
                    continue;
                }
                //온도가 감소한 경우 에어컨 켬 a
                dp[index][temp] = Math.min(dp[index][temp], dp[index - 1][temp + 1] + a < 0 ? Integer.MAX_VALUE : dp[index - 1][temp + 1] + a);
                //온도가 증가한 경우 에어컨 끔
                dp[index][temp] = Math.min(dp[index][temp], dp[index - 1][temp - 1]);
            }
        }
        
        int answer = Integer.MAX_VALUE;
        for(int temp = 1; temp < 52; temp ++) {
            answer = Math.min(answer, dp[onboard.length][temp]);
        }
        
        return answer;
    }
}