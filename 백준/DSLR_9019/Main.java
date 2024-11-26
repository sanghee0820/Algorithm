import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class NumberInfo {
    int number;
    StringBuilder path;

    public NumberInfo(int input) {
        this.number = input;
        this.path = new StringBuilder();
    }

    public NumberInfo(int input, StringBuilder path, char command) {
        this.number = input;
        this.path = new StringBuilder(path).append(command);
    }

    public String toAnswer() {
        return path.toString();
    }
}

class Main {
    public static String solution(int input, int output) {
        boolean[] checked = new boolean[10000];
        Queue<NumberInfo> queue = new LinkedList<>();
        queue.add(new NumberInfo(input));
        checked[input] = true;

        while (!queue.isEmpty()) {
            var current = queue.poll();

            if (current.number == output) {
                return current.toAnswer();
            }

            // D
            int next = (current.number * 2) % 10000;
            if (!checked[next]) {
                checked[next] = true;
                queue.add(new NumberInfo(next, current.path, 'D'));
            }

            // S
            next = current.number == 0 ? 9999 : current.number - 1;
            if (!checked[next]) {
                checked[next] = true;
                queue.add(new NumberInfo(next, current.path, 'S'));
            }

            // L
            next = ((current.number % 1000) * 10) + (current.number / 1000);
            if (!checked[next]) {
                checked[next] = true;
                queue.add(new NumberInfo(next, current.path, 'L'));
            }

            // R
            next = ((current.number % 10) * 1000) + (current.number / 10);
            if (!checked[next]) {
                checked[next] = true;
                queue.add(new NumberInfo(next, current.path, 'R'));
            }
        }
        return "";
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        for (int index = 0; index < n; index++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            bw.write(solution(input[0], input[1]) + "\n");
        }
        bw.close();
        br.close();
    }
}
