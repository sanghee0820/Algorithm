import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static final int[][] DIRECTIONS = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static int solution(int[][] map) {
        int[] distance = new int[map.length * map[0].length];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[0] = map[0][0];

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });
        pq.add(new int[]{0, distance[0]});

        boolean[] visited = new boolean[map.length * map.length];

        while (!pq.isEmpty()) {
            var cur = pq.poll();
            if (visited[cur[0]]) {
                continue;
            }
            int row = cur[0] / map.length;
            int column = cur[0] % map.length;
            visited[cur[0]] = true;
            for (int[] DIRECTION : DIRECTIONS) {
                int nextRow = row + DIRECTION[0];
                int nextColumn = column + DIRECTION[1];
                if (isOutOfRange(map, nextRow, nextColumn)) {
                    continue;
                }
                int key = nextRow * map.length + nextColumn;
                if (visited[key]) {
                    continue;
                }
                if (distance[key] > distance[cur[0]] + map[nextRow][nextColumn]) {
                    distance[key] = distance[cur[0]] + map[nextRow][nextColumn];
                    pq.add(new int[]{key, distance[key]});
                }
            }
        }

        return distance[distance.length - 1];
    }

    private static boolean isOutOfRange(int[][] map, int row, int column) {
        return row < 0 || column < 0 || row >= map.length || column >= map.length;
    }


    public static void main(String[] args) throws IOException {
        int tc = 1;
        while (true) {
            int t = Integer.parseInt(br.readLine());
            if (t == 0) {
                break;
            }
            int[][] map = new int[t][t];
            for (int index = 0; index < t; index++) {
                map[index] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt)
                    .toArray();
            }

            bw.write(String.format("Problem %d: ", tc++) + solution(map) + "\n");
        }
        bw.close();
        br.close();
    }


}