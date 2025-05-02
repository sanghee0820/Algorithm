import java.util.*;
class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];
        for (int index = 0; index < s.length; index++) {
            String[] data = s[index].split("");
            
            StringBuilder sb = remove110(data);
            insert110(sb, (data.length - sb.length()) / 3);
            answer[index] = sb.toString();

        }

        return answer;
    }
    
    private StringBuilder remove110(String[] data) {
        Stack<String> stack = new Stack<>();

        for (String s : data) {
            stack.push(s);

            if (stack.size() >= 3) {
                String third = stack.pop();
                String second = stack.pop();
                String first = stack.pop();

                if (!(first.equals("1") && second.equals("1") && third.equals("0"))) {
                    stack.push(first);
                    stack.push(second);
                    stack.push(third);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }

        return sb;
    }

    
    private void insert110(StringBuilder sb, int count) {
        int insertIndex = sb.lastIndexOf("0") + 1;

        sb.insert(insertIndex, "110".repeat(count));
    }
}