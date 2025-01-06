import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int solution(int[] ary) {
        Arrays.sort(ary);
        int n = ary.length;
        int max = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                for (int k = n - 1; k >= 0; k--) {
                    if (ary[k] < max) {
                        break;
                    }
                    
                    int sum = ary[k] - ary[i] - ary[j];
                    if (sum <= 0) {
                        break;
                    }

                    if (Arrays.binarySearch(ary, sum) >= 0) {
                        max = Math.max(max, ary[k]);
                        break;
                    }
                }
            }
        }

        return max;
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());

        int[] ary = new int[n];

        for (int index = 0; index < n; index++) {
            ary[index] = Integer.parseInt(br.readLine());
        }

        bw.write(solution(ary) + "");
        bw.close();
        br.close();
    }
}