class Solution {
    private static final int MOD = 1_234_567;
    public long solution(int n) {
        long[] dp = new long[n + 1];

        dp[0] = 1;
        dp[1] = 1;

        for(int index = 2; index <= n; index++) {
            dp[index] = (dp[index - 1] + dp[index - 2]) % MOD;
        }

        return dp[n];
    }
}