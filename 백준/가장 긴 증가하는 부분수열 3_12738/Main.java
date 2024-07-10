import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void solution(int n, int[] ary) throws IOException {
        List<Integer> LIS = new ArrayList<>();
        LIS.add(Integer.MAX_VALUE);

        for (int i = 0; i < n; i++) {
            int index = Collections.binarySearch(LIS, ary[i]);
            index = index >= 0 ? index : Math.abs(index) - 1;

            if (index == LIS.size()) {
                LIS.add(ary[i]);
                continue;
            }
            LIS.set(index, ary[i]);
        }
        bw.write(String.valueOf(LIS.size()));
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] ary = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        solution(N, ary);

        bw.close();
        br.close();
    }
}