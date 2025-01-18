class Solution {
   private static final long MOD = 1_000_000_007;
    // 4,7,10... 패턴 (3간격)
   // 가로 1 -> 1가지
   // 가로 2 -> 2가지
   // 가로 3 -> 5가지
   public long solution(int n) {
       long[] dp = new long[n + 6];
       long[] dp4 = new long[n + 6]; 
       long[] dp5 = new long[n + 6];
       long[] dp6 = new long[n + 6];
       
       dp[0] = 1;
       dp[1] = 1; 
       dp[2] = 3; 
       dp[3] = 10;  
       
       
       for(int index = 4; index <= n; index++) {
           dp[index] = (dp[index] + dp[index - 1]) % MOD;
           dp[index] = (dp[index] + dp[index - 2] * 2) % MOD;
           dp[index] = (dp[index] + dp[index - 3] * 5) % MOD;
           
           dp4[index] = (dp4[index - 3] + dp[index - 4] * 2) % MOD;
           dp[index] = (dp[index] + dp4[index]) % MOD;
           
           if(index < 5) {
               continue;
           }
           dp5[index] = (dp5[index - 3] + dp[index - 5] * 2) % MOD;
           dp[index] = (dp[index] + dp5[index]) % MOD;
           
           if(index < 6) {
               continue;
           }
           dp6[index] = (dp6[index - 3] + dp[index - 6] * 4) % MOD;
           dp[index] = (dp[index] + dp6[index]) % MOD;
       }
       
       return (int) (dp[n] % MOD);
   }
}