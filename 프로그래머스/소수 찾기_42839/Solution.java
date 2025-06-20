import java.util.*;

class Solution {
    public int solution(String numbers) {
        int[] intNumbers = Arrays.stream(numbers.split("")).mapToInt(Integer::parseInt).toArray();
        Set<Integer> answer = new HashSet<>();
        getAnswer(new ArrayList<>(), answer, intNumbers, new boolean[intNumbers.length]);
        return answer.size();
    }
    
    private void getAnswer(List<Integer> targets, Set<Integer> answer, int[] numbers, boolean[] visited) {
        if(targets.size() > numbers.length) {
            return;
        }
        
        addIfAnswer(targets, answer);
        
        for(int index = 0; index < numbers.length; index++ ) {
            if(visited[index]) {
                continue;
            }
            targets.add(numbers[index]);
            visited[index] = true;
            getAnswer(targets, answer, numbers, visited);
            
            targets.remove(targets.size() - 1);
            visited[index] = false;
        }
    }
    
    private void addIfAnswer(List<Integer> targets, Set<Integer> answer) {
        int number = 0;
        for(int target : targets) {
            number *= 10;
            number += target;
        }
        
        if(isPrime(number)) {
            answer.add(number);
        }
    }
    
    private boolean isPrime(int number) {
        if(number <= 1) {
            return false;
        }
        if(number <= 3) {
            return true;
        }
        
        if (number % 2 == 0 || number % 3 == 0) {
            return false;
        }
        
        for(int mod = 5; mod * mod <= number; mod++) {
            if(number % mod == 0 || number % (mod + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}