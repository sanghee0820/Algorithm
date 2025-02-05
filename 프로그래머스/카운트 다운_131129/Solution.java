import java.util.*;
class Solution {
    public int[] solution(int target) {
        int[] answer = {};
        
        int[][] dp = new int[target + 1][2];
        
        for(int index = 1; index < target + 1; index++) {
            dp[index][0] = Integer.MAX_VALUE;
        }
        
        for(int score = 1; score <= 20; score++) {
            for(int curScore = 0; curScore <= target; curScore ++) {
                var beforeSingle = curScore - score;
                if(beforeSingle >= 0 && dp[beforeSingle][0] != Integer.MAX_VALUE) {
                    if(dp[beforeSingle][0] + 1 == dp[curScore][0]) { 
                        dp[curScore][1] = Math.max(dp[beforeSingle][1] + 1, dp[curScore][1]);
                    }
                    if(dp[beforeSingle][0] + 1 < dp[curScore][0]) {
                        dp[curScore][0] = dp[beforeSingle][0] + 1;
                        dp[curScore][1] = dp[beforeSingle][1] + 1;
                    }
                }
                for(int multiple = 2; multiple <= 3; multiple++) {
                    var beforeScore = curScore - (score * multiple);
                    if(beforeScore >= 0 && dp[beforeScore][0] != Integer.MAX_VALUE) {
                        if(dp[beforeScore][0] + 1 < dp[curScore][0]) {
                            dp[curScore][0] = dp[beforeScore][0] + 1;
                            dp[curScore][1] = dp[beforeScore][1];
                        }
                    }
                }
                
                var beforeBool = curScore - 50;
                if(beforeBool >= 0 && dp[beforeBool][0] != Integer.MAX_VALUE) {
                    if(dp[beforeBool][0] + 1 == dp[curScore][0]) { 
                        dp[curScore][1] = Math.max(dp[beforeBool][1] + 1, dp[curScore][1]);
                    }
                    if(dp[beforeBool][0] + 1 < dp[curScore][0]) {
                        dp[curScore][0] = dp[beforeBool][0] + 1;
                        dp[curScore][1] = dp[beforeBool][1] + 1;
                    }
                }
            }
        }
        return new int[]{dp[target][0], dp[target][1]};
    }
}