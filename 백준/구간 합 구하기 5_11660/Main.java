import java.io.*;

public class Main {

    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] data = br.readLine().split(" ");
        int N = Integer.parseInt(data[0]);
        int M = Integer.parseInt(data[1]);

        int[][] sumOfMap = new int[N][N];

        for(int i = 0; i < N; i++){
            data = br.readLine().split(" ");
            if(i == 0){
                sumOfMap[i][0] = Integer.parseInt(data[0]);
                for(int j = 1; j < N; j++){
                    sumOfMap[i][j] = Integer.parseInt(data[j]) + sumOfMap[i][j-1];
                }
            }else{
                sumOfMap[i][0] = Integer.parseInt(data[0]) + sumOfMap[i-1][0];
                for(int j = 1; j < N; j++){
                    sumOfMap[i][j] = Integer.parseInt(data[j]) + sumOfMap[i][j-1] + sumOfMap[i-1][j] - sumOfMap[i-1][j-1];
                }
            }

        }

        for(int i = 0; i < M; i++){
            data = br.readLine().split(" ");
            int[] leftUpperEdge = new int[]{Integer.parseInt(data[0]) - 1, Integer.parseInt(data[1]) - 1};
            int[] rightDownEdge = new int[]{Integer.parseInt(data[2]) - 1, Integer.parseInt(data[3]) - 1};
            int sum = 0;
            sum += sumOfMap[rightDownEdge[0]][rightDownEdge[1]];
            if(leftUpperEdge[1] == 0){
                if(leftUpperEdge[0] != 0){
                    sum -= sumOfMap[leftUpperEdge[0] - 1][rightDownEdge[1]];
                }
            }else{
                if(leftUpperEdge[0] != 0){
                    sum = sum - sumOfMap[rightDownEdge[0]][leftUpperEdge[1] - 1] - sumOfMap[leftUpperEdge[0] - 1][rightDownEdge[1]]
                            + sumOfMap[leftUpperEdge[0] - 1][leftUpperEdge[1] - 1];
                }else{
                    sum = sum - sumOfMap[rightDownEdge[0]][leftUpperEdge[1] - 1];
                }
            }
            System.out.println(sum);
        }

    }
    public static void main(String[] args) throws IOException {
        solution();
    }
}
