import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void solution(int n) throws IOException {
        BigInteger count = new BigInteger("2");
        
        bw.write(count.pow(n).subtract(new BigInteger("1")) + "\n");
        
        if(n <= 20){
            hanoi(n, 1, 3, 2);
        }
    }

    public static void hanoi(int n, int start, int to, int mid) throws IOException {
        if(n == 1){
            bw.write(start + " " + to + "\n");
            return;
        }

        hanoi(n - 1, start, mid, to);
        bw.write(start + " " + to + "\n");
        hanoi(n - 1, mid, to, start);
    }


    public static void main(String[] args) throws IOException {
        var input = br.readLine();
        var n = Integer.parseInt(input);

        solution(n);

        bw.close();
        br.close();

    }
}
