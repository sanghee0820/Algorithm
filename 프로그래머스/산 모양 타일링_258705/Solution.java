import java.util.*;

class Solution {
    private final Integer MOD = 10_007;
    public int solution(int n, int[] tops) {
        int answer = 0;
        int[] dpA = new int[n + 1];
        int[] dpB = new int[n + 1];
        dpA[0] = 1; 
        dpB[0] = 0;
        
        for(int i = 1; i <= n; i++){
            if(tops[i - 1] == 1){
                dpA[i] = (dpA[i - 1] * 3 + dpB[i - 1] * 2) % MOD;
                dpB[i] = (dpA[i - 1] + dpB[i - 1]) % MOD;
                continue;
            }
            dpA[i] = (dpA[i - 1] * 2 + dpB[i - 1]) % MOD;
            dpB[i] = (dpA[i - 1] + dpB[i - 1]) % MOD;
        }
        answer = (dpA[n] + dpB[n]) % MOD;
        return answer;
    }
}