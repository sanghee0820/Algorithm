import java.util.*;
import java.io.*;

public class Main {

    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");
        int k = Integer.parseInt(data[0]);
        int n = Integer.parseInt(data[1]);

        int[] lanCables = new int[k];
        for(int i = 0; i < k; i++){
            lanCables[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(lanCables);

        long low = 1;
        long high = lanCables[k - 1];

        while(low <= high){
            long mid = (low + high) / 2;

            long lineCnt = 0;
            for(int lanCable : lanCables){
                lineCnt += lanCable / mid;
            }

            if(lineCnt >= n){
                low = mid + 1;
                continue;
            }
            high = mid - 1;
        }
        System.out.println(high);
    }
    public static void main(String[] args) throws IOException {
        solution();
    }
}

