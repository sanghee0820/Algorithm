class Solution {
    public int[] solution(int brown, int yellow) {
        int x=3;
        int y = (brown - 2 * x) / 2 + 2;
        int[] answer = new int[]{y,x};
        while( answer[1] * answer[0] - brown != yellow){
            answer[1]++;
            answer[0] = (brown - 2 * answer[1]) / 2 + 2;
        }
        
        return answer;
    }
}