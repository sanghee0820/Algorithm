```java
import java.util.*;
class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        
        Map<Integer, Map<Integer, Integer>> timePoints = new HashMap<>();
        for(int index = 0; index < routes.length; index++) {
            var route = routes[index];
            int cnt = 0;
            for(int routeIndex = 0; routeIndex < route.length - 1; routeIndex++) {
                var startPoint = points[route[routeIndex] - 1];
                var endPoint = points[route[routeIndex + 1] - 1];
                cnt = findPath(startPoint.clone(), endPoint, timePoints, cnt);
            }
        }
        for(int timeKey : timePoints.keySet()) {
            var timeInfo = timePoints.get(timeKey);
            for(int positionKey : timeInfo.keySet()) {
                if(timeInfo.get(positionKey) > 1) {
                    answer++;
                }
            }
        }
        return answer;
    }
    
    public int findPath(int[] cur, int[] endPoint, Map<Integer, Map<Integer, Integer>> timePoints, int cnt) {
        if(cnt == 0) {
            timePoints.putIfAbsent(cnt, new HashMap<>());
            var index = cur[0] * 100 + cur[1];
            timePoints.get(cnt).put(index, timePoints.get(cnt).getOrDefault(index, 0) + 1);
        }
        while(cur[0] != endPoint[0]) {
            cnt++;
            cur[0] += (endPoint[0] - cur[0]) / Math.abs(endPoint[0] - cur[0]);
            timePoints.putIfAbsent(cnt, new HashMap<>());
            var index = cur[0] * 100 + cur[1];
            timePoints.get(cnt).put(index, timePoints.get(cnt).getOrDefault(index, 0) + 1);
        }
        
        while(cur[1] != endPoint[1]) {
            cnt++;
            cur[1] += (endPoint[1] - cur[1]) / Math.abs(endPoint[1] - cur[1]);
            
            timePoints.putIfAbsent(cnt, new HashMap<>());
            var index = cur[0] * 100 + cur[1];
            timePoints.get(cnt).put(index, timePoints.get(cnt).getOrDefault(index, 0) + 1);
        }
        
        return cnt;
    }
}
```