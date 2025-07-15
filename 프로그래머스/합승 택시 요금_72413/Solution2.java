import java.util.*;

class Solution2 {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        Map<Integer, List<int[]>> map = getMap(n, fares);

        int[] aDistance = getDistance(n, a, map);
        int[] bDistance = getDistance(n, b, map);
        int[] sDistance = getDistance(n, s, map);

        for(int via = 1; via <= n; via ++) {
            if(aDistance[via] == Integer.MIN_VALUE ||
                bDistance[via] == Integer.MIN_VALUE ||
                sDistance[via] == Integer.MIN_VALUE
            ) {
                continue;
            }

            answer = Math.min(answer, aDistance[via] + bDistance[via] + sDistance[via]);
        }
        return answer;
    }

    public Map<Integer, List<int[]>> getMap(int n, int[][] fares) {
        Map<Integer, List<int[]>> map = new HashMap<>();

        for(int[] fare : fares) {
            map.putIfAbsent(fare[0], new ArrayList<>());
            map.putIfAbsent(fare[1], new ArrayList<>());
            map.get(fare[0]).add(new int[]{fare[1], fare[2]});
            map.get(fare[1]).add(new int[]{fare[0], fare[2]});
        }

        return map;
    }

    public int[] getDistance(int n, int startPosition, Map<Integer, List<int[]>> map) {
        int[] distance = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[startPosition] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{startPosition, 0});

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            if(visited[cur[0]]) {
                continue;
            }
            visited[cur[0]] = true;

            for(int[] nextInfo : map.getOrDefault(cur[0], new ArrayList<>())) {
                int next = nextInfo[0];
                int nextDistance = nextInfo[1];
                distance[next] = Math.min(distance[next], distance[cur[0]] + nextDistance);
                pq.add(new int[]{next, distance[next]});
            }
        }
        return distance;
    }
}