import java.util.*;
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> answer = new ArrayList<>();

        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        int count = matrix.length * matrix[0].length;

        visited[0][0] = true;
        answer.add(matrix[0][0]);
        int[] curPosition = new int[]{0, 0};
        while(true) {
            right(matrix, visited, answer, curPosition);
            if(answer.size() == count) {
                break;
            }
            down(matrix, visited, answer, curPosition);
            if(answer.size() == count) {
                break;
            }

            left(matrix, visited, answer, curPosition);
            if(answer.size() == count) {
                break;
            }

            up(matrix, visited, answer, curPosition);
            if(answer.size() == count) {
                break;
            }
        }
        return answer;
    }

    
    private void right(int[][] matrix, boolean[][] visited, List<Integer> answer, int[] curPosition) {
        
        while(true) {
            if(isOutOfRange(curPosition[0], curPosition[1] + 1, matrix) || visited[curPosition[0]][curPosition[1] + 1]) {
                break;
            }
            answer.add(matrix[curPosition[0]][curPosition[1] + 1]);
            visited[curPosition[0]][curPosition[1] + 1] = true;
            curPosition[1] += 1;
            
        }
    }

    private void down(int[][] matrix, boolean[][] visited, List<Integer> answer, int[] curPosition) {
        
        while(true) {
            if(isOutOfRange(curPosition[0] + 1, curPosition[1], matrix) || visited[curPosition[0] + 1][curPosition[1]]) {
                break;
            }
            answer.add(matrix[curPosition[0] + 1][curPosition[1]]);
            visited[curPosition[0] + 1][curPosition[1]] = true;
            curPosition[0] += 1;
        }
    }

    private void left(int[][] matrix, boolean[][] visited, List<Integer> answer, int[] curPosition) {
        
        while(true) {
            if(isOutOfRange(curPosition[0], curPosition[1] - 1, matrix) || visited[curPosition[0]][curPosition[1] - 1]) {
                break;
            }
            answer.add(matrix[curPosition[0]][curPosition[1] - 1]);
            visited[curPosition[0]][curPosition[1] - 1] = true;
            curPosition[1] -= 1;
        }
    }
    private void up(int[][] matrix, boolean[][] visited, List<Integer> answer, int[] curPosition) {
        
        while(true) {
            if(isOutOfRange(curPosition[0] - 1, curPosition[1], matrix) || visited[curPosition[0] - 1][curPosition[1]]) {
                break;
            }
            answer.add(matrix[curPosition[0] - 1][curPosition[1]]);
            visited[curPosition[0] - 1][curPosition[1]] = true;
            curPosition[0] -= 1;
        }
    }

    private boolean isOutOfRange(int row, int column, int[][] matrix) {
        return row < 0 || column < 0 || row >= matrix.length || column >= matrix[0].length;
    }
}