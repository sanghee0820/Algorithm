class Solution {
    public void sortColors(int[] nums) {
        int zeroIndex = 0;
        int twoIndex = nums.length - 1;
        int index = 0;
        while(index <= twoIndex) {
            if(nums[index] == 0) {
                swap(nums, zeroIndex++, index++);
                continue;
            }
            if(nums[index] == 1) {
                index++;
                continue;
            }
            swap(nums, index, twoIndex--);
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}