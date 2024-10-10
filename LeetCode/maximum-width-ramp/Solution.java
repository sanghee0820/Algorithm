import java.util.*;

class Solution {
    public int maxWidthRamp(int[] nums) {
        int max = Integer.MIN_VALUE;
        int[] maxNums = new int[nums.length];
        for(int index = nums.length - 1; index >= 0; index--){
            max = Math.max(max, nums[index]);
            maxNums[index] = max;
        }

        int i = 0;
        int j = 0;
        for(int left = 0; left < nums.length - 1; left++){
            var leftValue = nums[left];
            for(int right = left + 1; right < nums.length; right++){
                var rightMax = maxNums[right];
                if(rightMax < leftValue){
                    break;
                }

                if(j - i < right - left){
                    j = right;
                    i = left;
                }
            }
        }

        return j - i;
    }
}