import java.util.*;
class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[]{0,1000001};
        int left = 0;
        int right = 1;
        int sum = sequence[0];
        while(true){
            if(left >= sequence.length || sequence[left] > k){
                break;
            }
            while(right < sequence.length && sum < k){
                sum += sequence[right];
                right++;
            }
            if(sum == k){
                if(right - left - 1 < answer[1] - answer[0]){
                    answer[0] = left;
                    answer[1] = right - 1;
                }
            }
            sum -= sequence[left++];
        }
        return answer;
    }
}