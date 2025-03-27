import java.util.*;
class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        Map<Integer, List<Integer>> roads = new HashMap<>();
        for(int[] eg : edge) {
            roads.putIfAbsent(eg[0], new ArrayList<>());
            roads.putIfAbsent(eg[1], new ArrayList<>());
            roads.get(eg[0]).add(eg[1]);
            roads.get(eg[1]).add(eg[0]);
        }
        
        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[1] = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        Set<Integer> visited = new HashSet<>();
        pq.add(new int[]{1, 0});
        
        while(!pq.isEmpty()) {
            var cur = pq.poll();
            if(visited.contains(cur[0])) {
                continue;
            }
            visited.add(cur[0]);
            
            for(int next : roads.get(cur[0])) {
                if(visited.contains(next)) {
                    continue;
                }
                if(distance[next] > distance[cur[0]] + 1) {
                    distance[next] = distance[cur[0]] + 1;
                    pq.add(new int[]{next, cur[1] + 1});
                }
            }
        }
        
        int max = 0;
        for(int index = 2; index <= n; index++) {
            if(max < distance[index]) {
                answer = 1;
                max = distance[index];
                continue;
            }
            if(max == distance[index]) {
                answer++;
            }
        }
        return answer;
    }
}