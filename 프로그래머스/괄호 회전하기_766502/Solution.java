import java.util.*;
class Solution {

    private static final String[] OPEN_BRAKETS = new String[] {"(", "[", "{"};
    private static final String[] CLOSED_BRAKETS = new String[] {")", "]", "}"};

    public int solution(String s) {
        int answer = 0;

        Queue<String> braketsInfo = new LinkedList<>();
        for(String braket : s.split("")) {
            braketsInfo.add(braket);
        }

        for(int count = 0; count < braketsInfo.size(); count++) {
            braketsInfo.add(braketsInfo.poll());
            if(isCorrectBrakets(new LinkedList(braketsInfo))) {
                answer++;
                continue;
            }

        }
        return answer;
    }

    private boolean isCorrectBrakets(Queue<String> braketsInfo) {
        Stack<String> stack = new Stack<>();

        while(!braketsInfo.isEmpty()) {
            String curBraket = braketsInfo.poll();
            boolean openFlag = false;
            for(String openBraket : OPEN_BRAKETS) {
                if(curBraket.equals(openBraket)) {
                    stack.add(curBraket);
                    openFlag = true;
                }
            }
            if(openFlag) {
                continue;
            }
            if(stack.isEmpty()) {
                return false;
            }


            boolean closedFlag = false;
            for(int closedIndex = 0; closedIndex < 3; closedIndex++) {
                String closedBraket = CLOSED_BRAKETS[closedIndex];
                if(curBraket.equals(closedBraket) && stack.peek().equals(OPEN_BRAKETS[closedIndex])) {
                    stack.pop();
                    closedFlag = true;
                }
            }

            if(closedFlag) {
                continue;
            }

            return false;
        }

        return stack.isEmpty();
    }
}