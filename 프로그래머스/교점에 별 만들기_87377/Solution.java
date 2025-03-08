import java.util.*;

class Solution {
    public String[] solution(int[][] line) {
        Set<long[]> crossPoint = new HashSet<>();
        long minX = Long.MAX_VALUE;
        long minY = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE;
        long maxY = Long.MIN_VALUE;
        for(int line1Index = 0; line1Index < line.length; line1Index++) {
            var line1 = line[line1Index];
            
            for(int line2Index = line1Index + 1; line2Index < line.length; line2Index++) {
                var line2 = line[line2Index];
                
                long A = line1[0], B = line1[1], E = line1[2];
                long C = line2[0], D = line2[1], F = line2[2];
                
                long denominator = A*D - B*C;
                
                if(denominator == 0){
                    continue;
                }
                
                long numeratorX = B*F - E*D;
                long numeratorY = E*C - A*F;
                
                if(numeratorX % denominator == 0 && numeratorY % denominator == 0) {
                    long x = numeratorX / denominator;
                    long y = numeratorY / denominator;
                    
                    crossPoint.add(new long[]{x, y});
                    minX = Math.min(x, minX);
                    minY = Math.min(y, minY);
                    maxX = Math.max(x, maxX);
                    maxY = Math.max(y, maxY);
                }
            }
        }
        
        int height = (int)(maxY - minY + 1);
        int width = (int)(maxX - minX + 1);
        
        char[][] map = new char[height][width];
        for(char[] row : map) {
            Arrays.fill(row, '.');
        }
        
        for(long[] point : crossPoint) {
            int y = (int)(maxY - point[1]);
            int x = (int)(point[0] - minX);
            map[y][x] = '*';
        }
        
        String[] answer = new String[height];
        for(int row = 0; row < map.length; row++) {
            answer[row] = new String(map[row]);
        }
        return answer;
    }
}