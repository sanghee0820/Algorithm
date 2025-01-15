import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void solution(int[] ary) throws IOException {
        PriorityQueue<Integer> small = new PriorityQueue<>((a, b) -> b - a); //큰순
        PriorityQueue<Integer> big = new PriorityQueue<>();  //작은순

        int middle = ary[0];

        for (int size = 2; size < ary.length + 1; size++) {
            int current = ary[size - 1];
            bw.write(middle + "\n");

            // 고려하고있는 숫자 개수가 짝수일 경우 무조건 big이 하나 큼
            if (size % 2 == 0) {
                if (current < middle) {
                    small.add(current);
                    big.add(middle);
                    middle = small.poll();
                    continue;
                }
                big.add(current);
                continue;
            }

            // 고려하고 있는 숫자가 홀수 일 경우 무조건 small과 big이 같음
            if (current < middle) {
                small.add(current);
                continue;
            }
            big.add(current);
            small.add(middle);
            middle = big.poll();
        }
        bw.write(middle + "\n");
    }


    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[] ary = new int[n];
        for (int index = 0; index < n; index++) {
            ary[index] = Integer.parseInt(br.readLine());
        }
        solution(ary);
        bw.close();
        br.close();
    }
}