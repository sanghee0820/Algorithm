import java.util.*;
class Solution {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        while(n > 0){
            int devided = s / n;
            answer[n - 1] = devided;
            s = s - devided;
            n--;
        }
        Arrays.sort(answer);
        if(answer[0] == 0){
            return new int[]{-1};
        }
        return answer;
    }
}