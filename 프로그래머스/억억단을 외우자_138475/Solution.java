class Solution {
    public int[] solution(int e, int[] starts) {
        int[] answer = new int[starts.length];
        
        int[] cnt = new int[e + 1];
        for(int i = 1; i <= e; i++) {
            for(int j = i; j <= e; j += i) {
                cnt[j]++;
            }
        }
        
        int[] maxNumForStart = new int[e + 1];
        int maxNum = e;
        int maxCnt = cnt[e];
        
        for(int i = e; i >= 1; i--) {
            if(cnt[i] >= maxCnt) {
                maxNum = i;
                maxCnt = cnt[i];
            }
            maxNumForStart[i] = maxNum;
        }
        
        for(int i = 0; i < starts.length; i++) {
            answer[i] = maxNumForStart[starts[i]];
        }
        
        return answer;
    }
}