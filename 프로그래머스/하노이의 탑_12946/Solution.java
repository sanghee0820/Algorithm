import java.util.*;

class Solution {
    public int[][] solution(int n) {
        List<int[]> answer = new ArrayList<>();
        
        hanoi(answer, n, 1, 2, 3);
        
        return answer.stream().map(i -> i).toArray(int[][]::new);
    }
    
    public void hanoi(List<int[]> answer, int n, int from, int via, int to) {
        if(n == 1) {
            answer.add(new int[]{from, to});
            return;
        }
        hanoi(answer, n - 1, from, to, via);
        answer.add(new int[]{from, to});
        hanoi(answer, n - 1, via, from, to);
    }
}