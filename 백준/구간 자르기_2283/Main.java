import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static String solution(int[][] lines, int target) {
        int[] answer = new int[2];

        int maxLength = 0;
        int[] prefixSum = new int[1_000_002];
        for (int[] line : lines) {
            prefixSum[line[0] + 1]++;
            prefixSum[line[1] + 1]--;
            maxLength = Math.max(line[1], maxLength);
        }
        for (int index = 1; index <= maxLength; index++) {
            prefixSum[index] += prefixSum[index - 1];
        }
        for (int index = 1; index <= maxLength; index++) {
            prefixSum[index] += prefixSum[index - 1];
        }
        int left = 0;
        int right = 0;
        while (right <= maxLength) {
            int cur = prefixSum[right] - prefixSum[left];
            if (target == cur) {
                answer[0] = left;
                answer[1] = right;
                break;
            }

            if (target > cur) {
                right++;
                continue;
            }
            left++;
        }

        return String.format("%d %d", answer[0], answer[1]);
    }

    public static void main(String[] args) throws IOException {
        int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[][] lines = new int[nm[0]][2];

        for (int row = 0; row < nm[0]; row++) {
            lines[row] = Arrays.stream(br.readLine().split((" "))).mapToInt(Integer::parseInt)
                .toArray();
        }
        bw.write(solution(lines, nm[1]));
        bw.close();
        br.close();
    }
}