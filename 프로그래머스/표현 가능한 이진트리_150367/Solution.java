import java.util.*;

class Solution {
    public int[] solution(long[] numbers) {
        List<Integer> answer = new ArrayList<>();
        for(long number : numbers) {
            var binary = toBinary(number);
            if(dfs(binary, binary.length() / 2, 0, binary.length() - 1)) {
                answer.add(1);
                continue;
            }
            answer.add(0);
        }
        return answer.stream().mapToInt(i -> i).toArray();
    }
    
    public String toBinary(long number) {
        var sb = new StringBuilder();
        while(number != 0) {
            sb.append(number % 2);
            number = number / 2;
        }
        
        while((Math.log(sb.length() + 1) / Math.log(2)) % 1 != 0) {
            sb.append(0);
        }
        
        return sb.reverse().toString();
    }
    
    public boolean dfs(String binary, int root, int left, int right) {
        if(left >= right) {
            return true;
        }
        
        int leftChildIndex = (left + root - 1) / 2;
        int rightChildIndex = (root + 1 + right) / 2;
        if(binary.charAt(root) == '0') {
            if(binary.charAt(leftChildIndex) == '1') {
                return false;
            }
            
            if(binary.charAt(rightChildIndex) == '1') {
                return false;
            }
        }
        var leftResult = dfs(binary, leftChildIndex, left, root - 1);
        var rightResult = dfs(binary, rightChildIndex, root + 1, right);
        if(leftResult && rightResult) {
            return true;
        }
        return false;
    }
}