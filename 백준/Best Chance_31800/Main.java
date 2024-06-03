import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main { //	89728KB, 860ms

    public static int[] solution(int n, int[] advantages, int[] cost){
        int[] answer = new int[n];

        int maxAdvantage = 0;
        int secondMaxAdvantage = 0;
        for (int advantage : advantages) {
            if (advantage > maxAdvantage) {
                secondMaxAdvantage = maxAdvantage;
                maxAdvantage = advantage;
            } else if (advantage > secondMaxAdvantage) {
                secondMaxAdvantage = advantage;
            }
        }

        for (int i = 0; i < n; i++) {
            int maxOtherAdvantage = (advantages[i] == maxAdvantage) ? secondMaxAdvantage : maxAdvantage;
            int opportunityCost = maxOtherAdvantage - cost[i];
            answer[i] = advantages[i] - opportunityCost - cost[i];
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        int[] advantages = new int[n];

        String[] data = br.readLine().split(" ");
        for(int i = 0; i < n; i++){
            advantages[i] = Integer.parseInt(data[i]);
        }

        int[] cost = new int[n];

        data = br.readLine().split(" ");
        for(int i = 0; i < n; i++){
            cost[i] = Integer.parseInt(data[i]);
        }

        for(int answer : solution(n, advantages, cost)){
            bw.write(answer + " ");
        }
        bw.flush();
        br.close();
        bw.close();
    }
}
