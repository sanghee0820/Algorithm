import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int solution(long min, long max) {
        int count = (int) (max - min + 1);

        boolean[] isSquareFree = new boolean[count];

        for (long i = 2; i * i <= max; i++) {
            long square = i * i;

            long start = min + ((min % square == 0) ? 0 : (square - min % square));

            for (long j = start; j <= max; j += square) {
                int target = (int) (j - min);
                if (isSquareFree[target]) {
                    continue;
                }
                isSquareFree[target] = true;
                count--;
            }
        }

        return count;
    }


    public static void main(String[] args) throws IOException {
        long[] minMax = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        bw.write(solution(minMax[0], minMax[1]) + "");
        bw.close();
        br.close();
    }
}