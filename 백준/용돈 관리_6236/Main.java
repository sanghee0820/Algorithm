import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int solution(int[] moneys, int m) {

        int left = Arrays.stream(moneys).max().getAsInt();
        int right = 10_000 * 100_000;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (possible(moneys, m, mid)) {
                right = mid - 1;
                continue;
            }

            left = mid + 1;
        }

        return left;
    }

    public static boolean possible(int[] moneys, int m, int k) {
        int cnt = 0;
        int current = 0;
        for (int money : moneys) {
            if (current >= money) {
                current -= money;
                continue;
            }

            current = k - money;
            cnt++;
        }

        return cnt <= m;
    }

    public static void main(String[] args) throws IOException {
        int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] moneys = new int[nm[0]];

        for (int index = 0; index < nm[0]; index++) {
            moneys[index] = Integer.parseInt(br.readLine());
        }

        bw.write(solution(moneys, nm[1]) + "");
        bw.close();
        br.close();
    }
}