import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static long solution(int n) {
        Long[] dp = new Long[51];

        dp[0] = 1L;
        dp[1] = 1L;
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 2] + dp[i - 1] + 1) % 1000000007;
        }

        return dp[n];
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());

        bw.write(String.valueOf(solution(n)));
        bw.close();
        br.close();
    }
}
