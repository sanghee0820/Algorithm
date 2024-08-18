import java.util.*;
import java.util.stream.*;
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        
        Boolean[] takes = new Boolean[n + 1];
        Arrays.fill(takes, Boolean.TRUE);
        Arrays.sort(lost);
        Arrays.sort(reserve);
        
        for(int index : lost){
            boolean self = false;
            for(int i = 0; i < reserve.length; i++){
                if(reserve[i] == index){
                    reserve[i] = -1;
                    self = true;
                    break;
                }
            }
            if(self){
                continue;
            }
            takes[index] = Boolean.FALSE;
        }
        
        for(int index : reserve){
            if(index == -1){
                continue;
            }
            if(losted(takes, index - 1)){
                takes[index - 1] = true;
                continue;
            }
            
            if(index == n){
                continue;
            }
            if(losted(takes, index + 1)){
                takes[index + 1] = true;
            }
        }
        
        for(int i = 1; i <= n; i++){
            if(takes[i]){
                answer++;
            }
        }
        
        return answer;
    }
    
    public boolean losted(Boolean[] takes, int index){
        return takes[index].equals(Boolean.FALSE);
    }
}