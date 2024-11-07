import java.util.*;
class Solution {
    
    public int climbStairs(int n) {
        int one = n;
        int two = 0;

        int answer = 0;
        while(one >= 0){
            answer += calcCnt(one + two, two);

            one -= 2;
            two += 1;
        }

        return answer;
    }

    private int calcCnt(int n, int k){
        if (k == 0 || k == n){
            return 1;
        }
        if (k > n / 2){
            k = n - k;
        }
        long result = 1;
        for (int i = 1; i <= k; i++) {
            result = result * (n - i + 1) / i;
        }
        return (int) result;
    }
}