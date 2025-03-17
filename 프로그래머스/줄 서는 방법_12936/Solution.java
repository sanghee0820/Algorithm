import java.util.*;
class Solution {
    public int[] solution(int n, long k) {
        List<Integer> nList = new ArrayList<>();
        long nFactorial = 1;
        for(int index = 1; index <= n; index++) {
            nList.add(index);
            nFactorial *= index;
        }
        int[] answer = new int[n];
        k--;
        for(int index = 0; index < answer.length; index++) {
            
            int target = (int)(k / (nFactorial / nList.size()));
            answer[index] = nList.get(target);
            k = k % (nFactorial / nList.size());
            nFactorial /= nList.size();
            nList.remove(target);
        }
        
        return answer;
    }
}
