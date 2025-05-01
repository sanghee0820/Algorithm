import java.util.*;

class Solution {
    private static final int[][] DIRECTIONS = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    private static final String[] MOVES = {"d", "l", "r", "u"};

    public String solution2(int n, int m, int x, int y, int r, int c, int k) {
        int dist = Math.abs(x - r) + Math.abs(y - c);
        if (dist > k || (k - dist) % 2 != 0) {
            return "impossible";
        }

        String result = dfs(n, m, x, y, r, c, k, 0, new StringBuilder());
        
        if(result == null) {
            result = "impossible";
        }
        return result;
    }

    private String dfs(int n, int m, int x, int y, int r, int c, int k, int depth, StringBuilder path) {
        if (depth == k) {
            if (x == r && y == c) {
                return path.toString();
            }
            return null;
        }

        for (int index = 0; index < 4; index++) {
            int nx = x + DIRECTIONS[index][0];
            int ny = y + DIRECTIONS[index][1];
            String move = MOVES[index];

            if (isInvalidPosition(nx, ny, n, m)) {
                continue;
            }

            int dist = Math.abs(nx - r) + Math.abs(ny - c);
            if (depth + 1 + dist > k) {
                continue;
            }

            path.append(move);
            String result = dfs(n, m, nx, ny, r, c, k, depth + 1, path);
            path.deleteCharAt(path.length() - 1);

            if (result != null) {
                return result;
            }
        }

        return null;
    }

    private boolean isInvalidPosition(int x, int y, int n, int m) {
        return x < 1 || y < 1 || x > n || y > m;
    }
}