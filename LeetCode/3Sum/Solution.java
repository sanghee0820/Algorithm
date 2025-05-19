import java.util.*;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> result = new HashSet<>();
        int n = nums.length;

        for (int left = 0; left < n - 2; left++) {
            if (left > 0 && nums[left] == nums[left - 1]){
                continue;
            }

            for (int mid = left + 1; mid < n - 1; mid++) {
                if (mid > left + 1 && nums[mid] == nums[mid - 1]){
                    continue;
                }

                int target = -(nums[left] + nums[mid]);
                int right = Arrays.binarySearch(nums, mid + 1, n, target);

                if (right > mid) {
                    result.add(List.of(nums[left], nums[mid], nums[right]));
                }
            }
        }

        return new ArrayList<>(result);
    }
}