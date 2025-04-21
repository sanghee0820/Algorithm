public class Solution {
    public int solution(int n, int[][] results) {
        boolean[][] matchResult = new boolean[n + 1][n + 1];

        for (int[] result : results) {
            int from = result[0];
            int to = result[1];
            matchResult[from][to] = true;
        }

        for (int via = 1; via <= n; via++) {
            for (int from = 1; from <= n; from++) {
                for (int to = 1; to <= n; to++) {
                    if (matchResult[from][via] && matchResult[via][to]) {
                        matchResult[from][to] = true;
                    }
                }
            }
        }

        int answer = 0;
        for (int player = 1; player <= n; player++) {
            int cnt = 0;

            for (int opponent = 1; opponent <= n; opponent++) {
                if (matchResult[player][opponent] || matchResult[opponent][player]) {
                    cnt++;
                }
            }

            if (cnt == n - 1) {
                answer++;
            }
        }

        return answer;
    }
}