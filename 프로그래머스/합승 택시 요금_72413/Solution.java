import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        Map<Integer, List<int[]>> fareMap = new HashMap<>();
        for(int[] fare : fares) {
            int c = fare[0];
            int d = fare[1];
            int cost = fare[2];
            
            fareMap.putIfAbsent(c, new ArrayList<>());
            fareMap.putIfAbsent(d, new ArrayList<>());
            
            fareMap.get(c).add(new int[]{d, cost});
            fareMap.get(d).add(new int[]{c, cost});
        }
        
        var sDistance = getDistance(n, s, fareMap);
        var aDistance = getDistance(n, a, fareMap);
        var bDistance = getDistance(n, b, fareMap);
        
        for(int via = 1; via <= n; via++) {
            if(sDistance[via] == Integer.MAX_VALUE || aDistance[via] == Integer.MAX_VALUE || bDistance[via] == Integer.MAX_VALUE) {
                continue;
            }
            
            answer = Math.min(answer, sDistance[via] + aDistance[via] + bDistance[via]);
        }
        return answer;
    }
    
    private int[] getDistance(int n, int start, Map<Integer, List<int[]>> fareMap) {
        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{start, 0});
        boolean[] visited = new boolean[distance.length];
        
        while(!pq.isEmpty()) {
            var cur = pq.poll();
            
            if(visited[cur[0]]) {
                continue;
            }
            visited[cur[0]] = true;
            for(int[] fare : fareMap.get(cur[0])) {
                if(visited[fare[0]]) {
                    continue;
                }
                
                distance[fare[0]] = Math.min(distance[fare[0]], distance[cur[0]] + fare[1]);
                pq.add(new int[]{fare[0], distance[fare[0]]});
            }
        }
        
        return distance;
    }
}