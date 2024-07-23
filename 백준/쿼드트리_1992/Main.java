import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder strBuilder = new StringBuilder();
    public static String solution(int n, String[][] map){
        divideAndConquer(n, 0, 0, map);

        return strBuilder.toString();
    }

    public static void divideAndConquer(int size, int row, int column, String[][] map){
        if(size == 1){
            strBuilder.append(map[row][column]);
            return;
        }

        String current = map[row][column];
        boolean same = true;
        for(int curRow = row; curRow < row + size; curRow++){
            for(int curColumn = column; curColumn < column + size; curColumn++){
                if(current.equals(map[curRow][curColumn])){
                    continue;
                }
                same = false;
                break;
            }
            if(!same){
                break;
            }
        }
        if(same){
            strBuilder.append(current);
            return;
        }

        var middleRow = row + (size / 2);
        var middleColumn = column + (size / 2);
        strBuilder.append("(");
        divideAndConquer(size/2, row, column, map);
        divideAndConquer(size/2, row, middleColumn, map);
        divideAndConquer(size/2, middleRow, column, map);
        divideAndConquer(size/2, middleRow, middleColumn, map);
        strBuilder.append(")");
    }
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        String[][] map = new String[N][N];
        for(int i = 0; i < N; i++){
            map[i] = br.readLine().split("");
        }

        bw.write(solution(N, map));
        bw.close();
        br.close();
    }
}
