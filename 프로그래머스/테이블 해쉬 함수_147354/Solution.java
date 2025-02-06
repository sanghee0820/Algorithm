import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        Arrays.sort(data, (a,b) -> {
            if(a[col - 1] == b[col -1]) {
                return b[0] - a[0];
            }
            
            return a[col - 1] - b[col - 1];
        });
        
        
        // S_i는 각 컬럼의 값을 i로 나눈 나머지의 합
        List<Integer> S = new ArrayList<>();
        for(int rowIndex = row_begin - 1; rowIndex < row_end; rowIndex++) {
            int[] row = data[rowIndex];
            
            int S_i = 0;
            for(int dataIndex = 0; dataIndex < row.length; dataIndex++) {
                S_i += row[dataIndex] % (rowIndex + 1);
            }
            S.add(S_i);
        }
        
        for(int s : S) {
            answer = answer ^ s;
        }
        return answer;
    }
}