import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0;
        
        int waitTruckIndex = 0;
        int sumWeight = 0;
        Queue<int[]> crossTrucks = new LinkedList<>();
        while(waitTruckIndex < truck_weights.length || crossTrucks.size() != 0) {
            time++;
            
            if(crossTrucks.size() != 0) {
                var fastTruck = crossTrucks.peek();
                if(time - fastTruck[1] == bridge_length) {
                    sumWeight -= crossTrucks.poll()[0];
                }
            }
            
            if(waitTruckIndex == truck_weights.length) {
                continue;
            }
            
            if(crossTrucks.size() < bridge_length && sumWeight + truck_weights[waitTruckIndex] <= weight) {
                crossTrucks.add(new int[]{truck_weights[waitTruckIndex], time});
                sumWeight += truck_weights[waitTruckIndex];
                waitTruckIndex++;
            }
        }
        return time;
    }
}