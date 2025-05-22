class Solution {
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int curMax = nums[0];

        for (int index = 1; index < nums.length; index++) {
            curMax = Math.max(nums[index], curMax + nums[index]);
            max = Math.max(max, curMax);
        }

        return max;
    }
}