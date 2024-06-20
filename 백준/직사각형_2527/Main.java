import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

    public static String solution(int[][] squares) { //128 ms
    
        if (squares[0][2] < squares[1][0] // 첫 번째 사각형의 오른쪽이 두 번째 사각형의 왼쪽보다 왼쪽
		        || squares[0][3] < squares[1][1] //첫 번째 사각형의 위쪽이 두 번째 사각형의 아래쪽보다 아래
		        || squares[1][2] < squares[0][0] // 두 번째 사각형의 오른쪽이 첫 번째 사각형의 왼쪽보다 왼쪽
		        || squares[1][3] < squares[0][1]) // 두 번째 사각형의 위쪽이 첫 번째 사각형의 아래쪽보다 아래
		        {
            return "d";
        }
        if ((squares[0][0] == squares[1][2] && squares[0][1] == squares[1][3]) ||//첫 번째 사각형의 왼쪽 아래가 두 번째 사각형의 오른쪽 위와 같음
                (squares[0][0] == squares[1][2] && squares[0][3] == squares[1][1]) || // 첫 번째 사각형의 왼쪽 아래가 두 번째 사각형의 오른쪽 아래와 같음
                (squares[0][2] == squares[1][0] && squares[0][3] == squares[1][1]) || // 첫 번째 사각형의 오른쪽 위가 두 번째 사각형의 왼쪽 아래와 같음
                (squares[0][2] == squares[1][0] && squares[0][1] == squares[1][3])) { // 첫 번째 사각형의 오른쪽 위가 두 번째 사각형의 왼쪽 위와 같음
            return "c";
        }
        if (squares[0][2] == squares[1][0] || // 첫 번째 사각형의 오른쪽이 두 번째 사각형의 왼쪽과 같음
					       squares[0][3] == squares[1][1] || // 첫 번째 사각형의 위쪽이 두 번째 사각형의 아래와 같음
                 squares[1][2] == squares[0][0] || // 두 번째 사각형의 오른쪽이 첫 번째 사각형의 왼쪽과 같음
                 squares[0][1] == squares[1][3]) { // 두 번째 사각형의 위쪽이 첫 번째 사각형의 아래와 같음
            return "b";
        }
        return "a";
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int i = 0; i < 4; i++){
            var input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::valueOf).toArray();
            var array = List.of(1, "Object");
            System.out.println(solution(new int[][]{Arrays.copyOfRange(input, 0, 4), Arrays.copyOfRange(input, 4, 8)}));
        }
    }
}