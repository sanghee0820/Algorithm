import java.util.*;
class Solution {
    private final Double discountRatios[] = new Double[]{0.1, 0.2, 0.3, 0.4};
    
    private int maxEmoticonPlus = 0;
    private int maxEmoticonCost = 0;
    
    public int[] solution(int[][] users, int[] emoticons) {
        DFS(users, emoticons, new Double[emoticons.length], 0);
        
        int[] answer = new int[]{maxEmoticonPlus, maxEmoticonCost};
        return answer;
    }
    
    public void DFS(int[][] users, int[] emoticons, Double[] discounts, int cur){
        if(cur == emoticons.length){
            int totalCost = 0;
            int totalEmoticonPlus = 0;
            
            for(int[] user : users){
                int cost = 0;
                for(int emoticonIndex = 0; emoticonIndex < emoticons.length; emoticonIndex++){
                    if(user[0] / 100.0 <= discounts[emoticonIndex]){
                        cost += emoticons[emoticonIndex] * (1.0 - discounts[emoticonIndex]);
                    }
                }
                if(cost >= user[1]){
                    totalEmoticonPlus++;
                }else{
                    totalCost += cost;
                }
            }
        
            if(totalEmoticonPlus > maxEmoticonPlus){
                maxEmoticonPlus = totalEmoticonPlus;
                maxEmoticonCost = totalCost;
                return;
            }
            if(totalEmoticonPlus == maxEmoticonPlus && maxEmoticonCost < totalCost){
                maxEmoticonCost = totalCost;
                return;
            }
            return;
        }
        
        for(int discountIndex = 0; discountIndex < 4; discountIndex++){
            discounts[cur] = discountRatios[discountIndex];
            DFS(users, emoticons, discounts, cur + 1);
        }
    }
}