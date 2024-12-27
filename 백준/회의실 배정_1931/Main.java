import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int solution(int[][] timeLines) {
        Arrays.sort(timeLines, (a, b) -> {
            if(a[1] == b[1]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });

        int answer = 0;
        int beforeEnd = 0;
        for(int[] timeLine : timeLines) {
            if(beforeEnd <= timeLine[0]) {
                answer++;
                beforeEnd = timeLine[1];
            }
        }
        return answer;
    }
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[][] timeLine = new int[n][2];
        for(int index = 0; index < n; index++) {
            timeLine[index] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        bw.write(String.valueOf(solution(timeLine)));

        bw.close();
        br.close();
    }
}