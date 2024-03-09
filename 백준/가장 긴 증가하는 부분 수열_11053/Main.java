import java.util.*;
import java.io.*;


public class Main {
    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] ary = new int[Integer.parseInt(br.readLine())];
        String[] data = br.readLine().split(" ");
        for(int i = 0; i < ary.length; i++){
            ary[i] = Integer.parseInt(data[i]);
        }

        int[] dp = new int[ary.length];
        Arrays.fill(dp, 1);
        int answer = 0;
        for(int i = 0; i < ary.length; i++){
            for(int j = 0; j < i; j++){
                if(ary[i] > ary[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            answer = Math.max(dp[i], answer);
        }
        System.out.println(answer);
    }
    public static void main(String[] args) throws IOException {
        solution();
    }
}
