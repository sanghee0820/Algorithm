import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Main {
    public static int solution(String input, String output) {
        StringBuilder sb = new StringBuilder(output);

        while (sb.length() > input.length()) {
            Character last = sb.charAt(sb.length() - 1);
            if (last.equals('A')) {
                doA(sb);
                continue;
            }
            doB(sb);
        }
        if (sb.toString().equals(input)) {
            return 1;
        }
        return 0;
    }

    public static void doA(StringBuilder input) {
        input.deleteCharAt(input.length() - 1);
    }

    public static void doB(StringBuilder input) {
        input.deleteCharAt(input.length() - 1);
        input.reverse();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String input = br.readLine();
        String output = br.readLine();
        bw.write(String.format("%d", solution(input, output)));
        bw.close();
        br.close();
    }
}
