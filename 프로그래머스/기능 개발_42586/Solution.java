import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();

        Queue<Integer> queue = new LinkedList<>();
        for(int index = 0; index < progresses.length; index++) {
            int progress = progresses[index];
            int speed = speeds[index];

            queue.add((100 - progress) % speed == 0 ? (100 - progress) / speed : (100 - progress) / speed + 1);
        }


        int day = 1;

        while(!queue.isEmpty()) {
            int deploy = 0;
            while(!queue.isEmpty() && queue.peek() <= day) {
                deploy++;
                queue.poll();
            }

            day++;
            if(deploy == 0) {
                continue;
            }
            answer.add(deploy);
        }
        return answer.stream().mapToInt(i -> i).toArray();
    }
}