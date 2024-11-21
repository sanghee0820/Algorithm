import java.util.*;
class Solution {
    public int rob(int[] nums) {
        int[][] robbing = new int[2][nums.length + 1];
        robbing[0][1]= nums[0];

        for(int index = 2; index < nums.length + 1; index++){
            for(int before = 1; before < index - 1; before++){
                robbing[0][index] = Math.max(robbing[0][before] + nums[index - 1], robbing[0][index]);
                robbing[0][index] = Math.max(robbing[1][before] + nums[index - 1], robbing[1][index]);
                robbing[1][index] = Math.max(robbing[1][index], robbing[1][before]);
                robbing[1][index] = Math.max(robbing[1][index], robbing[0][before]);
            }
            robbing[0][index] = Math.max(robbing[1][index - 1] + nums[index - 1], robbing[0][index]);
            robbing[1][index] = Math.max(robbing[1][index], robbing[0][index - 1]);
        }

        return Math.max(robbing[0][nums.length], robbing[1][nums.length]);
    }
}