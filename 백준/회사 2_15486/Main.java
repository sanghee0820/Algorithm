import java.util.*;
import java.io.*;


public class Main {

    static class Consult{
        int time;
        int pay;

        public Consult(int time, int pay) {
            this.time = time;
            this.pay = pay;
        }
    }
    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        List<Consult> consultInfo = new ArrayList<>();
        for(int i = 0; i < n; i++){
            String[] data = br.readLine().split(" ");
            consultInfo.add(new Consult(Integer.parseInt(data[0]), Integer.parseInt(data[1])));
        }
        consultInfo.add(new Consult(1, 0));
        int[] dp = new int[n + 1];
        int max = -1;
        for(int i = 0; i <= n; i++){
            Consult cur = consultInfo.get(i);
            max = Math.max(dp[i], max);
            if(i + cur.time > n){
                dp[i] = max;
                continue;
            }
            dp[i + cur.time] = Math.max(max + cur.pay, dp[i + cur.time]);
        }
        System.out.println(dp[n]);
    }
    public static void main(String[] args) throws IOException {
        solution();
    }
}
