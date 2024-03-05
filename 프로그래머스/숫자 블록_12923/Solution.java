import java.util.*;
class Solution {
    public int[] solution(long begin, long end) {
        int[] answer = new int[(int)(end - begin) + 1];
        Arrays.fill(answer, 1); 
        for(int i = 0; i < end - begin + 1; i++){
            if(i + begin == 1){
                answer[0] = 0;
                continue;
            }
            for (int j = 2; j <= Math.sqrt(i + begin); j++) {
                if ( (i + begin) % j == 0 ){
                    if( ( i + begin) / j > 10000000 ){
                        answer[i] = j;
                        continue;
                    }else{
                        answer[i] = Math.max((int)(i + begin) / j, answer[i]);
                    }
                }
            }
        }
        return answer;
    }
}