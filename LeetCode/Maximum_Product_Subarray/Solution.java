import java.util.*;
class Solution {
    public int maxProduct(int[] nums) {
        int min = nums[0];
        int max = nums[0];
        int answer = nums[0];

        for(int index = 1; index < nums.length; index++){
            int temp = min;
            min = Math.min(Math.min(max * nums[index], min * nums[index]), nums[index]);
            max = Math.max(Math.max(max * nums[index], temp * nums[index]), nums[index]);
            answer = Math.max(max, answer);
        }
        return answer;
    }
}