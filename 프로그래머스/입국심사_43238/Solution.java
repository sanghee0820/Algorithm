import java.util.*;
class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);

        long right = (long)times[0]*n;
        long left = 1;

        while(left<=right){
            long avg = (left + right)/2;
            long temp = 0;

            for(int i = 0; i < times.length; i++){
                temp += avg / times[i];
                if(temp > n) break;
            }

            if(n > temp){
                left = avg + 1;
                continue;
            }
            right = avg - 1;
        }


        return left;
    }
}