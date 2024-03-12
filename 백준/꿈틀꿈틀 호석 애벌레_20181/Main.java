import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N, K;
        String[] NnK = br.readLine().split(" ");
        N = Integer.parseInt(NnK[0]);
        K = Integer.parseInt(NnK[1]);

        String[] data = br.readLine().split(" ");
        int[] ary = new int[N];
        for(int i = 0; i < N; i++){
            ary[i] = Integer.parseInt(data[i]);
        }

        int left = 0, right = 1;
        long[] dp = new long[N + 1];
        int cur = ary[left];
        while(N >= right){
            if(cur >= K){
                while(cur >= K){
                    dp[right] = Math.max(dp[left] + cur - K, dp[right]);
                    cur -= ary[left++];
                }
                continue;
            }
            dp[right] = Math.max(dp[right], dp[right - 1]);
            if(right == N){
                break;
            }
            cur += ary[right++];
        }

        System.out.println(dp[N]);
    }
    public static void main(String[] args) throws IOException {
        solution();
    }
}
