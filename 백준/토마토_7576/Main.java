import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static final int[][] DIRECTIONS = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private static int totalTomato = 0;

    public static int solution(int[][] map) {
        Set<int[]> ripedTomato = new HashSet<>();

        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[0].length; column++) {
                if (map[row][column] == 1) {
                    ripedTomato.add(new int[]{row, column});
                }
                if (map[row][column] == 0) {
                    totalTomato++;
                }
            }
        }

        int day = 0;
        while (totalTomato != 0) {
            ripedTomato = ripeTomato(map, ripedTomato);
            if (!ripedTomato.isEmpty()) {
                day++;
                continue;
            }
            if (totalTomato != 0) {
                return -1;
            }
        }

        return day;
    }

    public static Set<int[]> ripeTomato(int[][] map, Set<int[]> ripedTomato) {
        Set<int[]> newRipedTomato = new HashSet<>();
        for (int[] ripedPosition : ripedTomato) {
            for (int[] DIRECTION : DIRECTIONS) {
                int nextRow = ripedPosition[0] + DIRECTION[0];
                int nextColumn = ripedPosition[1] + DIRECTION[1];

                if (isOutOfRange(nextRow, nextColumn, map)) {
                    continue;
                }

                if (isRipedOrBlank(nextRow, nextColumn, map)) {
                    continue;
                }
                map[nextRow][nextColumn] = 1;
                totalTomato--;
                newRipedTomato.add(new int[]{nextRow, nextColumn});
            }
        }

        return newRipedTomato;
    }

    public static boolean isOutOfRange(int row, int column, int[][] map) {
        return row < 0 || column < 0 || row >= map.length || column >= map[0].length;
    }

    public static boolean isRipedOrBlank(int row, int column, int[][] map) {
        return map[row][column] == 1 || map[row][column] == -1;
    }

    public static void main(String[] args) throws IOException {
        int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[][] map = new int[nm[1]][nm[0]];

        for (int index = 0; index < nm[1]; index++) {
            map[index] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt)
                .toArray();
        }
        bw.write(String.valueOf(solution(map)));

        bw.close();
        br.close();
    }
}