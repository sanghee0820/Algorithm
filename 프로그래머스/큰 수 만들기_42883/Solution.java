import java.util.*;

class Solution {
    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder();

        for(String digit : number.split("")) {
            while(answer.length() > 0 &&
                Integer.parseInt(digit) > Integer.parseInt(String.valueOf(answer.charAt(answer.length() - 1))) &&
                k > 0
            ) {
                answer.deleteCharAt(answer.length() - 1);
                k--;
            }

            answer.append(digit);
        }

        while (k > 0) {
            answer.deleteCharAt(answer.length() - 1);
            k--;
        }

        return answer.toString();
    }
}