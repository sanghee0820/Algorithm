import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {


    public static int solution(int[] arr, int n){

        int[] dp = new int[n + 1];
        for(int amount = 1; amount < n + 1; amount++){
            for(int index = amount; index < n + 1; index ++){
                if(dp[index] == 0){
                    dp[index] = dp[index - amount] + arr[amount - 1];
                    continue;
                }

                dp[index] = Math.min(dp[index], dp[index - amount] + arr[amount - 1]);
            }
        }
        return dp[n];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        bw.write(String.valueOf(solution(arr, n)));

        bw.close();
        br.close();

    }
}
