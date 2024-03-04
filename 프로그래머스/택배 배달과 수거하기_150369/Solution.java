import java.util.*;
import java.util.stream.*;
class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int deliveriesMax = n;
        int pickupsMax = n;
        
        while(deliveriesMax != 0 || pickupsMax != 0){
            while(deliveriesMax != 0 && deliveries[deliveriesMax - 1] == 0){
                deliveriesMax--;
            }
            while(pickupsMax != 0 && pickups[pickupsMax - 1] == 0){
                pickupsMax--;
            }
            answer += Math.max(deliveriesMax, pickupsMax) * 2;
            int tempDelivery = 0;
            int tempPickup = 0;
            for(int i = deliveriesMax - 1; i > -1; i--){
                
                if(tempDelivery + deliveries[i] >= cap){
                    deliveries[i] -= cap - tempDelivery;
                    tempDelivery = cap;
                    break;
                }
                tempDelivery += deliveries[i];
                deliveries[i] = 0;
                while(deliveriesMax != 0 && deliveries[deliveriesMax - 1] == 0 ){
                    deliveriesMax--;
                }
            }
            for(int i = pickupsMax - 1; i > -1; i--){
                
                if(tempPickup + pickups[i] >= cap){
                    pickups[i] -= cap - tempPickup;
                    tempPickup = cap;
                    break;
                }
                tempPickup += pickups[i];
                pickups[i] = 0;
                while(pickupsMax != 0 && pickups[pickupsMax - 1] == 0){
                    pickupsMax--;
                }
            }
        }
        
        return answer;
    }
}