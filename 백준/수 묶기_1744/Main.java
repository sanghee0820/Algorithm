import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int solution(int[] ary) {
        List<Integer> overOne = new ArrayList<>();
        List<Integer> underOne = new ArrayList<>();
        int answer = 0;

        for (int data : ary) {
            if (data > 1) {
                overOne.add(data);
                continue;
            }
            if (data == 1) {
                answer++;
                continue;
            }
            underOne.add(data);
        }

        overOne.sort((a, b) -> b - a);
        underOne.sort(Comparator.comparingInt(a -> a));
        for (int index = 0; index < overOne.size(); index++) {
            if (index == overOne.size() - 1) {
                answer += overOne.get(index);
                continue;
            }
            answer += overOne.get(index) * overOne.get(++index);
        }

        for (int index = 0; index < underOne.size(); index++) {
            if (index == underOne.size() - 1) {
                answer += underOne.get(index);
                continue;
            }
            answer += underOne.get(index) * underOne.get(++index);
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        int aryLength = Integer.parseInt(br.readLine());

        int[] ary = new int[aryLength];
        for (int index = 0; index < aryLength; index++) {
            ary[index] = Integer.parseInt(br.readLine());
        }

        bw.write(solution(ary) + "");
        bw.close();
        br.close();
    }
}