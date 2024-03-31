import java.util.*;
import java.io.*;

public class Main2 {


    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");
        int n = Integer.parseInt(data[0]);
        int m = Integer.parseInt(data[1]);

        int[] memories = new int[n];
        int[] costs = new int[n];

        data = br.readLine().split(" ");
        for(int i = 0; i < n; i++){
            memories[i] = Integer.parseInt(data[i]);
        }
        data = br.readLine().split(" ");
        int sumCost = 0;
        for(int i = 0; i < n; i++){
            costs[i] = Integer.parseInt(data[i]);
            sumCost += costs[i];
        }

        long[][] dp = new long[n][sumCost + 1];
        for(long[] row : dp){
            Arrays.fill(row, -1);
            row[0] = 0;
        }
        int answer = Integer.MAX_VALUE;
        dp[0][costs[0]] = memories[0];
        if(dp[0][costs[0]] >= m){
            answer = costs[0];
        }

        for(int row = 1; row < dp.length; row++){
            for(int costIndex = 0; costIndex < dp[row].length; costIndex++){
                if(costIndex >= costs[row] && dp[row - 1][costIndex - costs[row]] != -1){
                    dp[row][costIndex] = Math.max(dp[row - 1][costIndex - costs[row]] + memories[row], dp[row - 1][costIndex]);
                }else{
                    dp[row][costIndex] = dp[row -1][costIndex];
                }

                if(dp[row][costIndex] >= m){
                    answer = Math.min(answer, costIndex);
                }
            }
        }

        System.out.println(answer);
    }
    public static void main(String[] args) throws IOException {
        solution();
    }
}

