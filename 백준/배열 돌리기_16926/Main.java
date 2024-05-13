import java.io.*;

public class Main {

    public static int[][] solution(int n, int m, int r, int[][] matrix){
        int count = Math.min(n, m) / 2;
        for(int i = 0; i < r; i++) {

            for(int j = 0; j < count; j++) {
                int temp = matrix[j][j];
                for(int k = j + 1; k < m - j; k++) {
                    matrix[j][k - 1] = matrix[j][k];
                }
                for(int k = j + 1; k < n - j; k++) {
                    matrix[k - 1][m - 1 - j] = matrix[k][m - 1 - j];
                }
                for(int k = m - 2 - j; k >= j; k--) {
                    matrix[n - 1 - j][k + 1] = matrix[n - 1 - j][k];
                }
                for(int k = n - 2 - j; k >= j; k--) {
                    matrix[k + 1][j] = matrix[k][j];
                }
                matrix[j + 1][j] = temp;
            }
        }
        return matrix;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] data = br.readLine().split(" ");

        int n = Integer.parseInt(data[0]);
        int m = Integer.parseInt(data[1]);
        int r = Integer.parseInt(data[2]);

        int[][] matrix = new int[n][m];
        for(int i = 0; i < n; i++){
            data = br.readLine().split(" ");
            for(int j = 0; j < m; j++){
                matrix[i][j] = Integer.parseInt(data[j]);
            }
        }

        int[][] answer = solution(n, m, r, matrix);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                bw.write(answer[i][j] + " ");
            }
            bw.write("\n");
        }
        bw.flush();
    }
}
