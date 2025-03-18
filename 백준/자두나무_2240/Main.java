import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int solution(int[] time, int move) {
        int[][][] dp = new int[2][time.length + 1][move + 1];

        for (int curTime = 1; curTime <= time.length; curTime++) {
            int curPlum = time[curTime - 1];
            if (curPlum == 1) {
                dp[0][curTime][0] = dp[0][curTime - 1][0] + 1;
            } else {
                dp[0][curTime][0] = dp[0][curTime - 1][0];
            }

            for (int curMove = 1; curMove <= move; curMove++) {
                if (curPlum == 1) {
                    dp[0][curTime][curMove] = Math.max(
                        dp[0][curTime - 1][curMove] + 1,
                        dp[1][curTime - 1][curMove - 1] + 1
                    );
                    dp[1][curTime][curMove] = Math.max(
                        dp[1][curTime - 1][curMove],
                        dp[0][curTime - 1][curMove - 1]
                    );
                    continue;
                }
                dp[0][curTime][curMove] = Math.max(
                    dp[0][curTime - 1][curMove],
                    dp[1][curTime - 1][curMove - 1]
                );
                dp[1][curTime][curMove] = Math.max(
                    dp[1][curTime - 1][curMove] + 1,
                    dp[0][curTime - 1][curMove - 1] + 1
                );
            }
        }

        int answer = 0;
        for (int curMove = 0; curMove <= move; curMove++) {
            answer = Math.max(answer,
                Math.max(dp[0][time.length][curMove], dp[1][time.length][curMove]));
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        int[] timeMove = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt)
            .toArray();

        int[] time = new int[timeMove[0]];
        for (int index = 0; index < time.length; index++) {
            time[index] = Integer.parseInt(br.readLine());
        }

        bw.write(String.valueOf(solution(time, timeMove[1])));

        bw.close();
        br.close();
    }
}