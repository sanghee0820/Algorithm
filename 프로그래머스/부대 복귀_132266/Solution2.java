import java.util.*;
class Solution2 {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] distance = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        Map<Integer, List<Integer>> roadMap = getRoadMap(roads);
        
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[destination] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        pq.add(new int[]{destination, 0});
        
        while(!pq.isEmpty()) {
            var cur = pq.poll();
            if(visited[cur[0]]) {
                continue;
            }
            distance[cur[0]] = cur[1];
            visited[cur[0]] = true;
            for(int next : roadMap.getOrDefault(cur[0], new ArrayList<>())) {
                pq.add(new int[]{next, cur[1] + 1});
            }
        }
        
        int[] answer = new int[sources.length];
        for(int index = 0; index < sources.length; index++) {
            var target = sources[index];
            if(distance[target] == Integer.MAX_VALUE) {
                answer[index] = -1;
                continue;
            }
            answer[index] = distance[target];
        }
        return answer;
    }
    
    public Map<Integer, List<Integer>> getRoadMap(int[][] roads) {
        Map<Integer, List<Integer>> roadMap = new HashMap<>();
        for(int[] road : roads) {
            roadMap.putIfAbsent(road[0], new ArrayList<>());
            roadMap.putIfAbsent(road[1], new ArrayList<>());
            roadMap.get(road[0]).add(road[1]);
            roadMap.get(road[1]).add(road[0]);
        }
        
        return roadMap;
    }
}