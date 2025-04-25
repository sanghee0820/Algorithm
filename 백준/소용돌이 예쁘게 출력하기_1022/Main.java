import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] DIRECTIONS = {
        {0, 1},
        {-1, 0},
        {0, -1},
        {1, 0}
    };

    public static String solution(int r1, int c1, int r2, int c2) {
        int rowCount = r2 - r1 + 1;
        int colCount = c2 - c1 + 1;
        int[][] result = new int[rowCount][colCount];

        int currentRow = 0;
        int currentCol = 0;
        int value = 1;
        int stepLength = 1;
        int filled = 0;
        int totalToFill = rowCount * colCount;
        int maxValue = 0;

        outer:
        while (true) {
            for (int[] direction : DIRECTIONS) {
                for (int step = 0; step < stepLength; step++) {
                    if (r1 <= currentRow && currentRow <= r2 && c1 <= currentCol
                        && currentCol <= c2) {
                        result[currentRow - r1][currentCol - c1] = value;
                        maxValue = Math.max(maxValue, value);
                        filled++;
                        if (filled == totalToFill) {
                            break outer;
                        }
                    }
                    currentRow += direction[0];
                    currentCol += direction[1];
                    value++;
                }

                if (Arrays.equals(direction, DIRECTIONS[1]) ||
                    Arrays.equals(direction, DIRECTIONS[3])
                ) {
                    stepLength++;
                }
            }
        }

        int width = String.valueOf(maxValue).length();
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                sb.append(String.format("%" + width + "d", result[row][col]));
                if (col < colCount - 1) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        bw.write(solution(input[0], input[1], input[2], input[3]));
        bw.close();
        br.close();
    }
}