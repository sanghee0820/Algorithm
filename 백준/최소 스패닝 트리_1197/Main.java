import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int solution(int n, int[][] edges) {
        int answer = 0;
        int[] parents = initUF(n);
        Arrays.sort(edges, Comparator.comparingInt(a -> a[2]));

        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            int cost = edge[2];
            if (isCycle(parents, a, b)) {
                continue;
            }
            union(parents, a, b);
            answer += cost;
        }
        return answer;
    }

    private static boolean isCycle(int[] parents, int a, int b) {
        return find(parents, a) == find(parents, b);
    }

    private static int[] initUF(int n) {
        int[] parents = new int[n + 1];
        for (int index = 0; index <= n; index++) {
            parents[index] = index;
        }

        return parents;
    }

    private static void union(int[] parents, int a, int b) {
        int aParent = find(parents, a);
        int bParent = find(parents, b);
        parents[bParent] = aParent;
    }

    private static int find(int[] parents, int target) {
        if (parents[target] == target) {
            return target;
        }

        parents[target] = find(parents, parents[target]);
        return parents[target];
    }

    public static void main(String[] args) throws IOException {
        int[] ve = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[][] edges = new int[ve[1]][3];
        for (int index = 0; index < ve[1]; index++) {
            edges[index] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        bw.write(String.valueOf(solution(ve[0], edges)));

        bw.close();
        br.close();
    }
}