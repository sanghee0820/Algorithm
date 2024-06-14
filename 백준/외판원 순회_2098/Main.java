import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

    public static int solution(int n, int[][] map) {

        int[][] dp = new int[(1 << n) - 1][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return tsp(0, 1, dp, map);
    }

    public static int tsp(int position, int visited, int[][] dp, int[][] map) {
        if (visited == (1 << map.length) - 1) {
            if (map[position][0] == 0) {
                return Integer.MAX_VALUE;
            }
            return map[position][0];
        }

        if (dp[visited][position] != -1) {
            return dp[visited][position];
        }
        dp[visited][position] = Integer.MAX_VALUE;
        for (int i = 0; i < map.length; i++) {
            if ((visited & (1 << i)) == 0 && map[position][i] != 0) {
                int cost = tsp(i, visited | (1 << i), dp, map);
                if (cost != Integer.MAX_VALUE) {
                    dp[visited][position] = Math.min(dp[visited][position], cost + map[position][i]);
                }
            }
        }

        return dp[visited][position];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int[][] map = new int[n][n];

        for (int i = 0; i < n; i++) {
            int[] row = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            map[i] = row;
        }

        bw.write(String.valueOf(solution(n, map)));

        bw.flush();
        bw.close();
        br.close();
    }
}
