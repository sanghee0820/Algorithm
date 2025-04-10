import java.util.*;
class Solution {
    public int singleNumber(int[] nums) {
        Map<Integer, Boolean> duplicatedMap = new HashMap<>();

        for(int num : nums) {
            if(!duplicatedMap.containsKey(num)) {
                duplicatedMap.put(num, Boolean.FALSE);
                continue;
            }

            duplicatedMap.put(num, Boolean.TRUE);
        }

        for(Map.Entry<Integer, Boolean> entry : duplicatedMap.entrySet()) {
            if(entry.getValue()) {
                continue;
            }
            return entry.getKey();
        }

        throw new IllegalArgumentException();
    }
}