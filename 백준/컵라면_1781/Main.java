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

    public static int solution(int[][] problems) {
        int answer = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });
        pq.addAll(Arrays.asList(problems));

        PriorityQueue<Integer> selected = new PriorityQueue<>();
        int day = 1;
        while (!pq.isEmpty()) {
            var cur = pq.poll();
            if (day <= cur[0]) {
                selected.add(cur[1]);
                day++;
                continue;
            }
            if (!selected.isEmpty() && selected.peek() > cur[1]) {
                continue;
            }
            selected.poll();
            selected.add(cur[1]);
        }

        return selected.stream().mapToInt(i -> i).sum();
    }


    public static void main(String[] args) throws IOException {
        int problemCount = Integer.parseInt(br.readLine());
        int[][] problems = new int[problemCount][2];

        for (int index = 0; index < problemCount; index++) {
            problems[index] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        bw.write(solution(problems) + "");
        bw.close();
        br.close();
    }
}