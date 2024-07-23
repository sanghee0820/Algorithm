import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int thueMorse(long k){
        if(k == 1){
            return 1;
        }
        if(k == 0){
            return 0;
        }

        if(k % 2 == 0){
            return thueMorse(k / 2);
        }
        return 1 - thueMorse(k / 2);
    }
    
    public static void main(String[] args) throws IOException {
        long k = Long.parseLong(br.readLine());
        bw.write(String.valueOf(thueMorse(k - 1)));
        bw.close();
        br.close();
    }
}
