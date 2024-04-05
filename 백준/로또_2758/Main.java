import java.io.*;

public class Main {
    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for(int i = 0; i < t; i++){
            String[] data = br.readLine().split(" ");
           int n = Integer.parseInt(data[0]);
           int m = Integer.parseInt(data[1]);

           long[][] dp = new long[m + 1][n + 1];
           dp[0][0] = 1;
           for(int number = 1; number < m + 1; number++){
               dp[number][0] = 1;
               for(int lottoIndex = 1; lottoIndex < n + 1; lottoIndex++){
                   dp[number][lottoIndex] = dp[number / 2][lottoIndex - 1] + dp[number - 1][lottoIndex];
               }
           }
            System.out.println(dp[m][n]);
        }
    }
    public static void main(String[] args) throws IOException {
        solution();
    }
}
