import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static final String REGEX = "(100+1+|01)+";

    public static String solution(String signal) {
        if (signal.matches(REGEX)) {
            return "YES";
        }

        return "NO";
    }


    public static void main(String[] args) throws IOException {
        int tc = Integer.parseInt(br.readLine());

        for (int index = 0; index < tc; index++) {
            String signal = br.readLine();

            bw.write(solution(signal) + "\n");
        }
        bw.close();
        br.close();
    }
}