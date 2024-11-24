import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int solution(int[][] matrix) {
        int answer = 0;

        int[][] dp = new int[matrix.length][matrix.length];

        for (int length = 1; length < matrix.length; length++) {
            for (int row = 0; row < matrix.length - length; row++) {
                dp[row][row + length] = Integer.MAX_VALUE;
                for (int column = row; column < row + length; column++) {
                    int cost = dp[row][column] + dp[column + 1][row + length]
                            + matrix[row][0] * matrix[column][1] * matrix[row + length][1];
                    dp[row][row + length] = Math.min(dp[row][row + length], cost);
                }
            }
        }

        return dp[0][matrix.length - 1];
    }

    public static void main(String[] args) throws IOException {
        var n = Integer.parseInt(br.readLine());

        int[][] matrix = new int[n][2];
        for (int i = 0; i < n; i++) {
            matrix[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        var answer = solution(matrix);
        bw.write(String.format("%d", answer));
        bw.close();
        br.close();
    }
}
