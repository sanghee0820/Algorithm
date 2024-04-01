import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[]{0, 0};
        PriorityQueue<Integer> descPq = new PriorityQueue<>();
        PriorityQueue<Integer> ascPq = new PriorityQueue<>(Collections.reverseOrder());
        
        for(String operation : operations){
            String[] data = operation.split(" ");
            if(data[0].equals("I")){
                descPq.add(Integer.parseInt(data[1]));
                ascPq.add(Integer.parseInt(data[1]));
                continue;
            }
            if(descPq.size() == 0 || ascPq.size() == 0){
                continue;
            }
            if(data[1].equals("1")){
                Integer maxValue = ascPq.poll();
                descPq.remove(maxValue);
                continue;
            }
            
            Integer minValue = descPq.poll();
            ascPq.remove(minValue);
        }
        if(descPq.size() == 0 || ascPq.size() == 0){
            return answer;
        }
        answer[0] = ascPq.poll();
        answer[1] = descPq.poll();
        return answer;
    }
}