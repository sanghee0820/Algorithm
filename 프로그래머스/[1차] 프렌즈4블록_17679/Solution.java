import java.util.*;
class Solution {
    private final int[][] directions = {{0,1}, {1,0}, {1,1}};
    
    private char[][] boards;
    
    public int solution(int m, int n, String[] datas) {
        int answer = 0;
        boards = new char[m][n];
        for(int i = 0; i < m; i++){
            String data = datas[i];
            for(int j = 0; j < n; j++){
                boards[i][j] = data.charAt(j);
            }
        }
        do{
            answer += getDeleted(m, n);
        }while(pressBoards(m,n));
        
        return answer;
    }
    
    public boolean pressBoards(int m, int n){
        boolean flag = false;
        for(int j = 0; j < n; j++){
            int blank = m;
            for(int i = m - 1; i > -1; i--){
                if(boards[i][j] == '.'){
                    for(int k = i; k > -1; k--){
                        if(boards[k][j] != '.'){
                            boards[i][j] = boards[k][j];
                            boards[k][j] = '.';
                            flag = true;
                            break;
                        }
                    }
                }
            }
        }
        return flag;
    }
    
    public int getDeleted(int m, int n){
        boolean[][] visited = new boolean[m][n];
        for(int i = 0; i < m-1; i++){
            for(int j = 0; j < n - 1 ; j++){
                if( boards[i][j] != '.'){
                    if( boards[i][j] == boards[i + directions[0][0]][j + directions[0][1]] &&
                    boards[i][j] == boards[i + directions[1][0]][j + directions[1][1]] &&
                    boards[i][j] == boards[i + directions[2][0]][j + directions[2][1]] ){
                    visited[i][j] = true;
                    visited[i + directions[0][0]][j + directions[0][1]] = true;
                    visited[i + directions[1][0]][j + directions[1][1]] = true;
                    visited[i + directions[2][0]][j + directions[2][1]] = true;
                    }
                }
            }
        }
        int result = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n ; j++){
                if( visited[i][j] == true ){
                    boards[i][j] = '.';
                    result ++;
                }
            }
        }
        return result;
    }
}