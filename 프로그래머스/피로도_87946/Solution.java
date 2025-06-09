import java.util.*;

class Solution {
    public int solution(int k, int[][] dungeons) {
        return getMaxDeungeons(new int[dungeons.length], 0, new HashSet<>(), dungeons, k);
    }
    
    public int getMaxDeungeons(int[] pickedIndex, int curIndex, Set<Integer> picked, int[][] dungeons, int k) {
        if(picked.size() == dungeons.length) {
            int maxCount = 0;
            for(int index : pickedIndex) {
                var dungeon = dungeons[index];
                if(k >= dungeon[0]) {
                    k -= dungeon[1];
                    maxCount++;
                    continue;
                }
                break;
            }
            return maxCount;
        }
        
        int maxCount = 0;
        for(int index = 0; index < dungeons.length; index++) {
            if(picked.contains(index)) {
                continue;
            }
            pickedIndex[curIndex] = index;
            picked.add(index);
            maxCount = Math.max(maxCount, getMaxDeungeons(pickedIndex, curIndex + 1, picked, dungeons, k));
            picked.remove(index);
        }
        
        return maxCount;
    }
}