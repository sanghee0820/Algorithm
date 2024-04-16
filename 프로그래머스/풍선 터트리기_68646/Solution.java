import java.util.*;
class Solution {
    public int solution(int[] a) {
        int min = a[0];
        
        Set<Integer> answer = new HashSet<>();
        answer.add(a[0]);
        for(int i = 1; i < a.length; i++){
            if(a[i] < min){
                min = a[i];
                answer.add(min);
            }
        }
        
        min = a[a.length - 1];
        answer.add(min);
        for(int i = a.length - 2; i > 0; i--){
            if(a[i] < min){
                min = a[i];
                answer.add(min);
            }
        }
        return answer.size();
    }
}