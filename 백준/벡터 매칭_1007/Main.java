import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static double solution(int[][] points) {
        int n = points.length;
        double minResult = Double.MAX_VALUE;

        for (int i = 0; i < (1 << n); i++) {
            if (Integer.bitCount(i) != n / 2) {
                continue;
            }

            int sumX = 0, sumY = 0;

            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    sumX += points[j][0];
                    sumY += points[j][1];
                    continue;
                }
                sumX -= points[j][0];
                sumY -= points[j][1];
            }

            double result = Math.sqrt(Math.pow(sumX, 2) + Math.pow(sumY, 2));
            minResult = Math.min(minResult, result);
        }

        return minResult;
    }

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine());

        for (int test = 0; test < testCase; test++) {
            int n = Integer.parseInt(br.readLine());
            int[][] points = new int[n][2];
            for (int pointIndex = 0; pointIndex < n; pointIndex++) {
                points[pointIndex] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            bw.write(String.format("%.7f\n", solution(points)));
        }
        bw.close();
        br.close();
    }
}