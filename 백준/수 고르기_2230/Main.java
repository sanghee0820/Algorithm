import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int solution(int[] ary, int m) {
        int answer = Integer.MAX_VALUE;
        Arrays.sort(ary);

        int left = 0;
        int right = 1;

        while (left < ary.length && right < ary.length) {
            var diff = Math.abs(ary[left] - ary[right]);

            if (diff >= m) {
                answer = Math.min(diff, answer);
                left++;
                continue;
            }
            right++;
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] a = new int[nm[0]];
        for (int index = 0; index < a.length; index++) {
            a[index] = Integer.parseInt(br.readLine());
        }

        bw.write(String.valueOf(solution(a, nm[1])));

        bw.close();
        br.close();
    }
}