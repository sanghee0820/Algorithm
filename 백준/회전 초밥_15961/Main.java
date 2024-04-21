import java.util.*;
import java.io.*;

public class Main {

    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] data = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int n = data[0];
        int sushiTypeCount = data[1];
        int period = data[2];
        int couponNumber = data[3];

        int[] belt = new int[n];
        for(int i = 0; i < n; i++){
            belt[i] = Integer.parseInt(br.readLine());
        }

        int[] dishInfo = new int[sushiTypeCount + 1];
        Set<Integer> before = new HashSet<>();
        for(int i = 0; i < period; i++){
            before.add(belt[i]);
            dishInfo[belt[i]]++;
        }

        int maxDish = checkSize(before, couponNumber);

        int left = 1;
        int right = period;
        for(; left < n; left++){
            dishInfo[belt[left - 1]] --;
            if(dishInfo[belt[left - 1]] == 0){
                before.remove(belt[left - 1]);
            }

            dishInfo[belt[right]]++;
            before.add(belt[right]);
            right = (right + 1) % n;

            int size = checkSize(before, couponNumber);

            maxDish = Math.max(maxDish, size);

        }
        System.out.println(maxDish);
    }

    private static int checkSize(Set<Integer> set, int couponNumber){
        int size = set.size();
        if(!set.contains(couponNumber)){
            size++;
        }

        return size;
    }

    public static void main(String[] args) throws IOException {

        solution();
    }
}
