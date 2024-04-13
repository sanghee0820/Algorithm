import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Main {

    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int people = Integer.parseInt(br.readLine());
        int[] weights = new int[people];
        int sum = 0;
        for (int i = 0; i < people; i++) {
            weights[i] = Integer.parseInt(br.readLine());
            sum += weights[i];
        }

        int[] dp = new int[sum + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        int diff = Integer.MAX_VALUE;
        dp[0] = 0;
        for(int weight : weights){
            for(int index = sum; index >= weight; index--){
                if (dp[index - weight] != Integer.MAX_VALUE) {
                    int value = dp[index - weight] + 1;
                    
                    if(dp[index] == Integer.MAX_VALUE){
                        dp[index] = dp[index - weight] + 1;
                    }else{
                        dp[index] = Math.max(dp[index - weight] + 1, dp[index]);
                    }
                    if (value == people / 2) {
                        diff = Math.min(Math.abs(sum - 2 * index), diff);
                    }
                }
            }
        }

        int minValue = (sum - diff) / 2;
        int maxValue = sum - minValue;
        System.out.println(minValue + " " + maxValue);
    }

    public static void main(String[] args) throws IOException {
        solution();
    }
}
