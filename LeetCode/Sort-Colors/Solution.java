class Solution {
    public void sortColors(int[] nums) {
        int[] counts = new int[3];
        for(int num : nums) {
            counts[num]++;
        }
        for(int index = 0; index < nums.length; index++) {
            if(counts[0] != 0) {
                counts[0]--;
                nums[index] = 0;
                continue;
            }
            if(counts[1] != 0) {
                counts[1]--;
                nums[index] = 1;
                continue;
            }

            counts[2]--;
            nums[index] = 2;
        }
    }
}