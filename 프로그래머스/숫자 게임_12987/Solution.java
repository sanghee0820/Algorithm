import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        Arrays.sort(B);
        boolean[] visited = new boolean[B.length];
        
        for(int aIndex = 0; aIndex < A.length; aIndex++){
            int bIndex = binarySearch(A[aIndex], B, visited);
            if(bIndex == -1 || bIndex == B.length){
                continue;
            }
            answer++;
            visited[bIndex] = true;
        }
        return answer;
    }
    
    public int binarySearch(int a, int[] B, boolean[] visited){
        int start = 0;
        int end = B.length;
        int result = -1;
        
        while(start <= end){
            int middle = (start + end) / 2;
            if(middle >= visited.length){
                break;
            }
            if(B[middle] > a){
                end = middle - 1;
                result = middle;
                continue;
            }
            start = middle + 1;
        }
        while(-1 < result && result < B.length && visited[result]){
            result++;
        }
        return result;
    }
}