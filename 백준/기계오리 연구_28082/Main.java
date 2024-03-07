import java.util.*;
import java.io.*;

public class Main {

    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] batteryInfos = br.readLine().split(" ");
        int batteryCnt = Integer.parseInt(batteryInfos[0]);
        int maxBatteryCnt = Integer.parseInt(batteryInfos[1]);

        long[] dp = new long[50001];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(String battery : br.readLine().split(" ")){
            for(int i = 50000 - Integer.parseInt(battery); i >= 0; i--){
                dp[i + Integer.parseInt(battery)] = Math.min(dp[i +  Integer.parseInt(battery)], dp[i] + 1);
            }
        }
        List<Integer> output = new ArrayList<>();
        for(int i = 1; i < 50001; i++){
            if(dp[i] <= maxBatteryCnt){
                output.add(i);
            }
        }
        System.out.println(output.size());
        
        for(Integer data : output){
            System.out.print(data + " ");
        }
    }
    public static void main(String[] args) throws IOException {
        solution();
    }
}
