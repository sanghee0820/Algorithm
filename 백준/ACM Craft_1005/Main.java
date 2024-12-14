import java.io.*;
import java.util.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int solution(int n, int[] times, int[][] orders, int target) {
        int[] inDegrees = new int[n + 1];
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int[] order : orders) {
            graph.putIfAbsent(order[0], new ArrayList<>());
            graph.get(order[0]).add(order[1]);
            inDegrees[order[1]]++;
        }

        int[] totalTimes = new int[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        for (int node = 1; node < n + 1; node++) {
            if (inDegrees[node] == 0) {
                queue.add(node);
                totalTimes[node] = times[node - 1];
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int nextNode : graph.getOrDefault(cur, new ArrayList<>())) {
                totalTimes[nextNode] = Math.max(totalTimes[cur] + times[nextNode - 1], totalTimes[nextNode]);
                inDegrees[nextNode]--;
                if (inDegrees[nextNode] == 0) {
                    queue.add(nextNode);
                }
            }
        }
        return totalTimes[target];
    }

    public static void main(String[] args) throws IOException {
        var t = Integer.parseInt(br.readLine());

        for (int test = 0; test < t; test++) {
            int[] nk = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] constructTime = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[][] constructOrder = new int[nk[1]][2];
            for (int order = 0; order < nk[1]; order++) {
                constructOrder[order] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            int target = Integer.parseInt(br.readLine());

            bw.write(solution(nk[0], constructTime, constructOrder, target) + "\n");
        }
        bw.close();
        br.close();
    }
}
