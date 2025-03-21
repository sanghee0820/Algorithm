import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static String solution(int n, int c, int[][] dependencies) {
        Map<Integer, List<long[]>> dependencyMap = new HashMap<>();

        for (int[] dependency : dependencies) {
            int a = dependency[0];
            int b = dependency[1];
            dependencyMap.putIfAbsent(b, new ArrayList<>());

            dependencyMap.get(b).add(new long[]{a, dependency[2]});
        }

        boolean[] visited = new boolean[n + 1];
        long[] distance = new long[n + 1];
        Arrays.fill(distance, Long.MAX_VALUE);
        distance[c] = 0;

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
        pq.add(new long[]{c, 0});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            int curNode = (int) cur[0];
            long curDist = cur[1];

            if (visited[curNode]) {
                continue;
            }
            visited[curNode] = true;

            for (long[] next : dependencyMap.getOrDefault(curNode, new ArrayList<>())) {
                int nextNode = (int) next[0];
                long nextCost = next[1];

                if (!visited[nextNode] && curDist + nextCost < distance[nextNode]) {
                    distance[nextNode] = curDist + nextCost;
                    pq.add(new long[]{nextNode, distance[nextNode]});
                }
            }
        }

        long maxDistance = Long.MIN_VALUE;
        int cnt = 0;
        for (int computer = 1; computer <= n; computer++) {
            if (distance[computer] == Long.MAX_VALUE) {
                continue;
            }
            cnt++;
            maxDistance = Math.max(maxDistance, distance[computer]);
        }
        return String.format("%d %d", cnt, maxDistance);
    }

    public static void main(String[] args) throws IOException {
        int tc = Integer.parseInt(br.readLine());

        for (int t = 0; t < tc; t++) {
            int[] ndc = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt)
                .toArray();
            int[][] dependency = new int[ndc[1]][3];
            for (int d = 0; d < ndc[1]; d++) {
                dependency[d] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt)
                    .toArray();
            }
            bw.write(solution(ndc[0], ndc[2], dependency) + "\n");
        }
        bw.close();
        br.close();
    }


}