import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static int[][] solution(int n, int m){
        List<List<Integer>> answer = new ArrayList<>();

        generateCombination(n, m, answer, new ArrayList<>());

        return answer.stream()
                .map(combination -> combination.stream().mapToInt(Integer::intValue).toArray())
                .toArray(int[][]::new);
    }

    public static void generateCombination(int n, int m, List<List<Integer>> answer, List<Integer> combinations){
        if(combinations.size() == m){
            answer.add(new ArrayList<>(combinations));
            return;
        }

        for(int i = 1; i <= n; i++){
            if (combinations.contains(i)) {
                continue;
            }
            combinations.add(i);
            generateCombination(n, m, answer, combinations);
            combinations.remove(combinations.size() - 1);
        }

    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        var input = br.readLine().split(" ");
        var n = Integer.parseInt(input[0]);
        var m = Integer.parseInt(input[1]);

        var answer = solution(n, m);
        for(int[] row : answer){
            bw.write(
                    Arrays.stream(row)
                            .mapToObj(String::valueOf)
                            .collect(Collectors.joining(" ")));
            bw.newLine();
        }

        bw.close();
        br.close();

    }
}
