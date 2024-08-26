import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        int n = citations.length;
        Arrays.sort(citations);
        for(int i = 0; i < n; i++){
            var h = citations.length - i;
            if(citations[i] >= h){
                answer = h;
                break;
            }
        }
        return answer;
    }
}