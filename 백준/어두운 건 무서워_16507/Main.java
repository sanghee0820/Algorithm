import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int[] solution(int[][] map, int[][] positions) {
        int[] answer = new int[positions.length];

        int[][] prefixSum = new int[map.length + 1][map[0].length + 1];
        for (int row = 1; row < prefixSum.length; row++) {
            for (int column = 1; column < prefixSum[0].length; column++) {
                prefixSum[row][column] =
                        prefixSum[row - 1][column] + map[row - 1][column - 1] + prefixSum[row][column - 1] - prefixSum[
                                row - 1][column - 1];
            }
        }

        for (int answerIndex = 0; answerIndex < positions.length; answerIndex++) {
            var target = positions[answerIndex];
            answer[answerIndex] =
                    (
                            prefixSum[target[2]][target[3]] +
                            prefixSum[target[0] - 1][target[1] - 1] -
                            prefixSum[target[0] - 1][target[3]] -
                            prefixSum[target[2]][target[1] - 1]
                    ) / ((target[2] - target[0] + 1) * (target[3] - target[1] + 1));
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        var data = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        var n = data[0];
        var m = data[1];
        var cnt = data[2];

        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int[][] positions = new int[cnt][4];
        for (int i = 0; i < cnt; i++) {
            positions[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        var answers = solution(map, positions);

        for (int answer : answers) {
            bw.write(answer + "\n");
        }
        bw.close();
        br.close();
    }
}
