import java.util.*;
class Solution {
    // 2 -> / 3
    // 4 -> / 2
    // 6 -> / 2
    // 8 -> / 2
    public int solution(int n) {
        int answer = 0;
        if(n % 2 == 1) {
            return 0;
        }
        int MOD = 1_000_000_007;
        int[] dp = new int[n / 2 + 1];

        dp[0] = 1;
        dp[1] = 3;
        long sum = 0;
    
        for (int index = 2; index < dp.length; index++) {
            sum = (sum + dp[index - 2]) % MOD;
            dp[index] = (int) ((dp[index - 1] * 3L % MOD) + (sum * 2L % MOD)) % MOD;
        }

        return dp[dp.length - 1];
    }
}