import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] DIRECTIONS = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static int solution(String[][] map) {
        int answer = 0;
        while (pop(map) != 0) {
            down(map);
            answer++;
        }

        return answer;
    }

    public static int pop(String[][] map) {
        boolean[][] popped = new boolean[map.length][map[0].length];

        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[0].length; column++) {
                if (map[row][column].equals(".") || popped[row][column]) {
                    continue;
                }
                bfs(map, row, column, popped);
            }
        }
        int count = 0;
        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[0].length; column++) {
                if (popped[row][column]) {
                    map[row][column] = "-";
                    count++;
                }
            }
        }
        return count;
    }


    public static void bfs(String[][] map, int row, int column, boolean[][] popped) {
        String type = map[row][column];

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        visited.add(row * 12 + column);
        queue.add(row * 12 + column);

        while (!queue.isEmpty()) {
            var cur = queue.poll();
            var curRow = cur / 12;
            var curColumn = cur % 12;

            for (int[] DIRECTION : DIRECTIONS) {
                int nextRow = curRow + DIRECTION[0];
                int nextColumn = curColumn + DIRECTION[1];
                if (isOutOfRange(nextRow, nextColumn, map)) {
                    continue;
                }
                if (!map[nextRow][nextColumn].equals(type)) {
                    continue;
                }
                if (visited.contains(nextRow * 12 + nextColumn)) {
                    continue;
                }
                visited.add(nextRow * 12 + nextColumn);
                queue.add(nextRow * 12 + nextColumn);
            }
        }

        if (visited.size() >= 4) {
            for (Integer visit : visited) {
                var curRow = visit / 12;
                var curColumn = visit % 12;
                popped[curRow][curColumn] = true;
            }
        }
    }

    public static void down(String[][] map) {
        for (int column = 0; column < map[0].length; column++) {
            Stack<String> stack = new Stack<>();
            int cnt = 0;
            for (int row = map.length - 1; row >= 0; row--) {
                if (map[row][column].equals("-")) {
                    cnt++;
                    continue;
                }
                stack.add(map[row][column]);
            }
            while (cnt != 0) {
                stack.add(".");
                cnt--;
            }

            for (int row = 0; row < map.length; row++) {
                map[row][column] = stack.pop();
            }
        }
    }

    public static boolean isOutOfRange(int row, int column, String[][] map) {
        return row < 0 || row >= map.length || column < 0 || column >= map[0].length;
    }

    public static void main(String[] args) throws IOException {
        final int row = 12;
        final int column = 6;

        String[][] map = new String[row][column];

        for (int index = 0; index < row; index++) {
            map[index] = br.readLine().split("");
        }

        bw.write(String.valueOf(solution(map)));

        bw.close();
        br.close();
    }
}