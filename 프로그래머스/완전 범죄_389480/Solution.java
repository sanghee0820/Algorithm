import java.util.*;
class Solution {
    public int solution(int[][] info, int n, int m) {
        int numItems = info.length;
        int[][] dp = new int[numItems + 1][n];
        for (int i = 0; i <= numItems; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = 0;
        
        for(int itemIndex = 0; itemIndex < info.length; itemIndex++) {
            var item = info[itemIndex];
            
            for(int aEvidence = 0; aEvidence < n; aEvidence++) {
                if(dp[itemIndex][aEvidence] == Integer.MAX_VALUE) {
                    continue;
                }
                
                //A에게 주기
                int newAEvidence = aEvidence + item[0];
                if(newAEvidence < n) {   
                    dp[itemIndex + 1][newAEvidence] = Math.min(dp[itemIndex][aEvidence], dp[itemIndex + 1][newAEvidence]);
                }
                
                //B에게 주기
                int newBEvidence = dp[itemIndex][aEvidence] + item[1];
                if(newBEvidence < m) { 
                    dp[itemIndex + 1][aEvidence] = Math.min(dp[itemIndex + 1][aEvidence], newBEvidence);
                }
            }
        }
        
        for(int aEvidence = 0; aEvidence < n; aEvidence++) {
            if(dp[info.length][aEvidence] == Integer.MAX_VALUE) {
                continue;
            }
            
            return aEvidence;
        }
        
        return -1;
    }
}