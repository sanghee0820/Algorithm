import java.util.*;
class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        
        for(int x = 1; x <= r2; x++) {
            long startY = x >= r1 ? 0 : (long) Math.ceil(Math.sqrt(Math.pow(r1, 2) - Math.pow(x, 2)));
            long endY = (long) Math.floor(Math.sqrt(Math.pow(r2, 2) - Math.pow(x, 2)));
            answer += endY - startY + 1;
        }
        
        return answer * 4;
    }
}