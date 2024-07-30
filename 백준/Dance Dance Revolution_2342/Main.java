import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static String solution(int[] positions) {
        int[][][] dp = new int[5][5][positions.length];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        var answer = dfs(dp, positions, 0, 0, 0);
        return String.valueOf(answer);
    }

    public static int dfs(int[][][] dp, int[] positions, int left, int right, int cnt) {

        if (cnt == positions.length - 1) {
            return 0;
        }

        if (dp[left][right][cnt] != -1) {
            return dp[left][right][cnt];
        }

        dp[left][right][cnt] = Math.min(
                dfs(dp, positions, positions[cnt], right, cnt + 1) + calcStrength(left, positions[cnt]),
                dfs(dp, positions, left, positions[cnt], cnt + 1) + calcStrength(right, positions[cnt])
        );

        return dp[left][right][cnt];
    }

    private static int calcStrength(int position, int nextPosition) {
        if (position == 0) {
            return 2;
        }

        int diff = Math.abs(position - nextPosition);
        if (diff == 0) {
            return 1;
        }
        if (diff == 2) {
            return 4;
        }
        return 3;
    }

    public static void main(String[] args) throws IOException {

        int[] positions = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        bw.write(solution(positions));
        bw.close();
        br.close();
    }
}
