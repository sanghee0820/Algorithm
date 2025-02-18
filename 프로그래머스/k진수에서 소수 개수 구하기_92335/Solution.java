import java.util.*;
class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        
        var nDigit = Integer.toString(n, k);
        
        var pArray = nDigit.split("0+");
        for(String p : pArray) {
            long pLong = Long.parseLong(p);
            if(isPrime(pLong)) {
                answer++;
            }
        }
        return answer;
    }
    
    public boolean isPrime(long a) {
        if (a < 2) return false;
        if (a == 2) return true;
        if (a % 2 == 0) return false;

        for (int i = 3; i <= Math.sqrt(a); i += 2) {
            if (a % i == 0) {
                return false;
            }
        }
        return true;
    }
}