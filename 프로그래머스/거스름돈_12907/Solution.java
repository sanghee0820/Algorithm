import java.util.*;
class Solution {
    public int solution(int n, int[] money) {
        int[] changes = new int[n + 1];
        changes[0] = 1;
        for(int coin : money){
            for(int i = coin; i < n + 1; i++){
                if(changes[i - coin] != 0){
                    changes[i] += changes[i - coin] % 1_000_000_007;
                }
            }
        }
        return changes[n];
    }
}