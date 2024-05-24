import java.io.*;
import java.util.*;

public class Main {

    public static int[] solution(int n, int m, int[][] conditions){
        int[] answer = new int[n];

        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[n + 1];
        for(int i = 0; i < n + 1; i++){
            graph.add(new ArrayList<>());
        }

        for(int[] condition : conditions){
            graph.get(condition[0]).add(condition[1]);
            inDegree[condition[1]]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
                answer[i - 1] = 1;
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next : graph.get(current)) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    queue.add(next);
                    answer[next - 1] = answer[current - 1] + 1;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] data = br.readLine().split(" ");
        int n = Integer.parseInt(data[0]);
        int m = Integer.parseInt(data[1]);

        int[][] condition = new int[m][2];

        for(int i = 0; i < m; i++){
            data = br.readLine().split(" ");
            condition[i][0] = Integer.parseInt(data[0]);
            condition[i][1] = Integer.parseInt(data[1]);
        }

        for(int answer : solution(n, m, condition)){
            bw.write(answer + " ");
        }
        bw.flush();
        br.close();
        bw.close();
    }
}
