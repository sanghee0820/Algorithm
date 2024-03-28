import java.io.*;
import java.util.*;

// 준현이는 한번 사면 팔지 않음. 살 수 있으면 무조건 매수.
// 모든 거래는 전량 매수와 전량 매도 -> 빚을 내서 주식하지 않음.
// 3일연속 상승하는 날은 무조건 다음날 하락 -> 3일 째 전량 매도
// 어제와 오늘 가격이 동일하면 가격 상승 X
// 3일 연속 하락하면 다음날은 무조건 상승 -> 3일 째 전량 매수
public class Main {

    private static final String WIN_J = "BNP";
    private static final String WIN_S = "TIMING";
    private static final String SAME = "SAMESAME";
    public static void solution() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int s = Integer.parseInt(bufferedReader.readLine());
        int sStock = 0;
        int j = s;
        int jStock = 0;

        int[] stocks = Arrays.stream(
                bufferedReader.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        int increase = 0;
        int decrease = 0;

        jStock = j / stocks[0];
        j = j % stocks[0];
        for(int i = 1; i < stocks.length; i++){
            jStock += j / stocks[i];
            j = j % stocks[i];
            if(stocks[i - 1] > stocks[i]){
                decrease++;
                increase = 0;
            }
            if( stocks[i - 1] < stocks[i]){
                increase++;
                decrease = 0;
            }
            if(increase >= 3){
                s += sStock * stocks[i];
                sStock = 0;
            }
            if(decrease >= 3){
                sStock += s / stocks[i];
                s = s % stocks[i];
            }
        }
        s += sStock * stocks[stocks.length - 1];
        j += jStock * stocks[stocks.length - 1];
        if(s > j){
            System.out.println(WIN_S);
        }else if( s < j ){
            System.out.println(WIN_J);
        }else{
            System.out.println(SAME);
        }
    }
    public static void main(String[] args) throws IOException {
        solution();
    }
}
