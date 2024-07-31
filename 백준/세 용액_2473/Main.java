import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


    public static int[] solution(int[] liqueurs) {
        Arrays.sort(liqueurs);

        int[] answer = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
        long minDiff = 3_000_000_001L;

        for (int i = 0; i < liqueurs.length; i++) {
            int left = i + 1;
            int right = liqueurs.length - 1;

            while (left < right) {
                long temp = (long) liqueurs[left] + liqueurs[right] + liqueurs[i];

                if (Math.abs(temp) < minDiff) {
                    minDiff = Math.abs(temp);
                    answer[0] = liqueurs[i];
                    answer[1] = liqueurs[left];
                    answer[2] = liqueurs[right];
                }

                if (temp < 0) {
                    left++;
                    continue;
                }

                right--;
            }
        }
        Arrays.sort(answer);

        return answer;
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] liqueurs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        bw.write(Arrays.stream(solution(liqueurs)).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
        bw.close();
        br.close();
    }
}
