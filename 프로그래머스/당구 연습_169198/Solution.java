import java.util.*;

class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        
        int i = 0;
        for(int[] ball : balls){
            int targetX = ball[0];
            int targetY = ball[1];
            int minDistance = Integer.MAX_VALUE;
            
            if(startX < targetX || startY != targetY) {
                int distance = (int) (Math.pow(startX + targetX, 2) + Math.pow(startY - targetY, 2));
                minDistance = Math.min(minDistance, distance);
            }
                        
            if(startX > targetX || startY != targetY) {
                int distance = (int) (Math.pow(2 * m - startX - targetX, 2) + Math.pow(startY - targetY, 2));
                minDistance = Math.min(minDistance, distance);
            }
            
            if( startX != targetX || startY < targetY) {
                int distance = (int) (Math.pow(startX - targetX, 2) + Math.pow(startY + targetY, 2));
                minDistance = Math.min(minDistance, distance);
            }

            if( startX != targetX || startY > targetY) {
                int distance = (int) (Math.pow(startX - targetX, 2) + Math.pow(2 * n - startY - targetY, 2)) ;
                minDistance = Math.min(minDistance, distance);
            }
            answer[i++] = minDistance;
        }
        return answer;
    }
}