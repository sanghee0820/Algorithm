import java.util.*;

class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        
        long[][] dp = new long[2][sequence.length];
        dp[0][0] = sequence[0];
        dp[1][0] = -sequence[0];
        answer = Math.max(dp[0][0], dp[1][0]);
        for(int i = 1; i < sequence.length; i++){
            dp[1][i] = Math.max(-sequence[i], dp[0][i - 1] - sequence[i]);
            dp[0][i] = Math.max(sequence[i], dp[1][i - 1] + sequence[i]);
            answer = Math.max(dp[1][i], answer);
            answer = Math.max(dp[0][i], answer);
        }
        return answer;
    }
}