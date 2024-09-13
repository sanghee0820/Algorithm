class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int left = 1;
        int right = 100_000;
        
        while(left <= right){
            
            int level = (left + right) / 2;
            
            if(inLimit(diffs, times, limit, level)){
                right = level - 1;
                continue;
            }
            left = level + 1;
        }
        
        return left;
    }
    
    public boolean inLimit(int[] diffs, int[] times, long limit, int level){
        long totalTime = 0;
        for(int i = 0; i < diffs.length; i++){
            int diff = diffs[i];
            int timeCur = times[i];
            int timePrev = i == 0 ? 0 : times[i - 1];
            
            if(diff <= level){
                totalTime += timeCur;
            }
            
            if(diff > level){
                totalTime += ((diff - level) * (timeCur + timePrev) + timeCur);
            }

            if(totalTime > limit){
                return false;
            }
        }
        
        return true;
    }
}