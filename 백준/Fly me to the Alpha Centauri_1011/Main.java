import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int solution(int[] positions) {
        int distance = positions[1] - positions[0];

        if (distance <= 3) {
            return distance;
        }

        int sqrt = (int) Math.sqrt(distance);
        int moves = sqrt * 2;

        if (sqrt * sqrt == distance) {
            return moves - 1;
        }

        if (distance <= sqrt * sqrt + sqrt) {
            return moves;
        }

        return moves + 1;
    }

    public static int[][] dpInit(int distance) {
        int[][] dp;
        if (distance % 2 == 0) {
            dp = new int[distance / 2 + 1][2];
        } else {
            dp = new int[distance / 2 + 2][2];
        }

        for (int index = 0; index < dp.length; index++) {
            dp[index][0] = Integer.MAX_VALUE;
        }
        return dp;
    }

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine());

        for (int test = 0; test < testCase; test++) {
            int[] positions = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            bw.write(String.format("%d\n", solution(positions)));
        }
        bw.close();
        br.close();
    }
}