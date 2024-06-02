import java.util.*;

class Solution {
    
    private int[] result;
    private int diff = -1;
    public int[] solution(int n, int[] info) {
        DFS(info, new int[11], 0, n);
        if(result == null){
            return new int[]{-1};
        }
        return result;
    }
    
    public void DFS(int[] apeach, int[] ryon, int current, int left){
        if(current == 11){
            if(left != 0){
                ryon[10] += left;
            }
            int[] scores = calculateScore(apeach, ryon);
            int apeachScore = scores[0];
            int ryonScore = scores[1];
            if(ryonScore > apeachScore){
                setResult(ryon, ryonScore - apeachScore);
            }
            if(left != 0){
                ryon[10] -= left;
            }
            return;
        }
        
        if(left > apeach[current]){
            ryon[current] = apeach[current] + 1;
            left -= apeach[current] + 1;
            DFS(apeach, ryon, current + 1, left);
            ryon[current] = 0;
            left += apeach[current] + 1;
        }
        DFS(apeach, ryon, current + 1, left);
    }
    
    public int[] calculateScore(int[] apeach, int[] ryon){
        int apeachScore = 0;
        int ryonScore = 0;
        for(int scoreIndex = 10; scoreIndex >= 0; scoreIndex--){
            if(apeach[10 - scoreIndex] == 0 && ryon[10 - scoreIndex] == 0){
                continue;
            }
            if(apeach[10 - scoreIndex] >= ryon[10 - scoreIndex]){
                apeachScore += scoreIndex;
                continue;
            }
            ryonScore += scoreIndex;
        }
        
        return new int[]{apeachScore, ryonScore};
    }
    
    public void setResult(int[] ryon, int difference){
        
        if(result == null || difference > this.diff){
            result = ryon.clone();
            this.diff = difference;
            return;
        }
        if(difference < this.diff){
            return;
        }
        for(int i = 10; i >= 0; i--){
            if(result[i] < ryon[i]){ 
                result = ryon.clone();
                return;
            }
            if(result[i] > ryon[i]){
                return;
            }
        }
    }
}