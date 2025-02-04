import java.util.*;

class Solution {
    public int solution(int storey) {
        int answer = 0;
        while(storey > 0) {
            var lastDigit = storey % 10;
            storey /= 10;
            
            if(lastDigit > 5) {
                storey++;
                answer += 10 - lastDigit;
                continue;
            }
            if(lastDigit < 5) {
                answer += lastDigit;
                continue;
            }
            
            if(storey % 10 >= 5) {
                storey++;
                answer += 5;
                continue;
            }
            answer += 5;
        }
        return answer;
    }
}