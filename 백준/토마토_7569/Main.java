import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[] dx = {-1, 1, 0, 0, 0, 0};
    private static final int[] dy = {0, 0, -1, 1, 0, 0};
    private static final int[] dz = {0, 0, 0, 0, -1, 1};

    public static int solution(int h, int n, int m, int[][][] map) {
        return bfs(h, n, m, map);
    }

    private static int bfs(int h, int n, int m, int[][][] map) {
        Queue<int[]> q = new LinkedList<>();
        for (int k = 0; k < h; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[k][i][j] == 1) {
                        q.add(new int[]{k, i, j});
                    }
                }
            }
        }

        while (!q.isEmpty()) {
            int[] t = q.poll();
            int z = t[0];
            int x = t[1];
            int y = t[2];

            for (int i = 0; i < 6; i++) {
                int nz = z + dz[i];
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m || nz < 0 || nz >= h) {
                    continue;
                }
                if (map[nz][nx][ny] == 0) {
                    map[nz][nx][ny] = map[z][x][y] + 1;
                    q.add(new int[]{nz, nx, ny});
                }
            }
        }

        int max = Integer.MIN_VALUE;
        if (checkZero(h, n, m, map)) {
            return -1;
        }
        for (int k = 0; k < h; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (max < map[k][i][j]) {
                        max = map[k][i][j];
                    }
                }
            }
        }

        return max - 1;
    }

    private static boolean checkZero(int h, int n, int m, int[][][] map) {
        for (int k = 0; k < h; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[k][i][j] == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        int[] nmh = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int m = nmh[0];
        int n = nmh[1];
        int h = nmh[2];
        int[][][] map = new int[h][n][m];

        for (int k = 0; k < h; k++) {
            for (int i = 0; i < n; i++) {
                map[k][i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
        }

        bw.write(solution(h, n, m, map) + "");
        bw.close();
        br.close();
    }
}