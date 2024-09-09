import java.util.*;
import java.util.stream.*;

class Area{
    int totalFatigue;
    int interval;
    public Area(int totalFatigue, int interval){
        this.totalFatigue = totalFatigue;
        this.interval = interval;
    }
}
class Solution {
    private static final List<String> mineralTypes = List.of("diamond", "iron", "stone");
    private static final int[] fatigues = new int[]{25, 5, 1};
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;  
        var totalPick = Arrays.stream(picks).sum();
        if(minerals.length > totalPick * 5){
            minerals = Arrays.copyOf(minerals, totalPick * 5);
        }
        List<Area> intervalInfo = new ArrayList<>();
        for(int interval = 0; interval < Math.ceil(minerals.length / 5.0); interval++){
            int fatigue = 0;
            for(int index = interval * 5; index < Math.min((interval + 1) * 5, minerals.length); index++){
                int typeIndex = mineralTypes.indexOf(minerals[index]);
                fatigue += fatigues[typeIndex];
            }
            var area = new Area(fatigue, interval);
            intervalInfo.add(area);
        }
        
        Collections.sort(intervalInfo, (a,b) -> {
            if(b.totalFatigue == a.totalFatigue){
                return a.interval - b.interval;
            }
            return b.totalFatigue - a.totalFatigue;
        });
        
        for(Area current : intervalInfo){
            int pick = 0;
            for(int i = 0; i < 3; i++){
                if(picks[i] == 0){
                    continue;
                }
                picks[i] -= 1;
                pick = i;
                break;
            }
            
            var interval = current.interval;
            for(int index = interval * 5; index < Math.min((interval + 1) * 5, minerals.length); index++){
                int typeIndex = mineralTypes.indexOf(minerals[index]);
                answer += Math.max(1, fatigues[typeIndex] / fatigues[pick]);
            }
        }
        
        return answer;
    }
}