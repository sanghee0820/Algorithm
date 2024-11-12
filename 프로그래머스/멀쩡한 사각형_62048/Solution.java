class Solution {
    public long solution(int w, int h) {
        long answer = (long) w * h - (w + h) + getGcd(w, h);
        
        return answer;
    }
    
    private int getGcd(int a, int b){
        int c = 0;
        
        while(b != 0){
            c = a;
            a = b;
            b = c % b;
        }
        
        return a;
    }
}