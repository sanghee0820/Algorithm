import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int solution(int[][] bags, int max) {
        int[][] dp = new int[bags.length + 1][max + 1];

        for (int i = 1; i < bags.length + 1; i++) {
            var bag = bags[i - 1];

            for (int j = 1; j < max + 1; j++) {
                if (j < bag[0]) {
                    dp[i][j] = dp[i - 1][j];
                    continue;
                }
                dp[i][j] = Math.max(dp[i - 1][j - bag[0]] + bag[1], dp[i - 1][j]);
            }
        }
        return dp[bags.length][max];
    }

    public static void main(String[] args) throws IOException {
        var data = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        var n = data[0];
        var m = data[1];

        int[][] bags = new int[n][2];
        for (int i = 0; i < n; i++) {
            bags[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        bw.write(String.valueOf(solution(bags, m)));
        bw.close();
        br.close();
    }
}
