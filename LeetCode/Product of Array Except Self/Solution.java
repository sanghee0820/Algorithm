class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] answer = new int[nums.length];
        answer[0] = 1;
        for(int index = 1; index < nums.length; index++ ){
            answer[index] = nums[index - 1] * answer[index - 1];
        }

        int curRight = nums[nums.length - 1];
        for(int index = nums.length - 2; index >= 0; index--) {
            answer[index] *= curRight;
            curRight *= nums[index];
        }

        return answer;
    }
}