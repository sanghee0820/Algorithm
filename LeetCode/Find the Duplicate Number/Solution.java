class Solution {
    public int findDuplicate(int[] nums) {
        int n = nums.length - 1;
        int[] visitedBits = new int[(n / 32) + 1];

        for (int num : nums) {
            int arrayIndex = num / 32;
            int bitPosition = num % 32;

            if ((visitedBits[arrayIndex] & (1 << bitPosition)) != 0) {
                return num;
            }

            visitedBits[arrayIndex] |= (1 << bitPosition);
        }

        return -1;
    }
}