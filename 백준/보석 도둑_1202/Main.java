import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static long solution(int[] bags, int[][] jewels) {
        long answer = 0;
        Arrays.sort(bags);
        Arrays.sort(jewels, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        int checkedIndex = 0;
        for (int bag : bags) {
            while (checkedIndex < jewels.length && jewels[checkedIndex][0] <= bag) {
                pq.add(jewels[checkedIndex++][1]);
            }
            if (pq.isEmpty()) {
                continue;
            }
            answer += pq.poll();
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int N = input[0];
        int M = input[1];
        int[][] jewels = new int[N][2];
        int[] bags = new int[M];
        for (int i = 0; i < N; i++) {
            jewels[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        for (int i = 0; i < M; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }
        long answer = solution(bags, jewels);
        bw.write(String.valueOf(answer));
        bw.close();
        br.close();
    }
}
